package com.twobard.btcprice.app.modules.storage

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Lewis on 13/02/2018.
 */
@Module
class StorageModule {

    val PREFS_FILENAME = "com.twobard.btcprice.app"

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesStorage(context: Context, gson: Gson ): StorageInterface {
        return SharedPrefsStorage(context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE), gson)
    }

}