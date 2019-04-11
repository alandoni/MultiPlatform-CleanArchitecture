package com.adqmobile.domain.presentation.task

import com.adqmobile.domain.Log
import com.adqmobile.domain.entities.IEntity
import com.adqmobile.domain.getIODispatcher
import com.adqmobile.domain.getMainDispatcher
import com.adqmobile.domain.usecases.UseCase
import kotlinx.coroutines.*

open class Task<U: IEntity, V: IEntity>(
    private val useCase: UseCase<U, V>,
    private val callBack: CallBack<V>?
) {
    private lateinit var error : Exception

    fun execute(param: U) : Job {
        var result : V? = null
        val job = GlobalScope.launch(getIODispatcher()) {
            try {
                Log.d("Going to call use case")
                result = useCase.execute(param)
                //callBack?.onFinish(result)
            } catch (e: Exception) {
                print(e)
                error = e
            }
            launch(getMainDispatcher()) {
                onPostExecute(result)
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