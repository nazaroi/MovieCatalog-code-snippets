package com.nazaroi.feature_search

import androidx.core.net.toUri
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nazaroi.base.mvi.MviNavigationCommand
import com.nazaroi.base.mvi.MviViewModel
import com.nazaroi.base.util.onFailure
import com.nazaroi.base.util.onSuccess
import com.nazaroi.common.screen.Screen
import com.nazaroi.common.screen.routeWithPathParams
import com.nazaroi.domain.usecase.movie.StreamMoviesBySearchQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val streamMoviesBySearchQueryUseCase: StreamMoviesBySearchQueryUseCase
) : MviViewModel<SearchState, SearchIntent, SearchEffect>() {

    override fun createInitialState(): SearchState {
        return SearchState()
    }

    override suspend fun handleIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.ChangeQuery -> {

                withContext(Dispatchers.IO) {
                    streamMovies(intent.newQuery)
                }
            }

            is SearchIntent.NavigateToMovieTrailer -> {
                navigate(
                    MviNavigationCommand.ToDeepLink(
                        Screen.MovieTrailer.routeWithPathParams(
                            intent.movieId.toString()
                        ).toUri()
                    )
                )
            }
        }
    }

    private suspend fun streamMovies(query:String) {

        reduceState {
            copy(loading = true)
        }

        streamMoviesBySearchQueryUseCase.invoke(StreamMoviesBySearchQueryUseCase.Params(query))
            .onSuccess {
                reduceState {
                    copy(
                        loading = false, streamingMoviesPagingData = it.cachedIn(viewModelScope)
                    )
                }
            }.onFailure {
                reduceState { copy(loading = false, fault = it) }
            }
    }
}