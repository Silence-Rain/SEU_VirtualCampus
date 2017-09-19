package common;

import java.io.Serializable;

public class BookInfo implements Serializable {
		
	private static final long serialVersionUID = 4;
	private int id;//书籍ID
	private String name;//书籍名
	private String isbn;//ISBN号
	private String author;//作者
	private String pub;//出版社
	private boolean isBorrowed;//是否已被借阅

	public BookInfo(int id, String name, String isbn, String author, String pub, boolean isBorrowed) {
		super();
		this.id = id;
		this.name = name;
		this.isbn = isbn;
		this.author = author;
		this.pub = pub;
		this.isBorrowed = isBorrowed;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPub() {
		return pub;
	}
	public void setPub(String pub) {
		this.pub = pub;
	}
	public boolean isBorrowed() {
		return isBorrowed;
	}
	public void setBorrowed(boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}
