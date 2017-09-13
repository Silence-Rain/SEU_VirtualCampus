package seu.vCampus.common;

import java.util.Date;
import java.util.Vector;

public class Order {
	String userID;//下单人的ＩＤ
	String orderID;//订单ＩＤ
	double cost;//订单总金额
	Date time;//下单时间
	Vector<String>vectorID;//订单里所有商品ID
	Vector<Integer>vectorBuyNum;//订单里每个商品的购买个数
	
	public void setUserID(String id) {
		userID=id;
	}
	public String getUserID() {
		return userID;
	}
	public void setCost(double c){
		cost=c;
	}
	public double getCost(){
		return cost;
	}
	public void setTime(Date t){
		time=t;
	}
	public Date getTime(){
		return time;
	}
	public void setOrderID(String o){
		orderID=o;
	}
	public String getOrderID(){
		return orderID;
	}
	public void setEveryProductID(Vector<String> id) {
		vectorID=id;
	}
	public Vector<String> getEveryProductID(){
		return vectorID;
	}
	public void setEveryProductBuyNum(Vector<Integer> num) {
		vectorBuyNum=num;
	}
	public Vector<Integer> getEveryProductBuyNum(){
		return vectorBuyNum;
	}
}
