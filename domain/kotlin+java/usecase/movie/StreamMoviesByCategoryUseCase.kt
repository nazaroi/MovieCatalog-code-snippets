package com.nazaroi.domain.usecase.movie

import androidx.paging.PagingData
import com.nazaroi.base.usecase.BaseUseCase
import com.nazaroi.base.usecase.UseCaseParams
import com.nazaroi.domain.enums.MovieCategory
import com.nazaroi.domain.model.movie.ShortMovie
import com.nazaroi.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StreamMoviesByCategoryUseCase @Inject constructor(
    private val repository: MovieRepository
) : BaseUseCase<StreamMoviesByCategoryUseCase.Params, Flow<PagingData<ShortMovie>>>() {

    override suspend fun execute(params: Params): Flow<PagingData<ShortMovie>> {
        return repository.streamMoviePagingDataByCategory(params.category)
    }

    data class Params(val category: MovieCategory) : UseCaseParams
}