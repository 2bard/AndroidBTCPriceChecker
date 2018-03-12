package com.twobard.btcprice.app.modules.price

import com.twobard.btcprice.app.modules.api.Api
import com.twobard.btcprice.app.modules.storage.StorageInterface
import io.reactivex.Observable

/**
 * Created by Lewis on 09/02/2018.
 */
class PriceRepository(val userApi: Api, val storageInterface: StorageInterface) {

    fun emptyRepository():Boolean{
        return storageInterface.clear()
    }

    fun getCachedPrice():PriceResponse? {
        return storageInterface.retrieve()
    }

    fun getUsers(): Observable<PriceResponse?> = when (getCachedPrice()) {

        null -> userApi.getUsers().doOnNext({ storageInterface.save(it) })
        else -> Observable.just(getCachedPrice())
                    .mergeWith(userApi.getUsers())
                    .doOnNext { storageInterface.save(it!!) }

    }

}