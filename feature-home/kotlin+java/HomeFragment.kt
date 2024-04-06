package com.nazaroi.feature_home

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.nazaroi.base.ktx.getParcelableCompat
import com.nazaroi.common.screen.Screen
import com.nazaroi.common.shared.SharedMviFragment
import com.nazaroi.domain.enums.MovieCategory
import com.nazaroi.feature_home.adapter.MovieCardAdapter
import com.nazaroi.feature_home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.EnumMap

@AndroidEntryPoint
class HomeFragment :
    SharedMviFragment<FragmentHomeBinding, HomeViewModel, HomeState, HomeIntent, HomeEffect>() {

    override val viewModel: HomeViewModel by activityViewModels()

    override fun createViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    private val movieCardAdapterMap: EnumMap<MovieCategory, MovieCardAdapter> =
        EnumMap(MovieCategory::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.setState(Screen.Home)
    }

    override fun setupViews() {

        binding.root.setOnRefreshListener {
            binding.root.isRefreshing = false
            viewModel.sendIntent(HomeIntent.RefreshMovies)
        }

        binding.nowPlayingSeeAll.setOnClickListener {
            navigateToMovieCategoryList(MovieCategory.NowPlaying)
        }

        binding.popularSeeAll.setOnClickListener {
            navigateToMovieCategoryList(MovieCategory.Popular)
        }

        binding.topRatedSeeAll.setOnClickListener {
            navigateToMovieCategoryList(MovieCategory.TopRated)
        }

        binding.upcomingSeeAll.setOnClickListener {
            navigateToMovieCategoryList(MovieCategory.Upcoming)
        }

        binding.nowPlayingRecyclerView.adapter = MovieCardAdapter {
            navigateToMovieTrailer(it.id)
        }.also {
            movieCardAdapterMap[MovieCategory.NowPlaying] = it
        }

        binding.popularRecyclerView.adapter = MovieCardAdapter {
            navigateToMovieTrailer(it.id)
        }.also {
            movieCardAdapterMap[MovieCategory.Popular] = it
        }

        binding.topRatedRecyclerView.adapter = MovieCardAdapter {
            navigateToMovieTrailer(it.id)
        }.also {
            movieCardAdapterMap[MovieCategory.TopRated] = it
        }

        binding.upcomingRecyclerView.adapter = MovieCardAdapter {
            navigateToMovieTrailer(it.id)
        }.also {
            movieCardAdapterMap[MovieCategory.Upcoming] = it
        }
    }

    private fun navigateToMovieCategoryList(category: MovieCategory) {
        viewModel.sendIntent(HomeIntent.NavigateToMovieCategory(category))
    }

    private fun navigateToMovieTrailer(movieId: Int) {
        viewModel.sendIntent(HomeIntent.NavigateToMovieTrailer(movieId))
    }

    override fun handleEffect(effect: HomeEffect) {
    }

    override fun renderState(state: HomeState) {
        binding.updateWithState(state, movieCardAdapterMap)
    }
}

private fun FragmentHomeBinding.updateWithState(
    state: HomeState, movieCardAdapterMap: EnumMap<MovieCategory, MovieCardAdapter>
) {
    nowPlayingRecyclerView.isVisible = !state.nowPlayingMovies.loading
    nowPlayingLoading.isVisible = state.nowPlayingMovies.loading
    popularRecyclerView.isVisible = !state.popularMovies.loading
    popularLoading.isVisible = state.popularMovies.loading
    topRatedRecyclerView.isVisible = !state.topRatedMovies.loading
    topRatedLoading.isVisible = state.topRatedMovies.loading
    upcomingRecyclerView.isVisible = !state.upcomingMovies.loading
    upcomingLoading.isVisible = state.upcomingMovies.loading

    movieCardAdapterMap[MovieCategory.NowPlaying]?.submitList(state.nowPlayingMovies.movies)
    movieCardAdapterMap[MovieCategory.Popular]?.submitList(state.popularMovies.movies)
    movieCardAdapterMap[MovieCategory.TopRated]?.submitList(state.topRatedMovies.movies)
    movieCardAdapterMap[MovieCategory.Upcoming]?.submitList(state.upcomingMovies.movies)
}