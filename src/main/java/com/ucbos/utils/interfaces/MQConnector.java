package com.ucbos.utils.interfaces;

import java.util.logging.Logger;

public abstract class MQConnector implements QCClientConnector {

	private static Logger LOGGER = Logger.getLogger("MQConnector.class.getName()");

	@Override
	public boolean getConnection() {

		LOGGER.info("This is a rabbit MQ , please enter the hostname and other details");
		return false;
	}

	public boolean getConnection(String hostName, String userName, String password, String exchange, String queue,
			String routingKey) {

		System.out.println("Get the MQ Connection");
		return true;

	}

	
}
