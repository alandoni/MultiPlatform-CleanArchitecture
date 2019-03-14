package com.adqmobile.cleanarchitecture.task

interface CallBack<T> {
    fun onFinish(result: T?)
    fun onCancel() { }
    fun onError(error : String) { }
}