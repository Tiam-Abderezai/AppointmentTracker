package Database;

import Model.User;
import static Database.Database.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiam
 */
public class UserCRUD {

    // INSERT INTO user (userName, password) VALUES ('test', 'test');
    public static User getUser(String userName, String password) {
        try {
            String sqlStatement = "SELECT * FROM U043Fk.user WHERE userName = '" + userName + "'" + "AND password = '" + password + "'";
            Database.makeConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sqlStatement);
            if (result.next()) {
                userName = result.getString("userName");
                password = result.getString("password");
                return new User(userName, password);
            } else {
                System.out.println("Invalid login");
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
