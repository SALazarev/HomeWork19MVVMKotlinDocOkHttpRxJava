package com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api

import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class OkHttpApi : ClientApi() {

    private var okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
        .readTimeout(5000, TimeUnit.MILLISECONDS)
        .writeTimeout(5000, TimeUnit.MILLISECONDS)
        .addNetworkInterceptor(HttpLoggingInterceptor())
        .build()
    

    override fun request(): String {
        val request = Request.Builder()
            .url(REQUEST_URL.toHttpUrl())
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
}