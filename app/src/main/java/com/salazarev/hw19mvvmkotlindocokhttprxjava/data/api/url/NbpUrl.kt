package com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.url

/**
 * Объект предоставления ресурсов посредством сервиса NBP.
 */
object NbpUrl : ResourceUrl {
    override fun itemListUrl() = "https://api.nbp.pl/api/cenyzlota/2013-01-01/2013-01-31/?format=json"

    override fun itemUrl(date: String) = "https://api.nbp.pl/api/cenyzlota/$date"
}