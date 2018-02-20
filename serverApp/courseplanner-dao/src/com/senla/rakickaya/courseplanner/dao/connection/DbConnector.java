package com.senla.rakickaya.courseplanner.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.senla.rakickaya.courseplanner.configuration.Config;

public class DbConnector {
	private static DbConnector dbConnector;
	private static Logger logger = Logger.getLogger(DbConnector.class);
	private Connection connection;

	public static DbConnector getInstance() {
		if (dbConnector == null) {
			dbConnector = new DbConnector();
		}
		return dbConnector;
	}

	private DbConnector() {
		connect();
	}

	private void connect() {
		try {
			Config config = Config.getInstance();
			Class.forName(config.getDriverNameJDBC());
			connection = DriverManager.getConnection(config.getUrlDb(), config.getUserDb(), config.getPasswordDb());
		} catch (ClassNotFoundException | SQLException e) {
			logger.error(e.getMessage());
		}
	}

	public Connection getConnection() {
		if (connection == null) {
			connect();
		}
		return connection;
	}

	public void closeConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

}
