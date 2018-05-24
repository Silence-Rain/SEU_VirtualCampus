package seu.vCampus.bz;

import common.MessageTypes;
import common.StudentRollInfo;
import common.UserInfo;
import seu.vCampus.util.SocketHelper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class IUserImpl implements MessageTypes ,IUser{
//	SocketHelper socketHelper = new SocketHelper();
	ObjectInputStream is;
	ObjectOutputStream os;

	public IUserImpl(SocketHelper sockethelper) {
		this.is = sockethelper.getIs();
		this.os = sockethelper.getOs();
	}

	
	public StudentRollInfo getMoreInfo(StudentRollInfo sri) {
		try {
			this.os.writeInt(301);
			this.os.flush();
			this.os.writeObject(sri);
			this.os.flush();
			try {
				if(this.is.readInt() == 3011)
					return (StudentRollInfo) this.is.readObject();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean UserRegister(UserInfo user) {
		// ע���û�
		try {
			this.os.writeInt(102);
			this.os.flush();
			this.os.writeObject(user);
			this.os.flush();
			if (this.is.readInt() == 1021)
				return true;//
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean UserLogout(UserInfo user) {
		// ע���û�
		try {
			this.os.writeInt(103);
			this.os.flush();
	//		this.os.writeObject(user);
	//		this.os.flush();
			if (this.is.readInt() == 1031)
				return true;//
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}