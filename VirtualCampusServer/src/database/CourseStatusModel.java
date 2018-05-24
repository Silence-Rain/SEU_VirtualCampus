package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.CourseStatusInfo;

/**
 * 课程选择记录Model
 * 统一实现Model接口
 * 
 * @author Silence
 *
 */
public class CourseStatusModel implements Model{
	
	/**
	 * 数据库接口
	 */
	private Connection con;
	/**
	 * SQL查询语句
	 */
	private String query;
	/**
	 * 课程选择记录信息
	 */
	private CourseStatusInfo info;
	
	public CourseStatusModel() {
		this.con = DBConnection.getConnection();
		this.query = "";
		this.info = null;
	}

	/**
	 * 数据库插入操作（实现Model接口）
	 * 
	 * @param obj 所插入的记录
	 * @return 是否插入成功
	 */
	@Override
	public boolean insert(Object obj) {
		info = (CourseStatusInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "insert into tbCourseStatus values ('" + info.getId() + "','" + info.getSelector() + "');";
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
	 * @param obj 所修改的记录
	 * @return 是否修改成功
	 */
	@Override
	public boolean modify(Object obj) {
		info = (CourseStatusInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "update tbCourseStatus set selector='" + info.getSelector() + "' where ID='" + info.getId() + "';";
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
	 * @param obj 所删除的记录
	 * @return 是否删除成功
	 */
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

	/**
	 * 数据库查询操作（实现Model接口）
	 * 根据输入不同，以ID、选课学生学号为key
	 * 
	 * @param obj 所查询的信息
	 * @return 所查询选课记录详细信息
	 */
	@Override
	public Object search(Object obj) {
		info = (CourseStatusInfo)obj;
		
		if (!info.getId().equals(""))
			query = "select * from tbCourseStatus where ID='" + info.getId() + "';";
		else if (!info.getSelector().equals(""))
			query = "select * from tbCourseStatus where selector='" + info.getSelector() + "';";
		
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
