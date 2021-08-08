package com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api

import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data.Quotation
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


abstract class ClientApi {
    companion object {
        const val REQUEST_URL = "https://api.nbp.pl/api/cenyzlota/last/30/?format=json"
    }

    abstract fun request(): String

    fun getRequestBody(date: String, price: Double): String {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter = moshi.adapter(Quotation::class.java)
        return adapter.toJson(Quotation(date, price))
    }

    fun getObject(request: String): List<Quotation>{
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val typeAdapter =  Types.newParameterizedType(List::class.java,Quotation::class.java)
        val jsonAdapter: JsonAdapter<List<Quotation>> =
            moshi.adapter( typeAdapter)

        return jsonAdapter.fromJson(request) ?: throw Exception()
    }

}