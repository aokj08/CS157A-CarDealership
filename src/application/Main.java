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

	/**
	 * For testing purposes
	 */
	public void insertTable() {

		dbM.queryQuiet(String.format("UPDATE Car SET customerID_FK = %d, invoiceID_FK = %d WHERE VIN = '%s';", Integer.valueOf(1), 4, 222));

	}

	public void dropTable() {
		dbM.queryQuiet("DROP TABLE Car;");
	}

	public void dropAll() {
		dbM.queryQuiet("DROP TABLE Car;");
		dbM.queryQuiet("DROP TABLE Customer;");
		dbM.queryQuiet("DROP TABLE Employee;");
		dbM.queryQuiet("DROP TABLE Dealership;");
		dbM.queryQuiet("DROP TABLE Invoice;");
	}
	/**
	 * For testing purposes
	 * @throws SQLException
	 */
	public void queryTable() {
		// ResultSet rs = dbM.query("select seq from sqlite_sequence WHERE name = 'Invoice';");
		// int invoiceIDSearch = -1;
		// try {
		// 	invoiceIDSearch = rs.getInt("seq");
		// } catch (SQLException e) {
		// 	// TODO Auto-generated catch block
		// 	e.printStackTrace();
		// }
		// System.out.println(invoiceIDSearch);

		ResultSet rs = dbM.query("SELECT * FROM Car;");
		try {
			while (rs.next()) {
				System.out.println(rs.getInt("invoiceID_FK"));
				System.out.println(rs.getString("VIN"));
				System.out.println(rs.getInt("customerID_FK"));
				System.out.println(rs.getInt("dealershipID_FK"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static void main(String[] args) {
		Main obj = new Main();
		// obj.insertTable();
		obj.queryTable();
		// new Homepage();
		// obj.dropAll();
	}

}
