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
		dbM.queryQuiet("INSERT OR IGNORE INTO Employee(email, password) values('admin', 'admin');");

	}

	public void createTable() {

		dbM.queryQuiet(
				"CREATE TABLE IF NOT EXISTS Customer(" +
					"customerID INTEGER PRIMARY KEY AUTOINCREMENT, " +
					"address STRING NOT NULL, city STRING NOT NULL, " +
					"zip INTEGER NOT NULL, " +
					"state STRING NOT NULL, " +
					"country STRING NOT NULL, " +
					"phone STRING NOT NULL, " + 
					"email STRING NOT NULL, " +
					"password STRING NOT NULL);"
				);
		dbM.queryQuiet(
				"CREATE TABLE IF NOT EXISTS Invoice(" +
				"invoiceID INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"customerID_FK INTEGER references Customer(customerID), " +
				"purchase_type STRING NOT NULL, " + 
				"quantity INTEGER NOT NULL, " + 
				"unit_price INTEGER NOT NULL, " + 
				"date STRING NOT NULL);"
				);
		dbM.queryQuiet(
				"CREATE TABLE IF NOT EXISTS Dealership(" +
				"dealershipID INTEGER PRIMARY KEY, " + 
				"location STRING NOT NULL, " +
				"address STRING NOT NULL, " +
				"zip INTEGER NOT NULL, " +
				"city STRING NOT NULL, " +
				"state STRING NOT NULL, " +
				"country STRING NOT NULL);"
				);
		dbM.queryQuiet(
				"CREATE TABLE IF NOT EXISTS Car(" +
				"VIN STRING PRIMARY KEY, " +
				"color STRING NOT NULL, " +
				"buy_price INTEGER NOT NULL, " + 
				"lease_price INTEGER NOT NULL, " +
				"producer STRING NOT NULL, " +
				"dealershipID_FK INTEGER references Dealership(dealershipID), " +
				"customerID_FK INTEGER references Customer(customerID), " +
				"invoiceID_FK INTEGER references Invoice(invoiceID));"
				);
		dbM.queryQuiet(
				"CREATE TABLE IF NOT EXISTS Employee(" +
				"employeeID INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"email STRING NOT NULL, " +
				"password STRING NOT NULL, " +
				"UNIQUE(email, password));"
				);
	}


	public static void main(String[] args) {
		Main obj = new Main();
		new Homepage();
	}

}
