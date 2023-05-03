package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static DBConnection dbConnection;
	
	private Connection connection;
	
	/*
	 * Singleton design, the connection and all tables will be established just once.
	 */
	private DBConnection() { 
		String url = "jdbc:sqlite:application.db";
		try {
			connection = DriverManager.getConnection(url);
		}
		catch (SQLException e) {
			System.out.println("Error connecting to database.");
			System.exit(1);
		}
	}
	
	/*
	 * Returns singleton instance of DBConnection class, creates one if not already present
	 */
	public static DBConnection getDBConnection() {
		if (dbConnection == null) {
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}
	
	/*
	 * Returns the Connection
	 */
	public Connection getConnection() {
		return connection;
	}
	
}
