package com.example.embeddedScheduler

import java.time.ZonedDateTime

interface ScheduleCreator {
    fun <T: Job> createJob(
        jobClass: Class<T>,
        id: String,
        data: String,
        triggerTime: ZonedDateTime
    )
}