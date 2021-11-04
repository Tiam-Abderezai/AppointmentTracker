package Database;

import Model.Customer;
import static Database.Database.conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerCRUD {

//    static int customerId = 0;
//    static int addressId = 0;
//    static int cityId = 0;
//    static int countryId = 0;
//    static String name = null;
//    static String address = null;
//    static String country = null;
//    static String city = null;
//    static String phone = null;
// Formatting Database's date-datatypes
    public static Date date = new Date();
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    public static String d = formatter.format(date);
    public static PreparedStatement ps = null;

    private static java.sql.Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
    }

    public static ObservableList<Customer> getAllCustomers() {
        int customerId = 0;
        int addressId = 0;
        int cityId = 0;
        int countryId = 0;
        String customerName = null;
        String address = null;
        String country = null;
        String city = null;
        String phone = null;
        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        // Try-block instructions for getAllCustomers() steps used to return ObservableList of Customer data
        try {

            // Write SQL statement
            String sqlStatement = "SELECT * FROM customer, address, country, city WHERE "
                    + "customer.addressId = address.addressId AND "
                    + "address.cityId = city.cityId AND "
                    + "city.countryId = country.countryId";

            // Create connection object
            Connection conn = Database.makeConnection();
            // Create statement object
            Statement statement = conn.createStatement();
            // Get all records from ResultSet object
            ResultSet result = statement.executeQuery(sqlStatement);
            // while loop that loops through each row of the tables storing data for the customers and returning them
            while (result.next()) {
                customerId = result.getInt("customerId");
                addressId = result.getInt("address.addressId");
                cityId = result.getInt("city.cityId");
                countryId = result.getInt("country.countryId");
                customerName = result.getString("customerName");
                country = result.getString("country");
                city = result.getString("city");
                address = result.getString("address");
                phone = result.getString("phone");
//          
                customerList.add(new Customer(customerId, addressId, cityId, countryId, customerName, address, country, city, phone));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return customerList;
        // End of 'getAllCustomers()'
    }

    public static Customer addCustomer(String customerName, String address, String country, String city, String phone) throws SQLException, Exception {

        try {

            // Country INSERT Statements
            String sqlStatement
                    = "INSERT INTO U043Fk.country (country, createDate, createdBy, lastUpdate, lastUpdateBy)"
                    + " VALUES ( ?, ?, ?, ?, ?);";
            // PreparedStatement objects sending SQL INSERT statements to the DB
            ps = conn.prepareStatement(sqlStatement);
            ps.setString(1, country);
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(3, "");
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(5, "");
            ps.executeUpdate();

            sqlStatement = "SELECT LAST_INSERT_ID() FROM U043Fk.country";

            PreparedStatement psCountry = conn.prepareStatement(sqlStatement);
            ResultSet rs = psCountry.executeQuery();
            rs.next();

            int countryId = rs.getInt(1);

            // (DONE)TODO implement these SQL Statements like above.
            // City SQL Statements
            sqlStatement = "INSERT INTO U043Fk.city (city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy)"
                    + " VALUES (?, ?, ?, ?, ?, ?);";
            ps = conn.prepareStatement(sqlStatement);
            ps.setString(1, city);
            ps.setInt(2, countryId);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(4, "");
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(6, "");
            ps.executeUpdate();

            sqlStatement = "SELECT LAST_INSERT_ID() FROM U043Fk.city";

            PreparedStatement psCity = conn.prepareStatement(sqlStatement);
            rs = psCity.executeQuery();
            rs.next();

            int cityId = rs.getInt(1);

            // Address SQL Statements
            sqlStatement = "INSERT INTO U043Fk.address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)"
                    + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            ps = conn.prepareStatement(sqlStatement);
            ps.setString(1, address);
            ps.setString(2, "");
            ps.setInt(3, cityId);
            ps.setString(4, "");
            ps.setString(5, phone);
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(7, "");
            ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(9, "");
            ps.executeUpdate();

            sqlStatement = "SELECT LAST_INSERT_ID() FROM U043Fk.address";

            PreparedStatement psAddress = conn.prepareStatement(sqlStatement);
            rs = psAddress.executeQuery();
            rs.next();

            int addressId = rs.getInt(1);

            // Customer SQL Statements
            sqlStatement = "INSERT INTO U043Fk.customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?);";
            ps = conn.prepareStatement(sqlStatement);
            ps.setString(1, customerName);
            ps.setInt(2, addressId);
            ps.setInt(3, 0);
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(5, "");
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(7, "");
            ps.executeUpdate();
            sqlStatement = "SELECT LAST_INSERT_ID() FROM U043Fk.customer";

            PreparedStatement psCustomer = conn.prepareStatement(sqlStatement);
            rs = psCustomer.executeQuery();
            rs.next();

            int customerId = rs.getInt(1);

            //Return addCustomer()'s parameters back to "saveCustomer()" method of "AddCustomer" controller 
            return new Customer(customerId, addressId, cityId, countryId, customerName, address, country, city, phone);

        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            // conn.rollback();
        } catch (Exception ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        // End of 'addCustomer()'
    }

    public static void deleteCustomer(int nameId) throws SQLException, Exception {

        System.out.println("deleteCRUD called");
        // Country SQL Statements
        String sqlStatement
                = "DELETE FROM U043Fk.customer WHERE customerId = " + nameId + ";";
        ps = conn.prepareStatement(sqlStatement);
        ps.executeUpdate();

//        return new Customer(nameId);
        // End of 'deleteCustomer()'
    }

    public static void updateCustomer(int customerId, int addressId, int cityId, int countryId, String customerName, String address, String country, String city, String phone) throws SQLException, Exception {

        try {

            // Country SQL Statements
            String sqlStatement
                    = "UPDATE U043Fk.country SET country = ?, lastUpdate = ?, lastUpdateBy = ? "
                    + "WHERE countryId = ?";
            ps = conn.prepareStatement(sqlStatement);
            ps.setString(1, country);
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(3, "test");
            ps.setInt(4, countryId);
            ps.executeUpdate();

            // City SQL Statements
            sqlStatement = "UPDATE U043Fk.city SET city = ?, countryId = ?, lastUpdate = ?, lastUpdateBy = ? "
                    + "WHERE cityId = ?";
            ps = conn.prepareStatement(sqlStatement);
            ps.setString(1, city);
            ps.setInt(2, countryId);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(4, "test");
            ps.setInt(5, cityId);
            ps.executeUpdate();

            // Address SQL Statements
            sqlStatement = "UPDATE U043Fk.address SET address = ?, cityId = ?, phone = ?, lastUpdate = ?, lastUpdateBy = ?"
                    + "WHERE addressId = ?";
            ps = conn.prepareStatement(sqlStatement);
            ps.setString(1, address);
            ps.setInt(2, cityId);
            ps.setString(3, phone);
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(5, "test");
            ps.setInt(6, addressId);
            ps.executeUpdate();

            // Customer SQL Statements
            sqlStatement = "UPDATE U043Fk.customer SET customer = ?, addressId = ?, lastUpdate = ?, lastUpdateBy = ?"
                    + "WHERE customerId = ?";
            ps = conn.prepareStatement(sqlStatement);
            ps.setString(1, customerName);
            ps.setInt(2, addressId);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(5, "test");
            ps.setInt(6, customerId);
            ps.executeUpdate();

            //Return addCustomer()'s parameters back to "updateCustomer()" method of "AddCustomer" controller 
//            return new Customer(customerNameId, customerName, address, country, city, phone);
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
//        return null;
        // End of 'updateCustomer()'
    }

}
