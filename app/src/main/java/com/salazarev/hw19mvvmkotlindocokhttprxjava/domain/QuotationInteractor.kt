package com.salazarev.hw19mvvmkotlindocokhttprxjava.domain

import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.ClientApi
import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.OkHttpApi
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data.Quotation

class QuotationInteractor {
    private var clientApi: ClientApi = OkHttpApi()

    fun getQuotation(): List<Quotation> = clientApi.getObject(clientApi.request())
}