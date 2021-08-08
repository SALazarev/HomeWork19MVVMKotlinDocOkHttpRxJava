package com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data

import com.squareup.moshi.Json
import java.util.*


data class Quotation(@Json(name = "data") val date: String, @Json(name = "cena") val price: Double) {
    val id: String = UUID.randomUUID().toString()
}