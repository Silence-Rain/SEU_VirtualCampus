package common;

import java.io.Serializable;

public class UserInfo implements Serializable{

	private static final long serialVersionUID = 11;
	private String stuId;//学生ID（学号）
	private String pwd;//密码
	private String type;//类型（admin，student，teacher）
	private String name;//姓名
	
	public UserInfo(String id, String p, String t, String n) {
		this.setStuId(id);
		this.setPwd(p);
		this.setType(t);
		this.setName(n);
	}
	
	public void setStuId(String param) {
		this.stuId = param;
	}
	
	public String getStuId() {
		return this.stuId;
	}
	
	public void setPwd(String param) {
		this.pwd = param;
	}
	
	public String getPwd() {
		return this.pwd;
	}
	
	public void setType(String param) {
		this.type = param;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setName(String param) {
		this.name = param;
	}
	
	public String getName() {
		return this.name;
	}
	
}
