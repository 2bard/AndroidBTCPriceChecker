package com.surge.test.mykotlinapplication

import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import com.surge.test.mykotlinapplication.modules.price.PriceActivityViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * Created by Lewis on 09/02/2018.
 */
class DataFragment: Fragment() {

    lateinit var viewModel: PriceActivityViewModel
    val subscriptions = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun addSubscription(disposable: Disposable): Disposable {
        subscriptions.add(disposable)
        return disposable
    }

    override fun onStop() {
        super.onStop()
        subscriptions.clear()
    }

}