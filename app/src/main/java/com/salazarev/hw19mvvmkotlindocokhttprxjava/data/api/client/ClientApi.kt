package com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.client

import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data.QuotationResponse
import java.io.IOException

/**
 * Интерфейс-контракт для работы с сетью.
 */
interface ClientApi {

    /**
     * Метод получения одной котировки цены золота в определённый день.
     * @param date Дата котировки.
     * @return Котировка золота в определённый день.
     */
    @Throws(IOException::class, IllegalStateException::class)
    fun getItem(date: String): QuotationResponse

    /**
     * Метод получения списка котировок стоимости золота.
     * @return Список котировок золота.
     */
    @Throws(IOException::class, IllegalStateException::class)
    fun getItemList(): List<QuotationResponse>
}