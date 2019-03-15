package com.adqmobile.cleanarchitecture.data

import android.content.Context
import com.adqmobile.domain.entities.IEntity
import com.adqmobile.domain.repositories.IApi
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Request<U: IEntity, V: IEntity>(private val context: Context) {

    private fun connect(api: IApi<U>) : HttpURLConnection {
        var urlConnection : HttpURLConnection? = null

        try {
            val url = URL("http://192.168.3.125:3000/api/" + api.getUrl() + "/")
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = api.getMethod().toString()
            if (api.getHeaders() != null) {
                urlConnection.headerFields.putAll(api.getHeaders()!!)
            }
            urlConnection.connect()
        } catch (e: Exception) {
            throw e
        } finally {
            urlConnection?.disconnect()
        }
        return urlConnection!!
    }

    private fun readResponse(urlConnection: HttpURLConnection) : String {
        val exception = Exception("Connection returned empty body")

        val inputStream = urlConnection.inputStream ?: throw exception

        val buffer = StringBuffer()
        val reader = BufferedReader(InputStreamReader(inputStream))

        var line: String?
        do {
            line = reader.readLine()
            buffer.append(line + "\n")
        } while (line != null)
        reader.close()

        if (buffer.isEmpty()) {
            throw exception
        }

        return buffer.toString()
    }

    fun request(api: IApi<U>, classOfV: Class<V>): V? {
        val urlConnection = connect(api)
        val resultString = readResponse(urlConnection)
        return Gson().fromJson(resultString, classOfV)
    }
}