package com.nazaroi.feature_home

import androidx.core.net.toUri
import androidx.lifecycle.viewModelScope
import com.nazaroi.base.ktx.logE
import com.nazaroi.base.mvi.MviNavigationCommand
import com.nazaroi.base.mvi.MviViewModel
import com.nazaroi.base.util.onFailure
import com.nazaroi.base.util.onSuccess
import com.nazaroi.common.screen.Screen
import com.nazaroi.common.screen.routeWithPathParams
import com.nazaroi.domain.enums.MovieCategory
import com.nazaroi.domain.usecase.movie.GetFirstPageMoviesByCategoryUseCase
import com.nazaroi.domain.usecase.genre.StreamMovieGenreCountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFirstPageMoviesByCategoryUseCase: GetFirstPageMoviesByCategoryUseCase,
    private val streamMovieGenreCountUseCase: StreamMovieGenreCountUseCase
) : MviViewModel<HomeState, HomeIntent, HomeEffect>() {

    override fun createInitialState(): HomeState {
        return HomeState()
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            streamMovieGenreCountUseCase().collect { result ->
                result.onSuccess { count ->
                    if (count != 0) {
                        sendIntent(HomeIntent.LoadMovies)
                    }
                }.onFailure {
                    logE("streamMovieGenreCountUseCase failed", throwable = it.cause)
                }
            }
        }
    }

    override suspend fun handleIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.LoadMovies -> {
                withContext(Dispatchers.IO) {
                    loadMovies()
                }
            }

            is HomeIntent.NavigateToMovieCategory -> {
                navigate(
                    MviNavigationCommand.ToDeepLink(
                        Screen.MovieCategory.routeWithPathParams(
                            intent.category.name
                        ).toUri()
                    )
                )
            }

            is HomeIntent.NavigateToMovieTrailer -> {
                navigate(
                    MviNavigationCommand.ToDeepLink(
                        Screen.MovieTrailer.routeWithPathParams(
                            intent.movieId.toString()
                        ).toUri()
                    )
                )
            }

            HomeIntent.RefreshMovies -> {
                refreshMovies()
            }
        }
    }

    private suspend fun refreshMovies() {
        reduceState {
            createInitialState()
        }

        loadMovies()
    }

    private suspend fun loadMovies() {
        reduceState {
            copy(
                nowPlayingMovies = nowPlayingMovies.copy(loading = true),
                popularMovies = popularMovies.copy(loading = true),
                topRatedMovies = topRatedMovies.copy(loading = true),
                upcomingMovies = upcomingMovies.copy(loading = true)
            )
        }

        MovieCategory.entries.onEach { movieCategory ->
            getFirstPageMoviesByCategoryUseCase.invoke(
                GetFirstPageMoviesByCategoryUseCase.Params(
                    movieCategory
                )
            ).onFailure {
                logE("getFirstPageMoviesByCategoryUseCase failed", throwable = it.cause)
                reduceState {
                    when (movieCategory) {
                        MovieCategory.NowPlaying -> {
                            copy(
                                nowPlayingMovies = nowPlayingMovies.copy(
                                    loading = false, fault = it
                                )
                            )
                        }

                        MovieCategory.Popular -> {
                            copy(
                                popularMovies = popularMovies.copy(
                                    loading = false, fault = it
                                )
                            )
                        }

                        MovieCategory.TopRated -> {
                            copy(
                                topRatedMovies = topRatedMovies.copy(
                                    loading = false, fault = it
                                )
                            )
                        }

                        MovieCategory.Upcoming -> {
                            copy(
                                upcomingMovies = upcomingMovies.copy(
                                    loading = false, fault = it
                                )
                            )
                        }
                    }
                }

            }.onSuccess {
                reduceState {
                    when (movieCategory) {
                        MovieCategory.NowPlaying -> {
                            copy(
                                nowPlayingMovies = nowPlayingMovies.copy(
                                    loading = false, movies = it
                                )
                            )
                        }

                        MovieCategory.Popular -> {
                            copy(
                                popularMovies = popularMovies.copy(
                                    loading = false, movies = it
                                )
                            )
                        }

                        MovieCategory.TopRated -> {
                            copy(
                                topRatedMovies = topRatedMovies.copy(
                                    loading = false, movies = it
                                )
                            )
                        }

                        MovieCategory.Upcoming -> {
                            copy(
                                upcomingMovies = upcomingMovies.copy(
                                    loading = false, movies = it
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}