package com.salazarev.hw19mvvmkotlindocokhttprxjava.util.json

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

/**
 * Класс обработки запросов json.
 * @param moshi Преобразователь json запросов в объекты.
 */
class JsonWorkerImpl(private val moshi: Moshi) : JsonWorker {
    override fun <T> getObjectList(request: String, classType: Class<T>): List<T> {
        val typeAdapter = Types.newParameterizedType(List::class.java, classType)
        val jsonAdapter: JsonAdapter<List<T>> = moshi.adapter(typeAdapter)
        return jsonAdapter.fromJson(request) ?: throw Exception()
    }

    override fun <T> getObject(request: String, classType: Class<T>): T {
        val typeAdapter = Types.newParameterizedType(List::class.java, classType)
        val jsonAdapter: JsonAdapter<List<T>> = moshi.adapter(typeAdapter)
        return jsonAdapter.fromJson(request)?.get(0) ?: throw Exception()
    }
}