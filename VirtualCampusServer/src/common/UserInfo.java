package common;

import java.io.Serializable;

/**
 * 用户信息
 * （即tbUser表的结构）
 * 
 * @author Silence
 *
 */
public class UserInfo implements Serializable{

	private static final long serialVersionUID = 11;
	/**
	 * 学生ID（学号）
	 */
	private String stuId;
	/**
	 * 密码
	 */
	private String pwd;
	/**
	 * 类型（admin，student，teacher）
	 */
	private String type;
	/**
	 * 姓名
	 */
	private String name;
	
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
