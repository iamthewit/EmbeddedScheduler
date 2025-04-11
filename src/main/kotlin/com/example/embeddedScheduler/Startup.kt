package com.example.embeddedScheduler

import org.quartz.Scheduler
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class Startup(val scheduler: Scheduler) {
    @EventListener(ApplicationReadyEvent::class)
    fun doSomethingAfterStartup() {
        println("hello world, I have just started up")

        val jobFactory = CreateReleaseOrderJobFactory(scheduler)
        for (i in 1..1000) {
            jobFactory.createJob()
        }
    }
}