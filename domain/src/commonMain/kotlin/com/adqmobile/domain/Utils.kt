package com.adqmobile.domain

import kotlin.reflect.KClass
import kotlinx.coroutines.CoroutineDispatcher

@UseExperimental(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CONSTRUCTOR)
@Retention(AnnotationRetention.SOURCE)
expect annotation class Throws(vararg val exceptionClasses: KClass<out Throwable>)

expect object Log{
    fun d(message: String)
    fun e(error:Throwable)
    fun e(message: String)
    fun i(message: String)
}

expect fun getMainDispatcher(): CoroutineDispatcher