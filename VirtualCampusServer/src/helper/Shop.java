package helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import common.GoodInfo;
import common.OrderInfo;
import database.GoodModel;
import database.OrderModel;

/**
 * 商店模块Controller
 * 
 * @author Silence
 *
 */
public class Shop {
	/**
	 * 商品信息Model
	 */
	private GoodModel goodModel;
	/**
	 * 订单信息Model
	 */
	private OrderModel orderModel;
	
	public Shop() {
		this.goodModel = new GoodModel();
		this.orderModel = new OrderModel();
	}
	
	/**
	 * 查询商品信息响应函数
	 * 
	 * @return 所有商品信息
	 */
	public GoodInfo[] queryGoods() {
		try {
			ResultSet rs = (ResultSet)goodModel.search(null);
			Vector<GoodInfo> v = new Vector<GoodInfo>();
			
			while (rs.next()) {
				GoodInfo temp =  new GoodInfo(rs.getInt("ID"), rs.getString("productName"), rs.getInt("remainNum"), rs.getDouble("price"), 
						rs.getString("supplier"), rs.getString("tag"));
				v.add(temp);
			}
			
			GoodInfo arr[] = (GoodInfo[])v.toArray(new GoodInfo[rs.getRow()]);
			
			return arr;
			
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return null;
		} 
	}
	
	/**
	 * 查询订单信息（学生教师）响应函数
	 * 
	 * @param info 当前用户信息
	 * @return 该用户所有订单信息
	 */
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
	
	/**
	 * 查询订单信息（管理员）响应函数
	 * 
	 * @return 所有订单信息
	 */
	public OrderInfo[] queryOrderAdmin() {
		try {
			ResultSet rs = (ResultSet)orderModel.search(null);
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
	
	/**
	 * 添加商品响应函数
	 * 
	 * @param info 要添加的商品
	 * @return 是否添加成功
	 */
	public boolean addGood(GoodInfo info) {
		return goodModel.insert(info);
	}
	
	/**
	 * 删除商品响应函数
	 * 
	 * @param info 要删除的商品
	 * @return 是否删除成功
	 */
	public boolean deleteGood(GoodInfo info) {
		return goodModel.delete(info);
	}
	
	/**
	 * 修改商品信息响应函数
	 * 
	 * @param info 要修改的商品
	 * @return 是否修改成功
	 */
	public boolean modifyGood(GoodInfo info) {
		return goodModel.modify(info);
	}
	
	/**
	 * 购买响应函数
	 * 
	 * @param info 订单信息列表
	 * @return 是否购买成功
	 */
	public boolean buy(OrderInfo[] info) {
		boolean flag = true;
		
		for (OrderInfo o: info) {
			flag &= orderModel.insert(o);
		}
		
		return flag;
	}
}
