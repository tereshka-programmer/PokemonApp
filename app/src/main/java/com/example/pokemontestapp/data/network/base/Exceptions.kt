package com.example.pokemontestapp.data.network.base

open class AppException : RuntimeException {
    constructor() : super()
    constructor(cause: Throwable) : super(cause)
}

// BackendException with statusCode=401 is usually mapped to this exception

class ConnectionException(cause: Throwable) : AppException(cause = cause)

open class BackendException(
    val code: Int,
) : AppException()

class ParseBackendResponseException(
    cause: Throwable
) : AppException(cause = cause)
