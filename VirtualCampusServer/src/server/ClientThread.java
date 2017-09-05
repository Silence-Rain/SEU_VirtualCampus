package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import helper.Login;

public class ClientThread extends Thread {
	private Socket client;
	private BufferedReader br;
	private PrintWriter pw;
	
	public ClientThread(Socket s) {
		client = s;
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
}
