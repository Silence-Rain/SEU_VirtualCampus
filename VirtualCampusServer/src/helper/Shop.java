package helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import common.GoodInfo;
import common.OrderInfo;
import database.GoodModel;
import database.OrderModel;

public class Shop {
	private GoodModel goodModel;
	private OrderModel orderModel;
	
	public Shop() {
		this.goodModel = new GoodModel();
		this.orderModel = new OrderModel();
	}
	
	public GoodInfo queryGoods(GoodInfo info) {
		try {
			ResultSet rs = (ResultSet)goodModel.search(info);
			
			if (rs.next()) {		
				return new GoodInfo(rs.getInt("ID"), rs.getString("productName"), rs.getInt("remainNum"), rs.getDouble("price"), 
						rs.getString("supplier"), rs.getString("tag"));
			}
			
			return null;
			
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return null;
		} 
	}
	
	public OrderInfo[] queryOrderStuTea(OrderInfo info) {
		try {
			ResultSet rs = (ResultSet)orderModel.search(info);
			Vector<OrderInfo> v = new Vector<OrderInfo>();
			
			while (rs.next()) {
				OrderInfo temp = new OrderInfo(rs.getInt("ID"), rs.getString("productName"), rs.getString("buyer"), rs.getInt("buyNum"), 
						rs.getLong("buyTime"));
				
				v.add(temp);
			}
			
			OrderInfo arr[] = (OrderInfo[])v.toArray(new OrderInfo[rs.getRow()]);
			
			return arr;
			
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return null;
		} 
	}
	
	public OrderInfo[] queryOrderAdmin() {
		try {
			ResultSet rs = (ResultSet)orderModel.searchAll();
			Vector<OrderInfo> v = new Vector<OrderInfo>();
			
			while (rs.next()) {
				OrderInfo temp = new OrderInfo(rs.getInt("ID"), rs.getString("productName"), rs.getString("buyer"), rs.getInt("buyNum"), 
						rs.getLong("buyTime"));
				
				v.add(temp);
			}
			
			OrderInfo arr[] = (OrderInfo[])v.toArray(new OrderInfo[rs.getRow()]);
			
			return arr;
			
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return null;
		} 
	}
	
	public boolean addGood(GoodInfo info) {
		return goodModel.insert(info);
	}
	
	public boolean deleteGood(GoodInfo info) {
		return goodModel.delete(info);
	}
	
	public boolean modifyGood(GoodInfo info) {
		return goodModel.modify(info);
	}
	
	public boolean buy(OrderInfo[] info) {
		boolean flag = true;
		
		for (OrderInfo o: info) {
			flag &= orderModel.insert(o);
		}
		
		return flag;
	}
}
