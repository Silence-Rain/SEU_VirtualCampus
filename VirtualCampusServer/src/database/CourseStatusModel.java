package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.CourseStatusInfo;

public class CourseStatusModel implements Model{
	
	private Connection con;
	private String query;
	private CourseStatusInfo info;
	
	public CourseStatusModel() {
		this.con = DBConnection.getConnection();
		this.query = "";
		this.info = null;
	}

	@Override
	public boolean insert(Object obj) {
		info = (CourseStatusInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "insert into tbCourseStatus values ('" + info.getId() + "','" + info.getName() + "','" + info.getSelector() + ");";
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
		info = (CourseStatusInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "update tbCourseStatus set courseName='" + info.getName() + "',selector='" + info.getSelector() + "' where ID='" + info.getId() + "';";
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
		info = (CourseStatusInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "delete from tbCourseStatus where ID='" + info.getId() + "';";
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
		info = (CourseStatusInfo)obj;
		
		if (info.getId() != null)
			query = "select * from tbCourseStatus where ID='" + info.getId() + "';";
		else if (info.getName() != null)
			query = "select * from tbCourseStatus where courseName='" + info.getName() + "';";
		
		try {
			Statement stmt = con.createStatement();
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
