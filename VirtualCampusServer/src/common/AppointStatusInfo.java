package common;

public class AppointStatusInfo {
	
	private String userID;
	private String item;
	private long appointDate;
	private long appointTime;
	
	public AppointStatusInfo(String userID, String item, long appointDate, long appointTime) {
		this.userID = userID;
		this.item = item;
		this.appointDate = appointDate;
		this.appointTime = appointTime;
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
	public long getAppointDate() {
		return appointDate;
	}
	public void setAppointDate(long appointDate) {
		this.appointDate = appointDate;
	}
	public long getAppointTime() {
		return appointTime;
	}
	public void setAppointTime(long appointTime) {
		this.appointTime = appointTime;
	}
}
