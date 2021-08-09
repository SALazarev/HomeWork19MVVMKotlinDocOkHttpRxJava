package com.salazarev.hw19mvvmkotlindocokhttprxjava.data.repository

import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data.QuotationResponse

interface ClientRepository {
    fun getListQuotation(): List<QuotationResponse>
    fun getQuotation(date: String): QuotationResponse
}