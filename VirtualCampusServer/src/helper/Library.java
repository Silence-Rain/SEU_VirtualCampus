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
				BookInfo temp = new BookInfo(rs.getInt("ID"), rs.getString("bookName"), rs.getString("author"), rs.getString("pub"), 
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
		info.setBorrowDate(System.currentTimeMillis());
		return bsModel.insert(info);
	}
	
	public boolean returnBook(BookStatusInfo info) {
		info.setReturnDate(System.currentTimeMillis());
		return bsModel.modify(info);
	}
}
