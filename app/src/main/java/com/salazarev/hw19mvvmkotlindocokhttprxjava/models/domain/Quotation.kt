package com.salazarev.hw19mvvmkotlindocokhttprxjava.models.domain

/**
 * Класс котировки цены на золото уровня бизнесс-логики.
 * @property date Дата котировки.
 * @property price Цена.
 */
data class Quotation(val date: String, val price: Double)