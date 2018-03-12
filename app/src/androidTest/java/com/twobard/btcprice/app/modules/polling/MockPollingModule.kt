package com.twobard.btcprice.app.modules.polling

import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.TestScheduler
import javax.inject.Singleton

/**
 * Created by Lewis on 12/02/2018.
 */
@Module
open class MockPollingModule(val testScheduler: TestScheduler) {

    @Provides
    @Singleton
    fun providesPollEmitter(): PollEmitterInterface {
        return MockPollEmitter(testScheduler)
    }

}