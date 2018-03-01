package com.surge.test.mykotlinapplication.modules.polling

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by Lewis on 15/02/2018.
 */
class PollEmitter:PollEmitterInterface {

    override fun getPollEmitter(): Observable<Long> {
        return Observable.interval(0,10, TimeUnit.SECONDS)
    }
}