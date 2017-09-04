package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import helper.Login;

public class ServerThread extends Thread {
	private ServerSocket server;
	private Socket client;
	private BufferedReader br;
	private PrintWriter pw;
	
	public ServerThread() {
		
		try {
			server = new ServerSocket(9001);		
			System.out.println("Listen on port 9001");
			
			this.start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(!server.isClosed()) {
			try {
				client = server.accept();
				
				br = new BufferedReader(new InputStreamReader(client.getInputStream()));
				pw = new PrintWriter(client.getOutputStream());
				
				Login lg = new Login();
				if (lg.login(br, pw)) {
					System.out.println("main thread");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				System.out.println("No client connection");
				e.printStackTrace();
			}
		}
	}
	
	public void close() {
		if (server != null) {
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
