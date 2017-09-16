package common;

import java.io.Serializable;

public class BookInfo implements Serializable {
	
	private static final long serialVersionUID = 4;
	private int id;
	private String name;
	private String author;
	private String pub;
	private boolean isBorrowed;

	public BookInfo(int id, String name, String author, String pub, boolean isBorrowed) {
		this.id = id;
		this.name = name;
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

}
