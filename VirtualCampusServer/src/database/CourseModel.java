package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.CourseInfo;

public class CourseModel implements Model{

	private Connection con;
	private String query;
	private CourseInfo info;
	
	public CourseModel() {
		this.con = DBConnection.getConnection();
		this.query = "";
		this.info = null;
	}

	@Override
	public boolean insert(Object obj) {
		info = (CourseInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "insert into tbCourse values (" + info.getId() + ",'" + info.getName() + "','" + info.getTeacher() 
			+ "','" + info.getPlace() + "','" + info.getTime() + "'," + info.getCredit() + ");";
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
		info = (CourseInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "update tbCourse set courseName='" + info.getName() + "',teacher='" + info.getTeacher() + "',pub='" 
			+ info.getPlace() + "',time='" + info.getTime() + "',credit=" + info.getCredit() + " where ID=" + info.getId() + ";";
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
		info = (CourseInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "delete from tbCourse where ID=" + info.getId() + ";";
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
		info = (CourseInfo)obj;
		
		if (info.getId() != 0)
			query = "select * from tbCourse where ID=" + info.getId() + ";";
		else if (info.getName() != null)
			query = "select * from tbCourse where courseName='" + info.getName() + "';";
		
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
