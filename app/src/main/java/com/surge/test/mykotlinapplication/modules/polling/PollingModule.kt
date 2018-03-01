package com.surge.test.mykotlinapplication.modules.polling

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Lewis on 12/02/2018.
 */
@Module
open class PollingModule{

    @Provides
    @Singleton
    fun providesPollEmitter(): PollEmitterInterface {
        return PollEmitter()
    }

}