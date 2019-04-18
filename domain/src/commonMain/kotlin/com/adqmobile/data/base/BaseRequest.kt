package com.adqmobile.data.base

import com.adqmobile.domain.NetworkException
import com.adqmobile.domain.entities.BaseEntity

abstract class BaseRequest {

    var error: NetworkException? = null

    abstract fun execute(api: BaseApi): String

    open fun onError(error: String?) {
        if (error != null) {
            this.error = NetworkException(error)
        } else {
            this.error = NetworkException("Unknown network error")
        }
    }
}

internal interface TypedRequest<U: BaseEntity> {
    fun request(): U?
}