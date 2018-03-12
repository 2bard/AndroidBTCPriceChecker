package com.twobard.btcprice.app.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Lewis on 12/02/2018.
 */
@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = app
}