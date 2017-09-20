package common;

import java.io.Serializable;

/**
 * 场馆预约记录信息
 * 
 * @author Silence
 *
 */
public class AppointStatusInfo implements Serializable{

	private static final long serialVersionUID = 7;
	private String userID;//预约用户ID
	private String item;//预约项目名称
	private int appointDate;//预约日期（数组下标）
	private int appointTime;//预约时间段（数组下标）
	private long timestamp;//预约时间戳
	
	
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
