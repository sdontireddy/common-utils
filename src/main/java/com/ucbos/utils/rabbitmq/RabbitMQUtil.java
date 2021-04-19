package com.ucbos.utils.rabbitmq;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import com.ucbos.utils.interfaces.MQConnector;

public class RabbitMQUtil {
	
	private static Logger LOGGER = Logger.getLogger("RabbitMQUtil.class.getName()");
	
	public static boolean sendDOtoExchange(String path, String fileName, int numberofFilestoProcess, String hostName,
			String userName, String password, String exchange, String queue, String routingKey) throws IOException {

		IntStream.range(1, numberofFilestoProcess).parallel().forEach(counter -> {
			String tempPath = path + counter + fileName;
			try {
				sendDOtoExchange(tempPath, hostName, userName, password, exchange, "", routingKey);
			} catch (Exception e) {
				LOGGER.severe("Exception while publishing to Exchange" + e.getMessage());
			}
		});
		return true;
	}

	public static boolean sendDOtoExchange(String filePath, String hostName, String userName, String password, String exchange,
			String queue, String routingKey) throws IOException, TimeoutException {
		Path path = Paths.get(filePath);
		MQConnector connector = new RabbitMQConnector();

		try {
			byte[] data = Files.readAllBytes(path);

			System.out.println("Reading  File Data " + filePath + "hostName" + hostName + exchange + ":" + routingKey);
			connector.getConnection(hostName, userName, password, exchange, "", routingKey);
			connector.write(data);

		} catch (IOException e) {
			LOGGER.severe("IO Exception while publishing the file " + path + " to Exchange ");
			throw e;
		}
		return true;
	}
}
