package com.adqmobile.cleanarchitecture.data

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.adqmobile.domain.entities.IEntity
import com.adqmobile.domain.repositories.IApi
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class Request<U: IEntity, V: IEntity>(private val context: Context) {

    fun request(api: IApi<U>, classOfV: Class<V>): V? {
        if (ContextCompat.checkSelfPermission(context.applicationContext, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(context as Activity,
                    Manifest.permission.INTERNET)) {
                return null
            }
        }

        var urlConnection : HttpURLConnection? = null
        var reader : BufferedReader? = null
        var exception : Exception? = null
        var gson : V? = null
        try {
            val url = URL("http://192.168.3.125:3000/api/" + api.getUrl() + "/")
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = api.getMethod().toString()
            if (api.getHeaders() != null) {
                urlConnection.headerFields.putAll(api.getHeaders()!!)
            }
            urlConnection!!.connect()

            val inputStream = urlConnection.inputStream
            val buffer = StringBuffer()
            if (inputStream == null) {
                null
            }
            reader = BufferedReader(InputStreamReader(inputStream))

            var line: String?
            do {
                line = reader.readLine()
                if (line != null) {
                    buffer.append(line + "\n")
                }
            } while (line != null)

            if (buffer.isEmpty()) {
                null
            }
            val resultString = buffer.toString()
            gson = Gson().fromJson(resultString, classOfV)
        } catch (e : Exception) {
            exception = e
        } finally {
            urlConnection?.disconnect()
            reader?.close()

            if (exception != null) {
                throw exception
            }
        }
        return gson
    }
}