package common;

public class AppointInfo {
	
	private String item;
	private String itemRemain;
	
	public AppointInfo(String item, String itemRemain) {
		this.item = item;
		this.itemRemain = itemRemain;
	}
	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getItemRemain() {
		return itemRemain;
	}
	public void setItemRemain(String itemRemain) {
		this.itemRemain = itemRemain;
	}
}
