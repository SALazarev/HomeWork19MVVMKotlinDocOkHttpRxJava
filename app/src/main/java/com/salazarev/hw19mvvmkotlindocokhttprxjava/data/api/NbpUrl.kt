package com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api

object NbpUrl {
    const val REQUEST_URL_LAST_30 = "https://api.nbp.pl/api/cenyzlota/last/30/?format=json"
    fun requestUrlByDate(date: String) = "https://api.nbp.pl/api/cenyzlota/$date"
}