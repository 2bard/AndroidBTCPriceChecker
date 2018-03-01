package com.surge.test.mykotlinapplication.modules.polling

/**
 * Created by Lewis on 16/02/2018.
 */
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import java.util.concurrent.TimeUnit

/**
 * Created by Lewis on 15/02/2018.
 */
class MockPollEmitter(private val scheduler: TestScheduler): PollEmitterInterface {

    override fun getPollEmitter(): Observable<Long> {
        return Observable.interval(5,5, TimeUnit.SECONDS).observeOn(scheduler)
    }
}
