package com.example.embeddedScheduler

import org.quartz.Scheduler
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.UUID

@Component
class Startup(
    val scheduler: Scheduler,
//    @Qualifier("amazonEventBridgeScheduleCreator"
    @Qualifier("quartzScheduleCreator"
    ) val scheduleCreator: ScheduleCreator) {
    @EventListener(ApplicationReadyEvent::class)
    fun doSomethingAfterStartup() {
        println("hello world, I have just started up")

        for (i in 1..5) {
            scheduleCreator.createJob(
                ReleaseOrderJob::class.java,
                UUID.randomUUID().toString(),
                "some string data",
                ZonedDateTime.now().plusSeconds(10)
            )
        }
    }
}