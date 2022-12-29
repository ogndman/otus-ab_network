package com.sample.otus.network.data

import com.squareup.moshi.Json

data class Currency(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "min_size")
    val minSize: Double
)