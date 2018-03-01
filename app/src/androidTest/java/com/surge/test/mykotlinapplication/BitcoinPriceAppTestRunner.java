package com.surge.test.mykotlinapplication;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;


/**
 * Created by Lewis on 12/02/2018.
 */

public class BitcoinPriceAppTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) {
        try{
            return super.newApplication(cl, TestBitcoinPriceApp.class.getName(), context);
        } catch (Exception e) {
            return null;
        }
    }
}
