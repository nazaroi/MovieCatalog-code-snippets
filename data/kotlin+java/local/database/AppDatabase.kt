package com.nazaroi.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nazaroi.base.converter.IntListConverter
import com.nazaroi.base.converter.StringListConverter
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
import com.nazaroi.data.local.entity.common.CompanyEntity
import com.nazaroi.data.local.entity.common.CountryEntity
import com.nazaroi.data.local.entity.common.LanguageEntity
import com.nazaroi.data.local.entity.credit.CreditMemberEntity
import com.nazaroi.data.local.entity.credit.MovieCreditMemberCrossRef
import com.nazaroi.data.local.entity.genre.GenreEntity
import com.nazaroi.data.local.entity.image.ImageEntity
import com.nazaroi.data.local.entity.movie.CategoryMovieOrder
import com.nazaroi.data.local.entity.movie.MovieEntity
import com.nazaroi.data.local.entity.movie.SearchResult
import com.nazaroi.data.local.entity.review.AuthorEntity
import com.nazaroi.data.local.entity.review.ReviewEntity

@Database(
    entities = [CompanyEntity::class, CountryEntity::class, LanguageEntity::class, CreditMemberEntity::class, GenreEntity::class, ImageEntity::class, MovieEntity::class, AuthorEntity::class, ReviewEntity::class, SearchResult::class, CategoryMovieOrder::class, MovieCreditMemberCrossRef::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(IntListConverter::class, StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun companyDao(): CompanyDao
    abstract fun countryDao(): CountryDao
    abstract fun languageDao(): LanguageDao
    abstract fun creditMemberDao(): CreditMemberDao
    abstract fun movieCreditCrossRefDao(): MovieCreditCrossRefDao
    abstract fun genreDao(): GenreDao
    abstract fun imageDao(): ImageDao
    abstract fun movieDao(): MovieDao
    abstract fun authorDao(): AuthorDao
    abstract fun reviewDao(): ReviewDao
    abstract fun searchResultDao(): SearchResultDao
    abstract fun categoryMovieOrderDao(): CategoryMovieOrderDao
}