package com.example.embeddedScheduler

import org.quartz.JobBuilder
import org.quartz.Scheduler
import org.quartz.TriggerBuilder
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.*

@Component
class QuartzScheduleCreator(private val scheduler: Scheduler) : ScheduleCreator {
    override fun <T : Job> createJob(jobClass: Class<T>, id: String, data: String, triggerTime: ZonedDateTime) {
        val jobDetail = JobBuilder.newJob(jobClass)
            .withIdentity(id)
            .usingJobData("data", data)
            .build()

        val trigger = TriggerBuilder.newTrigger()
            .startAt(Date.from(triggerTime.toInstant()))
            .build()

        // schedule the job
        scheduler.scheduleJob(jobDetail, trigger)
        println("Job Scheduled...")
    }
}