package com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class JsonWorker(private val moshi: Moshi) {

    fun <T> getObjectList(request: String, classType: Class<T>): List<T> {
        val typeAdapter = Types.newParameterizedType(List::class.java, classType)
        val jsonAdapter: JsonAdapter<List<T>> = moshi.adapter(typeAdapter)
        return jsonAdapter.fromJson(request) ?: throw Exception()
    }

    fun <T> getObject(request: String, classType: Class<T>): T {
        val typeAdapter = Types.newParameterizedType(List::class.java, classType)
        val jsonAdapter: JsonAdapter<List<T>> = moshi.adapter(typeAdapter)
        return jsonAdapter.fromJson(request)?.get(0) ?: throw Exception()
    }
}