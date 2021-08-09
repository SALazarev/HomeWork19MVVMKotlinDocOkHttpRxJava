package com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.url

object NbpUrl : ResourceUrl {
    override fun itemlistUrl() = "https://api.nbp.pl/api/cenyzlota/last/30/?format=json"

    override fun itemUrl(keyItem: String) = "https://api.nbp.pl/api/cenyzlota/$keyItem"
}