package simpleconsumer;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

public class Consumer 
{
    public static void main( String[] args )
    {
        if (args.length != 5){
            throw new IllegalArgumentException("Please provide the following arguments: bootstrap server, consumer group ID, the topic to subscribe to, the consumer name and whether to enable auto committing.");
        }

        String bootstrapServer = args[0];
        String consumerGroupId = args[1];
        String topic = args[2];
        String consumerName = args[3];
        Boolean isAutoCommit = Boolean.parseBoolean(args[4]);

        // Consumer Configuration
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);        
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        // Auto Commit Configuration
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, isAutoCommit);
        if (isAutoCommit) 
            props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // Subscribte to Kafka Topic
        consumer.subscribe(Arrays.asList(topic));

        // Poll for events and consume them
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.printf("%s received: %s%n", consumerName, record.value());
            }

            // Manually commit offset if it isn't handled automatically
            if (!isAutoCommit)
                consumer.commitSync();
        }
    }
}
