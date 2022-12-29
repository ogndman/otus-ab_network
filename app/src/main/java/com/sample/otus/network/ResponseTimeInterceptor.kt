package com.sample.otus.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okio.IOException

class ResponseTimeInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request: Request = chain.request()
        val t1 = System.currentTimeMillis()
        Log.v("Response time", "Sending request ${request.url}")

        val response = chain.proceed(request)
        val t2 = System.currentTimeMillis()
        Log.v("Response time", "Received response after time ${t2 - t1}ms")
        return response
    }
}