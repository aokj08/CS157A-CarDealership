package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.*;
import gui.*;

public class Main {

	private DBManager dbM; // We use dbM as a handle to the database.

	/**
	 * Public constructor, instantiating mainObj and dbM.
	 */
	public Main() {
		dbM = DBManager.getDBManager();
		//Create a default admin account in Employee table
		createTable();
		dbM.queryQuiet("INSERT INTO Employee(email, password) values('admin', 'admin');");

	}

	public void createTable() {

		dbM.queryQuiet(
				"CREATE TABLE IF NOT EXISTS Customer(customerID INTEGER PRIMARY KEY AUTOINCREMENT, address STRING NOT NULL, city STRING NOT NULL,"
						+ "zip INTEGER NOT NULL, state STRING NOT NULL, country STRING NOT NULL, phone STRING NOT NULL, email STRING NOT NULL, password STRING NOT NULL);");
		dbM.queryQuiet(
				"CREATE TABLE IF NOT EXISTS Invoice(invoiceID INTEGER NOT NULL, customerID_FK INTEGER references Customer(customerID),"
						+ "purchase_type STRING NOT NULL, quantity INTEGER NOT NULL, unit_price INTEGER NOT NULL, date STRING NOT NULL);");
		dbM.queryQuiet(
				"CREATE TABLE IF NOT EXISTS Dealership(dealershipID INTEGER PRIMARY KEY, location STRING NOT NULL, address STRING NOT NULL,"
						+ "zip INTEGER NOT NULL, city STRING NOT NULL, state STRING NOT NULL, country STRING NOT NULL);");
		dbM.queryQuiet(
				"CREATE TABLE IF NOT EXISTS Car(VIN STRING PRIMARY KEY, color STRING NOT NULL, buy_price INTEGER NOT NULL, lease_price INTEGER NOT NULL,"
						+ "producer STRING NOT NULL, dealershipID_FK INTEGER references Dealership(dealershipID), customerID_FK INTEGER references Customer(customerID),"
						+ "invoiceID_FK INTEGER references Invoice(invoiceID));");
		dbM.queryQuiet(
				"CREATE TABLE IF NOT EXISTS Car_Invoice(invoiceID_PK INTEGER, VIN_PK INTEGER, PRIMARY KEY(invoiceID_PK, VIN_PK));");
		dbM.queryQuiet(
				"CREATE TABLE IF NOT EXISTS Employee(employeeID INTEGER PRIMARY KEY AUTOINCREMENT, email STRING NOT NULL, password STRING NOT NULL);"
		);
	}

	/**
	 * For testing purposes
	 */
	public void insertTable() {

		dbM.queryQuiet(String.format("INSERT INTO Customer(address, city, zip, state, country, phone, email, password) values('%s', '%s', %d, '%s', '%s', '%s', '%s', '%s');", 
				"addr", "city", 11111, "ca", "US", "0161302013", "email", "pw"));
	}

	public void dropTable() {
		dbM.queryQuiet("DROP TABLE Car;");
	}
	/**
	 * For testing purposes
	 */
	public void queryTable() {
		ResultSet rs = dbM.query("SELECT * FROM Customer;");
		try {
			while (rs.next()) {
				System.out.println(rs.getInt("customerID"));
				System.out.println(rs.getString("address"));
				System.out.println(rs.getString("city"));
				System.out.println(rs.getInt("zip"));
				System.out.println(rs.getString("state"));
				System.out.println(rs.getString("country"));
				System.out.println(rs.getString("phone"));
				System.out.println(rs.getString("email"));
				System.out.println(rs.getString("password"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static void main(String[] args) {
		Main obj = new Main();
		new Homepage();
		// obj.dropTable();
	}

}
