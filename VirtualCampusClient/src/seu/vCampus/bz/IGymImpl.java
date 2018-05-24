package seu.vCampus.bz;
import common.CourseInfo;
import common.MessageTypes;
import seu.vCampus.util.SocketHelper;
import seu.vCampus.view.stu.mainView;
import common.AppointInfo;
import common.AppointStatusInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Arrays;

public class IGymImpl implements IGym,MessageTypes{
	SocketHelper socketHelper = new SocketHelper();	
	ObjectInputStream is;
	ObjectOutputStream os;
	 String id = mainView.getStudentId();
	
	public IGymImpl(SocketHelper sockethelper){
		this.is = sockethelper.getIs();
		this.os = sockethelper.getOs();
	}
	
	public List EnquiryRecord(AppointStatusInfo info){
		//����ԤԼ�ļ�¼
		try{
			this.os.writeInt(714);
			this.os.flush();
			this.os.writeObject(info);
			this.os.flush();
			try{
				if(this.is.readInt() == 7141)
				return Arrays.asList((AppointStatusInfo[])this.is.readObject());//����AppointStatusInfo���͵�List
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public AppointInfo[] EnquiryItem(){
		//������Ŀ���Ʋ���ʣ�ೡ��
		try{
			this.os.writeInt(701);
			this.os.flush();
	
			try{
				if(this.is.readInt() == 7011)
				return (AppointInfo[])this.is.readObject();//
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean AppointItem(AppointStatusInfo item){
		//ԤԼ��Ŀ
		try{
			this.os.writeInt(711);
			this.os.flush();
			this.os.writeObject(item);
			this.os.flush();
			if(this.is.readInt() == 7111)
				return true;//
		}catch(IOException e){
			e.printStackTrace();
		}
		return false;
		
	}
}
