package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import common.BankInfo;
import common.GoodInfo;
import common.MsgType;
import common.OrderInfo;
import common.StudentRollInfo;
import common.UserInfo;
import helper.Bank;
import helper.Login;
import helper.Shop;
import helper.StudentRoll;

public class ClientThread extends Thread 
	implements MsgType{
	
	private ServerThread currentServer;
	private Socket client;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	public ClientThread(Socket s, ServerThread st) {
		client = s;
		currentServer = st;
		try {
			ois = new ObjectInputStream(client.getInputStream());
			oos = new ObjectOutputStream(client.getOutputStream());
		} catch (IOException e) {
			System.out.println("Cannot get IO stream");
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		int cmd = 0;
		
		while (true) {
			try {
				cmd = ois.readInt();
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
				
				UserInfo info = null;
				Login lg = new Login();
				
				try {
					if (cmd != LOGOUT)
						info = (UserInfo)ois.readObject();
				} catch (IOException e) {
					System.out.println("Cannot get message from client");
					e.printStackTrace();
					
					break;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				switch (cmd) {
				//Login
				case LOGIN:
					try {
						if (lg.login(info)) {
							oos.writeInt(LOGIN_SUCCESS);
							
							currentServer.addClientConnection(this);
							System.out.println("Number of connected client: " + currentServer.getSize());
						}
						else {
							oos.writeInt(LOGIN_FAIL);
						}
						oos.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
						
					break;
					
				//Register
				case REGISTER:
					try {
						if (lg.register(info)) {
							oos.writeInt(REGISTER_SUCCESS);
						}
						else {
							oos.writeInt(REGISTER_FAIL);
						}
						oos.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					break;
					
				//Logout	
				case LOGOUT:
					try {
						if (currentServer.searchClientConnection(this)) {
							oos.writeInt(LOGOUT_SUCCESS);
							oos.flush();
							
							this.close();
						}
						else {
							oos.writeInt(LOGOUT_FAIL);
							oos.flush();
					}
					} catch (IOException e) {
						e.printStackTrace();
					}
					return;
				}
				
				break;
			
			//Bank module
			case 2:
				
				BankInfo bankInfo = null;
				Bank bk = new Bank();
				
				try {
					bankInfo = (BankInfo)ois.readObject();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				switch (cmd) {
				case BANK_BALANCE_QUERY:
					try {
						double result = bk.queryBalance(bankInfo);
						if (result != 0) {
							oos.writeInt(BANK_BALANCE_QUERY_SUCCESS);
							oos.writeDouble(result);	
						}
						else {
							oos.writeInt(BANK_BALANCE_QUERY_FAIL);
						}
						
						oos.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					break;
					
				case BANK_TRANSFER:
					try {
						if (bk.transfer(bankInfo)) {
							oos.writeInt(BANK_TRANSFER_SUCCESS);
						}
						else {
							oos.writeInt(BANK_TRANSFER_FAIL);
						}
						
						oos.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					break;
					
				case BANK_TRANSFER_RECORD_QUERY:
					try {
						BankInfo results[] = bk.queryTranferRecord(bankInfo);
						if (results != null) {
							oos.writeInt(BANK_TRANSFER_RECORD_SUCCESS);
							oos.writeObject(results);
						}
						else {
							oos.writeInt(BANK_TRANSFER_RECORD_FAIL);
						}
						
						oos.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					break;
						
				}
				
				
				break;
				
			//StudentRoll module
			case 3:
				
				StudentRollInfo stuInfo = null;
				StudentRoll sr = new StudentRoll();
				
				try {
					stuInfo = (StudentRollInfo)ois.readObject();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				switch (cmd) {
				case STUDENTROLL_INFO_QUERY:
					try {
						StudentRollInfo result = sr.query(stuInfo);
						if (result != null) {
							oos.writeInt(STUDENTROLL_INFO_QUERY_SUCCESS);
							oos.writeObject(result);	
						}
						else {
							oos.writeInt(STUDENTROLL_INFO_QUERY_FAIL);
						}
						
						oos.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					break;
				case STUDENTROLL_ADD_ADMIN:
					try {
						if (sr.addInfo(stuInfo)) {
							oos.writeInt(STUDENTROLL_ADD_ADMIN_SUCCESS);
						}
						else {
							oos.writeInt(STUDENTROLL_ADD_ADMIN_FAIL);
						}
						
						oos.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					break;
				case STUDENTROLL_DELETE_ADMIN:
					try {
						if (sr.deleteInfo(stuInfo)) {
							oos.writeInt(STUDENTROLL_DELETE_ADMIN_SUCCESS);
						}
						else {
							oos.writeInt(STUDENTROLL_DELETE_ADMIN_FAIL);
						}
						
						oos.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					break;
				case STUDENTROLL_MODIFY_ADMIN:
					try {
						if (sr.modifyInfo(stuInfo)) {
							oos.writeInt(STUDENTROLL_MODIFY_ADMIN_SUCCESS);
						}
						else {
							oos.writeInt(STUDENTROLL_MODIFY_ADMIN_FAIL);
						}
						
						oos.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					break;

				}
				
				break;
				
			//Library module
			case 4:
				break;
				
			//Shop module
			case 5:
				
				GoodInfo goodInfo = null;
				OrderInfo orderInfo = null;
				Shop sp = new Shop();
				
				switch(cmd) {
				case SHOP_GOODS_QUERY:
					try {
						goodInfo = (GoodInfo)ois.readObject();
						
						GoodInfo result = sp.queryGoods(goodInfo);
						if (result != null) {
							oos.writeInt(SHOP_GOODS_QUERY_SUCCESS);
							oos.writeObject(result);	
						}
						else {
							oos.writeInt(SHOP_GOODS_QUERY_FAIL);
						}
						
						oos.flush();
						
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
					break;
					
				case SHOP_ADD_ADMIN:
					try {
						goodInfo = (GoodInfo)ois.readObject();

						if (sp.addGood(goodInfo)) {
							oos.writeInt(SHOP_ADD_ADMIN_SUCCESS);
						}
						else {
							oos.writeInt(SHOP_ADD_ADMIN_FAIL);
						}
						
						oos.flush();
						
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					break;
					
				case SHOP_DELETE_ADMIN:
					try {
						goodInfo = (GoodInfo)ois.readObject();

						if (sp.deleteGood(goodInfo)) {
							oos.writeInt(SHOP_DELETE_ADMIN_SUCCESS);
						}
						else {
							oos.writeInt(SHOP_DELETE_ADMIN_FAIL);
						}
						
						oos.flush();
						
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					break;
					
				case SHOP_MODIFY_ADMIN:
					try {
						goodInfo = (GoodInfo)ois.readObject();

						if (sp.modifyGood(goodInfo)) {
							oos.writeInt(SHOP_MODIFY_ADMIN_SUCCESS);
						}
						else {
							oos.writeInt(SHOP_MODIFY_ADMIN_FAIL);
						}
						
						oos.flush();
						
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					break;
					
				case SHOP_ORDER_QUERY_ADMIN:
					try {						
						OrderInfo result[] = sp.queryOrderAdmin();
						
						if (result != null) {
							oos.writeInt(SHOP_ORDER_QUERY_ADMIN_SUCCESS);
						}
						else {
							oos.writeInt(SHOP_ORDER_QUERY_ADMIN_FAIL);
						}
						
						oos.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					break;
					
				case SHOP_BUY_STUTEA:
					try {
						OrderInfo order[] = (OrderInfo[])ois.readObject();
						
						if (sp.buy(order)) {
							oos.writeInt(SHOP_BUY_STUTEA_SUCCESS);
						}
						else {
							oos.writeInt(SHOP_BUY_STUTEA_FAIL);
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
					break;
					
				case SHOP_ORDER_QUERY_STUTEA:
					try {
						orderInfo = (OrderInfo)ois.readObject();
						
						OrderInfo result[] = sp.queryOrderStuTea(orderInfo);
						if (result != null) {
							oos.writeInt(SHOP_ORDER_QUERY_STUTEA_SUCCESS);
							oos.writeObject(result);	
						}
						else {
							oos.writeInt(SHOP_ORDER_QUERY_STUTEA_FAIL);
						}
						
						oos.flush();
						
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					break;
					
				}
				
				
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
				oos.close();
				ois.close();
				client.close();
				
				currentServer.closeClientConnection(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
