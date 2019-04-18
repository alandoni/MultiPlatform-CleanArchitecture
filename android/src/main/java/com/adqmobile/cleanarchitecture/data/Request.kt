package com.adqmobile.cleanarchitecture.data

import com.adqmobile.data.base.BaseApi
import com.adqmobile.data.base.BaseRequest
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class HttpRequest: BaseRequest() {

    private fun connect(api: BaseApi) : HttpURLConnection {
        var urlConnection : HttpURLConnection? = null

        try {
            val url = URL("http://192.168.0.13:3000/api/" + api.getUrl() + "/")
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = api.getMethod().toString()
            if (api.getHeaders() != null) {
                val headers = api.getHeaders()!!
                for (header in headers) {
                    urlConnection.setRequestProperty(header.key, header.value)
                }
            }
            if (api.getBody() != null) {
                insertBody(api.getBody().toString(), urlConnection)
            }
            urlConnection.connect()
        } catch (e: Exception) {
            e.printStackTrace()
            urlConnection?.disconnect()
            throw e
        }
        return urlConnection
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

    override fun execute(api: BaseApi): String {
        val urlConnection = connect(api)
        val resultString = readResponse(urlConnection)
        urlConnection.disconnect()
        return resultString
    }
}