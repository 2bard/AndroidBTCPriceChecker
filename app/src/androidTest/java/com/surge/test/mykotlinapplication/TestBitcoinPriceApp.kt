package com.surge.test.mykotlinapplication

import android.util.Log
import com.surge.test.mykotlinapplication.modules.AppModule
import com.surge.test.mykotlinapplication.modules.MockApiModule
import com.surge.test.mykotlinapplication.modules.price.PriceModule
import com.surge.test.mykotlinapplication.modules.storage.StorageModule
import com.surge.test.mykotlinapplication.modules.DaggerApplicationComponent
import com.surge.test.mykotlinapplication.modules.logging.LoggingModule
import com.surge.test.mykotlinapplication.modules.polling.MockPollingModule
import io.reactivex.schedulers.TestScheduler
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Lewis on 09/02/2018.
 */
class TestBitcoinPriceApp : BitcoinPriceApp() {

    var endpoint: String = "http://localhost"
    var testScheduler: TestScheduler = TestScheduler()

    override fun onCreate() {
        forceInject(endpoint)
    }

    fun forceInject(hostName: String){

        graph = DaggerMockApplicationComponent.builder()
                .appModule(AppModule(this))
                .mockApiModule(MockApiModule(hostName))
                .priceModule(PriceModule())
                .storageModule(StorageModule())
                .mockPollingModule(MockPollingModule(testScheduler))
                .loggingModule(LoggingModule())
                .build()

        graph.inject(this)
        Timber.plant(tree)
    }

}