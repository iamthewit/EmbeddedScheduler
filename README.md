# Embedded Scheduler

Sample project using the Quartz library to create an embedded scheduler.

Specifically wanted to test Quartz's clustering capabilities. To see it in action, build the images then deploy to minikube. Once it's up and running adjust the replicas and check the app instance logs to see where jobs are being triggered.