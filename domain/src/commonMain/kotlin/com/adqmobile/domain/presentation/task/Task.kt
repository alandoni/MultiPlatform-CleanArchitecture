package com.adqmobile.domain.presentation.task

import com.adqmobile.domain.Log
import com.adqmobile.domain.entities.IEntity
import com.adqmobile.domain.getMainDispatcher
import com.adqmobile.domain.usecases.UseCase
import kotlinx.coroutines.*

open class Task<U: IEntity, V: IEntity>(
    private val useCase: UseCase<U, V>,
    private val callBack: CallBack<V>?
) {

    private lateinit var error : Exception
    private var result : V? = null

    fun execute(param: U) : Job {

        val job = GlobalScope.launch(getMainDispatcher()) {
            try {
                Log.d("Going to call use case")
                result = useCase.execute(param)
                callBack?.onFinish(result)
            } catch (e: Exception) {
                error = e
                print(e)
                if (error.message != null) {
                    callBack?.onError(error.message!!)
                } else {
                    callBack?.onError(error.toString())
                }
            }
        }
        job.invokeOnCompletion {
            if (result != null) {

            } else {

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