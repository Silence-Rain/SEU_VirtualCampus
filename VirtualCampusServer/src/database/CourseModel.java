package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.CourseInfo;

/**
 * 课程信息Model
 * 统一实现Model接口
 * 
 * @author Silence
 *
 */
public class CourseModel implements Model{

	/**
	 * 数据库连接
	 */
	private Connection con;
	/**
	 * SQL查询语句
	 */
	private String query;
	/**
	 * 课程信息
	 */
	private CourseInfo info;
	
	public CourseModel() {
		this.con = DBConnection.getConnection();
		this.query = "";
		this.info = null;
	}

	/**
	 * 数据库插入操作（实现Model接口）
	 * 
	 * @param obj 所插入的课程
	 * @return 是否插入成功
	 */
	@Override
	public boolean insert(Object obj) {
		info = (CourseInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "insert into tbCourse values ('" + info.getId() + "','" + info.getName() + "','" + info.getTeacher() 
			+ "','" + info.getPlace() + "','" + info.getTime() + "'," + info.getCredit() + ");";
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
	 * 以ID为key
	 * 
	 * @param obj 所修改的课程
	 * @return 是否修改成功
	 */
	@Override
	public boolean modify(Object obj) {
		info = (CourseInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "update tbCourse set courseName='" + info.getName() + "',teacher='" + info.getTeacher() + "',place='" 
			+ info.getPlace() + "',time='" + info.getTime() + "',credit=" + info.getCredit() + " where ID='" + info.getId() + "';";
			System.out.println(query);
			
			if (stmt.executeUpdate(query) != 0)
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * 数据库删除操作（实现Model接口）
	 * 以ID为key
	 * 
	 * @param obj 所删除的课程
	 * @return 是否删除成功
	 */
	@Override
	public boolean delete(Object obj) {
		info = (CourseInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "delete from tbCourse where ID='" + info.getId() + "';";
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
	 * 根据输入不同，以ID、教师姓名为key或返回所有课程信息
	 * 
	 * @param obj 所查询的课程
	 * @return 所查询课程详细信息
	 */
	@Override
	public Object search(Object obj) {
		info = (CourseInfo)obj;

		if (info == null)
			query = "select * from tbCourse;";
		else if (!info.getId().equals(""))
			query = "select * from tbCourse where ID='" + info.getId() + "';";
		else if (!info.getTeacher().equals(""))
			query = "select * from tbCourse where teacher='" + info.getTeacher() + "';";
		
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
