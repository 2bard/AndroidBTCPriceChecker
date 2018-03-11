package com.surge.test.mykotlinapplication.modules.price

import android.util.Log
import com.surge.test.mykotlinapplication.ValueChangeListener
import com.surge.test.mykotlinapplication.modules.polling.PollEmitterInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Lewis on 09/02/2018.
 */
class PriceActivityViewModel(val priceRepository: PriceRepository,
                             val pollEmitter: PollEmitterInterface) {

    var priceResponse: PriceResponse? = null

    fun getPrice(): Observable<PriceResponse?> {
        return priceRepository.getUsers()
    }

    fun getCachedPrice(): PriceResponse? {
        return priceRepository.getCachedPrice()
    }

    fun startPolling(listener: ValueChangeListener): Disposable{
        Log.i("DEBUG", "Starting polling....")
        return pollEmitter.getPollEmitter().subscribe {
            Log.i("Price", "Getting price!")
            getPrice()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ t: PriceResponse? ->
                        Log.i("Price", "Processing payload from repository" )
                        priceResponse = t
                        listener.valuesChanged()
                    },{
                        priceResponse = null
                        listener.errorOccured()
                    })
        }
    }

}