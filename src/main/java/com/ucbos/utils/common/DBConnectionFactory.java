package com.ucbos.utils.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.PooledConnection;

import com.microsoft.sqlserver.jdbc.SQLServerXADataSource;

public enum DBConnectionFactory {

	INSTANCE;
	private static Logger LOGGER = Logger.getLogger("DBConnectionFactory.class.getName()");
	private static PooledConnection pc;
	private static SQLServerXADataSource XADataSource;
	private static Properties prop;
	static {
		try {
			InputStream is = null;

			prop = new Properties();
			is = DBConnectionFactory.class.getClassLoader().getResourceAsStream("config.properties");
			prop.load(is);

			XADataSource = new SQLServerXADataSource();
			XADataSource.setURL(getPropertyValue("jdbcConnectionString"));
			LOGGER.info("Connection : " + getPropertyValue("jdbcConnectionString"));

			pc = XADataSource.getPooledConnection();
		} catch (SQLException | IOException e) {

			e.printStackTrace();

		}

	}

	public static String getPropertyValue(String key) {
		return prop.getProperty(key);
	}

	public Connection getConnection() throws Exception {

		return pc.getConnection();

	}

	public void closeConnection(Connection conn) throws Exception {

		if (conn != null) {
			conn.close();
		}

	}

	public static void main(String[] args) {

		Connection conn;
		try {
			for (int i = 1; i < 10; i++) {
				conn = DBConnectionFactory.INSTANCE.getConnection();
				System.out.println("**** I :" + i + " *** Conn :" + conn.hashCode());
				DBConnectionFactory.INSTANCE.closeConnection(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
