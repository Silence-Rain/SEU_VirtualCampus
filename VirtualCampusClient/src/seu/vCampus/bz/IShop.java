package seu.vCampus.bz;

import java.util.Date;
import java.util.Vector;
import seu.vCampus.common.Product;
import seu.vCampus.common.Order;

public interface IShop {
//	public abstract void addProduct(String id);//添加购买商品
//	public abstract void deleteProduct(String id);//删除购买商品
//	public abstract void changeBuyNum(int n);//修改单品购买数量
	public abstract Vector<Product> inquireProduct(String name,String tab);//根据商品名称和标签查询商品
	public abstract Vector<Order> inquireOrder(String id,Date t);//根据下单日期查询订单
//	public abstract double cost(double p,int n);//计算订单总金额
	public abstract boolean buyOK(Vector<String>id,Vector<Integer>num,double cost);//确认购买
}