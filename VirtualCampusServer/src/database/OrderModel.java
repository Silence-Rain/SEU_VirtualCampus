package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.OrderInfo;

public class OrderModel implements Model{
	
	private Connection con;
	private String query;
	private OrderInfo info;
	
	public OrderModel() {
		this.con = DBConnection.getConnection();
		this.query = "";
		this.info = null;
	}

	@Override
	public boolean insert(Object obj) {
		info = (OrderInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "insert into tbOrder (productName,buyer,buyNum,buyTime) values ('" + info.getName() + "','" + info.getBuyer() 
			+ "'," + info.getBuyNum() + "," + info.getBuyTime() + ");";
			System.out.println(query);
			
			if (stmt.executeUpdate(query) != 0) {
				query = "update tbGoods set remainNum=remainNum-" + info.getBuyNum() + " where productName='" + info.getName() + "';";
				System.out.println(query);
				if (stmt.executeUpdate(query) != 0)
					return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean modify(Object obj) {
		info = (OrderInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "update tbOrder set productName='" + info.getName() + "',buyNum=" + info.getBuyNum() 
			+ " where buyer='" + info.getBuyer() + "' and buyTime=" + info.getBuyTime() + " where id='" + info.getId() + ";";
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
		info = (OrderInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "delete from tbOrder where buyer='" + info.getBuyer() + "' and buyTime=" + info.getBuyTime() + ";";
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
		info = (OrderInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "select * from tbOrder where buyer='" + info.getBuyer() + "';";
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
			query = "select * from tbOrder;";
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
