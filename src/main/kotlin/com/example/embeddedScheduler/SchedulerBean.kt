package com.example.embeddedScheduler

import org.quartz.Scheduler
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.quartz.SchedulerFactoryBean

@Bean
fun scheduler(factory: SchedulerFactoryBean): Scheduler {
    // Get the scheduler from the factory bean
    val scheduler = factory.scheduler

    // Start the scheduler
    scheduler.start()

    return scheduler
}
