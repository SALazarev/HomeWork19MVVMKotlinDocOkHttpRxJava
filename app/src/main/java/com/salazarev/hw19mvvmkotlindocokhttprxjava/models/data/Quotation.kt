package com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data

import com.squareup.moshi.Json


data class Quotation(@Json(name = "data") val date: String, @Json(name = "cena") val price: Double)