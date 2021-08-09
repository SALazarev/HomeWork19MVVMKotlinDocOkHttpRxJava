package com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api

import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data.Quotation
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class OkHttpApi : ClientApi {

    private var okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
        .readTimeout(5000, TimeUnit.MILLISECONDS)
        .writeTimeout(5000, TimeUnit.MILLISECONDS)
        .addNetworkInterceptor(HttpLoggingInterceptor())
        .build()


    private fun request(url: String): String {
        val request = Request.Builder()
            .url(url)
            .build()
        return handleResponse(request)
    }


    private fun handleResponse(request: Request): String {
        val response: Response = okHttpClient.newCall(request).execute()
        response.use {
            return try {
                if (response.isSuccessful) {
                    val body = response.body
                    body?.string() ?: "No content"
                } else "Response code: ${response.code}"
            } catch (e: Exception) {
                e.toString()
            }
        }
    }

    override fun getItem(date: String): Quotation {
        return getObject(request(NbpUrl.requestUrlByDate(date)))
    }

    override fun getItemList(): List<Quotation> {
        return getObjectList(request(NbpUrl.REQUEST_URL_LAST_30))
    }

    fun getObjectList(request: String): List<Quotation> {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val typeAdapter = Types.newParameterizedType(List::class.java, Quotation::class.java)
        val jsonAdapter: JsonAdapter<List<Quotation>> =
            moshi.adapter(typeAdapter)

        return jsonAdapter.fromJson(request) ?: throw Exception()
    }

    fun getObject(request: String): Quotation {
        val moshi = getMoshi()
        val typeAdapter = Types.newParameterizedType(List::class.java, Quotation::class.java)
        val jsonAdapter: JsonAdapter<List<Quotation>> =
            moshi.adapter(typeAdapter)

        return jsonAdapter.fromJson(request)?.get(0) ?: throw Exception()
    }

    fun getMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }



}