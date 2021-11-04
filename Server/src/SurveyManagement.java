import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.*;
import java.util.UUID;

public class SurveyManagement {
	public static void SurveyPublish(String name, String sid) throws SQLException {
		Connection connect = DBUtil.getDBConnection();
        String sql = "insert into PublishedSurvey(id, name)" + "values("+"?,?)";
        PreparedStatement stmt = connect.prepareStatement(sql);
       
        //send arguments
        stmt.setString(1, sid);
        stmt.setString(2, name);

        stmt.execute();
        connect.close();
	}
	
	public static ResultSet PublishedID() throws SQLException {
		Connection connect = DBUtil.getDBConnection();
		String sql = "select * from PublishedSurvey";
        PreparedStatement stmt = connect.prepareStatement(sql);
        stmt.execute();
        ResultSet result = stmt.getResultSet();
		return result;
	}
	
	public static void SurveyDelete(String sid) throws SQLException {
		Connection connect = DBUtil.getDBConnection();
        String sql1 = "delete from PublishedSurvey where id=" + ""+"?";
        String sql2 = "delete from ParticipatedSurvey where id=" + ""+"?";
        PreparedStatement stmt1 = connect.prepareStatement(sql1);
        PreparedStatement stmt2 = connect.prepareStatement(sql2);
        stmt1.setString(1, sid);
        stmt2.setString(1, sid);
        stmt1.execute();
        stmt2.execute();
        connect.close();
	}
	
	public static void SurveyEngage(String name, String sid) throws SQLException {
		Connection connect = DBUtil.getDBConnection();
        String sql = "insert into ParticipatedSurvey(id, name)" + "values("+"?,?)";
        PreparedStatement stmt = connect.prepareStatement(sql);
       
        //send arguments
        stmt.setString(1, sid);
        stmt.setString(2, name);

        stmt.execute();
        connect.close();
	}
	
	public static ResultSet SurveySearch(String sid) throws SQLException {
		Connection connect = DBUtil.getDBConnection();
		String sql = "select * from PublishedSurvey where id=" + ""+"?";
        PreparedStatement stmt = connect.prepareStatement(sql);
        stmt.setString(1, sid);
        stmt.execute();
        ResultSet result = stmt.getResultSet();
		return result;
	}
}
