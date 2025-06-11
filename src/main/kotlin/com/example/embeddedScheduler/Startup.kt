package com.example.embeddedScheduler

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.UUID

@Component
class Startup(val scheduleCreator: QuartzScheduleCreator) {
    /**
     * This method is called after the application has started.
     * It schedules 5 jobs of type ReleaseOrderJob to run 30 seconds after startup.
     */
    @EventListener(ApplicationReadyEvent::class)
    fun doSomethingAfterStartup() {
        println("hello world, I have just started up")

        for (i in 1..5) {
            scheduleCreator.createJob(
                GenericScheduledJob::class.java,
                UUID.randomUUID().toString(),
                "some string data",
                ZonedDateTime.now().plusSeconds(30)
            )
        }
    }
}