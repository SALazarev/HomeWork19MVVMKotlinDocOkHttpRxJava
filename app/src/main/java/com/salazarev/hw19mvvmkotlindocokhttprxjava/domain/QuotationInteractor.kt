package com.salazarev.hw19mvvmkotlindocokhttprxjava.domain
import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.repository.ClientRepository
import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.repository.ClientRepositoryImpl
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data.Quotation

class QuotationInteractor {
    val repo: ClientRepository = ClientRepositoryImpl()

    fun getLast30Quotation(): List<Quotation> = repo.getLast30Quotation()
    fun getQuotation(date: String): Quotation = repo.getQuotation(date)
}