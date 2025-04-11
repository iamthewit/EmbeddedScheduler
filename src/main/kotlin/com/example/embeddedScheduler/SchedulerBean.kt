package com.example.embeddedScheduler

import org.quartz.Scheduler
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.quartz.SchedulerFactoryBean

@Bean
fun scheduler(factory: SchedulerFactoryBean): Scheduler {
    val scheduler = factory.scheduler;
    scheduler.start();

    return scheduler;
}
