package simpleconsumer;

public class App
{
    public static void main( String[] args )
    {
        if (args.length != 5){
            throw new IllegalArgumentException("Please provide the following arguments: bootstrap server, consumer group ID, the topic to subscribe to and the consumer name.");
        }

        String bootstrapServer = args[0];
        String consumerGroupId = args[1];
        String topic = args[2];
        String consumerName = args[3];

        Consumer consumer = new Consumer(bootstrapServer, consumerGroupId, topic, consumerName);
        consumer.start();
    }
}
