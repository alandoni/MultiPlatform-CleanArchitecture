package com.adqmobile.controllers.task

interface CallBack<T> {
    fun onFinish(result: T?)
    fun onCancel() { }
    fun onError(error: String) { }
}