package com.adqmobile.presentation.task

import com.adqmobile.domain.Log
import com.adqmobile.domain.entities.BaseEntity
import com.adqmobile.domain.getIODispatcher
import com.adqmobile.domain.getMainDispatcher
import com.adqmobile.domain.usecases.BaseUseCase
import kotlinx.coroutines.*

open class Task<U: BaseEntity, V: BaseEntity>(
    private val useCase: BaseUseCase<U, V>,
    private val callBack: CallBack<V>?
) {
    private lateinit var error : Exception

    fun execute(param: U) : Job {
        var result : V? = null
        val job = GlobalScope.launch(getIODispatcher()) {
            try {
                result = useCase.execute(param)
            } catch (e: Exception) {
                Log.e(e)
                error = e
            } finally {
                launch(getMainDispatcher()) {
                    onPostExecute(result)
                }
            }
        }
        return job
    }

    fun onCancelled() {
        callBack?.onCancel()
    }

    fun onPostExecute(result: V?) {
        if (result != null) {
            callBack?.onFinish(result)
        } else {
            callBack?.onError(error.message!!)
        }
    }
}