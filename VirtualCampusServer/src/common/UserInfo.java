package common;

public class UserInfo {
	private String stuId;
	private String pwd;
	private String type;
	private String card;
	private String name;
	
	public void setInfoByQuery(String info) {

		String res[] = info.split("&");
		
		setStuId(res[0]);
		setPwd(res[1]);
		setType(res[2]);
		
		if (res.length > 3) {
			setName(res[3]);
			setCard(res[4]);
		}
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
	
	public void setCard(String param) {
		this.card = param;
	}
	
	public String getCard() {
		return this.card;
	}
}
