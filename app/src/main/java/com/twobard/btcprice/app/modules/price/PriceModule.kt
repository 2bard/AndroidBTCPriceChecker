package com.twobard.btcprice.app.modules.price

import com.twobard.btcprice.app.modules.api.Api
import com.twobard.btcprice.app.modules.polling.PollEmitterInterface
import com.twobard.btcprice.app.modules.storage.StorageInterface
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Lewis on 12/02/2018.
 */
@Module
class PriceModule {

    @Provides
    @Singleton
    fun providesPriceRepository(api: Api, storageInterface: StorageInterface): PriceRepository {
        return PriceRepository(api, storageInterface)
    }

    @Provides
    @Singleton
    fun providesPriceViewModel(priceRepository: PriceRepository,
                               pollEmitterInterface: PollEmitterInterface): PriceActivityViewModel {
        return PriceActivityViewModel(priceRepository, pollEmitterInterface)
    }

}