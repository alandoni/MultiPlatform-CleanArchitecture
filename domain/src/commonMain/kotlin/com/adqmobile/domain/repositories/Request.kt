package com.adqmobile.domain.repositories

import com.adqmobile.domain.entities.Entity

interface Request {
    fun execute(api: Api): String
}

internal interface Requester<U: Entity> {
    fun request(): U?
}