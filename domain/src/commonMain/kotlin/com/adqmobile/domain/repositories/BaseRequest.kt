package com.adqmobile.domain.repositories

import com.adqmobile.domain.entities.BaseEntity

interface BaseRequest {
    fun execute(api: BaseApi): String
}

internal interface TypedRequest<U: BaseEntity> {
    fun request(): U?
}