package common;

import java.io.Serializable;

/**
 * 图书馆书籍借阅信息
 * （即tbBookStatus表的结构）
 * 
 * @author Silence
 *
 */
public class BookStatusInfo implements Serializable {
	
	private static final long serialVersionUID = 5;
	/**
	 * 书籍ID
	 */
	private int id;
	/**
	 * 书名
	 */
	private String name;
	/**
	 * 借阅者学号
	 */
	private String borrower;
	/**
	 * 借书时间（时间戳）
	 */
	private long borrowDate;
	/**
	 * 还书时间（时间戳）
	 */
	private long returnDate;

	public BookStatusInfo(int id, String name, String borrower, long borrowDate, long returnDate) {
		this.id = id;
		this.name = name;
		this.borrower = borrower;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBorrower() {
		return borrower;
	}
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	public long getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(long borrowDate) {
		this.borrowDate = borrowDate;
	}
	public long getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(long returnDate) {
		this.returnDate = returnDate;
	}

}
