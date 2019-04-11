package com.adqmobile.domain.repositories

import com.adqmobile.domain.entities.Entity

interface IApi<U: Entity> {
    fun getUrl(): String
    fun getBody(): U?
    fun getHeaders(): Map<String, String>? {
        return mapOf(Pair("Content-type", "Application/JSON"))
    }
    fun getMethod(): HttpMethod

    fun execute(): Map<String, Any?>?
}

enum class HttpMethod {
    GET, POST, DELETE, UPDATE
}
