package com.nazaroi.feature_search

import com.nazaroi.base.mvi.MviIntent

sealed class SearchIntent : MviIntent {
    data class ChangeQuery(val newQuery: String) : SearchIntent()
    data class NavigateToMovieTrailer(val movieId: Int) : SearchIntent()
}