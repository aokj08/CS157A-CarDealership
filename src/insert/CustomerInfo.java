package insert;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DBManager;

public class CustomerInfo {
    private DBManager dbM;

    private int custId;
    private String addr;
    private String city;
    private int zip; 
    private String state;
    private String country;
    private String email;
    private String phone;
    private String password;

    /**
     * Used for returning CustomerInfo
     */
    public CustomerInfo(int custId, String addr, String city, int zip, String state, String country, String email, String phone, String password) {
        dbM = DBManager.getDBManager();
        this.custId = custId;
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
     * Used for inserting customer info
     */
    public CustomerInfo(String addr, String city, int zip, String state, String country, String email, String phone, String password) {
        dbM = DBManager.getDBManager();
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
     * Insert all the information into Customer table
     */
    public void insertCustomerInfo() {
        dbM.queryQuiet(String.format("INSERT INTO Customer(address, city, zip, state, country, phone, email, password)" +
        " values('%s', '%s', %d, '%s', '%s', '%s', '%s', '%s');", 
        addr, city, zip, state, country, phone, email, password));
    }

    /**
     * Given a customer ID, we return all the relevant information in a CustomerInfo object
     */
    public CustomerInfo getCustomerInfo(int custId) {
        String addr ="", city="", state="", country="", phone="", email="", password="";
        int zip = -1;
        ResultSet rs = dbM.query(String.format("SELECT * FROM Customer WHERE customerID = %d", custId));
        if(rs != null) {
            try {
                addr = rs.getString("address");
                city = rs.getString("city");
                zip = rs.getInt("zip");
                state = rs.getString("state");
                country = rs.getString("country");
                phone = rs.getString("phone");
                email = rs.getString("email");
                password = rs.getString("password");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new CustomerInfo(custId, addr, city, zip, state, country, phone, email, password);
    }

    /**
     * If we have time, we can implement change password.
     */

}
