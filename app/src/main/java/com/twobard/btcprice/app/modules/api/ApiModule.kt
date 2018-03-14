package com.twobard.btcprice.app.modules.api

import android.util.Log
import com.twobard.btcprice.app.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Lewis on 12/02/2018.
 */
@Module
class ApiModule(private val baseUrl: String) {

    @Provides
    @Singleton
    fun providesOkhttp(): OkHttpClient {

        if(BuildConfig.ENABLE_HTTP_LOGGING){
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            Log.i("HERE", "1")
            return OkHttpClient().newBuilder().addInterceptor(logger).build()
        } else {
            Log.i("HERE", "2")
            return OkHttpClient().newBuilder().build()
        }
    }

    @Provides
    @Singleton
    fun providesRetrofit(okhttp: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okhttp)
                .baseUrl(baseUrl)
                .build()
    }

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

}