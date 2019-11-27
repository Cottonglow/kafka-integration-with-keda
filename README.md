# Kafka integration with Keda
An example repository of using Kafka with Keda hosted on Kubernetes.

# Pre-requisite
* Download Kafka (2.3.1)
* Download the Strimzi Kafka operators (0.14.0)
* Clone the Keda repository (v1.0.0)
* Docker and Kubernetes

# Run the example
1. Start the Kafka and Zookeeper servers
    ```bash
    ./start-kafka.bat
    ```

1. Wait for the Kafka pod to run otherwise the consumer will continuously fail to communicate with Kafka.

1. Deploy the consumer
    ```bash
    ./start-consumer.bat
    ```

1. Deploy Keda
    ```bash
    ./start-keda.bat
    ```

1. Now everything should be setup. You'll notice that the consumer-service pod may not be there as Keda scaled the deployment down to 0 as it isn't being used.

1. Send messages to the consumer.

    ```bash
    cd <KAFKA INSTALL DIRECTORY>\bin\windows
    ./kafka-console-producer.bat --broker-list localhost:32100 --topic messages
    ```
1. You should now see the consumer scaling up as messages come in and down when there are no messages in the queue.
