package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.GoodInfo;

/**
 * 商品信息Model
 * 统一实现Model接口
 * 
 * @author Silence
 *
 */
public class GoodModel implements Model{
	
	/**
	 * 数据库连接
	 */
	private Connection con;
	/**
	 * SQL查询语句
	 */
	private String query;
	/**
	 * 商品信息
	 */
	private GoodInfo info;
	
	public GoodModel() {
		this.con = DBConnection.getConnection();
		this.query = "";
		this.info = null;
	}

	/**
	 * 数据库插入操作（实现Model接口）
	 * 
	 * @param obj 所插入的商品
	 * @return 是否插入成功
	 */
	@Override
	public boolean insert(Object obj) {
		info = (GoodInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "insert into tbGoods values (" + info.getId() + ",'" + info.getName() + "'," + info.getRemainNum() 
			+ "," + info.getPrice() + ",'" + info.getSupplier()+ "','" + info.getTag() + "');";
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
		info = (GoodInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "update tbGoods set productName='" + info.getName() + "',remainNum=" + info.getRemainNum() + ",price=" 
			+ info.getPrice()+ ",supplier='" + info.getSupplier() + "',tag='" + info.getTag() + "' where ID=" + info.getId() + ";";
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
		info = (GoodInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "delete from tbGoods where ID=" + info.getId() + ";";
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
	 * 
	 * @param obj 此处直接传入null
	 * @return 返回所有商品信息
	 */
	@Override
	public Object search(Object obj) {

		query = "select * from tbGoods;";
		
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
