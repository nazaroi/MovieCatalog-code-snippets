package com.nazaroi.data.remote.api

import com.nazaroi.data.remote.dto.credit.MovieCreditsResponse
import com.nazaroi.data.remote.dto.image.MovieImagesResponse
import com.nazaroi.data.remote.dto.movie.MovieCategoryResponse
import com.nazaroi.data.remote.dto.movie.MovieDto
import com.nazaroi.data.remote.dto.review.MovieReviewsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun fetchNowPlayingMovies(@Query("page") page: Int = 1): MovieCategoryResponse

    @GET("movie/popular")
    suspend fun fetchPopularMovies(@Query("page") page: Int = 1): MovieCategoryResponse

    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies(@Query("page") page: Int = 1): MovieCategoryResponse

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(@Query("page") page: Int = 1): MovieCategoryResponse

    @GET("movie/{movie_id}/credits")
    suspend fun fetchMovieCredits(@Path("movie_id") movieId: Int): MovieCreditsResponse

    @GET("movie/{movie_id}/images")
    suspend fun fetchMovieImages(@Path("movie_id") movieId: Int): MovieImagesResponse

    @GET("movie/{movie_id}")
    suspend fun fetchMovie(@Path("movie_id") movieId: Int): MovieDto

    @GET("movie/{movie_id}/reviews")
    suspend fun fetchMovieReviews(
        @Path("movie_id") movieId: Int, @Query("page") page: Int = 1
    ): MovieReviewsResponse
}