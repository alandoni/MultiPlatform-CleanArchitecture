package com.adqmobile.domain.repositories

abstract class BaseApi {
    abstract fun getUrl(): String
    abstract fun getBody(): String?
    open fun getHeaders(): Map<String, String>? {
        return mapOf(Pair("Content-type", "Application/JSON"))
    }
    abstract fun getMethod(): HttpMethod
    protected abstract fun execute(): String?
}

enum class HttpMethod {
    GET, POST, DELETE, UPDATE
}
