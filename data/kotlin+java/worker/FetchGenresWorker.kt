package com.nazaroi.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.nazaroi.data.local.database.AppDatabase
import com.nazaroi.data.mapper.genre.GenreDtoToEntityMapper
import com.nazaroi.data.remote.api.GenreApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class FetchGenresWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val api: GenreApi,
    private val database: AppDatabase,
    private val genreDtoToEntityMapper: GenreDtoToEntityMapper
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {

            api.fetchMovieGenres().genres.map(genreDtoToEntityMapper::map).let {
                database.genreDao().insertAll(it)
            }

            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}