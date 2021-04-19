package com.ucbos.utils.rabbitmq;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ucbos.utils.rabbitmq.RabbitMQClient;


/**
 * Util
 * 
 * @author sdontireddy
 *
 */
public class RabbitMQClientTest {

	private final static String QUEUE_NAME = "hello";
	private final static String HOSTNAME = "localhost";
	private final static String EXCHANGE = "DistributionOrder.Exchange";
	private final static String USERNAME = "guest";
	
	private final static String PASSWORD = "guest";
	private final static String ROUTINGKEY = "1.v2";
	
	private RabbitMQClient rabbitClient ;
	
	@Before
	public void setUp() throws Exception {
		 rabbitClient = new RabbitMQClient(HOSTNAME, USERNAME, PASSWORD, EXCHANGE, QUEUE_NAME, ROUTINGKEY);
	}

	@Test
	public void testPublishToExchange() throws Exception {
		rabbitClient.publishToExchange("Updated message 3 changed 3");
		//assertT
		
	}
	
	@Test
	public void testPublishToQueue() throws Exception {
		rabbitClient.publishToQueue("Updated message from Queue changed");
	}
	
	
	@After
	public void tearDown() throws Exception {
	
	}

}