package com.example.embeddedScheduler

import org.springframework.stereotype.Component
import software.amazon.awssdk.services.scheduler.SchedulerAsyncClient
import software.amazon.awssdk.services.scheduler.model.*
import software.amazon.awssdk.services.scheduler.model.Target.*
import java.time.ZonedDateTime
import java.util.concurrent.CompletionException


// example taken from : https://github.com/awsdocs/aws-doc-sdk-examples/blob/main/javav2/example_code/scheduler/src/main/java/com/example/eventbrideschedule/scenario/EventbridgeSchedulerActions.java#L39
@Component
class AmazonEventBridgeScheduleCreator:ScheduleCreator {
    override fun <T : Job> createJob(jobClass: Class<T>, id: String, data: String, triggerTime: ZonedDateTime) {
        val schedulerClient = SchedulerAsyncClient.create()

        val targetArn = "targetArn" // the Amazon Resource Name (ARN) of the target task
        val roleArn = "roleArn" // the ARN of the IAM role to be used for the schedule
        val input = data // the input data for the target task
        // TODO: combine data with jobClass so that the eventual SQS message we receive will contain the information of which job to execute

        // This would be our SQS queue
        val target = builder()
            .arn(targetArn)
            .roleArn(roleArn)
            .input(input)
            .build()

        // Do we want flexible time windows?
        // https://docs.aws.amazon.com/scheduler/latest/UserGuide/managing-schedule-flexible-time-windows.html
        val useFlexibleTimeWindow = false // whether to use a flexible time window for the schedule execution
        val flexibleTimeWindowMinutes = 15
        val flexibleTimeWindow = FlexibleTimeWindow.builder()
            .mode(
//                if (useFlexibleTimeWindow)
//                    FlexibleTimeWindowMode.FLEXIBLE
//                else
                    FlexibleTimeWindowMode.OFF
            )
//            .maximumWindowInMinutes(
//                if (useFlexibleTimeWindow)
//                    flexibleTimeWindowMinutes
//                else
//                    null
//            )
            .build()

        val scheduleRequest = CreateScheduleRequest.builder()
            .name("Schedule Name: $id")
            .scheduleExpression("at(${triggerTime})") // "at(yyyy-mm-ddThh:mm:ss)"
//            .groupName("Schedule Group Name") // TBD
            .target(target)
            .actionAfterCompletion(ActionAfterCompletion.DELETE)
//            .startDate() // EventBridge Scheduler ignores StartDate for one-time schedules
//            .endDate() // EventBridge Scheduler ignores EndDate for one-time schedules
            .flexibleTimeWindow(flexibleTimeWindow)
            .build()

        schedulerClient.createSchedule(scheduleRequest)
            .thenApply { response ->
                println("Successfully created schedule")
            }
            .whenComplete({ result, ex ->
                if (ex != null) {
                    if (ex is ConflictException) {
                        // Handle ConflictException
                        println("A conflict exception occurred while creating the schedule: ${ex.message}")
                        throw CompletionException(
                            "A conflict exception occurred while creating the schedule: " + ex.message,
                            ex
                        )
                    } else {
                        throw CompletionException("Error creating schedule: " + ex.message, ex)
                    }
                }
            })
    }
}