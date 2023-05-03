package search;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBManager;

public class InvoiceManager {
	
//	private static InvoiceManager invoiceManager;
	private DBManager dbManager;
	
	/**
	 * By calling DBManager.getDBManager(), we have access to the database connection object.
	 */
	private InvoiceManager() {
		dbManager = DBManager.getDBManager();
		
	}
	
	/**
	 * Returns a result set of the entire invoice table. 
	 * Available for employees only.
	 */
	public ResultSet getInvoiceInfo() {
		ResultSet invoiceInfo = dbManager.query("SELECT * FROM Invoice;");
		try {
			if (invoiceInfo == null || invoiceInfo.next() == false) {
				return new ArrayList<InvoiceInfo>();
			}
			List<InvoiceInfo> invoiceInfoList = new ArrayList<InvoiceInfo>();
			invoiceInfoList.add(new InvoiceInfo(
				invoiceInfo.getInt("invoiceID"),
				invoiceInfo.getInt("customerID_FK"),
				invoiceInfo.getString("purchaseType"),
				invoiceInfo.getInt("quantity"),
				invoiceInfo.getInt("unit_price"),
				invoiceInfo.getString("data")
				
			));
			while (invoiceInfo.next() == true) {
				invoiceInfoList.add(new InvoiceInfo(
						invoiceInfo.getInt("invoiceID"),
						invoiceInfo.getInt("customerID_FK"),
						invoiceInfo.getString("purchaseType"),
						invoiceInfo.getInt("quantity"),
						invoiceInfo.getInt("unit_price"),
						invoiceInfo.getString("data")
				));
			}
			return invoiceInfoList;
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
