package com.twobard.btcprice.app.modules.price

import com.twobard.btcprice.app.ValueChangeListener
import com.twobard.btcprice.app.modules.polling.PollEmitterInterface
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
        return pollEmitter.getPollEmitter().subscribe {
            getPrice()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ t: PriceResponse? ->
                        priceResponse = t
                        listener.valuesChanged()
                    },{
                        listener.errorOccured()
                    })
        }
    }

}