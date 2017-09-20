package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.StudentRollInfo;

public class StudentRollModel implements Model{
	
	private Connection con;
	private String query;
	private StudentRollInfo info;
	
	public StudentRollModel() {
		this.con = DBConnection.getConnection();
		this.query = "";
		this.info = null;
	}	

	@Override
	public boolean insert(Object obj) {
		info = (StudentRollInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "insert into tbStudentRoll values ('" + info.getId() + "','" + info.getName() + "','" + info.getAge()
			+ "','" + info.getGender() + "','" + info.getBirthday() + "','" + info.getBirthPlace() + "','" + info.getEntranceTime()
			+ "','" + info.getPhoto() + "','" + info.getNation() + "','" + info.getDepartment() + "','" + info.getMajor()
			+ "','" + info.getDormitory() + "');";
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
		info = (StudentRollInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "update tbStudentRoll set stuName='" + info.getName() + "',age='" + info.getAge()+ "',gender='" + info.getGender() 
			+ "',birthday='" + info.getBirthday() + "',birthPlace='" + info.getBirthPlace()+ "',entranceTime='" + info.getEntranceTime() 
			+ "',photo='" + info.getPhoto() + "',nation='" + info.getNation() + "',department='" + info.getDepartment() 
			+ "',major='" + info.getMajor() + "',dormitory='" + info.getDormitory() + "' where ID='" + info.getId() + "';";
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
		info = (StudentRollInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "delete from tbStudentRoll where ID='" + info.getId() + "';";
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
		info = (StudentRollInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "select * from tbStudentRoll where ID='" + info.getId() + "';";
			System.out.println(query);
			
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs != null)
				return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Object searchAll() {
		try {
			Statement stmt = con.createStatement();
			query = "select * from tbStudentRoll;";
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
