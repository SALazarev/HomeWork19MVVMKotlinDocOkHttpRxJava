package com.salazarev.hw19mvvmkotlindocokhttprxjava.data.repository

import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.ClientApi
import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.OkHttpApi
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data.Quotation

class ClientRepositoryImpl: ClientRepository {
    private var clientApi: ClientApi = OkHttpApi()

    override fun getLast30Quotation(): List<Quotation> = clientApi.getItemList()
    override fun getQuotation(date: String): Quotation = clientApi.getItem(date)
}