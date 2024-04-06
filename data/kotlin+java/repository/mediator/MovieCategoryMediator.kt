package com.nazaroi.data.repository.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.nazaroi.base.ktx.logE
import com.nazaroi.base.logger.LogcatLogger
import com.nazaroi.common.constant.ApiPageSize
import com.nazaroi.data.local.database.AppDatabase
import com.nazaroi.data.local.entity.movie.CategoryMovieOrder
import com.nazaroi.data.local.entity.movie.MovieEntity
import com.nazaroi.data.mapper.movie.ShortMovieDtoToEntityMapper
import com.nazaroi.data.remote.api.MovieApi
import com.nazaroi.domain.enums.MovieCategory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MovieCategoryMediator @AssistedInject constructor(
    private val movieApi: MovieApi,
    private val database: AppDatabase,
    @Assisted private val movieCategory: MovieCategory,
    private val shortMovieDtoToEntityMapper: ShortMovieDtoToEntityMapper
) : RemoteMediator<Int, MovieEntity>() {

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, MovieEntity>
    ): MediatorResult {

        LogcatLogger.debug(javaClass.simpleName, "------")

        LogcatLogger.debug(javaClass.simpleName, "load loadType: $loadType")

        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val itemCount =
                    database.categoryMovieOrderDao().countForMovieCategory(movieCategory)
                val lastLoadedPage = itemCount / ApiPageSize
                LogcatLogger.debug(javaClass.simpleName, "load itemCount $itemCount")
                lastLoadedPage + 1
            }
        }

        LogcatLogger.debug(javaClass.simpleName, "load page: $page")

        return try {

            val response = when (movieCategory) {
                MovieCategory.NowPlaying -> movieApi.fetchNowPlayingMovies(page)
                MovieCategory.Popular -> movieApi.fetchPopularMovies(page)
                MovieCategory.TopRated -> movieApi.fetchTopRatedMovies(page)
                MovieCategory.Upcoming -> movieApi.fetchUpcomingMovies(page)
            }

            LogcatLogger.debug(
                javaClass.simpleName, "load response.results.size: ${response.results.size}"
            )

            database.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    database.categoryMovieOrderDao().deleteAllForMovieCategory(movieCategory)
                }

                database.movieDao()
                    .insertAll(response.results.map(shortMovieDtoToEntityMapper::map))

                database.categoryMovieOrderDao()
                    .insertAll(response.results.mapIndexed { index, movie ->
                        CategoryMovieOrder(
                            movieCategory = movieCategory,
                            movieId = movie.id,
                            orderInList = index + (page - 1) * ApiPageSize,
                            page
                        )
                    })
            }

            MediatorResult.Success(endOfPaginationReached = response.page == response.total_pages)
        } catch (e: IOException) {
            logE("Error loading movies by category", throwable = e)
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            logE("Error loading movies by category", throwable = e)
            MediatorResult.Error(e)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(category: MovieCategory): MovieCategoryMediator
    }
}