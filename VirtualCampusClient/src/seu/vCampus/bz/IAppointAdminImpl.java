package seu.vCampus.bz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

import common.AppointInfo;
import common.GoodInfo;
import common.MessageTypes;
import common.MsgType;
import seu.vCampus.util.SocketHelper;

public class IAppointAdminImpl implements IAppointAdmin,MsgType{
	SocketHelper sockethelper;
	ObjectInputStream is;
	ObjectOutputStream os;
	 public IAppointAdminImpl(SocketHelper sockethelper)
	  {
		this.sockethelper = sockethelper;
	    this.is = sockethelper.getIs();
	    this.os = sockethelper.getOs();
	  }
	public boolean addItemAdmin(String name,String num) {
		try {
			System.out.println("进入函数");
			this.os.writeInt(APPOINT_ITEM_ADD);
			this.os.flush();
			System.out.println("发送了APPOINT_ITEM_ADD");
			
			
			String remain="";
			for(int i=0;i<3;i++) {
				remain=remain+num;
				for(int j=1;j<6;j++) {
					remain=remain+"&"+num;
				}
				remain=remain+";";
			}
			System.out.println(remain);
			AppointInfo item=new AppointInfo(name,remain);
			this.os.writeObject(item);
			
			this.os.flush();
			if(this.is.readInt()==APPOINT_ITEM_ADD_SUCCESS) {
					return true;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean deleteItemAdmin(String name) {
		try {
			this.os.writeInt(APPOINT_ITEM_DELETE);
			this.os.flush();
			AppointInfo item=new AppointInfo(name,"");
			this.os.writeObject(item);
			this.os.flush();
			if(this.is.readInt()==APPOINT_ITEM_DELETE_SUCCESS) {
					return true;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public AppointInfo[] inquireItemAdmin() {
		try {
			this.os.writeInt(APPOINT_ITEM_QUERY);
			this.os.flush();
			AppointInfo item=new AppointInfo("","");
			//this.os.writeObject(item);
			//this.os.flush();
			try {
				if(this.is.readInt()==APPOINT_ITEM_QUERY_SUCCESS) {
					return (AppointInfo[])(this.is.readObject());
				}
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean modifyItemAdmin(String name, String[][] array) {
		try {
			this.os.writeInt(APPOINT_ITEM_MODIFY);
			this.os.flush();
			String remainNum="";
			for(int i=0;i<3;i++) {
				for(int j=0;j<6;j++) {
					remainNum=remainNum+array[i][j]+"&";
				}
				remainNum=remainNum+";";
			}
			AppointInfo item=new AppointInfo(name,remainNum);
			this.os.writeObject(item);
			this.os.flush();
			if(this.is.readInt()==APPOINT_ITEM_MODIFY_SUCCESS) {
				return true;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
