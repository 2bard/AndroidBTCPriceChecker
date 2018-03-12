package com.twobard.btcprice.app

import com.twobard.btcprice.app.DaggerMockApplicationComponent
import com.twobard.btcprice.app.modules.AppModule
import com.twobard.btcprice.app.modules.MockApiModule
import com.twobard.btcprice.app.modules.price.PriceModule
import com.twobard.btcprice.app.modules.storage.StorageModule
import com.twobard.btcprice.app.modules.logging.LoggingModule
import com.twobard.btcprice.app.modules.polling.MockPollingModule
import io.reactivex.schedulers.TestScheduler
import timber.log.Timber

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