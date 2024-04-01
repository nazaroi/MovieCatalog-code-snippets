package com.nazaroi.domain.usecase.movie

import androidx.paging.PagingData
import com.nazaroi.base.usecase.BaseUseCase
import com.nazaroi.base.usecase.UseCaseParams
import com.nazaroi.domain.model.movie.ShortMovie
import com.nazaroi.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StreamMoviesBySearchQueryUseCase @Inject constructor(
    private val repository: MoviesRepository
) : BaseUseCase<StreamMoviesBySearchQueryUseCase.Params, Flow<PagingData<ShortMovie>>>() {

    override suspend fun execute(params: Params): Flow<PagingData<ShortMovie>> {
        return repository.streamMoviesPagingDataBySearchQuery(params.query)

    }

    data class Params(val query: String) : UseCaseParams
}