package seu.vCampus.bz;

import java.util.List;

import common.GoodInfo;

public abstract interface IShopAdmin {
	public abstract boolean addProductAdmin(String id,String name,double price,int remainNum,String supplier,String tab);//管理员添加库存商品
	public abstract boolean deleteProductAdmin(String id);//管理员删除库存商品
	public abstract List inquireProduct();//按商品ID查询商品
	public abstract boolean changeProduct(String id,String name,String supplier,double price,int num,String tab);//管理员修改库存商品信息
}