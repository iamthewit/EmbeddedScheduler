package com.example.embeddedScheduler

import java.time.ZonedDateTime

class AmazonEventBridgeScheduleCreator:ScheduleCreator {
    override fun <T : Job> createJob(jobClass: Class<T>, id: String, data: String, triggerTime: ZonedDateTime) {
        // TODO
    }
}