package com.surge.test.mykotlinapplication.modules.price

import com.surge.test.mykotlinapplication.modules.api.Api
import com.surge.test.mykotlinapplication.modules.polling.PollEmitterInterface
import com.surge.test.mykotlinapplication.modules.storage.StorageInterface
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