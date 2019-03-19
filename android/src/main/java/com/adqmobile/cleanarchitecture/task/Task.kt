package com.adqmobile.cleanarchitecture.task

import android.os.AsyncTask
import com.adqmobile.domain.entities.IEntity
import com.adqmobile.domain.usecases.UseCase
import java.lang.Exception
import java.lang.RuntimeException

open class Task<U: IEntity, V: IEntity>(
    private val useCase: UseCase<U, V>,
    private val callBack: CallBack<V>
): AsyncTask<U, Unit, V?>() {

    private lateinit var error : Exception

    override fun doInBackground(vararg params: U) : V? {
        return try {
            this.useCase.execute(params[0])
        } catch (e : Exception) {
            e.printStackTrace()
            error = e
            null
        }
    }

    override fun onCancelled() {
        super.onCancelled()
        callBack.onCancel()
    }

    override fun onPostExecute(result: V?) {
        super.onPostExecute(result)
        if (result != null) {
            callBack.onFinish(result)
        } else {
            callBack.onError(error.message!!)
        }
    }
}