package seu.vCampus.bz;

import seu.vCampus.common.*;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import seu.vCampus.util.SocketHelper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class IShopAdminImpl implements IShopAdmin,MessageTypes{
	ObjectInputStream is;
	ObjectOutputStream os;
	 public IShopAdminImpl(SocketHelper sockethelper)
	  {
	    this.is = sockethelper.getIs();
	    this.os = sockethelper.getOs();
	  }
	public boolean addProductAdmin(String id,String name,double price,int remainNum,String supplier,String tab) {
		try {
			this.os.writeInt(SHOP_ADD_ADMIN);
			this.os.flush();
			Product p=new Product();
			p.setProductID(id);
			p.setProductName(name);
			p.setPrice(price);
			p.setRemainNum(remainNum);
			p.setSupplier(supplier);
			p.setTab(tab);
			this.os.writeObject(p);
			this.os.flush();
			try {
				if(((Boolean)this.is.readObject()).booleanValue())
					return true;
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean deleteProductAdmin(String id) {
		try {
			this.os.writeInt(SHOP_DELETE_ADMIN);
			this.os.flush();
			Product p=new Product();
			p.setProductID(id);
			this.os.writeObject(p);
			this.os.flush();
			try {
				if(((Boolean)this.is.readObject()).booleanValue())
					return true;
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean changeProductRemainNum(String id,int num) {
		try {
			this.os.writeInt(SHOP_MODIFY_ADMIN);
			this.os.flush();
			Product p=new Product();
			p.setProductID(id);
			p.setRemainNum(num);
			this.os.writeObject(p);
			this.os.flush();
			try {
				if(((Boolean)this.is.readObject()).booleanValue())
					return true;
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean changeProductPrice(String id,double price) {
		try {
			this.os.writeInt(SHOP_MODIFY_ADMIN);
			this.os.flush();
			Product p=new Product();
			p.setProductID(id);
			p.setPrice(price);
			this.os.writeObject(p);
			this.os.flush();
			try {
				if(((Boolean)this.is.readObject()).booleanValue())
					return true;
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean changeProductTab(String id,String tab) {
		try {
			this.os.writeInt(SHOP_MODIFY_ADMIN);
			this.os.flush();
			Product p=new Product();
			p.setProductID(id);
			p.setTab(tab);
			this.os.writeObject(p);
			this.os.flush();
			try {
				if(((Boolean)this.is.readObject()).booleanValue())
					return true;
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public Vector<Order> inquireOrder(Date t) {
		try {
			this.os.writeInt(514);
			this.os.flush();
			this.os.writeObject(t);
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
}