package com.example.embeddedScheduler

import org.quartz.Job
import org.quartz.JobExecutionContext
import java.time.Instant
import java.util.Date

class GenericScheduledJob: Job {
    /**
     * This method is called when the job is executed.
     * It retrieves the job data from the context and prints a message with the current date,
     * job class, job ID, and data passed to the job.
     *
     * @param context The context in which the job is executed.
     */
    override fun execute(context: JobExecutionContext) {
        val dataMap = context.jobDetail.jobDataMap
        val id = context.jobDetail.key.name
        val data = dataMap.getString("data")

        println("${Date.from(Instant.now())} Releasing order... Job: ${GenericScheduledJob::class.java} ID: $id, Param: $data")
    }
}