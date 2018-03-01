package com.surge.test.mykotlinapplication.modules.price

/**
 * Created by Lewis on 09/02/2018.
 */
class PriceResponse {

    val time: Time? = null
    val disclaimer: String? = null
    val chartName: String? = null
    val bpi: Prices? = null

    data class Prices(
            val USD: Currency,
            val GBP: Currency,
            val EUR: Currency
    )

    data class Time(
            val updated: String,
            val updatedISO: String,
            val updateduk: String
    )

    data class Currency(
            val code: String,
            val symbol: String,
            val rate: String,
            val description: String,
            val rate_float: Float
    )
}