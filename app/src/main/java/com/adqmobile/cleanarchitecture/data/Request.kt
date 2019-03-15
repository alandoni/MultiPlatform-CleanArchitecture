package com.adqmobile.cleanarchitecture.data

import android.util.Log
import com.adqmobile.domain.entities.IEntity
import com.adqmobile.domain.repositories.IApi
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class Request<U: IEntity, V: IEntity> {

    fun request(api: IApi<U>, classOfV: Class<V>): V? {
        var urlConnection : HttpURLConnection? = null
        var reader : BufferedReader? = null
        var exception : Exception? = null
        try {
            val url = URL(URL("http://localhost:3000"), api.getUrl())
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = api.getMethod().toString()
            if (api.getHeaders() != null) {
                urlConnection.headerFields.putAll(api.getHeaders()!!)
            }
            urlConnection!!.connect()

            val inputStream = urlConnection.inputStream
            val buffer = StringBuffer()
            if (inputStream == null) {
                return null
            }
            reader = BufferedReader(InputStreamReader(inputStream))
            var line: String
            do {
                line = reader.readLine()
                buffer.append(line + "\n")
            } while (line != null)

            if (buffer.isEmpty()) {
                return null
            }
            val resultString = buffer.toString()
            val gson = Gson().fromJson(resultString, classOfV)
            return gson
        } catch (e : Exception) {
            exception = e
        } finally {
            urlConnection?.disconnect()
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {
                    Log.e("Request", "Error closing stream", e)
                }

            }
            if (exception != null) {
                throw exception
            }
            return null
        }
    }
}