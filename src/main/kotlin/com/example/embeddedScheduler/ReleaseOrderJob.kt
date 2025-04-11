package com.example.embeddedScheduler

import org.quartz.Job
import org.quartz.JobExecutionContext
import java.time.Instant
import java.util.Date

class ReleaseOrderJob: Job {
    override fun execute(context: JobExecutionContext) {
        val dataMap = context.jobDetail.jobDataMap
        val param1 = dataMap.getString("param1")

        println("${Date.from(Instant.now())} Releasing order... Job: ${ReleaseOrderJob::class.java} Param: $param1")
    }
}