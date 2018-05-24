package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.OrderInfo;

/**
 * 订单信息Model
 * 统一实现Model接口
 * 
 * @author Silence
 *
 */
public class OrderModel implements Model{
	
	/**
	 * 数据库连接
	 */
	private Connection con;
	/**
	 * SQL查询语句
	 */
	private String query;
	/**
	 * 订单信息
	 */
	private OrderInfo info;
	
	public OrderModel() {
		this.con = DBConnection.getConnection();
		this.query = "";
		this.info = null;
	}

	/**
	 * 数据库插入操作（实现Model接口）
	 * 
	 * @param obj 所插入的订单
	 * @return 是否插入成功
	 */
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

	/**
	 * 数据库修改操作（实现Model接口）
	 * 以买家学号，购买时间为key
	 * 
	 * @param obj 所修改的订单
	 * @return 是否修改成功
	 */
	@Override
	public boolean modify(Object obj) {
		info = (OrderInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "update tbOrder set productName='" + info.getName() + "',buyNum=" + info.getBuyNum() 
			+ " where buyer='" + info.getBuyer() + "' and buyTime=" + info.getBuyTime() + ";";
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
	 * 以买家学号，购买时间为key
	 * 
	 * @param obj 所修改的订单
	 * @return 是否修改成功
	 */
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

	/**
	 * 数据库查询操作（实现Model接口）
	 * 根据输入不同，以买家学号为key或返回所有订单信息
	 * 
	 * @param obj 所查询的信息
	 * @return 所查询的所有订单信息
	 */
	@Override
	public Object search(Object obj) {
		info = (OrderInfo)obj;
		
		if (info == null)
			query = "select * from tbOrder;";
		else
			query = "select * from tbOrder where buyer='" + info.getBuyer() + "';";
		
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
