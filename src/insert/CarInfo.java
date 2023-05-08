package insert;

import java.sql.ResultSet;  
import java.sql.SQLException;

import database.DBManager;

import java.util.ArrayList;

public class CustomerInfo {
    private static DBManager dbM;

    private int vin;
    private String color;
    private int buyPrice;
    private int leasePrice;
    private String producer;
    private int dealershipId;
    private int customerId;
    private int invoiceId;

   
    /**
     * Used for returning and inserting car info
     */
    public CarInfo(int vin, String color, int buyPrice, int leasePrice, String procuder, int dealId, int custId, int invoiceId) {
        dbM = DBManager.getDBManager();
        this.vin = vin;
        this.color = color;
        this.buyPrice = buyPrice;
        this.leasePrice = leasePrice;
        this.producer = producer;
        this.dealershipId = dealId;
        this.customerId = custId;
        this.invoiceId = invoiceId;
    }

    /**
     * Insert all the information into Car table
     */
    public void insertCarInfo() {
        dbM.queryQuiet(String.format("INSERT INTO Car(VIN, color, buy_price, lease_price, producer, dealershipID_FK, customerID_FK, invoiceID_FK);" +
        " values(%d, '%s', %d, %d, '%s', %d, %d, %d)", 
        vin, color, buyPrice, leasePrice, producer, dealershipId, customerId, invoiceid));
    }

    /**
     * given a dealership ID, return all the relevant car data in an array list of CarInfo objects
     */
    public static ArrayList<CarInfo> getDealershipCarsInfo(int dealershipId, Boolean privilege) {
    	ArrayList<CarInfo> dealershipCars = new ArrayList<>();
        String color ="", producer="";
        int vin = -1, buy = -1, lease = -1, dealership = -1, customer = -1, invoice = -1;
        if (privilege)
        	ResultSet rs = dbM.query(String.format("SELECT * FROM Car WHERE dealershipId = %d;", dealershipId));
        else
        	ResultSet rs = dbM.query(String.format("SELECT * FROM Car WHERE dealershipId = %d AND customerID = null AND invoiceID = null;"), dealershipId);
        if(rs != null) {
            try {
            	vin = rs.getString("VIN");
                color = rs.getString("color");
                buy = rs.getInt("buy_price");
                lease = rs.getInt("lease_price");
                producer = rs.getString("producer");
                dealership = rs.getInt("dealershipID_FK");
                customer = rs.getInt("customerID_FK");
                invoice = rs.getInt("invoiceID_FK");
                dealershipCars.add(new CarInfo(vin, color, buy, lease, producer, dealership, customer, invoice));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dealershipCars;
    }

    
    
    /**
     * given a dealership ID, return all the relevant car data in an array list of CarInfo objects
     */
    public static ArrayList<CarInfo> getAllCarsInfo(Boolean privilege) {
    	ArrayList<CarInfo> dealershipCars = new ArrayList<>();
        String color ="", producer="";
        int vin = -1, buy = -1, lease = -1, dealership = -1, customer = -1, invoice = -1;
        if (privilege)
        	ResultSet rs = dbM.query("SELECT * FROM Car");
        else
        	ResultSet rs = dbM.query(String.format("SELECT * FROM Car WHERE customerID = null AND invoiceID = null;"));
        if(rs != null) {
            try {
            	vin = rs.getString("VIN");
                color = rs.getString("color");
                buy = rs.getInt("buy_price");
                lease = rs.getInt("lease_price");
                producer = rs.getString("producer");
                dealership = rs.getInt("dealershipID_FK");
                customer = rs.getInt("customerID_FK");
                invoice = rs.getInt("invoiceID_FK");
                dealershipCars.add(new CarInfo(vin, color, buy, lease, producer, dealership, customer, invoice));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dealershipCars;
    }
    
    public int getVIN() {
    	return vin;
    }
    
    public String getColor() {
    	return color;
    }
    
    public int getBuyPrice() {
    	return buyPrice;
    }
    
    public int getLeasePrice() {
    	return leasePrice;
    }
    
    public String getProducer() {
    	return producer;
    }
    
    public int getDealershipOfCar() {
    	return dealershipId;
    }
    
    public int getCustomerOfCar() {
    	return customerId;
    }
    
    public int getInvoiceOfCar() {
    	return invoiceId;
    }
    
    /**
     * If we have time, we can implement change password.
     */

}
