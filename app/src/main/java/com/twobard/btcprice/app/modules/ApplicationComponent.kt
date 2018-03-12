package com.twobard.btcprice.app.modules

import com.twobard.btcprice.app.BitcoinPriceApp
import com.twobard.btcprice.app.modules.api.ApiModule
import com.twobard.btcprice.app.modules.polling.PollingModule
import com.twobard.btcprice.app.modules.price.PriceModule
import com.twobard.btcprice.app.modules.storage.StorageModule
import com.twobard.btcprice.app.modules.logging.LoggingModule
import com.twobard.btcprice.app.views.PriceActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Lewis on 12/02/2018.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        ApiModule::class,
        PriceModule::class,
        StorageModule::class,
        PollingModule::class,
        LoggingModule::class))
interface ApplicationComponent {
    fun inject(target: BitcoinPriceApp)
    fun inject(target: PriceActivity)
}