package client;

import java.io.*;
import java.net.*;


public class Client extends Thread implements MsgType{

	public static void main(String[] args) {
		
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		String curUser = "09015331";
		
		try{
			socket = new Socket("localhost", 8081);
			pw = new PrintWriter(socket.getOutputStream());
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));	


			pw.println(LOGIN);
			pw.flush();
			pw.println("09015331&admin&admin");
			pw.flush();	
			if (Integer.parseInt(br.readLine()) == LOGIN_SUCCESS)
				System.out.println("Login success");
			else
				System.out.println("Login fail");
			
			
			pw.println(BANK_TRANSFER);
			pw.flush();
			pw.println("09015331&3000&09015336&-1000&1560000000");
			pw.flush();
			if (Integer.parseInt(br.readLine()) == BANK_TRANSFER_SUCCESS)
				System.out.println("Tranfer success");
			else
				System.out.println("Tranfer fail");
			
			
			pw.println(BANK_TRANSFER_RECORD_QUERY);
			pw.flush();
			pw.println("09015331");
			pw.flush();
			if (Integer.parseInt(br.readLine()) == BANK_TRANSFER_RECORD_SUCCESS) {
				int len = Integer.parseInt(br.readLine());
				
				for (int i = 0; i < len; i++) {
					String data = br.readLine();
					System.out.println(data);
				}
			}	
			else
				System.out.println("Query fail");
			
			
			pw.println(LOGOUT);
			pw.flush();	
			if (Integer.parseInt(br.readLine()) == LOGOUT_SUCCESS)
				System.out.println("Logout success");
			else
				System.out.println("Logout fail");
			

			
			br.close();

			pw.close();

			socket.close();

		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
