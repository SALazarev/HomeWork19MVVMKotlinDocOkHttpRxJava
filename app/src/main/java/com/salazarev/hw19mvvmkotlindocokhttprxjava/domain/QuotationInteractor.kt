package com.salazarev.hw19mvvmkotlindocokhttprxjava.domain

import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.repository.ClientRepository
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.domain.Quotation

class QuotationInteractor(private val repo: ClientRepository) {
    fun getLast30Quotation(): List<Quotation> =
        repo.getListQuotation().map { Quotation(it.date, it.price) }

    fun getQuotation(date: String): Quotation =
        repo.getQuotation(date).let { Quotation(it.date, it.price) }
}