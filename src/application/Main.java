package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.UIManager;

import database.DBManager;
import gui.LoginUI;

public class Main {

	private DBManager dbM; // We use dbM as a handle to the database.

	/**
	 * Public constructor, instantiating mainObj and dbM.
	 */
	public Main() {
		dbM = DBManager.getDBManager();

	}

	/**
	 * For testing purposes
	 */
	public void createTable() {

		/*
		 * For simplicity, just create all the tables here.
		 */
		dbM.queryQuiet(
				"CREATE TABLE IF NOT EXISTS Customer(customerID INTEGER PRIMARY KEY, address STRING NOT NULL, city STRING NOT NULL,"
						+ "zip INTEGER NOT NULL, state STRING NOT NULL, country STRING NOT NULL, phone STRING NOT NULL, email STRING NOT NULL);");
		dbM.queryQuiet(
				"CREATE TABLE IF NOT EXISTS Invoice(invoiceID INTEGER NOT NULL, customerID_FK INTEGER references Customer(customerID),"
						+ "purchase_type STRING NOT NULL, quantity INTEGER NOT NULL, unit_price INTEGER NOT NULL, data STRING NOT NULL);");
		dbM.queryQuiet(
				"CREATE TABLE IF NOT EXISTS Dealership(dealershipID INTEGER PRIMARY KEY, location STRING NOT NULL, address STRING NOT NULL,"
						+ "zip INTEGER NOT NULL, city STRING NOT NULL, state STRING NOT NULL, country STRING NOT NULL);");
		dbM.queryQuiet(
				"CREATE TABLE IF NOT EXISTS Car(VIN STRING PRIMARY KEY, color STRING NOT NULL, buy_price INTEGER NOT NULL, lease_price INTEGER NOT NULL,"
						+ "producer STRING NOT NULL, dealershipID_FK INTEGER references Dealership(dealershipID), customerID_FK INTEGER references Customer(customerID),"
						+ "invoiceID_FK INTEGER references Invoice(invoiceID));");
		dbM.queryQuiet(
				"CREATE TABLE IF NOT EXISTS Car_Invoice(invoiceID_PK INTEGER, VIN_PK INTEGER, PRIMARY KEY(invoiceID_PK, VIN_PK));");
	}

	/**
	 * For testing purposes
	 */
	public void insertTable() {

		dbM.queryQuiet(String.format("INSERT INTO Customer values(%d, '%s', '%s', %d, '%s', '%s', '%s', '%s');", 2,
				"addr", "city", 11111, "ca", "US", "0161302013", "email"));
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
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static void main(String[] args) {
		Main mainObj = new Main();
		mainObj.createTable();

    
		
=======
		mainObj.insertTable();
		mainObj.queryTable();

	}

}
