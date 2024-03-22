package com.nazaroi.domain.usecase.movie

import com.nazaroi.base.usecase.BaseUseCase
import com.nazaroi.base.usecase.UseCaseParams
import com.nazaroi.domain.model.movie.Movie
import com.nazaroi.domain.repository.MovieRepository
import javax.inject.Inject

class GetDetailedMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase<GetDetailedMovieUseCase.Params, Movie>() {

    override suspend fun execute(params: Params): Movie {
        return movieRepository.getDetailedMovie(params.movieId, params.updateFromNetwork)
    }

    data class Params(val movieId: Int, val updateFromNetwork: Boolean = false) : UseCaseParams
}