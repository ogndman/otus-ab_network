package com.sample.otus.network

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

class NetworkServiceRetrofitCall {

    private var client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.coinbase.com/")
        .client(client)
        .build();

    private val service = retrofit.create(CurrenciesService::class.java)

    fun request(callback: (response: String?) -> Unit) {
        val call = service.getCurrencies()

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                callback.invoke(response.body()?.string())
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    interface CurrenciesService {

        @GET("v2/currencies")
        fun getCurrencies(): Call<ResponseBody>
    }
}