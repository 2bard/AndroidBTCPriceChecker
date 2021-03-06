package com.twobard.btcprice.app.modules.price

/**
 * Created by Lewis on 09/02/2018.
 */
class PriceResponse {

    val time: Time? = null
    val disclaimer: String? = null
    val chartName: String? = null
    val bpi: Map<String, Currency>? = null

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