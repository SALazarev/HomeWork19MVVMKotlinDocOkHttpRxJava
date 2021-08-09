package com.salazarev.hw19mvvmkotlindocokhttprxjava.data.repository

import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data.Quotation

interface ClientRepository {
    fun getLast30Quotation(): List<Quotation>
    fun getQuotation(date: String): Quotation
}