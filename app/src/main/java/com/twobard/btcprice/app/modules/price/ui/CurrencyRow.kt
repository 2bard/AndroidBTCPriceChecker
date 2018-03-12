package com.twobard.btcprice.app.modules.price.ui

import android.content.Context
import android.text.Html
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.*
import com.twobard.btcprice.app.R
import com.twobard.btcprice.app.modules.price.PriceResponse
import kotlinx.android.synthetic.main.tablerow_currency_partial.column_currency as currencyView
import kotlinx.android.synthetic.main.tablerow_currency_partial.column_price as currencyPrice
import kotlinx.android.synthetic.main.tablerow_currency_partial.view.*

/**
 * Created by Lewis on 19/02/2018.
 */
class CurrencyRow : FrameLayout {

    var view: View
    var currency: PriceResponse.Currency

    constructor(context: Context, attrs: AttributeSet?, currency: PriceResponse.Currency) : super(context, attrs) {
        view = LayoutInflater.from(context).inflate(R.layout.tablerow_currency_partial, null, false)
        this.addView(view)
        this.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        this.currency = currency
        update(this.currency)
    }

    fun buildTableRow(currency: PriceResponse.Currency) : String {
        val symbol = Html.fromHtml(currency.symbol)
        val rate = "%.2f".format(currency.rate_float)
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

    fun update(currency: PriceResponse.Currency){
        view.column_price.fadeText(buildTableRow(currency))
        view.column_currency.fadeText(currency.code)
    }
}