package com.surge.test.mykotlinapplication.modules.storage

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.surge.test.mykotlinapplication.modules.api.Api
import com.surge.test.mykotlinapplication.modules.price.PriceRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Lewis on 13/02/2018.
 */
@Module
class StorageModule {

    val PREFS_FILENAME = "com.surge.test.mykotlinapplication"

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