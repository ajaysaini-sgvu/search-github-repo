package com.asanarebel.android.ui.utils

import com.asanarebel.android.utils.rx.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider constructor(private val testScheduler: TestScheduler) : SchedulerProvider {
    override fun ui(): Scheduler = testScheduler
    override fun computation(): Scheduler = testScheduler
    override fun io(): Scheduler = testScheduler
}
