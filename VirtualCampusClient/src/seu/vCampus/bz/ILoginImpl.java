package seu.vCampus.bz;

import common.MessageTypes;
import common.UserInfo;
import seu.vCampus.util.SocketHelper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ILoginImpl implements ILogin, MessageTypes {

	ObjectInputStream is;
	ObjectOutputStream os;

	public ILoginImpl(SocketHelper sockethelper) {
		this.is = sockethelper.getIs();
		this.os = sockethelper.getOs();
	}

	public boolean Login(UserInfo user) {
		try {
			this.os.writeInt(101);
			this.os.flush();
			this.os.writeObject(user);
			this.os.flush();

			if (this.is.readInt() == 1011) {
				return true;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	/*
	 * public boolean Login(String uId, String uPwd, String uTypes) { try { if
	 * (uTypes == "ѧ��") { this.os.writeInt(0); this.os.flush();
	 * this.os.writeUTF(uId); this.os.flush(); this.os.writeUTF(uPwd);
	 * this.os.flush();
	 * 
	 * return this.is.readBoolean(); } this.os.writeInt(1); this.os.flush();
	 * this.os.writeUTF(uId); this.os.flush(); this.os.writeUTF(uPwd);
	 * this.os.flush(); return this.is.readBoolean(); } catch (IOException e) {
	 * e.printStackTrace(); } return false; }
	 */
}