package com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.url

interface ResourceUrl {
    fun itemlistUrl(): String
    fun itemUrl(keyItem: String): String
}