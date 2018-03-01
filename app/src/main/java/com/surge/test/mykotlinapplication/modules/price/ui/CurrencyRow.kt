package com.surge.test.mykotlinapplication.modules.price.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.surge.test.mykotlinapplication.R
import kotlinx.android.synthetic.main.tablerow_currency_partial.column_currency as currencyView
import kotlinx.android.synthetic.main.tablerow_currency_partial.column_price as currencyPrice
import kotlinx.android.synthetic.main.tablerow_currency_partial.view.*

/**
 * Created by Lewis on 19/02/2018.
 */
class CurrencyRow : TableRow {

    var view: View
    var priceText : TextView? = null
    var currencyText : TextView? = null

    constructor(context: Context, attrs: AttributeSet?, tableRoot: TableLayout, price: String, currency: String) : super(context, attrs) {
        view = LayoutInflater.from(context).inflate(R.layout.tablerow_currency_partial, tableRoot, true)

        this.layoutParams = TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
       // currencyView.setText("Hey")
        //currencyPrice.text = "currency"
    }

}