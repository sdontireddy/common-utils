package com.ucbos.utils.rabbitmq;

import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.ucbos.utils.interfaces.MQConnector;
/**
 * 
 * @author sdontireddy
 *
 */
public class RabbitMQConnector extends MQConnector {
	private static Logger LOGGER = Logger.getLogger("RabbitMQConnector.class.getName()");

	private CachingConnectionFactory connectioFactory;
	private RabbitAdmin admin;
	private RabbitTemplate template;
	private String exchange;
	private String queue;
	private String routingKey;

	@Override
	public boolean getConnection(String hostName, String userName, String password, String exchange, String queue,
			String routingKey) {
		connectioFactory = new CachingConnectionFactory();
		connectioFactory.setHost(hostName);
		connectioFactory.setUsername(userName);
		connectioFactory.setPassword(password);

		admin = new RabbitAdmin(connectioFactory);
		template = new RabbitTemplate(connectioFactory);
		setExchange(exchange);
		setQueue(queue);
		setRoutingKey(routingKey);
		return false;
	}

	@Override
	public boolean read() {

		return false;
	}

	@Override
	public boolean write(String message) throws IOException {
		publishToExchange(message);
		return true;
	}

	public boolean write(byte[] data) throws IOException {
		publishToExchange(data);
		return true;

	}

	public void publishToExchange(byte[] message) throws IOException {
		System.out.println("Message Sent to " + getExchange() + "::" + getRoutingKey());
		getTemplate().convertAndSend(getExchange(), getRoutingKey(), message);

		LOGGER.info("Message Sent to exchange : " + message + "'");
	}

	public void publishToExchange(String message) throws IOException {
		try {
			System.out.println("Message Sent to " + getExchange() + "::" + getRoutingKey());

			getTemplate().convertAndSend(getExchange(), getRoutingKey(), message.getBytes("UTF-8"));

		} catch (IOException e) {
			throw e;
		}
	}

	public void publishToQueue(String message) throws IOException {
		getTemplate().convertAndSend(getQueue(), message);
		LOGGER.info("Message Sent to Queue : " + message + "'");
	}

	public boolean receive(String queueName) throws Exception {
		// set up the listener and container

		return true;
	}

	public void close() {
		connectioFactory.clearConnectionListeners();
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

	public RabbitTemplate getTemplate() {
		return template;
	}

	public void setTemplate(RabbitTemplate template) {
		this.template = template;
	}

	public RabbitAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(RabbitAdmin admin) {
		this.admin = admin;
	}

}
