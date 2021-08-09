package com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.client

import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data.QuotationResponse
import java.io.IOException

interface ClientApi {
    @Throws(IOException::class, IllegalStateException::class)
    fun getItem(date: String): QuotationResponse

    @Throws(IOException::class, IllegalStateException::class)
    fun getItemList(): List<QuotationResponse>
}