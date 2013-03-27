package dbhandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.Constants;

import model.Rating;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

public class DBHandler {

	public boolean addRecordIntoDb(Rating rating) throws SQLException {
		java.sql.PreparedStatement ps = DBConnect.getConnection().prepareStatement(
				Constants.QUERY_INSERT_RATING + " (?,?,?,?,?);");
		ps.setString(1, rating.getVoteRatio());
		ps.setInt(2, rating.getVoteCount());
		ps.setFloat(3, rating.getRating());
		ps.setString(4, rating.getMovieName());
		ps.setInt(5, rating.getYear());
		int val = ps.executeUpdate();
		if (val > 0)
			return true;

		return false;
	}

	public boolean addRecordIntoDb(String insertStmt) {
		int val = doInsertUpdateDeleteDBRecord(insertStmt, DBConnect.getConnection());
		if (val > 0)
			return true;

		return false;
	}

	private int doInsertUpdateDeleteDBRecord(String query, Connection conn) {

		// query = Util.escape(query);
		Statement stmt = null;
		int val = 0;
		try {
			stmt = conn.createStatement();
			val = stmt.executeUpdate(query);
		} catch (MySQLSyntaxErrorException me) {
			System.out.println("Error in select query =" + query);
		} catch (SQLException e) {
			System.out.println("Error in select query =" + query);
			e.printStackTrace();
		}
		return val;
	}

	private ResultSet getResults(String query, Connection conn) {

		// query = Util.escape(query);
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		} catch (MySQLSyntaxErrorException me) {
			System.out.println("Error in select query =" + query);
		} catch (SQLException e) {
			System.out.println("Error in select query =" + query);
			e.printStackTrace();
		}
		return rs;
	}

}
