package com.example.embeddedScheduler

import org.quartz.Job
import org.quartz.JobBuilder
import org.quartz.Scheduler
import org.quartz.TriggerBuilder
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.*

@Component
class QuartzScheduleCreator(private val scheduler: Scheduler) {
    /**
     * This method creates a job of the specified type with the given ID and data,
     * and schedules it to run at the specified trigger time.
     *
     * @param jobClass The class of the job to be scheduled.
     * @param id The unique identifier for the job.
     * @param data The data to be passed to the job.
     * @param triggerTime The time at which the job should be triggered.
     */
    fun <T : Job> createJob(
        jobClass: Class<T>,
        id: String,
        data: String,
        triggerTime: ZonedDateTime)
    {
        // Create a job detail with the specified job class and identity
        val jobDetail = JobBuilder.newJob(jobClass)
            .withIdentity(id)
            .usingJobData("data", data)
            .build()

        // Set the trigger to start at the specified time
        val trigger = TriggerBuilder.newTrigger()
            .startAt(Date.from(triggerTime.toInstant()))
            .build()

        // schedule the job
        scheduler.scheduleJob(jobDetail, trigger)
        println("Job Scheduled...")
    }
}