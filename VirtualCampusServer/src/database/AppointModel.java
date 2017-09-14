package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.AppointInfo;

public class AppointModel implements Model{

	private Connection con;
	private String query;
	private AppointInfo info;
	
	public AppointModel() {
		this.con = DBConnection.getConnection();
		this.query = "";
		this.info = null;
	}

	@Override
	public boolean insert(Object obj) {
		info = (AppointInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "insert into tbAppoint values ('" + info.getItem() + "','" + info.getItemRemain() + "');";
			System.out.println(query);
			
			if (stmt.executeUpdate(query) != 0)
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean modify(Object obj) {
		info = (AppointInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "update tbAppoint set itemRemain='" + info.getItemRemain() + "' where item='" + info.getItem() + "';";
			System.out.println(query);
			
			if (stmt.executeUpdate(query) != 0)
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(Object obj) {
		info = (AppointInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "delete from tbAppoint where item='" + info.getItem() + "';";
			System.out.println(query);
			
			if (stmt.executeUpdate(query) != 0)
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Object search(Object obj) {
		info = (AppointInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "select * from tbAppoint;";
			System.out.println(query);
			
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs != null)
				return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
