package com.adqmobile.domain.usecases

import com.adqmobile.domain.Throws

interface BaseUseCase<T, V> {

    @Throws
    fun execute(param: T): V
}