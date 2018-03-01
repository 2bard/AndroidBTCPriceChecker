package com.surge.test.mykotlinapplication

import com.surge.test.mykotlinapplication.modules.MockApiModule
import com.surge.test.mykotlinapplication.modules.AppModule
import com.surge.test.mykotlinapplication.modules.ApplicationComponent
import com.surge.test.mykotlinapplication.modules.logging.LoggingModule
import com.surge.test.mykotlinapplication.modules.polling.MockPollingModule
import com.surge.test.mykotlinapplication.modules.price.PriceModule
import com.surge.test.mykotlinapplication.modules.storage.StorageModule
import com.surge.test.mykotlinapplication.views.PriceActivity
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