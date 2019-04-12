package com.adqmobile.domain

class ValidationException(message: String): Throwable(message)
class DatabaseException(message: String): Throwable(message)
class NetworkException(message: String): Throwable(message)