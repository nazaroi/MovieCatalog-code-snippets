package com.nazaroi.domain.usecase.genre

import com.nazaroi.base.usecase.noparams.NoParamsFlowUseCase
import com.nazaroi.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StreamMovieGenreCountUseCase @Inject constructor(
    private val repository: MoviesRepository
) : NoParamsFlowUseCase<Int>() {
    override fun execute(): Flow<Int> {
        return repository.streamMovieGenreCount()
    }
}