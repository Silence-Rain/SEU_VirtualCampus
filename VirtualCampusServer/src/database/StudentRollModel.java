package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.StudentRollInfo;

/**
 * 学生学籍信息模块Model
 * 统一实现Model接口
 * 
 * @author Silence
 *
 */
public class StudentRollModel implements Model{
	
	/**
	 * 数据库连接
	 */
	private Connection con;
	/**
	 * SQL查询语句
	 */
	private String query;
	/**
	 * 学生学籍信息
	 */
	private StudentRollInfo info;
	
	public StudentRollModel() {
		this.con = DBConnection.getConnection();
		this.query = "";
		this.info = null;
	}	

	/**
	 * 数据库插入操作（实现Model接口）
	 * 
	 * @param obj 所插入的学生信息
	 * @return 是否插入成功
	 */
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

	/**
	 * 数据库修改操作（实现Model接口）
	 * 以学号ID为key
	 * 
	 * @param obj 所修改的学生信息
	 * @return 是否修改成功
	 */
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

	/**
	 * 数据库修改操作（实现Model接口）
	 * 以学号ID为key
	 * 
	 * @param obj 所修改的学生信息
	 * @return 是否修改成功
	 */
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

	/**
	 * 数据库查询操作（实现Model接口）
	 * 根据输入不同，以ID、ID与姓名为key，或返回所有学生信息
	 * 
	 * @param obj 所查询的信息
	 * @return 所查询的所有学生信息
	 */
	@Override
	public Object search(Object obj) {
		info = (StudentRollInfo)obj;
		
		if (info != null)
			query = "select * from tbStudentRoll;";
		else if (!info.getId().equals("")) {
				query = "select * from tbStudentRoll where ID='" + info.getId() + "';";
			
				if (!info.getName().equals("")) {
					query = "select * from tbStudentRoll where stuName='" + info.getName() + "' and ID='" + info.getId() + "';";
				}
		}
		
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
