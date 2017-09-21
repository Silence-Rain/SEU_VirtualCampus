package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.BankInfo;

/**
 * 银行模块Model
 * 统一实现Model接口
 * 
 * @author Silence
 *
 */
public class BankModel implements Model{
	/**
	 * 数据库连接
	 */
	private Connection con;
	/**
	 * SQL查询语句
	 */
	private String query;
	/**
	 * 银行账户信息
	 */
	private BankInfo info;
	
	public BankModel() {
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
		info = (BankInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "insert into tbBank values ('" + info.getId() + "'," + info.getBalance() + ",'" + info.getPwd() + "','" + info.getTransferTo() + "',"
			+ info.getTransferAmount() + "," + info.getTransferDate() + ");";
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
	 * @param obj 所修改的记录
	 * @return 是否修改成功
	 */
	@Override
	public boolean modify(Object obj) {
		info = (BankInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "update tbBank set balance=" + info.getBalance() + ",pwd='" + info.getPwd() + ",transferTo=" + info.getTransferTo()
			+ "',transferAmount=" + info.getTransferAmount() + ",transferDate=" + info.getTransferDate() + " where userID='" + info.getId() + "';";
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
	 * @param obj 所删除的记录
	 * @return 是否删除成功
	 */
	@Override
	public boolean delete(Object obj) {
		info = (BankInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "delete from tbBank where userID='" + info.getId() + "';";
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
	 * 根据输入，以userID为key或以userID，密码共同作为key
	 * 
	 * @param obj 当前用户信息
	 * @return 当前用户的所有记录
	 */
	@Override
	public Object search(Object obj) {
		info = (BankInfo)obj;
		
		if (!info.getId().equals("")) {
			query = "select * from tbBank where userID='" + info.getId() + "' order by transferDate;";
			
			if (!info.getPwd().equals("")) {
				query = "select * from tbBank where userID='" + info.getId() + "' and pwd='" + info.getPwd() + "' order by transferDate;";
			}
		}
		
		try {
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
