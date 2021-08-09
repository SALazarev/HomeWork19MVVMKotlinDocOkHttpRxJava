package com.salazarev.hw19mvvmkotlindocokhttprxjava.data.repository

import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.client.ClientApi
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data.QuotationResponse

/**
 * Репозиторий для работы с данными котировок из сети.
 */
class ClientRepositoryImpl(private val clientApi: ClientApi) : ClientRepository {
    override fun getListQuotation(): List<QuotationResponse> = clientApi.getItemList()
    override fun getQuotation(date: String): QuotationResponse = clientApi.getItem(date)
}