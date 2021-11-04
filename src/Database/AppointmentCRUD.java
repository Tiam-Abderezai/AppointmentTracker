package Database;

import Model.Appointment;
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

public class AppointmentCRUD {

    public static Date date = new Date();
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    public static String d = formatter.format(date);
    public static PreparedStatement ps = null;

    private static java.sql.Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
    }

    public static Appointment addAppointment(String type, String start, String end) throws SQLException, Exception {

        try {

            // 'Country' SELECT Statements
            String sqlStatement
                    = "SELECT FROM U043Fk.country (country, createDate, createdBy, lastUpdate, lastUpdateBy)"
                    + " VALUES ( ?, ?, ?, ?, ?);";
            ps = conn.prepareStatement(sqlStatement);
            ps.setString(1, "");
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(3, "");
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(5, "");
            ps.executeUpdate();

            // 'City' SELECT Statements
            sqlStatement = "SELECT FROM U043Fk.city (city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy)"
                    + " VALUES ( ?, LAST_INSERT_ID(), ?, ?, ?, ?);";
            ps = conn.prepareStatement(sqlStatement);
            ps.setString(1, "");
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(3, "");
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(5, "");
            ps.executeUpdate();

            // 'Address' SELECT Statements
            sqlStatement = "SELECT FROM U043Fk.address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)"
                    + " VALUES ( ?, ?, LAST_INSERT_ID(), ?, ?, ?, ?, ?, ?);";
            ps = conn.prepareStatement(sqlStatement);
            ps.setString(1, "");
            ps.setString(2, "");
            ps.setString(3, "");
            ps.setString(4, "");
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(6, "");
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(8, "");
            ps.executeUpdate();
            
  //         'Customer' SELECT Statements
            sqlStatement = "SELECT FROM U043Fk.customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)"
                    + " VALUES ( ?, ?, ?, ?, ?, ?, ?);";
            ps = conn.prepareStatement(sqlStatement);
            ps.setString(1, "");
            ps.setInt(2, 1);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(4, "");
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(6, "");

            ps.executeUpdate();
//****************************TODO***************************************
// LAST ERROR MESSAGE 11/24/19
// Nov 24, 2019 11:04:33 PM Database.AppointmentCRUD addAppointment
// SEVERE: null
// java.sql.SQLException: No value specified for parameter 15
//***********************************************************************
            // Appointment SQL Statements
            sqlStatement = "INSERT INTO U043Fk.appointment (customerId, userId, title, description, location, contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)"
                    //14 INSERT Parameters

                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            // 15 VALUES Parameters

            ps = conn.prepareStatement(sqlStatement);
            ps.setInt(1, 1);// customerId
            ps.setInt(2, 1);// userId
            ps.setString(3, "");// title
            ps.setString(4, "");// description
            ps.setString(5, "");// location
            ps.setString(6, "");// contact
            ps.setString(7, type);// type
            ps.setString(8, "");// url
            ps.setString(9, start);// start
            ps.setString(10, end);// end
            ps.setTimestamp(11, Timestamp.valueOf(LocalDateTime.now()));// createDate
            ps.setString(12, "");// createdBy
            ps.setTimestamp(13, Timestamp.valueOf(LocalDateTime.now())); // lastUpdate
            ps.setString(14, "");// lastUpdateBy

            ps.executeUpdate();

            //Return addAppointment()'s parameters back to "saveAppointment()" method of "AddAppointment" controller 
            return new Appointment(type, start, end);
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ObservableList<Appointment> getAllAppointments() {

        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try {
            String type = null;
            String start = null;
            String end = null;

            String sqlStatement = "SELECT * FROM customer, appointment WHERE "
                    + "customer.customerId = appointment.customerId";

            Connection conn = Database.makeConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sqlStatement);
            while (result.next()) {
                type = result.getString("type");
                start = result.getString("start");
                end = result.getString("end");
//          
                appointmentList.add(new Appointment(start, end, type));
            }

        } catch (SQLException ex) {
//            Logger.getLogger(AppointmentCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return appointmentList;
    }

    public static Appointment deleteAppointment(String dateStart, String dateEnd, String type) throws SQLException, Exception {
        return new Appointment(dateEnd, dateStart, type);
    }

    public static Appointment updateAppointment(String dateStart, String dateEnd, String type) throws SQLException, Exception {
        return new Appointment(dateEnd, dateStart, type);
    }

}
