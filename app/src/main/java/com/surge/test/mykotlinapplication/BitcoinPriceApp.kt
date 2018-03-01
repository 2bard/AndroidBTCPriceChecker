package com.surge.test.mykotlinapplication

import android.app.Application
import android.content.Context
import com.surge.test.mykotlinapplication.modules.api.ApiModule
import com.surge.test.mykotlinapplication.modules.AppModule
import com.surge.test.mykotlinapplication.modules.ApplicationComponent
import com.surge.test.mykotlinapplication.modules.DaggerApplicationComponent
import com.surge.test.mykotlinapplication.modules.logging.LoggingModule
import com.surge.test.mykotlinapplication.modules.polling.PollingModule
import com.surge.test.mykotlinapplication.modules.price.PriceModule
import com.surge.test.mykotlinapplication.modules.storage.StorageModule
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