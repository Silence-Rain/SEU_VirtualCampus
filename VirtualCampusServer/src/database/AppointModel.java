package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.AppointInfo;

/**
 * 场馆预约项目Model
 * 统一实现Model接口
 * 
 * @author Silence
 *
 */
public class AppointModel implements Model{

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
	private AppointInfo info;
	
	public AppointModel() {
		this.con = DBConnection.getConnection();
		this.query = "";
		this.info = null;
	}

	/**
	 * 数据库插入操作（实现Model接口）
	 * 
	 * @param obj 所插入的项目
	 * @return 是否插入成功
	 */
	@Override
	public boolean insert(Object obj) {
		info = (AppointInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "insert into tbAppoint values ('" + info.getItem() + "','" + info.getItemRemainStr() + "');";
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
	 * 以item为key修改itemRemain
	 * 
	 * @param obj 所修改的项目
	 * @return 是否修改成功
	 */
	@Override
	public boolean modify(Object obj) {
		info = (AppointInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "update tbAppoint set itemRemain='" + info.getItemRemainStr() + "' where item='" + info.getItem() + "';";
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
	 * 以item为key
	 * 
	 * @param obj 所删除的项目
	 * @return 是否删除成功
	 */
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
	
	/**
	 * 数据库查询操作（实现Model接口）
	 * 根据输入不同，以item为key或返回所有项目信息
	 * 
	 * @param obj 所查找的项目名称
	 * @return 项目具体信息
	 */
	@Override
	public Object search(Object obj) {
		info = (AppointInfo)obj;
		if (info == null)
			query = "select * from tbAppoint;";
		else
			query = "select * from tbAppoint where item='" + info.getItem() + "';";
			
		
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
