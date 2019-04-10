package com.adqmobile.domain.usecases

import com.adqmobile.domain.Throws

interface UseCase<T, V> {

    @Throws
    fun execute(param: T): V
}