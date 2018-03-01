package com.surge.test.mykotlinapplication.modules.storage

import android.content.SharedPreferences
import com.google.gson.Gson
import com.surge.test.mykotlinapplication.modules.price.PriceResponse

/**
 * Created by Lewis on 13/02/2018.
 */
class SharedPrefsStorage(val sharedPreferences: SharedPreferences, val gson: Gson): StorageInterface {

    val PREFS_KEY = "PRICE"

    override fun save(priceResponse: PriceResponse):Boolean {
        val editor = sharedPreferences.edit()
        editor.putString(PREFS_KEY, gson.toJson(priceResponse))
        return editor.commit()
    }

    override fun retrieve():PriceResponse? {
        val string = sharedPreferences.getString(PREFS_KEY,null)

        string.let {
            return gson.fromJson(string, PriceResponse::class.java)
        }

        return null
    }

    override fun clear():Boolean {
        return sharedPreferences.edit().clear().commit()
    }
}