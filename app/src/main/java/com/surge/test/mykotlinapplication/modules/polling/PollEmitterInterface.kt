package com.surge.test.mykotlinapplication.modules.polling

import io.reactivex.Observable

/**
 * Created by Lewis on 15/02/2018.
 */
interface PollEmitterInterface {
    fun getPollEmitter(): Observable<Long>
}