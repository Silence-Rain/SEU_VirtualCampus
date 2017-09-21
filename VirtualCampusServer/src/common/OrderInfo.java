package common;

import java.io.Serializable;

/**
 * 商品订单信息
 * （即tbOrder表的结构）
 * 
 * @author Silence
 *
 */
public class OrderInfo implements Serializable {

	private static final long serialVersionUID = 9;
	/**
	 * 订单ID
	 */
	private int id;
	/**
	 * 商品名称
	 */
	private String name;
	/**
	 * 买家学号
	 */
	private String buyer;
	/**
	 * 购买数量
	 */
	private int buyNum;
	/**
	 * 购买时间
	 */
	private long buyTime;

	public OrderInfo(int id, String name, String buyer, int buyNum, long buyTime) {
		super();
		this.id = id;
		this.name = name;
		this.buyer = buyer;
		this.buyNum = buyNum;
		this.buyTime = buyTime;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public long getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(long buyTime) {
		this.buyTime = buyTime;
	}

}
