package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 建立数据库连接，加载JDBC驱动
 * 
 * @author Silence
 *
 */
public class DBConnection {
	/**
	 * 数据库连接
	 */
	private static Connection con = null;
	/**
	 * 数据库文件相对路径
	 */
	private static String DBurl = "jdbc:Access:///../db/vCampus.accdb";
	
	/**
	 * 建立与数据库的连接
	 * 静态方法，其他model中直接调用
	 * 
	 * @return 数据库连接
	 */
	public static Connection getConnection() {
		if (con == null) {
			try{
				Class.forName("com.hxtt.sql.access.AccessDriver");
				System.out.println("Driver loaded");
				
				con = DriverManager.getConnection(DBurl);
				System.out.println("Database connected");
			}
			catch (ClassNotFoundException e){
				System.out.println("Fail to load driver");
			}
			catch (SQLException e){
				System.out.println("Fail to connect database");
			}
		}
		
		return con;
	}
}
