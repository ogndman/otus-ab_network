package com.sample.otus.network.data

import com.squareup.moshi.Json

data class CurrencyData(
    @field:Json(name = "data")
    val data: List<Currency>
)