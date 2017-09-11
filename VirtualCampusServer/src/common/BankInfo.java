package common;

public class BankInfo {
	private String id;
	private double balance;
	private String transferTo;
	private double transferAmount;
	private long transferDate;

	public void setInfoByQuery(String info) {
		String res[] = info.split("&");
		
		setId(res[0]);
		
		if (res.length > 1) {
			setBalance(Double.parseDouble(res[1]));
			setTransferTo(res[2]);
			setTransferAmount(Double.parseDouble(res[3]));
			setTransferDate(Long.parseLong(res[4]));
		}
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
