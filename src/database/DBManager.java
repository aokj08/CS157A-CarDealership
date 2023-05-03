package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class DBManager {
	
	private static DBManager dbManager;
	
	private DBConnection dbConnection;
	private Connection connection;
	
	/*
	 * Connects to singleton instance of DBConnection and gets its connection
	 */
	private DBManager() {
		dbConnection = DBConnection.getDBConnection();
		connection = dbConnection.getConnection();
	}
	
	/*
	 * Get singleton instance of DBManager, creates a new instance if one does not exist
	 */
	public static DBManager getDBManager() {
		if (dbManager == null) {
			dbManager = new DBManager();
		}
		return dbManager;
	}
	
	/*
	 * Runs select query on database with given string
	 */
	public ResultSet query(String sqlQuery) {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			return rs;
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
        }
	}
	
	/*
	 * Runs update, insert, or delete query with given string
	 */
	public void queryQuiet(String sqlQuery) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(sqlQuery);
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
}

	

