package com.surge.test.mykotlinapplication.modules.api

import com.surge.test.mykotlinapplication.modules.price.PriceResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Lewis on 09/02/2018.
 */
interface Api {

    @GET("v1/bpi/currentprice.json")
    fun getUsers(): Observable<PriceResponse>

}