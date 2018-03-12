package com.twobard.btcprice.app

import android.app.Application
import android.content.Context
import com.twobard.btcprice.app.modules.api.ApiModule
import com.twobard.btcprice.app.modules.AppModule
import com.twobard.btcprice.app.modules.ApplicationComponent
import com.twobard.btcprice.app.modules.DaggerApplicationComponent
import com.twobard.btcprice.app.modules.logging.LoggingModule
import com.twobard.btcprice.app.modules.polling.PollingModule
import com.twobard.btcprice.app.modules.price.PriceModule
import com.twobard.btcprice.app.modules.storage.StorageModule
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Lewis on 09/02/2018.
 */
open class BitcoinPriceApp : Application() {

    @Inject lateinit var context: Context
    @Inject lateinit var tree: Timber.Tree
    lateinit var graph: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        graph = DaggerApplicationComponent.builder()
                .appModule(AppModule(this))
                .apiModule(ApiModule("https://api.coindesk.com"))
                .priceModule(PriceModule())
                .storageModule(StorageModule())
                .pollingModule(PollingModule())
                .loggingModule(LoggingModule())
                .build()

        graph.inject(this)
        Timber.plant(tree)
    }
}