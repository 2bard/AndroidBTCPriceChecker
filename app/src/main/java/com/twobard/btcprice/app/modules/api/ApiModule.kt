package com.twobard.btcprice.app.modules.api

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
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient().newBuilder().addInterceptor(logger).build()
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