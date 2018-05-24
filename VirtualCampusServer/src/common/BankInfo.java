package common;

import java.io.Serializable;

/**
 * 银行账户信息
 * （即tbBank表的结构）
 * 
 * @author Silence
 *
 */
public class BankInfo implements Serializable{

	private static final long serialVersionUID = 3;
	/**
	 * 用户ID
	 */
	private String id;
	/**
	 * 当前余额
	 */
	private double balance;
	/**
	 * 用户交易密码
	 */
	private String pwd;
	/**
	 * 转账对象
	 */
	private String transferTo;
	/**
	 * 转账数额
	 */
	private double transferAmount;
	/**
	 * 转账时间（时间戳）
	 */
	private long transferDate;
	
	public BankInfo(String id, double b, String pwd, String to, double am, long dt) {
		this.setId(id);
		this.setBalance(b);
		this.setPwd(pwd);
		this.setTransferTo(to);
		this.setTransferAmount(am);
		this.setTransferDate(dt);
	}
	
	public void setId(String param) {
		this.id = param;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setBalance(double param) {
		this.balance = param;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public void setPwd(String param) {
		this.pwd = param;
	}
	
	public String getPwd() {
		return this.pwd;
	}
	
	public void setTransferTo(String param) {
		this.transferTo = param;
	}
	
	public String getTransferTo() {
		return this.transferTo;
	}
	
	public void setTransferAmount(double param) {
		this.transferAmount = param;
	}
	
	public double getTransferAmount() {
		return this.transferAmount;
	}
	
	public void setTransferDate(long param) {
		this.transferDate = param;
	}
	
	public long getTransferDate() {
		return this.transferDate;
	}

}
