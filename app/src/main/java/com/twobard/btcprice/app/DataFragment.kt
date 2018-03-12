package com.twobard.btcprice.app

import android.support.v4.app.Fragment
import android.os.Bundle
import com.twobard.btcprice.app.modules.price.PriceActivityViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * Created by Lewis on 09/02/2018.
 */
class DataFragment: Fragment() {

    lateinit var viewModel: PriceActivityViewModel
    val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun addDisposble(disposable: Disposable): Disposable {
        disposables.add(disposable)
        return disposable
    }

    override fun onStop() {
        super.onStop()

        //clear any disposables but allow new disposables to be created
        //https://blog.kaush.co/2017/06/21/rxjava-1-rxjava-2-disposing-subscriptions/
        disposables.clear()
    }

}