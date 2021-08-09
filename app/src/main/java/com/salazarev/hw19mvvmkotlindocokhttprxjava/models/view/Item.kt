package com.salazarev.hw19mvvmkotlindocokhttprxjava.models.view

import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data.Quotation

data class Item(private val quotation: Quotation){
    val date: String = quotation.date
}