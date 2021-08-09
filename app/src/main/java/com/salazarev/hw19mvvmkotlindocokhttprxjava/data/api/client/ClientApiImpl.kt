package com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.client

import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.url.NbpUrl
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data.QuotationResponse
import com.salazarev.hw19mvvmkotlindocokhttprxjava.util.json.JsonWorker
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class ClientApiImpl(
    private val okHttpClient: OkHttpClient,
    private val jsonWorker: JsonWorker
) : ClientApi {

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
        return  okHttpClient.newCall(request).execute().body?.string().orEmpty()
    }
}