package com.salazarev.hw19mvvmkotlindocokhttprxjava.domain

import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.repository.ClientRepository
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.domain.Quotation
import javax.inject.Inject

/**
 * Класс работы с котировками цен на золото.
 */
class QuotationInteractor @Inject constructor(private val repo: ClientRepository) {
    /**
     * Метод загрузки списка котировок цен на золото.
     * @return Список котировок.
     */
    fun getQuotationList(): List<Quotation> =
        repo.getListQuotation().map { Quotation(it.date, it.price) }

    /**
     * Метод загрузки котировки цены на золото по определённой даты.
     * @param date Дата запрашиваемой котировки.
     * @return Котировка по нужной дате.
     */
    fun getQuotation(date: String): Quotation =
        repo.getQuotation(date).let { Quotation(it.date, it.price) }
}