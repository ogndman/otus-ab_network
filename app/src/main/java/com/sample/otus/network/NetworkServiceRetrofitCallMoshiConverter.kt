package com.sample.otus.network

import com.sample.otus.network.data.CurrencyData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

class NetworkServiceRetrofitCallMoshiConverter {

    private var client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.coinbase.com/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    private val service = retrofit.create(CurrenciesService::class.java)

    fun request(callback: (response: CurrencyData?) -> Unit) {
        val call = service.getCurrencies()

        call.enqueue(object : Callback<CurrencyData> {
            override fun onResponse(call: Call<CurrencyData>, response: Response<CurrencyData>) {
                callback.invoke(response.body())
            }

            override fun onFailure(call: Call<CurrencyData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    interface CurrenciesService {

        @GET("v2/currencies")
        fun getCurrencies(): Call<CurrencyData>
    }
}