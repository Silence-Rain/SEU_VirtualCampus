package common;

public class UserInfo {
	private String stuId;
	private String pwd;
	private String type;
	
	public void setInfoByQuery(String info) {
		String res[] = info.split("&");
		
		setStuId(res[0]);
		setPwd(res[1]);
		setType(res[2]);
	}
	
	public void setStuId(String s) {
		this.stuId = s;
	}
	
	public String getStuId() {
		return this.stuId;
	}
	
	public void setPwd(String p) {
		this.pwd = p;
	}
	
	public String getPwd() {
		return this.pwd;
	}
	
	public void setType(String t) {
		this.type = t;
	}
	
	public String getType() {
		return this.type;
	}
}
