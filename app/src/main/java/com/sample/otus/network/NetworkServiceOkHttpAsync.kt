package com.sample.otus.network

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class NetworkServiceOkHttpAsync {

    private val client = OkHttpClient()

    fun request(callback: (response: String?) -> Unit) {
        val request: Request = Request.Builder()
            .url("https://api.coinbase.com/v2/currencies")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // todo something
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                callback.invoke(json)
            }
        })
    }
}