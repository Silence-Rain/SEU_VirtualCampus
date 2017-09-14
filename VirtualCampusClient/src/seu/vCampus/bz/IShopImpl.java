package seu.vCampus.bz;

import seu.vCampus.common.Product;
import seu.vCampus.common.Order;
import seu.vCampus.common.MessageTypes;
import seu.vCampus.util.SocketHelper;
import java.util.List;
import java.util.Date;
import java.util.Vector;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;

public class IShopImpl implements IShop,MessageTypes{
	
	ObjectInputStream is;
	ObjectOutputStream os;
	 public IShopImpl(SocketHelper sockethelper)
	  {
	    this.is = sockethelper.getIs();
	    this.os = sockethelper.getOs();
	  }
	public Vector<Product> inquireProduct(String name,String tab) {
		try {
			this.os.writeInt(SHOP_GOODS_QUERY);
			this.os.flush();
			Product p=new Product();
			p.setProductName(name);
			p.setTab(tab);
			this.os.writeObject(p);
			this.os.flush();
			try {
				return (Vector<Product>)this.is.readObject();
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Vector<Order> inquireOrder(String id,Date t) {
		try {
			this.os.writeInt(SHOP_ORDER_QUERY_STUTEA);
			this.os.flush();
			Order order=new Order();
			order.setUserID(id);
			order.setTime(t);
			this.os.writeObject(order);
			this.os.flush();
			try {
				return (Vector<Order>)this.is.readObject();
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/*
	public double cost(double price,int n) {
		try {
			price=(double) this.is.readObject();
			return price*n;
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	*/
	public boolean buyOK(Vector<String>id,Vector<Integer>num,double cost) {
		try {
			this.os.writeInt(SHOP_BUY_STUTEA);
			this.os.flush();
			Order order=new Order();
			order.setEveryProductID(id);
			order.setEveryProductBuyNum(num);
			order.setCost(cost);
			this.os.writeObject(order);
			this.os.flush();
			try {
				if(((Boolean)this.is.readObject()).booleanValue())
					return true;
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}