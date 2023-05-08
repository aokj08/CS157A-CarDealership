package insert;

import java.sql.ResultSet; 
import java.sql.SQLException;

import database.DBManager;

import java.util.ArrayList;

public class CustomerInfo {
    private static DBManager dbM;

    private int dealershipId;
    private String location;
    private String addr;
    private String city;
    private int zip; 
    private String state;
    private String country;

    public DealershipInfo focusDealership;
    
    /**
     * Used for returning DealershipInfo
     */
    public DealershipInfo(int dealID, String location, String addr, String city, int zip, String state, String country) {
        dbM = DBManager.getDBManager();
        this.dealershipID = dealID;
        this.location = location;
        this.addr = addr;
        this.city = city;
        this.zip = zip;
        this.state = state;
        this.country = country;
    }

    /**
     * Used for inserting dealership info
     */
    public DealershipInfo(String location, String addr, String city, int zip, String state, String country) {
        dbM = DBManager.getDBManager();
        this.location = location;
        this.addr = addr;
        this.city = city;
        this.zip = zip;
        this.state = state;
        this.country = country;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    /**
     * Insert all the information into Dealership table
     */
    public void insertDealershipInfo() {
        dbM.queryQuiet(String.format("INSERT INTO Dealership(location, address, zip, city, state, country);" +
        " values('%s', '%s', %d, '%s', '%s', '%s')", 
        location, addr, zip, city, state, country));
    }

    /**
     * Given a dealership location, we return all the relevant information in a CustomerInfo object
     */
    public static DealershipInfo getDealershipInfo(int zip) {
        String addr ="", city="", state="", country="", location="";
        int dealershipID = -1;
        ResultSet rs = dbM.query(String.format("SELECT * FROM Dealership WHERE zip = %d;", zip));
        if(rs != null) {
            try {
            	dealershipID = rs.getInt("dealershipID");
                addr = rs.getString("address");
                city = rs.getString("city");
                location = rs.getString("location");
                state = rs.getString("state");
                country = rs.getString("country");
                location = rs.getString("location");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new DealershipInfo(dealershipID, location, addr, city, zip, state, country);
    }

    
    
    public static ArrayList<DealershipInfo> getAllDealershipsInfo() {
        String addr ="", city="", state="", country="", location="";
        int dealershipID = -1, zip = -1;
        ArrayList<DealershipInfo> dealerships = new ArrayList<>();
        ResultSet rs = dbM.query("SELECT * FROM Dealership");
        while (rs.next()) {
            try {
            	dealershipID = rs.getInt("dealershipID");
                addr = rs.getString("address");
                city = rs.getString("city");
                location = rs.getString("location");
                state = rs.getString("state");
                country = rs.getString("country");
                location = rs.getString("location");
                dealerships.add(new DealershipInfo(dealershipID, location, addr, city, zip, state, country));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dealerships;
    }
    
    public int getDealID() {
    	return dealershipId;
    }
    
    public String getLocation() {
    	return location;
    }
    
    public String getAddress() {
    	return addr;
    }
    
    public String getCity() {
    	return city;
    }
    
    public int getZipCode() {
    	return zip;
    }
    
    public String getState() {
    	return state;
    }
    
    public String getCountry() {
    	return coutnry;
    }
    
    /**
     * If we have time, we can implement change password.
     */

}
