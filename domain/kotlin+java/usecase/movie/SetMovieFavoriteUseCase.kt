package com.nazaroi.domain.usecase.movie

import com.nazaroi.base.usecase.UseCaseParams
import com.nazaroi.base.usecase.noresult.NoResultUseCase
import com.nazaroi.domain.repository.MoviesRepository
import javax.inject.Inject

class SetMovieFavoriteUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : NoResultUseCase<SetMovieFavoriteUseCase.Params>() {

    override suspend fun execute(params: Params) {
        return moviesRepository.setFavorite(params.movieId, params.isFavorite)
    }

    data class Params(val movieId: Int, val isFavorite: Boolean) : UseCaseParams
}