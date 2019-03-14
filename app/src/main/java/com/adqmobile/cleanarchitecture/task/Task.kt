package com.adqmobile.cleanarchitecture.task

import android.os.AsyncTask
import com.adqmobile.domain.entities.IEntity
import com.adqmobile.domain.usecases.UseCase
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject

open class Task<T: UseCase<U, V>, U, V: IEntity>(private val callBack: CallBack<V>): AsyncTask<U, Unit, V?>() {

    @Inject protected lateinit var useCase : T
    private lateinit var error : Exception

    override fun doInBackground(vararg params: U) : V? {
        return try {
            this.useCase.execute(params[0])
        } catch (e : RuntimeException) {
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