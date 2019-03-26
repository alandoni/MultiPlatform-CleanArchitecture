package com.adqmobile.domain.usecases

import com.adqmobile.domain.Throws

abstract class UseCase<T, V> {

    @Throws
    abstract fun run(params: T) : V

    @Throws
    fun execute(params: T) : V {
        return this.run(params)
    }
}