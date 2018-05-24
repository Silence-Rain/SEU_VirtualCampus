package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.BookStatusInfo;

/**
 * 书籍借阅信息Model
 * 统一实现Model接口
 * 
 * @author Silence
 *
 */
public class BookStatusModel implements Model{

	/**
	 * 数据库连接
	 */
	private Connection con;
	/**
	 * SQL查询语句
	 */
	private String query;
	/**
	 * 书籍借阅信息
	 */
	private BookStatusInfo info;
	
	public BookStatusModel() {
		this.con = DBConnection.getConnection();
		this.query = "";
		this.info = null;
	}

	/**
	 * 数据库插入操作（实现Model接口）
	 * 
	 * @param obj 所插入的借阅记录
	 * @return 是否插入成功
	 */
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

	/**
	 * 数据库修改操作（实现Model接口）
	 * 以ID和借阅时间共同作为key，修改该记录的还书时间
	 * 
	 * @param obj 所修改的借阅记录
	 * @return 是否修改成功
	 */
	@Override
	public boolean modify(Object obj) {
		info = (BookStatusInfo)obj;
		
		try {
			Statement stmt = con.createStatement();
			query = "update tbBookStatus set returnDate=" + info.getReturnDate() + " where ID=" + info.getId() + " and borrowDate="
			+ info.getBorrowDate() + ";";
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
	 * 以ID作为key
	 * 
	 * @param obj 所删除的借阅记录
	 * @return 是否删除成功
	 */
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

	/**
	 * 数据库查询操作（实现Model接口）
	 * 根据输入不同，以借阅人或书名作为key
	 * 
	 * @param obj 当前用户信息
	 * @return 所查询的所有借阅记录
	 */
	@Override
	public Object search(Object obj) {
		info = (BookStatusInfo)obj;
		
		if (!info.getBorrower().equals(""))
			query = "select * from tbBookStatus where borrower='" + info.getBorrower() + "';";
		else if (!info.getName().equals(""))
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
