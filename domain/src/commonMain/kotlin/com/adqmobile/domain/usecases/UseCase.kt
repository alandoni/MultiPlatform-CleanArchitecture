package com.adqmobile.domain.usecases

abstract class UseCase<T, V> {
    abstract fun run(params: T) : V

    fun execute(params: T) : V {
        return this.run(params)
    }
}