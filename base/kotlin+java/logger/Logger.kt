package com.nazaroi.base.logger

interface Logger {
    fun debug(tag: String, msg: String)
    fun info(tag: String, msg: String)
    fun warn(tag: String, msg: String)
    fun error(tag: String, msg: String, throwable: Throwable? = null)
}