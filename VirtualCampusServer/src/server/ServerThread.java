package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import view.ServerFrameView_MY;

/**
 * 服务器主线程
 * 
 * @author Silence
 *
 */
public class ServerThread extends Thread {
	/**
	 * 服务器Socket
	 */
	private ServerSocket server;
	/**
	 * 已连接的客户端线程向量
	 */
	private Vector<ClientThread> clients;
	
	public ServerThread() {
		
		try {
			server = new ServerSocket(8081);
			//ServerFrameView_MY.setTextArea("服务端主线程启动\n监听8081端口");
			System.out.println("Server main thread start.\nListen on port 8081");
			clients = new Vector<ClientThread>();
			
			this.start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		//当服务器在运行
		while(!server.isClosed()) {
			try {
				Socket client = server.accept();//监听新的客户端
				
				ClientThread current = new ClientThread(client, this);
				current.start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close() {
		//如果服务器Socket已被打开
		if (server != null) {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 返回当前已连接客户端数量
	 */
	public int getSize() {
		return clients.size();
	}
	
	/**
	 * 向向量中添加新的客户端
	 */
	public int addClientConnection(ClientThread ct) {
		clients.add(ct);
		
		return clients.size();
	}

	/**
	 * 从向量中移除关闭的客户端
	 * 
	 * @param ct 要关闭的客户端线程
	 * @return 关闭状态
	 */
	public boolean closeClientConnection(ClientThread ct) {	
		if (clients.contains(ct)) {
			clients.remove(ct);
			
			return true;
		}

		return false;	
	}
	
	/**
	 * 在向量中寻找客户端
	 */
	public boolean searchClientConnection(ClientThread ct) {		
		return clients.contains(ct);
	}
}
