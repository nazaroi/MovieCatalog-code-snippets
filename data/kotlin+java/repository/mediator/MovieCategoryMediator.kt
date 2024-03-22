package com.nazaroi.data.repository.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
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
    private val api: MovieApi,
    private val database: AppDatabase,
    @Assisted private val movieCategory: MovieCategory,
    private val shortMovieDtoToEntityMapper: ShortMovieDtoToEntityMapper
) : RemoteMediator<Int, MovieEntity>() {

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val itemCount = database.categoryMovieOrderDao().countForMovieCategory(movieCategory)
                val lastLoadedPage = itemCount / ApiPageSize
                lastLoadedPage + 1
            }
        }

        return try {

            val response = when (movieCategory) {
                MovieCategory.NowPlaying -> api.fetchNowPlayingMovies(page)
                MovieCategory.Popular -> api.fetchPopularMovies(page)
                MovieCategory.TopRated -> api.fetchTopRatedMovies(page)
                MovieCategory.Upcoming -> api.fetchUpcomingMovies(page)
            }

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
                            orderInList = index + (page - 1) * ApiPageSize
                        )
                    })
            }

            MediatorResult.Success(endOfPaginationReached = response.page == response.total_pages)
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(category: MovieCategory): MovieCategoryMediator
    }
}