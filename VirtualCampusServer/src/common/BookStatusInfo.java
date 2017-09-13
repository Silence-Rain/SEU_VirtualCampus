package common;

public class BookStatusInfo {
	
	private int id;
	private String name;
	private String borrower;
	private long borrowDate;
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
