package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import common.UserInfo;
import database.DBConnection;

/**
 * 登录模块Model
 * 统一实现Model接口
 * 
 * @author Silence
 *
 */
public class LoginModel implements Model{
	
	/**
	 * 数据库连接
	 */
	private Connection con;
	/**
	 * SQL查询语句
	 */
	private String query;
	/**
	 * 用户账户信息
	 */
	private UserInfo info;
	
	public LoginModel() {
		this.info = null;
		this.con = DBConnection.getConnection();
		this.query = "";
	}
	
	/**
	 * 数据库插入操作（实现Model接口）
	 * 
	 * @param obj 所插入的用户
	 * @return 是否插入成功
	 */
	@Override
	public boolean insert(Object obj) {
		info = (UserInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "insert into tbUser values ('" + info.getStuId() + "','" + info.getPwd() + "','" + info.getType() + "','" 
			+ info.getName() + "');";
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
	 * 以u_ID为key
	 * 
	 * @param obj 所修改的用户
	 * @return 是否修改成功
	 */
	@Override
	public boolean modify(Object obj) {
		info = (UserInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "update tbUser set u_Pwd='" + info.getPwd() + "',u_Type='" + info.getType() + "',u_Name=" + info.getName() 
			+ "' where u_ID='" + info.getStuId() + "';";
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
	 * 以u_ID为key
	 * 
	 * @param obj 所删除的用户
	 * @return 是否删除成功
	 */
	@Override
	public boolean delete(Object obj) {
		info = (UserInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "delete from tbUser where u_ID='" + info.getStuId() + "';";
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
	 * 以u_ID为key
	 * 
	 * @param obj 所查询的用户
	 * @return 所查询用户详细信息
	 */
	@Override
	public Object search(Object obj) {
		info = (UserInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "select * from tbUser where u_ID='" + info.getStuId() + "';";
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
