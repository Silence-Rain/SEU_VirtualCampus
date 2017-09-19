package helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import common.BookInfo;
import common.BookStatusInfo;
import database.BookModel;
import database.BookStatusModel;

public class Library {
	private BookModel bookModel;
	private BookStatusModel bsModel;
	
	public Library() {
		this.bookModel = new BookModel();
		this.bsModel = new BookStatusModel();
	}
	
	public BookInfo[] queryBook(BookInfo info) {
		try {
			ResultSet rs = (ResultSet)bookModel.search(info);
			Vector<BookInfo> v = new Vector<BookInfo>();
			
			while (rs.next()) {	
				BookInfo temp = new BookInfo(rs.getInt("ID"), rs.getString("bookName"), rs.getString("ISBN"), rs.getString("author"), rs.getString("pub"), 
						rs.getBoolean("isBorrowed"));
				v.add(temp);
			}
			
			BookInfo arr[] = (BookInfo[])v.toArray(new BookInfo[rs.getRow()]);
			
			return arr;
			
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return null;
		} 
	}
	
	public boolean addBook(BookInfo info) {
		return bookModel.insert(info);
	}
	
	public boolean modifyBook(BookInfo info) {
		return bookModel.modify(info);
	}
	
	public boolean deleteBook(BookInfo info) {
		return bookModel.delete(info);
	}
	
	public BookStatusInfo[] queryStatus(BookStatusInfo info) {
		try {
			ResultSet rs = (ResultSet)bsModel.search(info);
			Vector<BookStatusInfo> v = new Vector<BookStatusInfo>();
			
			while (rs.next()) {	
				BookStatusInfo temp = new BookStatusInfo(rs.getInt("ID"), rs.getString("bookName"), rs.getString("borrower"), rs.getLong("borrowDate"), 
						rs.getLong("returnDate"));
				v.add(temp);
			}
			
			BookStatusInfo arr[] = (BookStatusInfo[])v.toArray(new BookStatusInfo[rs.getRow()]);
			
			return arr;
			
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return null;
		} 
	}
	
	public boolean borrowBook(BookStatusInfo info) {
		BookInfo temp = new BookInfo(info.getId(), null, null, null, null, false);
		boolean flag = false;
		ResultSet rs = (ResultSet)bookModel.search(temp);
		
		try {
			if (rs.next()) {
				temp.setName(rs.getString("bookName"));
				temp.setIsbn(rs.getString("ISBN"));
				temp.setAuthor(rs.getString("author"));
				temp.setPub(rs.getString("pub"));
				flag = rs.getBoolean("isBorrowed");
				temp.setBorrowed(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (!flag)
			return bookModel.modify(temp) && bsModel.insert(info);
		else
			return false;
	}
	
	public boolean returnBook(BookStatusInfo info) {
		BookInfo temp = new BookInfo(info.getId(), null, null, null, null, false);
		boolean flag = true;
		ResultSet rs = (ResultSet)bookModel.search(temp);
		
		try {
			if (rs.next()) {
				temp.setName(rs.getString("bookName"));
				temp.setIsbn(rs.getString("ISBN"));
				temp.setAuthor(rs.getString("author"));
				temp.setPub(rs.getString("pub"));
				flag = rs.getBoolean("isBorrowed");
				temp.setBorrowed(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (flag)
			return bookModel.modify(temp) && bsModel.modify(info);
		else
			return false;
	}
}
