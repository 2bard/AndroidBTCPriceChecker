package com.twobard.btcprice.app.views

import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.twobard.btcprice.app.BitcoinPriceApp
import com.twobard.btcprice.app.DataFragment
import com.twobard.btcprice.app.R
import com.twobard.btcprice.app.ValueChangeListener
import com.twobard.btcprice.app.modules.price.PriceActivityViewModel
import com.twobard.btcprice.app.modules.price.ui.CurrencyRow
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class PriceActivity : MVVMActivity(), ValueChangeListener {

    @Inject
    lateinit var priceViewModel: PriceActivityViewModel

    private val TAG_WORKER_FRAGMENT: String = "FRAGMENT_WORKER"
    var mWorkerFragment: DataFragment? = null
    var rows = HashMap<String, CurrencyRow>()

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
    }

    fun getBitcoinPriceApplication(): BitcoinPriceApp {
        return application as BitcoinPriceApp
    }

    override fun errorOccured() {
        Snackbar.make(container, getString(R.string.error_retrieving), Snackbar.LENGTH_SHORT ).show()
    }

    override fun valuesChanged() {
        priceViewModel.priceResponse?.bpi?.forEach { code , details ->
            if(rows.containsKey(code)){
                rows[code]?.update(details)
            } else {
                addCurrencyRowToTable(code, CurrencyRow(this, null, details ))
            }
        }

        textview_last_updated.text = getDate()
        frame_logo.startAnimating()
    }

    fun addCurrencyRowToTable(code: String, currencyRow: CurrencyRow){
        rows.put(code, currencyRow)
        table_currency.addView(currencyRow)
    }

    fun getDate(): String{
        val sdf = SimpleDateFormat("MM/dd/yyyy HH:mm:ss", getCurrentLocale())
        val netDate = Date(System.currentTimeMillis())
        return String.format(Locale.ENGLISH,"Last updated: %s", sdf.format(netDate))
    }

    override fun onResume() {
        super.onResume()

        mWorkerFragment?.addDisposble(
                priceViewModel.startPolling(this)
        )
    }

    fun getCurrentLocale(): Locale {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            resources.configuration.locales.get(0)
        } else {
            resources.configuration.locale
        }
    }
}
