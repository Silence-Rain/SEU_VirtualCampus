package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection con = null;
	private static String DBurl = "jdbc:Access:///C:/Ñ§Ï°/ÏîÄ¿/seu2017-java/db/vCampus.accdb";
	
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
