package common;

import java.io.Serializable;

public class AppointStatusInfo implements Serializable{

	private static final long serialVersionUID = 7;
	private String userID;
	private String item;
	private int appointDate;
	private int appointTime;
	private long timestamp;
	
	
	public AppointStatusInfo(String userID, String item, int appointDate, int appointTime, long ts) {
		this.userID = userID;
		this.item = item;
		this.appointDate = appointDate;
		this.appointTime = appointTime;
		this.timestamp = ts;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getAppointDate() {
		return appointDate;
	}
	public void setAppointDate(int appointDate) {
		this.appointDate = appointDate;
	}
	public int getAppointTime() {
		return appointTime;
	}
	public void setAppointTime(int appointTime) {
		this.appointTime = appointTime;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long ts) {
		this.timestamp = ts;
	}
}
