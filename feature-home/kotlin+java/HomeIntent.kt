package com.nazaroi.feature_home

import com.nazaroi.base.mvi.MviIntent
import com.nazaroi.domain.enums.MovieCategory

sealed class HomeIntent : MviIntent {
    data object LoadMovies : HomeIntent()
    data object RefreshMovies : HomeIntent()
    data class NavigateToMovieTrailer(val movieId: Int) : HomeIntent()
    data class NavigateToMovieCategory(val category: MovieCategory) : HomeIntent()
}