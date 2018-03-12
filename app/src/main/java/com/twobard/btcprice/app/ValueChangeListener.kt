package com.twobard.btcprice.app

/**
 * Created by Lewis on 19/02/2018.
 */
interface ValueChangeListener {
    fun valuesChanged()
    fun errorOccured()
}