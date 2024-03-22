package com.nazaroi.base.fault

import android.content.res.Resources
import android.database.sqlite.SQLiteException
import com.google.gson.JsonParseException
import com.nazaroi.base.util.HttpStatusCode
import retrofit2.HttpException
import java.io.FileNotFoundException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

sealed class Fault(open val message: String, open val cause: Throwable? = null)

fun Throwable.toFault(): Fault {
    return when (this) {
        is HttpException -> HttpFault(HttpStatusCode.from(code()), cause = this)
        is IOException -> when (this) {
            is SocketTimeoutException -> NetworkTimeoutFault(cause = this)
            is UnknownHostException -> NoInternetConnectionFault(cause = this)
            is SSLHandshakeException -> SecureConnectionFault(cause = this)
            is FileNotFoundException -> FileNotFoundFault(cause = this)
            else -> UnexpectedFault(cause = this)
        }

        is Resources.NotFoundException -> ResourceNotFoundFault(cause = this)
        is JsonParseException -> ParsingFault(cause = this)
        is SQLiteException -> DatabaseFault(cause = this)
        is ValidationException -> ValidationFault(
            issues = issues, cause = this
        )

        else -> UnexpectedFault(cause = this)
    }
}