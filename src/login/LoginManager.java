package login;


import staticResources.Configuration;
import staticResources.PasswordEncryption;

import java.sql.*;

/**
 *
 */
public class LoginManager {

    /**
     * @return true if user have registered and input correct password.
     */
    public boolean attemptLogin(ResultSet resultSet, String inputPassword) {
        try {
            while(resultSet.next()) {
                String pwd = resultSet.getString("User_Password");
                String slt = resultSet.getString("Salt");
                String nu = PasswordEncryption.encryptPassword(inputPassword, slt);

                if (nu.trim().equals(pwd.trim()))
                    return  true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * retrieve the values from DB by using userName.
     */
    public ResultSet getResultSetbyUsername(String username) throws Exception {
        String connString = Configuration.MySQLConUrl + "System_DB_Test_Model";
        Connection conn;

        Class.forName(Configuration.MySQLdriver).newInstance();
        conn = DriverManager.getConnection(connString,
                Configuration.MySQLrootUser, Configuration.MySQLrootPassword);
        ConnectionManager.DatabaseManager.getSystemConn();
        Statement statement = conn.createStatement();

        // SQL INJECTION POINT, FIX WHEN APPLYING SECURITY
        // you should Google 'prepared statement' :)
        ResultSet resultSet = statement
                .executeQuery("SELECT * FROM users where Username='" + username + "'");

        return resultSet;
    }

    /**
     * @return the destination URL to which user with given ID should be redirected to.
     */
    public String getDestination(int userId) {
        String prefix = "/CharityWare/";
        switch (userId) {
            case 1:
                return prefix + "uclAdmin.jsp";
            case 2:
                return prefix + "CharityAdminServlet";
            default:
                return prefix + "login.jsp";
        }
    }
}
