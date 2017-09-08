package seu.vCampus.bz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import seu.vCampus.common.Bank;
import seu.vCampus.common.MessageTypes;
import seu.vCampus.util.SocketHelper;
public abstract class IBankImpl implements IBank,MessageTypes{
	ObjectInputStream is;
	  ObjectOutputStream os;

	  public IBankImpl(SocketHelper sockethelper)
	  {
	    this.is = sockethelper.getIs();
	    this.os = sockethelper.getOs();
	  }

	  public String checkAccount(String bCard, String bPwd) {
	    try {
	      this.os.writeInt(101);
	      this.os.flush();
	      this.os.writeObject(bCard);
	      this.os.flush();
	      this.os.writeObject(bPwd);
	      this.os.flush();
	      try
	      {
	        return (String)this.is.readObject();
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

	  public boolean transferAccount(String money,String receiveID)
	  {
	    try {
	      this.os.writeInt(100);
	      this.os.flush();
	      this.os.writeObject(money);
	      this.os.flush();
	      this.os.writeObject(receiveID);
	      this.os.flush();
	      try
	      {
	        return ((Boolean)this.is.readObject()).booleanValue();
	      } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    return false;
	  }
	}


