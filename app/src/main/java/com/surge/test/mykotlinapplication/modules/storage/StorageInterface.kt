package com.surge.test.mykotlinapplication.modules.storage

import com.surge.test.mykotlinapplication.modules.price.PriceResponse

/**
 * Created by Lewis on 13/02/2018.
 */
interface StorageInterface {

    fun save(priceResponse: PriceResponse):Boolean
    fun retrieve():PriceResponse?
    fun clear():Boolean
}