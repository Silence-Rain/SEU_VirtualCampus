package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.BookInfo;

/**
 * 书籍信息模块Model
 * 统一实现Model接口
 * 
 * @author Silence
 *
 */
public class BookModel implements Model{
	
	/**
	 * 数据库连接
	 */
	private Connection con;
	/**
	 * SQL查询语句
	 */
	private String query;
	/**
	 * 书籍信息
	 */
	private BookInfo info;
	
	public BookModel() {
		this.con = DBConnection.getConnection();
		this.query = "";
		this.info = null;
	}

	/**
	 * 数据库插入操作（实现Model接口）
	 * 
	 * @param obj 所插入的书籍
	 * @return 是否插入成功
	 */
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

	/**
	 * 数据库修改操作（实现Model接口）
	 * 以ID为key
	 * 
	 * @param obj 所修改的书籍
	 * @return 是否修改成功
	 */
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

	/**
	 * 数据库删除操作（实现Model接口）
	 * 以ID为key
	 * 
	 * @param obj 所删除的书籍
	 * @return 是否删除成功
	 */
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

	/**
	 * 数据库修改操作（实现Model接口）
	 * 根据输入不同，分别以ID、书名、作者为key，若参数为null则返回所有书籍
	 * 
	 * @param obj 所查询的书籍
	 * @return 所查询的书籍详细信息
	 */
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
