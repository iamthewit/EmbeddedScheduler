# Minikube

## Run the project inside minikube

Start Minikube:

`minikube start`

Build the application JAR:

Note: You need to have the DB running in docker to build at the moment. 
Haven't figured out how to deal with this yet.

`./gradlew build`

Build and load the Docker image into Minikube:

`minikube image build -t embedded-scheduler:latest .`

Create a ConfigMap for the init script:

`kubectl create configmap init-script --from-file=init.sql=quartz_tables.sql`

Apply the Kubernetes configuration:

`kubectl apply -f k8s-deployment.yaml`

Check the deployment status:

`kubectl get pods`

## Access the PostgreSQL database running inside Minikube

Port-forward the PostgreSQL service to your local machine:

`kubectl port-forward service/postgres 5432:5432`

## Look at the logs of a pod
`kubectl logs -f <pod-name>`

## Delete deployment
`kubectl delete -f k8s-deployment.yaml`