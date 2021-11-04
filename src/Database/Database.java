package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiam
 */
public class Database {

    public static final String dbName = "U043Fk";
    public static final String DB_URL = "jdbc:mysql://52.206.157.109/" + dbName;
    public static final String username = "U043Fk";
    public static final String password = "53688153375";
    public static final String driver = "com.mysql.jdbc.Driver";
    public static Connection conn;

    public static Connection makeConnection() {
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(DB_URL, username, password);

            System.out.println("Connection successful!");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public static void closeConnection() throws ClassNotFoundException, SQLException, Exception {
        conn.close();
        System.out.println("Connection closed.");

    }

    public static Connection getConn() {
        return conn;
    }

}
