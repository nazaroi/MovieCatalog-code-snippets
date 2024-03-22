package com.nazaroi.feature_home

import com.nazaroi.base.fault.Fault
import com.nazaroi.base.mvi.MviState
import com.nazaroi.domain.model.movie.ShortMovie

data class HomeState(
    val nowPlayingMovies: MoviesState = MoviesState(),
    val popularMovies: MoviesState = MoviesState(),
    val topRatedMovies: MoviesState = MoviesState(),
    val upcomingMovies: MoviesState = MoviesState()
) : MviState

data class MoviesState(
    val loading: Boolean = false,
    val movies: List<ShortMovie> = emptyList(),
    val fault: Fault? = null
)