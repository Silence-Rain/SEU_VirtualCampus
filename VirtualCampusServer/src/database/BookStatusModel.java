package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.BookStatusInfo;

public class BookStatusModel implements Model{

	private Connection con;
	private String query;
	private BookStatusInfo info;
	
	public BookStatusModel() {
		this.con = DBConnection.getConnection();
		this.query = "";
		this.info = null;
	}

	@Override
	public boolean insert(Object obj) {
		info = (BookStatusInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "insert into tbBookStatus values (" + info.getId() + ",'" + info.getName() + "','" + info.getBorrower() 
			+ "'," + info.getBorrowDate() + "," + info.getReturnDate() + ");";
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
		info = (BookStatusInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "update tbBookStatus set bookName='" + info.getName() + "',borrower='" + info.getBorrower() + "',borrowDate=" 
			+ info.getBorrowDate()+ ",returnDate=" + info.getReturnDate() + " where ID=" + info.getId() + ";";
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
		info = (BookStatusInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "delete from tbBookStatus where ID=" + info.getId() + ";";
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
		info = (BookStatusInfo)obj;
		
		if (info.getBorrower() != null)
			query = "select * from tbBookStatus where borrower=" + info.getBorrower() + ";";
		else if (info.getName() != null)
			query = "select * from tbBookStatus where bookName='" + info.getName() + "';";
		
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
