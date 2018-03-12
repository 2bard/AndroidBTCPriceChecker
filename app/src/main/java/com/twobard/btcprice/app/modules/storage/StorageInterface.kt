package com.twobard.btcprice.app.modules.storage

import com.twobard.btcprice.app.modules.price.PriceResponse

/**
 * Created by Lewis on 13/02/2018.
 */
interface StorageInterface {

    fun save(priceResponse: PriceResponse):Boolean
    fun retrieve():PriceResponse?
    fun clear():Boolean
}