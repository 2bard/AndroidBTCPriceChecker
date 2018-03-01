package com.surge.test.mykotlinapplication.modules

import com.surge.test.mykotlinapplication.BitcoinPriceApp
import com.surge.test.mykotlinapplication.modules.api.ApiModule
import com.surge.test.mykotlinapplication.modules.polling.PollingModule
import com.surge.test.mykotlinapplication.modules.price.PriceModule
import com.surge.test.mykotlinapplication.modules.storage.StorageModule
import com.surge.test.mykotlinapplication.modules.logging.LoggingModule
import com.surge.test.mykotlinapplication.views.PriceActivity
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