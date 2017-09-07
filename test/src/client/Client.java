package client;

import java.io.*;
import java.net.*;


public class Client extends Thread{

	public static void main(String[] args) {
		
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try{
			socket = new Socket("localhost", 8081);
			pw = new PrintWriter(socket.getOutputStream());
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));	


			pw.println(101);
			pw.flush();
			pw.println("09015331&admin&admin");
			pw.flush();	
			if (Integer.parseInt(br.readLine()) == 1011)
				System.out.println("Login success");
			else
				System.out.println("Login fail");
			
			
			pw.println(102);
			pw.println("09015330&15330&student");
			pw.flush();	
			if (Integer.parseInt(br.readLine()) == 1021)
				System.out.println("Register success");
			else
				System.out.println("Register fail");
			
			
			pw.println(103);
			pw.flush();	
			if (Integer.parseInt(br.readLine()) == 1031)
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
