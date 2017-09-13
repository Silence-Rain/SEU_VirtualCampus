package seu.vCampus.common;

public class Product {
	String productID;//商品ＩＤ
	String productName;//商品名称
	double price;//商品单价
	int remainNum;//商品库存量
	String supplier;//生产厂家
	String tab;//标签
	
	public void setProductID(String id){
		productID=id;
	}
	public String getProductID(){
		return productID;
	}
	public void setProductName(String name){
		productName=name;
	}
	public String getProductName(){
		return productName;
	}
	public void setPrice(double p){
		price=p;
	}
	public double getPrice(){
		return price;
	}
	public void setRemainNum(int r){
		remainNum=r;
	}
	public int getRemainNum(){
		return remainNum;
	}
	public void setSupplier(String s){
		supplier=s;
	}
	public String getSupplier(){
		return supplier;
	}
	public void setTab(String t){
		tab=t;
	}
	public String getTab(){
		return tab;
	}
}
