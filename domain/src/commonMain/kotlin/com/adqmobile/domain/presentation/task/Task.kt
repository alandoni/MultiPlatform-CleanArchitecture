package com.adqmobile.domain.presentation.task

import com.adqmobile.domain.UI
import com.adqmobile.domain.entities.IEntity
import com.adqmobile.domain.usecases.UseCase
import kotlinx.coroutines.*

open class Task<U: IEntity, V: IEntity>(
    private val useCase: UseCase<U, V>,
    private val callBack: CallBack<V>?
) {

    private lateinit var error : Exception
    private var result : V? = null

    fun execute(param: U) : Job {

        val job = GlobalScope.launch(UI()) {
            delay(3000)
            try {
                async {
                    result = useCase.execute(param)
                }
            } catch (e: Exception) {
                error = e
                print(e)
            }
        }
        job.invokeOnCompletion {
            if (result != null) {
                callBack?.onFinish(result)
            } else if (error.message != null) {
                callBack?.onError(error.message!!)
            } else {
                callBack?.onError(error.toString())
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