package com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data

import com.squareup.moshi.Json

/**
 * Класс котировки цены на золото уровня хранения данных.
 * @property date Дата котировки.
 * @property price Цена.
 */
data class QuotationResponse(
    @Json(name = "data") val date: String,
    @Json(name = "cena") val price: Double
)