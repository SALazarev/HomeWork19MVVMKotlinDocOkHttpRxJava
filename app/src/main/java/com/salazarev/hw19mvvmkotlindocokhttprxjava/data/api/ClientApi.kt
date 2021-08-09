package com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api

import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data.Quotation
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.IOException

interface ClientApi {
    fun getItem(date: String): Quotation
    fun getItemList(): List<Quotation>
}