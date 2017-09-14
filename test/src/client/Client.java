package client;

import java.io.*;
import java.net.*;

import common.BankInfo;
import common.StudentRollInfo;
import common.UserInfo;


public class Client extends Thread implements MsgType{

	public static void main(String[] args) throws ClassNotFoundException {
		
		Socket socket = null;
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		
		String curUser = "09015331";
		
		try{
			socket = new Socket("localhost", 8081);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());	


			UserInfo info = new UserInfo("09015331", "admin", "admin", "", "");
			oos.writeInt(LOGIN);
			oos.writeObject(info);
			oos.flush();	
			if (ois.readInt() == LOGIN_SUCCESS)
				System.out.println("Login success");
			else
				System.out.println("Login fail");
			
			/*
			//Bank Module
			BankInfo bankInfo = new BankInfo("09015331", 3000, "09015336", -500, 1506000000);
			oos.writeInt(BANK_TRANSFER);
			oos.writeObject(bankInfo);
			oos.flush();
			if (ois.readInt() == BANK_TRANSFER_SUCCESS)
				System.out.println("Transfer success");
			else
				System.out.println("Transfer fail");
			
			
			oos.writeInt(BANK_TRANSFER_RECORD_QUERY);
			oos.writeObject(bankInfo);
			oos.flush();
			if (ois.readInt() == BANK_TRANSFER_RECORD_SUCCESS) {
				BankInfo res[] = (BankInfo[])ois.readObject();
				
				for (BankInfo b: res) {
					System.out.println(b.getId()+"\t"+b.getBalance()+"\t"+b.getTransferTo()+"\t"+b.getTransferAmount()+"\t"+b.getTransferDate());
				}
			}	
			else
				System.out.println("Query fail");
			*/
			
			
			/*
			//StudentRoll Module
			StudentRollInfo stuInfo = new StudentRollInfo("09015331", "", "", "", "", "", "", "", "", "", "", "");
			oos.writeInt(STUDENTROLL_INFO_QUERY);
			oos.writeObject(stuInfo);
			oos.flush();
			if (ois.readInt() == STUDENTROLL_INFO_QUERY_SUCCESS) {
				StudentRollInfo res = (StudentRollInfo)ois.readObject();
				
				System.out.println(res.getId()+"\t"+res.getName()+"\t"+res.getAge()+"\t"+res.getGender()+"\t"+res.getBirthday()+"\t"+res.getBirthPlace()+"\t"
				+res.getEntranceTime()+"\t"+res.getPhoto()+"\t"+res.getNation()+"\t"+res.getDepartment()+"\t"+res.getMajor()+"\t"+res.getDormitory());
			}
			else
				System.out.println("Query fail");
			*/
				
			
			
			oos.writeInt(LOGOUT);
			oos.flush();	
			if (ois.readInt() == LOGOUT_SUCCESS)
				System.out.println("Logout success");
			else
				System.out.println("Logout fail");
			

			
			ois.close();

			oos.close();

			socket.close();

		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
