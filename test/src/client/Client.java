package client;

import java.io.*;
import java.net.*;


public class Client {

	public static void main(String[] args) {
		
		Socket socket = null;
		
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		OutputStream os = null;
		PrintWriter pw = null;
		
		try{
			socket = new Socket("localhost", 9001);
			os = socket.getOutputStream();
			pw = new PrintWriter(os);

			pw.println("09015331&admin&admin");
			pw.flush();
			
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			System.out.println(br.readLine());
			
			br.close();
			isr.close();
			is.close();

			pw.close();
			os.close();

			socket.close();

		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
