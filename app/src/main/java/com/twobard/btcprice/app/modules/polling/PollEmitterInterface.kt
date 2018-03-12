package com.twobard.btcprice.app.modules.polling

import io.reactivex.Observable

/**
 * Created by Lewis on 15/02/2018.
 */
interface PollEmitterInterface {
    fun getPollEmitter(): Observable<Long>
}