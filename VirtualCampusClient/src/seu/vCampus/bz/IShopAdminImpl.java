package seu.vCampus.bz;

import java.util.Arrays;
import java.util.List;

import common.*;
import seu.vCampus.util.SocketHelper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import common.GoodInfo;
public class IShopAdminImpl implements IShopAdmin,MsgType{
	SocketHelper sockethelper = new SocketHelper();
	ObjectInputStream is;
	ObjectOutputStream os;
	 public IShopAdminImpl(SocketHelper sockethelper)
	  {
		System.out.println("还没有进入is");
	    this.is = sockethelper.getIs();
	    System.out.println("有进入is");
	    this.os = sockethelper.getOs();
	    System.out.println("有进入os");
	  }
	public boolean addProductAdmin(String id,String name,double price,int remainNum,String supplier,String tab) {
		try {
			this.os.writeInt(SHOP_GOODS_ADD);
			System.out.println("发了SHOP_GOODS_ADD");
			this.os.flush();
			GoodInfo p=new GoodInfo(Integer.valueOf(id),name,remainNum,price,supplier,tab);
			this.os.writeObject(p);
			this.os.flush();
			if(this.is.readInt()==SHOP_GOODS_ADD_SUCCESS) {
					return true;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean deleteProductAdmin(String id) {
		try {
			this.os.writeInt(SHOP_GOODS_DELETE);
			this.os.flush();
			GoodInfo p=new GoodInfo(Integer.valueOf(id),"",0,0.0,"","");
			this.os.writeObject(p);
			this.os.flush();
			if(this.is.readInt()==SHOP_GOODS_DELETE_SUCCESS) {
					return true;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public List inquireProduct() {
		try {
			this.os.writeInt(SHOP_GOODS_QUERY);
			this.os.flush();
			GoodInfo p=new GoodInfo(0,"",0,0.0,"","");
			this.os.writeObject(p);
			this.os.flush();
			try {
				if(this.is.readInt()==SHOP_GOODS_QUERY_SUCCESS) {
					return Arrays.asList((GoodInfo[])this.is.readObject());
				}
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
	public boolean changeProduct(String id,String name,String supplier,double price,int num,String tab) {
		try {
			this.os.writeInt(SHOP_GOODS_MODIFY);
			this.os.flush();
			GoodInfo p=new GoodInfo(Integer.valueOf(id),name,num,price,supplier,tab);
			this.os.writeObject(p);
			this.os.flush();
			if(this.is.readInt()==SHOP_GOODS_MODIFY_SUCCESS) {
					return true;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<OrderInfo> inquireOrderAdmin(long t) {
		try {
			this.os.writeInt(SHOP_ORDER_QUERY_ADMIN);
			this.os.flush();
			OrderInfo o=new OrderInfo(0,"","",0,t);
			this.os.writeObject(o);
			this.os.flush();
			try {
				if(this.is.readInt()==SHOP_ORDER_QUERY_ADMIN_SUCCESS) {
					List list=Arrays.asList(this.is.readObject());
					return list;
				}
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