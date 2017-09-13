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
				Login(cmd);
				
				break;
			
			//Bank module
			case 2:
				Bank(cmd);				
				
				break;
				
			//StudentRoll module
			case 3:
				StudentRoll(cmd);
				
				break;
				
			//Library module
			case 4:
				Library(cmd);
				
				break;
				
			//Shop module
			case 5:
				Shop(cmd);				
				
				break;
				
			//Course module
			case 6:
				Course(cmd);
				
				break;
				
			//Appoint module
			case 7:
				Appoint(cmd);
				
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
	
	
	
	
	
	/////////////////////////////////////////////////////////////////
	//Utility functions
	/////////////////////////////////////////////////////////////////
	
	private void Login(int cmd) {
		UserInfo info = null;
		Login lg = new Login();
		
		try {
			if (cmd != LOGOUT)
				info = (UserInfo)ois.readObject();
		} catch (IOException e) {
			System.out.println("Cannot get message from client");
			e.printStackTrace();
			
			return;
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
	}
	
	private void Bank(int cmd) {
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
					oos.writeInt(BANK_TRANSFER_RECORD_QUERY_SUCCESS);
					oos.writeObject(results);
				}
				else {
					oos.writeInt(BANK_TRANSFER_RECORD_QUERY_FAIL);
				}
				
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
				
		}
	}
	
	private void StudentRoll(int cmd) {

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
		case STUDENTROLL_ADD:
			try {
				if (sr.addInfo(stuInfo)) {
					oos.writeInt(STUDENTROLL_ADD_SUCCESS);
				}
				else {
					oos.writeInt(STUDENTROLL_ADD_FAIL);
				}
				
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
		case STUDENTROLL_DELETE:
			try {
				if (sr.deleteInfo(stuInfo)) {
					oos.writeInt(STUDENTROLL_DELETE_SUCCESS);
				}
				else {
					oos.writeInt(STUDENTROLL_DELETE_FAIL);
				}
				
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
		case STUDENTROLL_MODIFY:
			try {
				if (sr.modifyInfo(stuInfo)) {
					oos.writeInt(STUDENTROLL_MODIFY_SUCCESS);
				}
				else {
					oos.writeInt(STUDENTROLL_MODIFY_FAIL);
				}
				
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;

		}
	}
	
	private void Library(int cmd) {
		
	}
	
	private void Shop(int cmd) {
		GoodInfo goodInfo = null;
		Shop sp = new Shop();
		
		if (cmd / 10 == 50) {
			try {
				goodInfo = (GoodInfo)ois.readObject();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			switch(cmd) {
			case SHOP_GOODS_QUERY:
				try {
					GoodInfo result = sp.queryGoods(goodInfo);
					if (result != null) {
						oos.writeInt(SHOP_GOODS_QUERY_SUCCESS);
						oos.writeObject(result);	
					}
					else {
						oos.writeInt(SHOP_GOODS_QUERY_FAIL);
					}
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				break;
				
			case SHOP_GOODS_ADD:
				try {
					if (sp.addGood(goodInfo)) {
						oos.writeInt(SHOP_GOODS_ADD_SUCCESS);
					}
					else {
						oos.writeInt(SHOP_GOODS_ADD_FAIL);
					}
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			case SHOP_GOODS_DELETE:
				try {
					if (sp.deleteGood(goodInfo)) {
						oos.writeInt(SHOP_GOODS_DELETE_SUCCESS);
					}
					else {
						oos.writeInt(SHOP_GOODS_DELETE_FAIL);
					}
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			case SHOP_GOODS_MODIFY:
				try {
					if (sp.modifyGood(goodInfo)) {
						oos.writeInt(SHOP_GOODS_MODIFY_SUCCESS);
					}
					else {
						oos.writeInt(SHOP_GOODS_MODIFY_FAIL);
					}
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
			}
		}
		else {
			switch (cmd) {
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
				
			case SHOP_ORDER_BUY:
				try {
					OrderInfo order[] = (OrderInfo[])ois.readObject();
					
					if (sp.buy(order)) {
						oos.writeInt(SHOP_ORDER_BUY_SUCCESS);
					}
					else {
						oos.writeInt(SHOP_ORDER_BUY_FAIL);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				break;
				
			case SHOP_ORDER_QUERY_STUTEA:
				try {
					OrderInfo orderInfo = (OrderInfo)ois.readObject();
					
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
		}

	}
	
	private void Course(int cmd) {
		
	}
	
	private void Appoint(int cmd) {
		
	}
}
