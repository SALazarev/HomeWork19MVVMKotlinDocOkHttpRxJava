package com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.client

import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.JsonWorker
import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.url.NbpUrl
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data.QuotationResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class ClientApiImpl(private val okHttpClient: OkHttpClient, private val jsonWorker: JsonWorker) :
    ClientApi {

    override fun getItem(date: String): QuotationResponse {
        return jsonWorker.getObject(request(NbpUrl.itemUrl(date)), QuotationResponse::class.java)
    }

    override fun getItemList(): List<QuotationResponse> {
        return jsonWorker.getObjectList(
            request(NbpUrl.itemlistUrl()),
            QuotationResponse::class.java
        )
    }

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
}