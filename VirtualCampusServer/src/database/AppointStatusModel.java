package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.AppointStatusInfo;

/**
 * 预约项目状态Model
 * 统一实现Model接口
 * 
 * @author Silence
 *
 */
public class AppointStatusModel implements Model{

	/**
	 * 数据库连接
	 */
	private Connection con;
	/**
	 * SQL查询语句
	 */
	private String query;
	/**
	 * 场馆预约项目信息
	 */
	private AppointStatusInfo info;
	
	public AppointStatusModel() {
		this.con = DBConnection.getConnection();
		this.query = "";
		this.info = null;
	}

	/**
	 * 数据库插入操作（实现Model接口）
	 * 
	 * @param obj 所插入的预约记录
	 * @return 是否插入成功
	 */
	@Override
	public boolean insert(Object obj) {
		info = (AppointStatusInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "insert into tbAppointStatus values ('" + info.getUserID() + "','" + info.getItem() + "'," + info.getAppointDate()
			+ "," + info.getAppointTime() + "," + info.getTimestamp() + ");";
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
	 * 以userID为key
	 * 
	 * @param obj 所修改的预约记录
	 * @return 是否修改成功
	 */
	@Override
	public boolean modify(Object obj) {
		info = (AppointStatusInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "update tbAppointStatus set item='" + info.getItem() + "',appointDate=" + info.getAppointDate() + ",appointTime='" 
			+ info.getAppointTime() + ",timestamp='" + info.getTimestamp() + " where userID='" + info.getUserID() + "';";
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
	 * 以userID为key
	 * 
	 * @param obj 所删除的预约记录
	 * @return 是否删除成功
	 */
	@Override
	public boolean delete(Object obj) {
		info = (AppointStatusInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "delete from tbAppointStatus where userID='" + info.getUserID() + "';";
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
	 * 根据输入判断以userID或item作为key
	 * 
	 * @param obj 所查询的预约记录
	 * @return 查询的所有记录
	 */
	@Override
	public Object search(Object obj) {
		info = (AppointStatusInfo)obj;
		
		if (info.getUserID() != null)
			query = "select * from tbAppointStatus where userID='" + info.getUserID() + "';";
		else if (info.getItem() != null)
			query = "select * from tbAppointStatus where item='" + info.getItem() + "';";
		
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
