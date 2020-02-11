package simpleconsumer;

public class App
{
    public static void main( String[] args )
    {
        if (args.length != 6){
            throw new IllegalArgumentException("Please provide the following arguments: bootstrap server, consumer group ID, the topic to subscribe to, the consumer name, kafka user name and the kafka user password.");
        }

        String bootstrapServer = args[0];
        String consumerGroupId = args[1];
        String topic = args[2];
        String consumerName = args[3];
        String kafkaUserName = args[4];
        String kafkaUserPassword = args [5];

        Consumer consumer = new Consumer(bootstrapServer, consumerGroupId, topic, consumerName, kafkaUserName, kafkaUserPassword);
        consumer.start();
    }
}
