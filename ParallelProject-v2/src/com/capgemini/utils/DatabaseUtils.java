package com.capgemini.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {

	private static Connection connection;
	
	private static DatabaseUtils object = new DatabaseUtils();

	private DatabaseUtils() {

	}

	public synchronized static DatabaseUtils getInstance() {

		return object;
	}

	public Connection openDatabaseConnections() throws SQLException, ClassNotFoundException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection = DriverManager.getConnection("jdbc:oracle:thin:@172.16.10.2:1521:orcl","seed23","seed23");
		return connection;

	}
	
	public Connection openDatabaseConnections(String username, String password) throws SQLException, ClassNotFoundException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection = DriverManager.getConnection("jdbc:oracle:thin:@172.16.10.2:1521:orcl",username,password);
		return connection;

	}

	public void closeDatabaseConnections() throws SQLException {

		
		if (connection != null)
			connection.close();
	}
}
