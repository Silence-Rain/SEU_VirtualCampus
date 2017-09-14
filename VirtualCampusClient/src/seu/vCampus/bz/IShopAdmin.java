package seu.vCampus.bz;

import java.util.Date;
import java.util.Vector;
import seu.vCampus.common.Order;

public interface IShopAdmin {
	public abstract boolean addProductAdmin(String id,String name,double price,int remainNum,String supplier,String tab);//添加库存商品
	public abstract boolean deleteProductAdmin(String id);//删除库存商品
	public abstract boolean changeProductPrice(String id,double price);//修改商品单价
	public abstract boolean changeProductRemainNum(String id,int num);//修改商品库存量
	public abstract boolean changeProductTab(String id,String tab);//修改商品标签
	public abstract Vector<Order> inquireOrder(Date t);//根据日期查询订单
}