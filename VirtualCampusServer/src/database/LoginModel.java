package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import common.UserInfo;
import database.DBConnection;

public class LoginModel implements Model{
	private UserInfo info;
	private Connection con;
	private String query;
	
	public LoginModel() {
		this.info = null;
		this.con = DBConnection.getConnection();
		this.query = "";
	}
	
	@Override
	public boolean insert(Object obj) {
		info = (UserInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "insert into tbUser values ('" + info.getStuId() + "','" + info.getPwd() + "','" + info.getType() + "');";
			
			if (stmt.executeUpdate(query) != 0)
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean modify(Object obj) {
		info = (UserInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "update tbUser set u_Pwd='" + info.getPwd() + "',u_Type='" + info.getType() + "' where u_ID='" + info.getStuId() + "';";
			
			if (stmt.executeUpdate(query) != 0)
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(Object obj) {
		info = (UserInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "delete from tbUser where u_ID='" + info.getStuId() + "';";
			
			if (stmt.executeUpdate(query) != 0)
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Object search(Object obj) {
		info = (UserInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "select * from tbUser where u_ID='" + info.getStuId() + "';";
			
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs != null)
				return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
