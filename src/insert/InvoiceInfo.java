/*
 * This class will communicate with GUI which will insert all invoice information into the Invoice table in database. 
 */


package insert;

import java.util.ArrayList;

public class InvoiceInfo {
	
	private DBManager dbManager;
	
	public InvoiceInfo() {
		dbManager = DBManager.getDBManager();
	}
	
	/**
	 * sets the customer ID of a car (found by its vin) to an input customer id
	 */
	public void setCarOwner(int vin, int customerID)
	{
		dbManager.queryQuiet("UPDATE Car SET customerID_FK = " + customerID + " WHERE VIN = " + vin + ";");
	}
	
	/**
	 * will insert a new invoice into the invoice table and then car_invoice table
	 */
	public void makeInvoice(int invoiceID, int customerID, ArrayList<Integer> vins, String purchaseType, String date)
	{
		int price = 0;
		if (purchaseType.equals("buy"))
		{
			for (int i  0; i < vins.size(); i++)
			{
				ResultSet carPrices = dbManager.query("SELECT buy_price FROM Car WHERE VIN = " + vins.get(i) + ";");
				while(carPrices.next())
				{
					price += carPrices.getInt("buy_price");
				}
			}
		}
		else if (purchaseType.equals("lease"))
		{
			for (int i  0; i < vins.size(); i++)
			{
				ResultSet carPrices = dbManager.query("SELECT lease_price FROM Car WHERE VIN = " + vins.get(i) + ";");
				while(carPrices.next())
				{
					price += carPrices.getInt("buy_price");
				}
			}
		}
		dbManager.quietQuery("INSERT INTO Invoice VALUES (" + invoiceID + ", " + customerID + ", \'" + purchaseType + "\', " + vins.size() + ", " + price + ", \'" + date + "\');")
		for (int i  0; i < vins.size(); i++)
		{
			dbManager.quietQuery("INSERT INTO Car_Invoice VALUES (" + invoiceID + ", " + vins.get(i) + ");");
			dbManager.quietQuery("UPDATE Car SET InvoiceID_FK = " + invoiceID + ", CustomerID_FK = " + customerID + "WHERE VIN = " + vins.get(i));
		}
	}
	
	
	public void insertDealership(int id, String location, int address, int zip, String city, String state, String coutnry)
	{
		dbManager.quietQuery("INSERT INTO Dealership VALUES (" + id + ", \'" + location + "\', " + address + ", " + zip + ", \'" + city + "\', \'" + state + "\', \'" + country + "\');");
	}
	
	public void insertCar(int vin, String color, int buy, int lease, String producer, int dealID)
	{
		dbManager.quietQuery("INSERT INTO Car VALUES ("  + vin + ", \'" + color + "\', " + buy + ", " + lease + ", \'" + producer + "\', " + dealID + ");");
	}
	
	public void insertCustomer(int customerID, String address, String city, int zip, String state, String country, int phone, String email)
	{
		dbManager.quietQuery("INSERT INTO Customer VALUES (" + customerID + ", \'" + address + "\', \'" + city + "\', " + zip + ", \'" + state + "\', \'" + country + "\', " + phone + ", \'" + email + "\');");
	}
}
