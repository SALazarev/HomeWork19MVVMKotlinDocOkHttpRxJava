package com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.url

/**
 * Интерфейс предоставления адресов на ресурсы.
 */
interface ResourceUrl {
    /**
     * Предоставление url-адреса на список котировок.
     * @return Ссылка на список котировок.
     */
    fun itemListUrl(): String
    /**
     * Предоставление url-адреса на одну котировку.
     * @param date Дата запрашиваемой котировки.
     * @return Ссылка на котировку по нужной дате.
     */
    fun itemUrl(date: String): String
}