package com.ucbos.utils.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * An utility useful to get the RabbitMQ connection , publish and subscribe to a
 * specific queue
 * 
 * @author sdontireddy
 *
 */
public class RabbitJavaClient2 {

	private static Logger LOGGER = Logger.getLogger("RabbitClient.class.getName()");
	private ConnectionFactory connectioFactory;
	private Connection connection;
	private Channel channel;
	private String exchange;
	private String queue;
	private String routingKey;
    private static RabbitJavaClient2 rabbitJavaClient = null; 

	public static RabbitJavaClient2 getRabbitJavaClient(String hostName, String userName, String password) throws Exception{
	
		 if (rabbitJavaClient == null) 
			 rabbitJavaClient = new RabbitJavaClient2(hostName , userName , password); 
	  
	        return rabbitJavaClient; 

		
	}

	public RabbitJavaClient2(String hostName, String userName, String password) throws IOException, TimeoutException {

		connectioFactory = new ConnectionFactory();
		connectioFactory.setHost(hostName);
		try {

			connectioFactory.setHost(hostName);

			connectioFactory.setUsername(userName);
			connectioFactory.setPassword(password);
			connection = connectioFactory.newConnection();
			Channel channel = connection.createChannel();
			setChannel(channel);

		} catch (IOException | TimeoutException e) {
			LOGGER.severe("IOException " + e.getMessage());
			e.printStackTrace();

			throw e;
		}

	}
	
	
	
	public void publishToExchange(byte[] message) throws IOException {
		try {
			//getChannel().queueDeclare(getQueue(), false, false, false, null);
			
			getChannel().basicPublish(getExchange(),getRoutingKey(), null, message);
			LOGGER.info("Message Sent to exchange : " + message + "'");
		} catch (IOException e) {
			throw e;
		}
	}
	
	public void publishToExchange(String message) throws IOException {
		try {
			publishToExchange(message.getBytes("UTF-8"));
		} catch (IOException e) {
			throw e;
		}
	}
	
	public void publishToQueue(String queueName , String message) throws IOException {
		try {
			//getChannel().queueDeclare(getQueue(), false, false, false, null);
			channel.basicPublish("", queueName, null, message.getBytes());

			//getChannel().basicPublish("", queueName, null, message.getBytes("UTF-8"));
			LOGGER.info("Message Sent to Queue : "+queueName + message + "'");
		} catch (IOException e) {
			throw e;
		}
	}

	public void receive() throws Exception {

		getChannel().queueDeclare(getQueue(), false, false, false, null);
		System.out.println("Consuming....");
		getChannel().basicConsume(getQueue(), true, newConsumer(getChannel()));
		System.out.println("Completed consuming");
	}

	private DefaultConsumer newConsumer(Channel channel) {
		return new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				  String message = new String(body, "UTF-8");
				System.out.println("Message Recieved" +message);
			}
		};
	}
  
	public void closeConnection() {
		try {
			System.out.println("Close All COnnections");
			
			getChannel().close();
			connection.close();
			
		} catch (Exception e) {
			LOGGER.severe("Exception while closing the RabbitMQ connection " + e.getMessage());
			//throw e;
		}

	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}
	
	public void close(){
		this.closeConnection();
	}
}
