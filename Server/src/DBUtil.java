import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class DBUtil {
	public static final String Url = "jdbc:mysql://localhost:3306/User";
    public static final String User = "root";
    public static final String Password = "wjwhy1998";
    private static Connection connect = null;
    
    public static Connection getDBConnection() {
    	try {
            //load driver
            Class.forName("com.mysql.jdbc.Driver");
            //get database connection
            connect = DriverManager.getConnection(Url, User, Password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	return connect;
    }
}
