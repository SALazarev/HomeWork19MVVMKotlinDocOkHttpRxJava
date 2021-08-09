package com.salazarev.hw19mvvmkotlindocokhttprxjava.util.json

interface JsonWorker {
    fun <T> getObject(request: String, classType: Class<T>): T
    fun <T> getObjectList(request: String, classType: Class<T>): List<T>
}