package com.ucbos.utils.rabbitmq;

import org.junit.Test;

import com.ucbos.utils.interfaces.MQConnector;
import com.ucbos.utils.rabbitmq.RabbitMQConnector;

public class QCClientConnectorTest {
	private final static String HOSTNAME = "localhost";
	private final static String EXCHANGE = "DistributionOrder.Exchange";
	private final static String USERNAME = "guest";
	private final static String PASSWORD = "guest";
	private final static String ROUTINGKEY = "v1";
	
	@Test
	public void testRabbitMQClientConnector() throws Exception{
		MQConnector connector = new RabbitMQConnector();
		connector.getConnection(HOSTNAME, USERNAME, PASSWORD, EXCHANGE, "", ROUTINGKEY);
		connector.write("Helloooo");
	}
	
	
}

