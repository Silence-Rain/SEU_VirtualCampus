package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.BookInfo;

public class BookModel implements Model{
	
	private Connection con;
	private String query;
	private BookInfo info;
	
	public BookModel() {
		this.con = DBConnection.getConnection();
		this.query = "";
		this.info = null;
	}

	@Override
	public boolean insert(Object obj) {
		info = (BookInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "insert into tbBook (bookName, ISBN, author, pub, isBorrowed) values ('" + info.getName() + "','" + info.getIsbn() + "','" + info.getAuthor() 
			+ "','" + info.getPub() + "'," + info.isBorrowed() + ");";
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
		info = (BookInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "update tbBook set bookName='" + info.getName() + "',ISBN='" + info.getIsbn() + "',author='" + info.getAuthor() + "',pub='" 
			+ info.getPub()+ "',isBorrowed=" + info.isBorrowed() + " where ID=" + info.getId() + ";";
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
		info = (BookInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "delete from tbBook where ID=" + info.getId() + ";";
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
		info = (BookInfo)obj;
		
		if (info.getId() != 0)
			query = "select * from tbBook where ID='" + info.getId() + "';";
		else if (!info.getName().equals(""))
			query = "select * from tbBook where bookName='" + info.getName() + "';";
		else if (!info.getAuthor().equals(""))
			query = "select * from tbBook where author='" + info.getAuthor() + "';";
		else
			query = "select * from tbBook;";
		
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
