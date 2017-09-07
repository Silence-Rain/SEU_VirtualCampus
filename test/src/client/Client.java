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

			pw.println("09015331&admin&admin");
			pw.flush();
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
			System.out.println(br.readLine());
			
			sleep(10000);
			
			br.close();

			pw.close();

			socket.close();

		}
		catch(IOException e){
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
