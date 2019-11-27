## Create Image
FROM strimzi/kafka:0.11.3-kafka-2.1.0

EXPOSE 8090

CMD ["sh", "-c", "bin/kafka-console-consumer.sh --bootstrap-server kafka-cluster-kafka-bootstrap.keda-sample:9092 --topic messages --from-beginning --consumer-property group.id=testSample"]
