package com.example.embeddedScheduler

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class EmbeddedSchedulerApplication

fun main(args: Array<String>) {
	runApplication<EmbeddedSchedulerApplication>(*args)
}

/**
 * Some minor testing shows that with a large number of schedules triggering at the same time you will be
 * bottle necked by the DB due to a large amount of read/write.
 */