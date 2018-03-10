package com.surge.test.mykotlinapplication

import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import android.support.test.rule.ActivityTestRule
import com.squareup.spoon.Spoon
import com.surge.test.mykotlinapplication.views.PriceActivity
import io.reactivex.schedulers.TestScheduler
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Rule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class PriceActivityInstrumentedTest {

    private var priceActivity: PriceActivity? = null
    lateinit var server: MockWebServer
    lateinit var testScheduler: TestScheduler
    lateinit var app: TestBitcoinPriceApp

    @get:Rule
    var rule: ActivityTestRule<PriceActivity> = ActivityTestRule(PriceActivity::class.java, false, false)

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()

        app = InstrumentationRegistry.getTargetContext().applicationContext as TestBitcoinPriceApp
        app.endpoint = server.url("/").url().toExternalForm()
        testScheduler = app.testScheduler
        app.forceInject(app.endpoint)
    }

    @Test
    @Throws(Exception::class)
    fun testPriceIsShown() {

        priceActivity.let {
            priceActivity?.finish()
        }

        val mockResponse = MockResponse()
                .setResponseCode(200)
                .setBody(getStringFromFile(InstrumentationRegistry.getContext(),
                        "mock_response_success.json"))
        server.enqueue(mockResponse)

        val intent = Intent()
        priceActivity = rule.launchActivity(intent)

        assertNotNull(priceActivity?.priceViewModel)
        assertEquals(priceActivity?.mWorkerFragment?.disposables?.size(), 1)
        Thread.sleep(5000)
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        Thread.sleep(5000)
        Spoon.screenshot(priceActivity, "price_retrieved")
        assertEquals("6,200.6822", priceActivity?.priceViewModel?.getCachedPrice()?.bpi?.GBP?.rate)
        assertEquals("£6200.68", priceActivity?.column_price_gbp?.text)
    }

    @Test
    @Throws(Exception::class)
    fun testErrorIsShown() {

        priceActivity.let {
            priceActivity?.finish()
        }

        val mockResponse = MockResponse().setResponseCode(500)
        server.enqueue(mockResponse)

        val intent = Intent()
        priceActivity = rule.launchActivity(intent)

        assertNotNull(priceActivity?.priceViewModel)
        priceActivity?.priceViewModel?.priceRepository?.emptyRepository()
        assertNull(priceActivity?.priceViewModel?.getCachedPrice())

        Thread.sleep(5000)
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        Spoon.screenshot(priceActivity, "error_shown")
        assertEquals(priceActivity?.mWorkerFragment?.disposables?.size(), 1)
        Espresso.onView(ViewMatchers.withText("Error retrieving price"))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Throws(Exception::class)
    fun getStringFromFile(context: Context, filePath: String): String {
        val stream = context.resources.assets.open(filePath)
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
