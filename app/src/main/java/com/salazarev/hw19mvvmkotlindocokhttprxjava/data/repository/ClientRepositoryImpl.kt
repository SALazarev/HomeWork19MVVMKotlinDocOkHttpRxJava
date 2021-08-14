package com.salazarev.hw19mvvmkotlindocokhttprxjava.data.repository

import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.client.ClientApi
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data.QuotationResponse
import javax.inject.Inject

/**
 * Репозиторий для работы с данными котировок из сети.
 */
class ClientRepositoryImpl @Inject constructor(private val clientApi: ClientApi) :
    ClientRepository {
    override fun getListQuotation(): List<QuotationResponse> = clientApi.getItemList()
    override fun getQuotation(date: String): QuotationResponse = clientApi.getItem(date)
}