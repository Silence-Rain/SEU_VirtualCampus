package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ServerThread extends Thread {
	private ServerSocket server;
	private Vector<ClientThread> clients;
	
	public ServerThread() {
		
		try {
			server = new ServerSocket(8081);
			System.out.println("Server main thread start.\nListen on port 8081");
			clients = new Vector<ClientThread>();
			
			this.start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(!server.isClosed()) {
			try {
				Socket client = server.accept();
				
				ClientThread current = new ClientThread(client, this);
				clients.add(current);
				current.start();
				
				for (int i = 0; i < clients.size(); i++) { 
					System.out.println(clients.get(i)); 
				} 
				System.out.println("Number of connected client: " + clients.size());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close() {
		if (server != null) {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean closeClientConnection(ClientThread ct) {
		if (clients.contains(ct)) {
			clients.remove(ct);
			
			return true;
		}

		return false;	
	}
}
