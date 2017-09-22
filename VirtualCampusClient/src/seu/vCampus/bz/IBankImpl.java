package seu.vCampus.bz;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JTextField;

import common.Bank;
import common.BankInfo;
import common.GoodInfo;
import common.MsgType;
import seu.vCampus.util.SocketHelper;
import seu.vCampus.view.stu.mainView;
public  class IBankImpl implements IBank,MsgType{
	ObjectInputStream is;
	  ObjectOutputStream os;
	  String id = mainView.getStudentId();
	  

	  public IBankImpl(SocketHelper sockethelper)
	  {
		  this.is = sockethelper.getIs();
		  this.os = sockethelper.getOs();
	  }

	  public double checkAccount(String bCad) {
		  try {
		      this.os.writeInt(BANK_BALANCE_QUERY);
		      this.os.flush();
		      System.out.println(bCad);
		      BankInfo Banktemp = new BankInfo(bCad, 0,"", "", 0, 0);
		      //Banktemp.setId(bCad);
		      this.os.writeObject(Banktemp);
		      this.os.flush();
		      try
		      {
		    	  if(this.is.readInt() == 2011)
		        return  this.is.readDouble();
		      } catch (IOException e) {
		        e.printStackTrace();
		      }
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		    return 0;
	  }

	  public boolean transferAccount(double money,String receiver,String pwd,Long time, double banlance)
	  {
	      try {
				this.os.writeInt(BANK_TRANSFER);
	      this.os.flush();
	      String id = mainView.getStudentId();	
	      BankInfo Banktemp = new BankInfo(id, banlance,pwd, receiver, money,time);
	      Banktemp.setTransferAmount(money);
	      Banktemp.setTransferTo(receiver);
	      Banktemp.setPwd(pwd);
	     
	      this.os.writeObject(Banktemp);
	      this.os.flush();
	     
	    	  if(this.is.readInt() == 2021)
			        return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return false;
	  }
	  
	  public  List record(){
		  try {
			this.os.writeInt(BANK_TRANSFER_RECORD_QUERY);
			this.os.flush();
			BankInfo Banktemp = new BankInfo(id, 0,"", "", 0, 0);
		      this.os.writeObject(Banktemp);
		      this.os.flush();
		    	  if(this.is.readInt() == 2031)
					try {
						return Arrays.asList((BankInfo[])this.is.readObject());
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






