package com.twobard.btcprice.app.modules.api

import com.twobard.btcprice.app.modules.price.PriceResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Lewis on 09/02/2018.
 */
interface Api {

    @GET("v1/bpi/currentprice.json")
    fun getUsers(): Observable<PriceResponse>

}