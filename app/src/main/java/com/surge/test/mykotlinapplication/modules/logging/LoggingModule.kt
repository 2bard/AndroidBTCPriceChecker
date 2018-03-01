package com.surge.test.mykotlinapplication.modules.logging

import dagger.Module
import dagger.Provides
import timber.log.Timber
import javax.inject.Singleton

/**
 * Created by Lewis on 19/02/2018.
 */
@Module
class LoggingModule {

    @Provides
    @Singleton
    fun providesLoggingTree(): Timber.Tree {
        return Timber.DebugTree()
    }

}