package com.adqmobile.domain.repositories

import com.adqmobile.domain.entities.IEntity

interface IApi<U: IEntity> {
    fun getUrl() : String
    fun getBody() : U
    fun getHeaders() : Map<String, List<String>>? {
        return null
    }
    fun getMethod() : HttpMethod
}

enum class HttpMethod {
    GET, POST, DELETE, UPDATE
}
