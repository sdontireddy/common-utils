package com.ucbos.utils.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

public class RabbitMQSubscriber {

	private final static String QUEUE_NAME = "hello";
	private final static String HOSTNAME = "localhost";
	private final static String EXCHANGE = "DistributionOrder.Exchange";
	private final static String USERNAME = "guest";
	private final static String PASSWORD = "guest";
	private final static String ROUTINGKEY = "v1";
	private final static String ExCHANGE_QUEUE = "DistributionOrder.Queue";

  public static void main(String[] argv) throws Exception {
	  
	
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    //channel.queueDeclare(ExCHANGE_QUEUE, false, false, false, null);
    
   // channel.exchangeDeclare(EXCHANGE, "topic",true);
   // String queueName = channel.queueDeclare().getQueue();
  /// String queueName = channel.queueDeclare(queue, durable, exclusive, autoDelete, arguments)clare().getQueue();
   // System.out.println("queue name " + queue);
  //  channel.queueBind(queueName, EXCHANGE, "#");
    
    System.out.println(" [*] Waiting for messages..... To exit press CTRL+C");

    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
          throws IOException {
        String message = new String(body, "UTF-8");
        System.out.println(" [x] Received '" + message + "'");
      }
    };
    System.out.println("Consuming");
    channel.basicConsume(ExCHANGE_QUEUE, true, consumer);
  }
}