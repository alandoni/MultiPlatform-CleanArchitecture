package com.adqmobile.domain.repositories

import com.adqmobile.domain.entities.Entity

interface Request<U: Entity> {
    fun execute(api: IApi<U>): Map<String, String?>?
}