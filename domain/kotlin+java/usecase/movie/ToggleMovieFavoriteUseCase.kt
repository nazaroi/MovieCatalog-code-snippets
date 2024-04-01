package com.nazaroi.domain.usecase.movie

import com.nazaroi.base.usecase.UseCaseParams
import com.nazaroi.base.usecase.noresult.NoResultUseCase
import com.nazaroi.domain.repository.MoviesRepository
import javax.inject.Inject

class ToggleMovieFavoriteUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : NoResultUseCase<ToggleMovieFavoriteUseCase.Params>() {

    override suspend fun execute(params: Params) {
        return moviesRepository.toggleFavorite(params.movieId)
    }

    data class Params(val movieId: Int) : UseCaseParams
}