	package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import common.AppointInfo;
import common.AppointStatusInfo;
import common.BankInfo;
import common.BookInfo;
import common.BookStatusInfo;
import common.CourseInfo;
import common.CourseStatusInfo;
import common.GoodInfo;
import common.MsgType;
import common.OrderInfo;
import common.StudentRollInfo;
import common.UserInfo;
import helper.Bank;
import helper.Login;
import helper.Shop;
import helper.StudentRoll;
import helper.Library;
import helper.Course;
import helper.Appoint;

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
		System.out.println(cmd);
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
		
		BookInfo bookInfo = null;
		BookStatusInfo bsInfo = null;
		Library lb = new Library();
		
		if (cmd / 10 == 40) {
			try {
				bookInfo = (BookInfo)ois.readObject();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			switch(cmd) {
			case LIBRARY_BOOK_QUERY:
				try {
					BookInfo result[] = lb.queryBook(bookInfo);
					if (result != null) {
						oos.writeInt(LIBRARY_BOOK_QUERY_SUCCESS);
						oos.writeObject(result);	
					}
					else {
						oos.writeInt(LIBRARY_BOOK_QUERY_FAIL);
					}
					
					oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			case LIBRARY_BOOK_ADD:
				try {
					int writeBack = (lb.addBook(bookInfo)) ? LIBRARY_BOOK_ADD_SUCCESS : LIBRARY_BOOK_ADD_FAIL;
					oos.writeInt(writeBack);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			case LIBRARY_BOOK_DELETE:
				try {
					int writeBack = (lb.deleteBook(bookInfo)) ? LIBRARY_BOOK_DELETE_SUCCESS : LIBRARY_BOOK_DELETE_FAIL;
					oos.writeInt(writeBack);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			case LIBRARY_BOOK_MODIFY:
				try {
					int writeBack = (lb.modifyBook(bookInfo)) ? LIBRARY_BOOK_MODIFY_SUCCESS : LIBRARY_BOOK_MODIFY_FAIL;
					oos.writeInt(writeBack);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
			
			}
		}
		else {
			try {
				bsInfo = (BookStatusInfo)ois.readObject();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			switch(cmd) {
			case LIBRARY_STATUS_BORROW:
				try {
					int writeBack = (lb.borrowBook(bsInfo)) ? LIBRARY_STATUS_BORROW_SUCCESS : LIBRARY_STATUS_BORROW_FAIL;
					oos.writeInt(writeBack);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			case LIBRARY_STATUS_RETURN:
				try {
					int writeBack = (lb.returnBook(bsInfo)) ? LIBRARY_STATUS_RETURN_SUCCESS : LIBRARY_STATUS_RETURN_FAIL;
					oos.writeInt(writeBack);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			case LIBRARY_STATUS_QUERY:
				try {
					BookStatusInfo result[] = lb.queryStatus(bsInfo);
					if (result != null) {
						oos.writeInt(LIBRARY_STATUS_QUERY_SUCCESS);
						oos.writeObject(result);	
					}
					else {
						oos.writeInt(LIBRARY_STATUS_QUERY_FAIL);
					}
					
					oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			}
		}
		
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
		CourseInfo courseInfo = null;
		CourseStatusInfo csInfo = null;
		Course cs = new Course();
		
		if (cmd / 10 == 60) {
			try {
				if (cmd != COURSE_QUERY)
					courseInfo = (CourseInfo)ois.readObject();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			switch(cmd) {
			case COURSE_QUERY:
				try {
					CourseInfo result[] = cs.queryCourse(courseInfo);
					
					if (result != null) {
						oos.writeInt(COURSE_QUERY_SUCCESS);
						oos.writeObject(result);	
					}
					else {
						oos.writeInt(COURSE_QUERY_FAIL);
					}
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				break;
				
			case COURSE_ADD:
				try {
					int wb = (cs.addCourse(courseInfo)) ? COURSE_ADD_SUCCESS : COURSE_ADD_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			case COURSE_DELETE:
				try {
					int wb = (cs.deleteCourse(courseInfo)) ? COURSE_DELETE_SUCCESS : COURSE_DELETE_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			case COURSE_MODIFY:
				try {
					int wb = (cs.modifyCourse(courseInfo)) ? COURSE_MODIFY_SUCCESS : COURSE_MODIFY_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
			}
		}
		else {
			try {
				csInfo = (CourseStatusInfo)ois.readObject();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			switch (cmd) {
			case COURSE_STATUS_SELECT:
				try {
					int wb = (cs.selectCourse(csInfo)) ? COURSE_STATUS_SELECT_SUCCESS : COURSE_STATUS_SELECT_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			case COURSE_STATUS_DESELECT:
				try {
					int wb = (cs.deselectCourse(csInfo)) ? COURSE_STATUS_DESELECT_SUCCESS : COURSE_STATUS_DESELECT_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			case COURSE_STATUS_CURRICULUM:
				try {
					CourseInfo result[] = cs.queryCurriculum(csInfo);
					if (result != null) {
						oos.writeInt(COURSE_STATUS_CURRICULUM_SUCCESS);
						oos.writeObject(result);	
					}
					else {
						oos.writeInt(COURSE_STATUS_CURRICULUM_FAIL);
					}
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			case COURSE_STATUS_QUERY:
				try {
					CourseStatusInfo result[] = cs.queryStatus(csInfo);
					if (result != null) {
						oos.writeInt(COURSE_STATUS_QUERY_SUCCESS);
						oos.writeObject(result);	
					}
					else {
						oos.writeInt(COURSE_STATUS_QUERY_FAIL);
					}
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			}
		}
	}
	
	private void Appoint(int cmd) {
		
		AppointInfo apInfo = null;
		AppointStatusInfo apsInfo = null;
		Appoint ap = new Appoint();
		
		if (cmd / 10 == 70) {
			try {
				apInfo = (AppointInfo)ois.readObject();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			switch(cmd) {
			case APPOINT_ITEM_QUERY:
				try {
					AppointInfo[] result = ap.queryAppointItem(apInfo);
					if (result != null) {
						oos.writeInt(APPOINT_ITEM_QUERY_SUCCESS);
						oos.writeObject(result);	
					}
					else {
						oos.writeInt(APPOINT_ITEM_QUERY_FAIL);
					}
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				break;
				
			case APPOINT_ITEM_ADD:
				try {
					int wb = (ap.addItem(apInfo)) ? APPOINT_ITEM_ADD_SUCCESS : APPOINT_ITEM_ADD_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			case APPOINT_ITEM_DELETE:
				try {
					int wb = (ap.deleteItem(apInfo)) ? APPOINT_ITEM_DELETE_SUCCESS : APPOINT_ITEM_DELETE_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			case APPOINT_ITEM_MODIFY:
				try {
					int wb = (ap.modifyItem(apInfo)) ? APPOINT_ITEM_MODIFY_SUCCESS : APPOINT_ITEM_MODIFY_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
			}
		}
		else {
			try {
				apsInfo = (AppointStatusInfo)ois.readObject();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			switch (cmd) {
			case APPOINT_STATUS_ADD:
				try {
					int wb = (ap.addStatus(apsInfo)) ? APPOINT_STATUS_ADD_SUCCESS : APPOINT_STATUS_ADD_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			case APPOINT_STATUS_DELETE:
				try {
					int wb = (ap.deleteStatus(apsInfo)) ? APPOINT_STATUS_DELETE_SUCCESS : APPOINT_STATUS_DELETE_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			case APPOINT_STATUS_MODIFY:
				try {
					int wb = (ap.modifyStatus(apsInfo)) ? APPOINT_STATUS_MODIFY_SUCCESS : APPOINT_STATUS_MODIFY_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			case APPOINT_STATUS_RECORD_QUERY:
				try {
					AppointStatusInfo result[] = ap.queryStatus(apsInfo);
					if (result != null) {
						oos.writeInt(APPOINT_STATUS_RECORD_QUERY_SUCCESS);
						oos.writeObject(result);	
					}
					else {
						oos.writeInt(APPOINT_STATUS_RECORD_QUERY_FAIL);
					}
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			}
		}
		
	}
}
