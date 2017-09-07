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
		Login lg = new Login();
		if (lg.login(br, pw)) {
			System.out.println("Running client thread");
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
