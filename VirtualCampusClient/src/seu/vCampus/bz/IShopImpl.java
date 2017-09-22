package seu.vCampus.bz;

import seu.vCampus.util.SocketHelper;
import java.util.List;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import common.Bank;
import common.GoodInfo;
import common.MsgType;
import common.OrderInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.ParseException;

public class IShopImpl implements IShop, MsgType {
	
	

	ObjectInputStream is;
	ObjectOutputStream os;

	public IShopImpl(SocketHelper sockethelper) {
		this.is = sockethelper.getIs();
		this.os = sockethelper.getOs();
	}

	public List checkGoods() {
		try {
			this.os.writeInt(501);//////////////////////////////////////////////////////
			this.os.flush();
			GoodInfo Shoptemp = new GoodInfo(0, "", 0, 0, "", "");
		//	this.os.writeObject(Shoptemp);
		//	this.os.flush();
			try {
				if (this.is.readInt() == 5011) {
					System.out.println("jinlai 5011");
					return Arrays.asList((GoodInfo[]) this.is.readObject());
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public boolean addOrder(OrderInfo[] list) {
		try {
			this.os.writeInt(512);//////////////////////////////////////////////////
			this.os.flush();
			this.os.writeObject(list);
			this.os.flush();
			if (this.is.readInt() == 5121){
				System.out.println("jinlai 5121");
				return true;
			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List record(String id) {
		try {
			this.os.writeInt(513);///////////////////////////////////////
			this.os.flush();
			OrderInfo Shoptemp = new OrderInfo(0, "", "", 0, 0);
			this.os.writeObject(Shoptemp);
			this.os.flush();
			try {
				if (this.is.readInt() == 5131)
					return Arrays.asList((OrderInfo[]) this.is.readObject());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}