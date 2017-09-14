package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.BankInfo;

public class BankModel implements Model{
	private Connection con;
	private String query;
	private BankInfo info;
	
	public BankModel() {
		this.con = DBConnection.getConnection();
		this.query = "";
		this.info = null;
	}	

	@Override
	public boolean insert(Object obj) {
		info = (BankInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "insert into tbBank values ('" + info.getId() + "'," + info.getBalance() + ",'" + info.getTransferTo() + "',"
			+ info.getTransferAmount() + "," + info.getTransferDate() + ");";
			System.out.println(query);
			
			if (stmt.executeUpdate(query) != 0)
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean modify(Object obj) {
		info = (BankInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "update tbBank set balance=" + info.getBalance() + ",transferTo=" + info.getTransferTo()
			+ "',transferAmount=" + info.getTransferAmount() + ",transferDate=" + info.getTransferDate() + " where userID='" + info.getId() + "';";
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

	@Override
	public Object search(Object obj) {
		info = (BankInfo)obj;
		
		try {
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			query = "select * from tbBank where userID='" + info.getId() + "' order by transferDate;";
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
