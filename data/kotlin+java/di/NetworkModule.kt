package com.nazaroi.data.di

import com.nazaroi.data.remote.api.AuthenticationApi
import com.nazaroi.data.remote.api.GenreApi
import com.nazaroi.data.remote.api.MovieApi
import com.nazaroi.data.remote.api.SearchApi
import com.nazaroi.data.remote.api.interceptor.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().client(okHttpClient).baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

    @Singleton
    @Provides
    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(apiKeyInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build()
    }

    @Singleton
    @Provides
    fun provideApiKeyInterceptor(): ApiKeyInterceptor {
        val apiKey = "4f676cf3e20f48b80184af52c4ffdb86"
        return ApiKeyInterceptor(apiKey)
    }

    @Singleton
    @Provides
    fun provideAuthenticationApi(retrofit: Retrofit): AuthenticationApi =
        retrofit.create(AuthenticationApi::class.java)

    @Singleton
    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    @Singleton
    @Provides
    fun provideGenreApi(retrofit: Retrofit): GenreApi = retrofit.create(GenreApi::class.java)

    @Singleton
    @Provides
    fun provideSearchApi(retrofit: Retrofit): SearchApi = retrofit.create(SearchApi::class.java)
}