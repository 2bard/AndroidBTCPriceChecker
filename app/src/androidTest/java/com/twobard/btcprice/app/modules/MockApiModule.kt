package com.twobard.btcprice.app.modules

import com.twobard.btcprice.app.modules.api.Api
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Created by Lewis on 12/02/2018.
 */
@Module
class MockApiModule(private val hostname: String) {

    @Provides
    @Singleton
    fun providesApi(): Api {

        var retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(hostname)
                .build()

        return retrofit.create(Api::class.java)
    }

}