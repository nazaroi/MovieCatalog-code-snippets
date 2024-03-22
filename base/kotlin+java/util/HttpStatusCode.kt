package com.nazaroi.base.util

enum class HttpStatusCode(val code: Int, val description: String) {
    Unauthorized(401, "Access denied due to missing or invalid credentials."), Forbidden(
        403, "Access to the requested resource is forbidden."
    ),
    NotFound(404, "The requested resource was not found."), TooManyRequests(
        429, "Too many requests in a given amount of time."
    ),
    InternalServerError(500, "The server encountered an unexpected condition."), BadRequest(
        400, "The request could not be understood by the server."
    ),
    ServiceUnavailable(
        503, "The server is currently unable to handle the request."
    ),
    GatewayTimeout(504, "The server did not receive a timely response."), Undefined(
        -1, "The status code is undefined or not recognized."
    );

    companion object {
        fun from(code: Int): HttpStatusCode = entries.find { it.code == code } ?: Undefined
    }
}