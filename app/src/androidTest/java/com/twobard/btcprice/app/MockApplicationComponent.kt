package com.twobard.btcprice.app

import com.twobard.btcprice.app.modules.MockApiModule
import com.twobard.btcprice.app.modules.AppModule
import com.twobard.btcprice.app.modules.ApplicationComponent
import com.twobard.btcprice.app.modules.logging.LoggingModule
import com.twobard.btcprice.app.modules.polling.MockPollingModule
import com.twobard.btcprice.app.modules.price.PriceModule
import com.twobard.btcprice.app.modules.storage.StorageModule
import com.twobard.btcprice.app.views.PriceActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Lewis on 12/02/2018.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        MockApiModule::class,
        PriceModule::class,
        StorageModule::class,
        MockPollingModule::class,
        LoggingModule::class))
interface MockApplicationComponent: ApplicationComponent {

    override fun inject(target: BitcoinPriceApp)
    override fun inject(target: PriceActivity)
}