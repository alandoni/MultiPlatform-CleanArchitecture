package com.adqmobile.domain.repositories

import com.adqmobile.domain.entities.IEntity

interface Request<U: IEntity> {
    fun execute(api: IApi<U>): Map<String, Any?>?
}