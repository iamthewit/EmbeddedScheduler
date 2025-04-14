package com.example.embeddedScheduler

import org.quartz.JobExecutionContext
import java.time.Instant
import java.util.Date

class ReleaseOrderJob: Job {
    override fun execute(context: JobExecutionContext) {
        val dataMap = context.jobDetail.jobDataMap
        val id = context.jobDetail.key.name
        val data = dataMap.getString("data")

        println("${Date.from(Instant.now())} Releasing order... Job: ${ReleaseOrderJob::class.java} ID: $id, Param: $data")
    }
}