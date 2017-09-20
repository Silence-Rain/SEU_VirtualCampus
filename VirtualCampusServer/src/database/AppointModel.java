package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.AppointInfo;

public class AppointModel implements Model{

	private Connection con;//数据库连接
	private String query;//SQL查询语句
	private AppointInfo info;
	
	public AppointModel() {
		this.con = DBConnection.getConnection();
		this.query = "";
		this.info = null;
	}

	//数据库插入操作
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

	//数据库修改操作（以item为key修改itemRemain）
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

	//数据库删除操作（以item为key）
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

	//数据库查询操作，返回所有项目信息
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
	
	//数据库查询操作（以item为key）
	public Object searchByName(Object obj) {
		info = (AppointInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "select * from tbAppoint where item='" + info.getItem() + "';";
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
