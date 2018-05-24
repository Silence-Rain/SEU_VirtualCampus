package seu.vCampus.bz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

import common.BookInfo;
import common.GoodInfo;
import common.MsgType;
import seu.vCampus.util.SocketHelper;

public class ILibraryAdminImpl implements ILibraryAdmin,MsgType {
	
	SocketHelper sockethelper = new SocketHelper();
	ObjectInputStream is;
	ObjectOutputStream os;
	
	
	public ILibraryAdminImpl(SocketHelper sockethelper)
	{
		this.is = sockethelper.getIs();
		this.os = sockethelper.getOs();
	}
	public List inquireBook() {
		try {
			this.os.writeInt(LIBRARY_BOOK_QUERY);
			this.os.flush();
			BookInfo b=new BookInfo(0, "", "", "", "", false);
			this.os.writeObject(b);
			this.os.flush();
			try {
				if(this.is.readInt()==LIBRARY_BOOK_QUERY_SUCCESS) {
					return Arrays.asList((BookInfo[])this.is.readObject());
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
	public boolean addBook(String isbn,String name,String author,String pub,int num) {
		boolean flag=true;
		try {
			for(int i=0;i<num;i++) {
				this.os.writeInt(LIBRARY_BOOK_ADD);
				this.os.flush();
				BookInfo b=new BookInfo(0, name, isbn, author, pub, false);
				this.os.writeObject(b);
				this.os.flush();
				if(this.is.readInt()==LIBRARY_BOOK_ADD_SUCCESS) {
					//System.out.println("进入");
					flag&=true;
				}
				else {
					//System.out.println("wei进入");
					flag&=false;
				}
				//System.out.println(flag);
			}
			//System.out.println(flag);
			return flag;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean deleteBook(int id) {
		try {
			this.os.writeInt(LIBRARY_BOOK_DELETE);
			this.os.flush();
			BookInfo b=new BookInfo(id,"","","","",false);
			this.os.writeObject(b);
			this.os.flush();
			if(this.is.readInt()==LIBRARY_BOOK_DELETE_SUCCESS) {
				return true;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean modifyBook(int id,String isbn,String name,String author,String pub,boolean isBorrowed) {
		try {
			this.os.writeInt(LIBRARY_BOOK_MODIFY);
			this.os.flush();
			BookInfo b=new BookInfo(id,name,isbn,author,pub,isBorrowed);
			this.os.writeObject(b);
			this.os.flush();
			if(this.is.readInt()==LIBRARY_BOOK_MODIFY_SUCCESS) {
				System.out.println("收到LIBRARY_BOOK_MODIFY_SUCCESS)");
				return true;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
