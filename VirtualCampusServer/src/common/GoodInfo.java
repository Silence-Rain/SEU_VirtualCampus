package common;

import java.io.Serializable;

/**
 * 商品信息
 * （即tbGoods表的结构）
 * 
 * @author Silence
 *
 */
public class GoodInfo implements Serializable {

	private static final long serialVersionUID = 8;
	/**
	 * 商品ID
	 */
	private int id;
	/**
	 * 商品名
	 */
	private String name;
	/**
	 * 商品剩余数量
	 */
	private int remainNum;
	/**
	 * 商品单价
	 */
	private double price;
	/**
	 * 商品供应商
	 */
	private String supplier;
	/**
	 * 商品标签（衣、食、住、行）
	 */
	private String tag;
	
	public GoodInfo(int id, String name, int remainNum, double price, String supplier,
			String tag) {
		this.id = id;
		this.name = name;
		this.remainNum = remainNum;
		this.price = price;
		this.supplier = supplier;
		this.tag = tag;
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
	public int getRemainNum() {
		return remainNum;
	}
	public void setRemainNum(int remainNum) {
		this.remainNum = remainNum;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}
