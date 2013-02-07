package login;


import staticResources.Configuration;
import java.sql.*;

public class LoginManager {

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
