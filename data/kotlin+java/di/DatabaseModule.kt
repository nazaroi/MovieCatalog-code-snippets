package com.nazaroi.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.nazaroi.data.local.dao.common.CompanyDao
import com.nazaroi.data.local.dao.common.CountryDao
import com.nazaroi.data.local.dao.common.LanguageDao
import com.nazaroi.data.local.dao.credit.CreditMemberDao
import com.nazaroi.data.local.dao.credit.MovieCreditCrossRefDao
import com.nazaroi.data.local.dao.genre.GenreDao
import com.nazaroi.data.local.dao.image.ImageDao
import com.nazaroi.data.local.dao.movie.CategoryMovieOrderDao
import com.nazaroi.data.local.dao.movie.MovieDao
import com.nazaroi.data.local.dao.movie.SearchResultDao
import com.nazaroi.data.local.dao.review.AuthorDao
import com.nazaroi.data.local.dao.review.ReviewDao
import com.nazaroi.data.local.database.AppDatabase
import com.nazaroi.data.worker.FetchGenresWorker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {

        val callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                val workRequest = OneTimeWorkRequestBuilder<FetchGenresWorker>().build()
                WorkManager.getInstance(context).enqueue(workRequest)
            }
        }

        return Room.databaseBuilder(
            context.applicationContext, AppDatabase::class.java, "app_database64"
        ).fallbackToDestructiveMigration().addCallback(callback).build()
    }

    @Singleton
    @Provides
    fun provideCompanyDao(appDatabase: AppDatabase): CompanyDao {
        return appDatabase.companyDao()
    }

    @Singleton
    @Provides
    fun provideCountryDao(appDatabase: AppDatabase): CountryDao {
        return appDatabase.countryDao()
    }

    @Singleton
    @Provides
    fun provideLanguageDao(appDatabase: AppDatabase): LanguageDao {
        return appDatabase.languageDao()
    }

    @Singleton
    @Provides
    fun provideGenreDao(appDatabase: AppDatabase): GenreDao {
        return appDatabase.genreDao()
    }

    @Singleton
    @Provides
    fun provideCreditMemberDao(appDatabase: AppDatabase): CreditMemberDao {
        return appDatabase.creditMemberDao()
    }

    @Singleton
    @Provides
    fun provideMovieCreditCrossRefDao(appDatabase: AppDatabase): MovieCreditCrossRefDao {
        return appDatabase.movieCreditCrossRefDao()
    }

    @Singleton
    @Provides
    fun provideImageDao(appDatabase: AppDatabase): ImageDao {
        return appDatabase.imageDao()
    }

    @Provides
    fun provideCategoryMovieOrderDao(appDatabase: AppDatabase): CategoryMovieOrderDao {
        return appDatabase.categoryMovieOrderDao()
    }

    @Singleton
    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun provideSearchResultDao(appDatabase: AppDatabase): SearchResultDao {
        return appDatabase.searchResultDao()
    }

    @Singleton
    @Provides
    fun provideAuthorDao(appDatabase: AppDatabase): AuthorDao {
        return appDatabase.authorDao()
    }

    @Singleton
    @Provides
    fun provideReviewDao(appDatabase: AppDatabase): ReviewDao {
        return appDatabase.reviewDao()
    }
}