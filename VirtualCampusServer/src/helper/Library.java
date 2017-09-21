package helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import common.BookInfo;
import common.BookStatusInfo;
import database.BookModel;
import database.BookStatusModel;

/**
 * 图书馆模块Controller
 * 
 * @author Silence
 *
 */
public class Library {
	/**
	 * 书籍信息Model
	 */
	private BookModel bookModel;
	/**
	 * 书籍借阅状态Model
	 */
	private BookStatusModel bsModel;
	
	public Library() {
		this.bookModel = new BookModel();
		this.bsModel = new BookStatusModel();
	}
	
	/**
	 * 查询书籍响应函数
	 * 根据所传参数不同，采取不同方式查找
	 * 
	 * @param info 所查询的key（书名，作者，etc）
	 * @return 所查询书籍的详情
	 */
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
	
	/**
	 * 添加书籍响应函数
	 * 
	 * @param info 要添加的书籍
	 * @return 是否添加成功
	 */
	public boolean addBook(BookInfo info) {
		return bookModel.insert(info);
	}
	
	/**
	 * 修改书籍信息响应函数
	 * 
	 * @param info 要修改的书籍
	 * @return 是否修改成功
	 */
	public boolean modifyBook(BookInfo info) {
		return bookModel.modify(info);
	}
	
	/**
	 * 删除书籍响应函数
	 * 
	 * @param info 要删除的书籍
	 * @return 是否删除成功
	 */
	public boolean deleteBook(BookInfo info) {
		return bookModel.delete(info);
	}
	
	/**
	 * 查询借阅记录响应函数
	 * 
	 * @param info 当前用户信息
	 * @return 当前用户所有借阅记录
	 */
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
	
	/**
	 * 借书响应函数
	 * 通过ID在tbBook中查询到书籍完整信息，修改该书的isBorrowed属性为true
	 * 在tbBookStatus表中插入新的借阅记录
	 * 
	 * @param info 用户信息及书籍ID
	 * @return 是否借阅成功
	 */
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
	
	/**
	 * 还书响应函数
	 * 通过ID在tbBook中查询到书籍完整信息，修改该书的isBorrowed属性为false
	 * 在tbBookStatus表修改原借阅记录，增加还书时间字段
	 * 
	 * @param info 用户信息及书籍ID
	 * @return 是否还书成功
	 */
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
