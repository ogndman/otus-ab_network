package com.sample.otus.network

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class NetworkServiceOkHttpBlocking {

    private val client = OkHttpClient()

    fun request(callback: (response: String?) -> Unit) {
        val request: Request = Request.Builder()
            .url("https://api.coinbase.com/v2/currencies")
            .build()
        try {
            val json = client.newCall(request).execute().body?.string()
            callback.invoke(json)
        } catch (exception: IOException) {
            // todo something
        }
    }
}