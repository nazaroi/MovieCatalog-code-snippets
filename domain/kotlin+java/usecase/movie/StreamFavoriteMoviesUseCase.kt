package com.nazaroi.domain.usecase.movie

import androidx.paging.PagingData
import com.nazaroi.base.usecase.noparams.NoParamsUseCase
import com.nazaroi.domain.model.movie.ShortMovie
import com.nazaroi.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StreamFavoriteMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository
) : NoParamsUseCase<Flow<PagingData<ShortMovie>>>() {

    override suspend fun execute(): Flow<PagingData<ShortMovie>> {
        return repository.streamFavoriteMoviesPagingData()

    }
}