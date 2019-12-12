:: Build Docker Images
docker build -f Dockerfile -t simple-consumer .

:: Start Consumer
kubectl apply -f consumer-deployment.yml -n keda-sample
kubectl apply -f consumer-service.yml -n keda-sample
