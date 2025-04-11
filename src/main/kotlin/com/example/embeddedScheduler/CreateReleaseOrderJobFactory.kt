package com.example.embeddedScheduler

import org.quartz.JobBuilder
import org.quartz.Scheduler
import org.quartz.TriggerBuilder
import java.time.Instant
import java.util.*

class CreateReleaseOrderJobFactory(private val scheduler: Scheduler) {
    fun createJob() {
        val jobDetail = JobBuilder.newJob(ReleaseOrderJob::class.java)
            .usingJobData("param1", "value1")
            .build()

        val jobStartDateTime = Instant.now().plusSeconds(60)

        val trigger = TriggerBuilder.newTrigger()
            .startAt(Date.from(jobStartDateTime))
            .build()

        // schedule the job
        scheduler.scheduleJob(jobDetail, trigger)
        println("Job Scheduled...")
    }
}