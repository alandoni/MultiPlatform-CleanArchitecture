package com.adqmobile.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual object Log{
    actual fun d(message: String) {
        print(message)
    }

    actual fun e(message: String) {
        print(message)
    }

    actual fun i(message: String) {
        print(message)
    }

    actual fun e(error: Throwable) {
        print(error)
    }
}

actual fun getMainDispatcher(): CoroutineDispatcher {
    return Dispatchers.Main
}

actual fun getIODispatcher(): CoroutineDispatcher {
    return Dispatchers.IO
}