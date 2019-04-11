package com.adqmobile.cleanarchitecture.data

import com.adqmobile.domain.Log
import com.adqmobile.domain.entities.IEntity
import com.adqmobile.domain.repositories.IApi
import com.adqmobile.domain.repositories.Request
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import com.google.gson.reflect.TypeToken

class HttpRequest<U: IEntity>: Request<U> {

    private fun connect(api: IApi<U>) : HttpURLConnection {
        var urlConnection : HttpURLConnection? = null

        try {
            val url = URL("http://192.168.0.16:3000/api/" + api.getUrl() + "/")
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = api.getMethod().toString()
            if (api.getHeaders() != null) {
                val headers = api.getHeaders()!!
                for (header in headers) {
                    urlConnection.setRequestProperty(header.key, header.value)
                }
            }
            if (api.getBody() != null) {
                insertBody(Gson().toJson(api.getBody()), urlConnection)
            }
            urlConnection.connect()
        } catch (e: Exception) {
            e.printStackTrace()
            urlConnection?.disconnect()
            throw e
        }
        return urlConnection!!
    }

    private fun insertBody(body: String, urlConnection: HttpURLConnection) {
        val outputInBytes = body.toByteArray(charset("UTF-8"))
        val os = urlConnection.outputStream
        os.write(outputInBytes)
        os.close()
    }

    private fun readResponse(urlConnection: HttpURLConnection) : String {
        val exception = Exception("Connection returned empty body")

        val inputStream = urlConnection.inputStream ?: throw exception
        val buffer = StringBuffer()
        val reader = BufferedReader(InputStreamReader(inputStream))

        var line: String?
        do {
            line = reader.readLine()?: break
            buffer.append(line + "\n")
        } while (line != null)
        reader.close()

        if (buffer.isEmpty()) {
            throw exception
        }
        return buffer.toString()
    }

    override fun execute(api: IApi<U>): Map<String, String?>? {
        val urlConnection = connect(api)
        val resultString = readResponse(urlConnection)
        Log.d(resultString)
        urlConnection.disconnect()
        val type = object : TypeToken<Map<String, String>>(){}.type
        return Gson().fromJson(resultString, type)
    }
}