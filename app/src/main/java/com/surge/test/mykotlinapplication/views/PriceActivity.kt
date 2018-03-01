package com.surge.test.mykotlinapplication.views

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.text.Html
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import com.surge.test.mykotlinapplication.BitcoinPriceApp
import com.surge.test.mykotlinapplication.DataFragment
import com.surge.test.mykotlinapplication.R
import com.surge.test.mykotlinapplication.ValueChangeListener
import com.surge.test.mykotlinapplication.modules.price.ui.CurrencyRow
import com.surge.test.mykotlinapplication.modules.price.PriceActivityViewModel
import com.surge.test.mykotlinapplication.modules.price.PriceResponse
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.lang.Math.round
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class PriceActivity : MVVMActivity(), ValueChangeListener {

    @Inject
    lateinit var priceViewModel: PriceActivityViewModel

    private val TAG_WORKER_FRAGMENT: String = "FRAGMENT_WORKER"
    var mWorkerFragment: DataFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getBitcoinPriceApplication().graph.inject(this)
        setupFragment()
    }

    fun setupFragment(){
        val fm = supportFragmentManager
        mWorkerFragment = fm.findFragmentByTag(TAG_WORKER_FRAGMENT) as DataFragment?

        mWorkerFragment.let {
            Timber.d("Creating data fragment")
            mWorkerFragment = DataFragment()
            fm.beginTransaction().add(mWorkerFragment, TAG_WORKER_FRAGMENT).commit()
            mWorkerFragment?.viewModel = priceViewModel
        }

        mWorkerFragment?.addSubscription(
                priceViewModel.startPolling(this)
        )
    }

    fun getBitcoinPriceApplication(): BitcoinPriceApp {
        return application as BitcoinPriceApp
    }

    override fun errorOccured() {
        Snackbar.make(container, getString(R.string.error_retrieving), Snackbar.LENGTH_SHORT ).show()
    }

    override fun valuesChanged() {
        column_currency_gbp.text = priceViewModel.priceResponse?.bpi?.GBP!!.code
        column_price_gbp.fadeText(buildTableRow(priceViewModel.priceResponse?.bpi?.GBP!!))
        column_currency_eur.text = priceViewModel.priceResponse?.bpi?.EUR!!.code
        column_price_eur.fadeText(buildTableRow(priceViewModel.priceResponse?.bpi?.EUR!!))
        column_currency_usd.text = priceViewModel.priceResponse?.bpi?.USD!!.code
        column_price_usd.fadeText(buildTableRow(priceViewModel.priceResponse?.bpi?.USD!!))
        textview_last_updated.fadeText(getDate())
        btc_logo_background.startAnimating()
    }

    fun buildTableRow(currency: PriceResponse.Currency) : String {
        val symbol = Html.fromHtml(currency.symbol)
        val df = DecimalFormat("#.##")
        val rate = "%.2f".format(currency.rate_float)
        Timber.d("Returning $symbol$rate")
        return "$symbol$rate"
    }

    inline fun TextView.fadeText(newText: String){
        if(this.text == newText) return

        val anim = AlphaAnimation(1.0f, 0.0f)
        anim.duration = 200
        anim.repeatCount = 1
        anim.repeatMode = Animation.REVERSE

        anim.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationRepeat(p0: Animation?) {
                text = newText
            }

            override fun onAnimationEnd(p0: Animation?) {}
            override fun onAnimationStart(p0: Animation?) {}
        })

        this.startAnimation(anim)
    }

    fun getDate(): String{
        val sdf = SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
        val netDate = Date(System.currentTimeMillis())
        return String.format(Locale.ENGLISH,"Last updated: %s", sdf.format(netDate))
    }
}
