package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import common.MsgType;
import helper.Login;

public class ClientThread extends Thread 
	implements MsgType{
	
	private ServerThread currentServer;
	private Socket client;
	private BufferedReader br;
	private PrintWriter pw;
	
	public ClientThread(Socket s, ServerThread st) {
		client = s;
		currentServer = st;
		try {
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			pw = new PrintWriter(client.getOutputStream());
		} catch (IOException e) {
			System.out.println("Cannot get IO stream");
			e.printStackTrace();
		}
	}
	
	public void run() {
		int cmd = 0;
		
		while (true) {
			try {
				cmd = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			switch (cmd / 100) {
			//Error: variable cmd hasn't been initialize
			case 0:
				System.out.println("Abnormal command");
				return;
				
			//Login	module
			case 1:
				
				Login lg = new Login();
				String token = "";
				try {
					if (cmd != LOGOUT)
						token = br.readLine();
				} catch (IOException e) {
					System.out.println("Cannot get message from client");
					e.printStackTrace();
					
					break;
				}
				
				switch (cmd) {
				//Login
				case LOGIN:
					if (lg.login(token)) {
						pw.println(LOGIN_SUCCESS);
						
						currentServer.addClientConnection(this);
						System.out.println("Number of connected client: " + currentServer.getSize());
					}
					else {
						pw.println(LOGIN_FAIL);
					}
					pw.flush();
						
					break;
					
				//Register
				case REGISTER:
					if (lg.register(token)) {
						pw.println(REGISTER_SUCCESS);
					}
					else {
						pw.println(REGISTER_FAIL);
					}
					pw.flush();
					
					break;
					
				//Logout	
				case LOGOUT:
					if (currentServer.searchClientConnection(this)) {
						pw.println(LOGOUT_SUCCESS);
						pw.flush();
						
						this.close();
					}
					else {
						pw.println(LOGOUT_FAIL);
						pw.flush();
					}
					return;
				}
				
				break;
			
			//Bank module
			case 2:
				break;
				
			//StudentRoll module
			case 3:
				break;
				
			//Library module
			case 4:
				break;
				
			//Shop module
			case 5:
				break;
				
			//Course module
			case 6:
				break;
				
			//Appoint module
			case 7:
				break;
					
			}
			
		}
	}
	
	public void close() {
		if (client != null) {
			try {
				client.close();
				
				currentServer.closeClientConnection(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
