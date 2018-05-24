package seu.vCampus.bz;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import common.OrderInfo;

public interface IShop {
	public abstract List checkGoods();
	public abstract boolean addOrder(OrderInfo[] list);
	public abstract List record(String id);
}