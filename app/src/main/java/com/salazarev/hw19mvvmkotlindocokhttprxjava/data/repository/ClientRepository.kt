package com.salazarev.hw19mvvmkotlindocokhttprxjava.data.repository

import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data.QuotationResponse

/**
 * Интерфейс репозитория для работы с данными котировок из сети.
 */
interface ClientRepository {
    /**
     * Предоставление списка котировок.
     * @return Список котировок.
     */
    fun getListQuotation(): List<QuotationResponse>

    /**
     * Предоставление котировки по дате.
     * @param date Дата котировки.
     * @return Котировка по нужной дате.
     */
    fun getQuotation(date: String): QuotationResponse
}