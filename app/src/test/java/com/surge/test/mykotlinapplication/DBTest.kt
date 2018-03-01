package com.surge.test.mykotlinapplication

import android.content.Context
import com.google.gson.Gson
import com.surge.test.mykotlinapplication.modules.price.PriceResponse
import com.surge.test.mykotlinapplication.modules.storage.SharedPrefsStorage
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.RuntimeEnvironment
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.robolectric.internal.bytecode.RobolectricInternals.getClassLoader
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


/**
 * Created by Lewis on 14/02/2018.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class DBTest {

    val gson = Gson()
    lateinit var sharedPreferencesHelper: SharedPrefsStorage

    @Before
    fun setUp() {
        val sharedPreferences = RuntimeEnvironment.application.getSharedPreferences("com.surge.test.mykotlinapplication", Context.MODE_PRIVATE)
        sharedPreferencesHelper = SharedPrefsStorage(sharedPreferences, gson)
    }

    @Test
    @Throws(Exception::class)
    fun testSaveAndRetrieve() {
        val priceResponseString = getStringFromFile("mock_response_success.json")
        val priceResponse = gson.fromJson(priceResponseString, PriceResponse::class.java)
        val result = sharedPreferencesHelper.save(priceResponse)
        assertTrue(result)

        val retrievedPriceResponse = sharedPreferencesHelper.retrieve()

        assertNotNull(retrievedPriceResponse)
        assertNotNull(retrievedPriceResponse?.disclaimer)
        assertNotNull(retrievedPriceResponse?.chartName)
        assertNotNull(retrievedPriceResponse?.disclaimer)
        assertNotNull(retrievedPriceResponse?.bpi)
        assertNotNull(retrievedPriceResponse?.time)

        assertEquals(retrievedPriceResponse?.time, priceResponse?.time)
        assertEquals(retrievedPriceResponse?.disclaimer, priceResponse?.disclaimer)
        assertEquals(retrievedPriceResponse?.chartName, priceResponse?.chartName)
        assertEquals(retrievedPriceResponse?.bpi, priceResponse?.bpi)

        sharedPreferencesHelper.clear()
        assertNull(sharedPreferencesHelper.retrieve())
    }

    @Throws(Exception::class)
    fun getStringFromFile(filePath: String): String {
        val stream = getClassLoader().getResourceAsStream(filePath)
        val ret = convertStreamToString(stream)
        stream.close()
        return ret
    }

    @Throws(Exception::class)
    fun convertStreamToString(`is`: InputStream): String {
        val reader = BufferedReader(InputStreamReader(`is`))
        val sb = StringBuilder()

        reader.forEachLine {
            sb.append(it).append("\n")
        }
        reader.close()
        return sb.toString()
    }
}