import java.sql.Connection;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.*;
import java.util.UUID;

public class UserManagement {
	public static void UserRegister(String name, String password) throws SQLException {
		Connection connect = DBUtil.getDBConnection();
        String sql = "insert into SurveyCorps(name, password)" + "values("+"?,?)";
        PreparedStatement stmt = connect.prepareStatement(sql);
       
        //send arguments
        stmt.setString(1, name);
        stmt.setString(2, MD5Util.md5encrypt(password));

        stmt.execute();
        connect.close();
	}
	
	public static ResultSet UserFind(String name) throws SQLException {
		Connection connect = DBUtil.getDBConnection();
		String sql = "select * from SurveyCorps where name=\"" + name + "\"";
        PreparedStatement stmt = connect.prepareStatement(sql);
        stmt.execute();
        ResultSet result = stmt.getResultSet();
		return result;
	}
	
	public static void UserDelete(String name) throws SQLException {
		Connection connect = DBUtil.getDBConnection();
        String sql = "delete from SurveyCorps where name=" + ""+"?";
        PreparedStatement stmt = connect.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.execute();
        connect.close();
	}
}
