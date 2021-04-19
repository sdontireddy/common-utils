package com.ucbos.utils.rabbitmq;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ucbos.utils.rabbitmq.RabbitJavaClient;
import com.ucbos.utils.rabbitmq.RabbitMQClient;


/**
 * Util
 * 
 * @author sdontireddy
 *
 */
public class RabbitJavaClientTest {

	/*private final static String QUEUE_NAME = "hello";
	private final static String HOSTNAME = "localhost";
	private final static String EXCHANGE = "DistributionOrder.Exchange";
	private final static String USERNAME = "guest";
	
	private final static String PASSWORD = "guest";
	private final static String ROUTINGKEY = "1.v2";*/
	
	private RabbitJavaClient rabbitClient ;
	
	@Before
	public void setUp() throws Exception {
		 
	}


	
	@Test
	@Ignore
	public void testPublishToQueue() throws Exception {
		rabbitClient =RabbitJavaClient.getRabbitJavaClient("host", "servicebus", "servicebus");
		rabbitClient.publishToQueue("queuename","SD message from Queue changed");
	}
	
	
	@After
	public void tearDown() throws Exception {
		rabbitClient.close();
	}

}