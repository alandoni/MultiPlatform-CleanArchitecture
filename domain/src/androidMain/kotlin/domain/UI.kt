package com.adqmobile.domain

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

actual class UI() : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        Dispatchers.Main.dispatch(context, block)
    }
}