package seu.vCampus.bz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

import common.AppointStatusInfo;
import common.BookInfo;
import common.BookStatusInfo;
import seu.vCampus.util.SocketHelper;
import seu.vCampus.view.stu.mainView;

public class ILibraryImpl implements ILibrary{
	SocketHelper socketHelper = new SocketHelper();	
	ObjectInputStream is;
	ObjectOutputStream os;
	 String id = mainView.getStudentId();
	
	public ILibraryImpl(SocketHelper sockethelper){
		this.is = sockethelper.getIs();
		this.os = sockethelper.getOs();
	}
	
	public List EnquiryAllBook(BookInfo book){
		
			//�������е��鼮
			try{
				this.os.writeInt(401);
				this.os.flush();
				this.os.writeObject(book);
				this.os.flush();
				try{
					if(this.is.readInt() == 4011)
					return Arrays.asList((BookInfo[])this.is.readObject());//����AppointStatusInfo���͵�List
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
			return null;
		}
	
	public boolean BorrowBook(BookStatusInfo book){
		//�����鼮
		try{
			this.os.writeInt(411);
			this.os.flush();
			this.os.writeObject(book);
			this.os.flush();
			if(this.is.readInt() == 4111)
			    return true;//����AppointStatusInfo���͵�List
		}catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public List EnquiryRecord(BookStatusInfo book){
		//�鿴������ļ�¼
		try{
			this.os.writeInt(413);
			this.os.flush();
			this.os.writeObject(book);
			this.os.flush();
			try{
				if(this.is.readInt() == 4131)
				return Arrays.asList((BookStatusInfo[])this.is.readObject());//����AppointStatusInfo���͵�List
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;

		
	}
	public boolean ReturnBook(BookStatusInfo book){
		//����
		try{
			this.os.writeInt(412);
			this.os.flush();
			this.os.writeObject(book);
			this.os.flush();
			if(this.is.readInt() == 4121)
			    return true;//����AppointStatusInfo���͵�List
		}catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}
}
