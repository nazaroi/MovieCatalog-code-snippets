package com.nazaroi.domain.usecase.movie

import com.nazaroi.base.usecase.BaseUseCase
import com.nazaroi.base.usecase.UseCaseParams
import com.nazaroi.domain.enums.MovieCategory
import com.nazaroi.domain.model.movie.ShortMovie
import com.nazaroi.domain.repository.MoviesRepository
import javax.inject.Inject

class GetFirstPageMoviesByCategoryUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : BaseUseCase<GetFirstPageMoviesByCategoryUseCase.Params, List<ShortMovie>>() {

    override suspend fun execute(params: Params): List<ShortMovie> {
        return moviesRepository.getFirstPageMoviesByCategory(params.category)
    }

    data class Params(val category: MovieCategory) : UseCaseParams
}

