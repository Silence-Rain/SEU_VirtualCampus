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
import view.ServerFrameView_MY;

/**
 * 客户端线程
 * 
 * @author Silence
 *
 */
public class ClientThread extends Thread 
	implements MsgType{
	
	/**
	 * 客户端当前连接的服务器线程
	 */
	private ServerThread currentServer;
	/**
	 * 客户端Socket
	 */
	private Socket client;
	/**
	 * Socket对象输入流
	 */
	private ObjectInputStream ois;
	/**
	 * Socket对象输出流
	 */
	private ObjectOutputStream oos;
	
	public ClientThread(Socket s, ServerThread st) {
		client = s;
		currentServer = st;
		try {
			//建立输入输出流（次序与客户端相反）
			ois = new ObjectInputStream(client.getInputStream());
			oos = new ObjectOutputStream(client.getOutputStream());
			currentServer.addClientConnection(this);
			
			//ServerFrameView_MY.setTextArea("客户端已连接\n客户端IP：" + client.getInetAddress().getHostAddress());
			System.out.println("Client connected");
			//ServerFrameView_MY.setTextNumber(currentServer.getSize());
			System.out.println("Number of connected client: " + currentServer.getSize());
		} catch (IOException e) {
			System.out.println("Cannot get IO stream");
			e.printStackTrace();
		}
	}
	
	public void run() {
		int cmd = 0;//从客户端读到的消息
		
		while (true) {
			//读取消息
			try {
				cmd = ois.readInt();
				System.out.println(cmd);
			} catch (IOException e) {
				//读不到指令，说明已登出
				return;
			}
			
			//判断消息属于哪一类型，调用对应模块函数完成相应功能
			switch (cmd / 100) {
			//错误：cmd没有被正确初始化
			case 0:
				System.out.println("Abnormal command");
				return;
				
			//登录模块
			case 1:
				Login(cmd);
				break;
			
			//银行模块
			case 2:
				Bank(cmd);				
				break;
				
			//学籍管理模块
			case 3:
				StudentRoll(cmd);
				break;
				
			//图书馆模块
			case 4:
				Library(cmd);
				break;
				
			//商店模块
			case 5:
				Shop(cmd);				
				break;
				
			//课程选择模块
			case 6:
				Course(cmd);
				break;
				
			//场馆预约模块
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
				//ServerFrameView_MY.setTextArea("客户端已断开\n客户端IP：" + client.getInetAddress().getHostAddress());
				System.out.println("客户端已断开\n客户端IP：" + client.getInetAddress().getHostAddress());
				client.close();

				currentServer.closeClientConnection(this);//在服务器线程中关闭该客户端
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * 各模块功能函数
	 * 
	 * 服务器端与客户端的数据交流遵从以下模式：
	 * 1. 服务器从客户端读取消息
	 * 2. 服务器从客户端读取所需参数（可选）
	 * 3. 服务器向客户端写回请求状态
	 * 4. 服务器向客户端写回请求结果集（可选） 
	 */
	
	

	/**
	 * 登录模块
	 * 
	 * @param cmd 接受的消息
	 */
	private void Login(int cmd) {
		UserInfo info = null;
		Login lg = new Login();
		
		try {
			//登出操作不需要从客户端读取信息
			if (cmd != LOGOUT)
				info = (UserInfo)ois.readObject();
		} catch (IOException e) {
			System.out.println("Cannot get message from client");
			e.printStackTrace();
			
			return;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//根据不同消息，进行不同操作
		switch (cmd) {
		//登录
		case LOGIN:
			try {
				if (lg.login(info)) {
					oos.writeInt(LOGIN_SUCCESS);
					
					//ServerFrameView_MY.setTextArea("用户" + info.getStuId() + "已登录");
				}
				else {
					oos.writeInt(LOGIN_FAIL);
				}
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			break;
			
		//注册
		case REGISTER:
			try {
				int wb = (lg.register(info)) ? REGISTER_SUCCESS : REGISTER_FAIL;
				oos.writeInt(wb);
				
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
			
		//登出	
		case LOGOUT:
			try {
				if (currentServer.searchClientConnection(this)) {
					//ServerFrameView_MY.setTextArea("用户" + info.getStuId() + "已登出");
					oos.writeInt(LOGOUT_SUCCESS);
					this.close();
				}
				else {
					oos.writeInt(LOGOUT_FAIL);
				}
				
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
	}
	
	/**
	 * 银行模块
	 * 
	 * @param cmd 接受的消息
	 */
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
		
		//根据不同消息，进行不同操作
		switch (cmd) {
		//余额查询
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
			
		//转账
		case BANK_TRANSFER:
			try {
				int wb = (bk.transfer(bankInfo)) ? BANK_TRANSFER_SUCCESS : BANK_TRANSFER_FAIL;
				oos.writeInt(wb);
				
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
		
		//转账记录查询
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
	
	/**
	 * 学籍信息模块
	 * 
	 * @param cmd 接受的消息
	 */
	private void StudentRoll(int cmd) {

		StudentRollInfo stuInfo = null;
		StudentRoll sr = new StudentRoll();
		
		try {
			if (cmd != STUDENTROLL_INFO_QUERY_ADMIN)
				stuInfo = (StudentRollInfo)ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//根据不同消息，进行不同操作
		switch (cmd) {
		//查询学生学籍信息
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
			
		//添加学生信息
		case STUDENTROLL_ADD:
			try {
				int wb = (sr.addInfo(stuInfo)) ? STUDENTROLL_ADD_SUCCESS : STUDENTROLL_ADD_FAIL;
				oos.writeInt(wb);
				
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
			
		//删除学生信息
		case STUDENTROLL_DELETE:
			try {
				int wb = (sr.deleteInfo(stuInfo)) ? STUDENTROLL_DELETE_SUCCESS : STUDENTROLL_DELETE_FAIL;
				oos.writeInt(wb);
				
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
			
		//修改学生信息
		case STUDENTROLL_MODIFY:
			try {
				int wb = (sr.modifyInfo(stuInfo)) ? STUDENTROLL_MODIFY_SUCCESS : STUDENTROLL_MODIFY_FAIL;
				oos.writeInt(wb);
				
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
			
		//学籍信息查询（管理员查询）
		case STUDENTROLL_INFO_QUERY_ADMIN:
			try {
				StudentRollInfo[] result = sr.queryAll();
				if (result != null) {
					oos.writeInt(STUDENTROLL_INFO_QUERY_ADMIN_SUCCESS);
					oos.writeObject(result);	
				}
				else {
					oos.writeInt(STUDENTROLL_INFO_QUERY_ADMIN_FAIL);
				}
				
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 图书馆模块
	 * 
	 * @param cmd 接受的消息
	 */
	private void Library(int cmd) {
		
		BookInfo bookInfo = null;
		BookStatusInfo bsInfo = null;
		Library lb = new Library();
		
		//书籍信息查询（40），对tbBook表进行操作
		if (cmd / 10 == 40) {
			try {
				bookInfo = (BookInfo)ois.readObject();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			//根据不同消息，进行不同操作
			switch(cmd) {
			//书籍信息查询
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
				
			//添加书籍
			case LIBRARY_BOOK_ADD:
				try {
					int writeBack = (lb.addBook(bookInfo)) ? LIBRARY_BOOK_ADD_SUCCESS : LIBRARY_BOOK_ADD_FAIL;
					oos.writeInt(writeBack);
					oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			//删除书籍
			case LIBRARY_BOOK_DELETE:
				try {
					int writeBack = (lb.deleteBook(bookInfo)) ? LIBRARY_BOOK_DELETE_SUCCESS : LIBRARY_BOOK_DELETE_FAIL;
					oos.writeInt(writeBack);
					oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			//修改书籍信息
			case LIBRARY_BOOK_MODIFY:
				try {
					int writeBack = (lb.modifyBook(bookInfo)) ? LIBRARY_BOOK_MODIFY_SUCCESS : LIBRARY_BOOK_MODIFY_FAIL;
					oos.writeInt(writeBack);
					oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
			
			}
		}
		//书籍借阅状态查询（41），对tbBookStatus表进行操作
		else {
			try {
				bsInfo = (BookStatusInfo)ois.readObject();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//根据不同消息，进行不同操作
			switch(cmd) {
			//借书
			case LIBRARY_STATUS_BORROW:
				try {
					int writeBack = (lb.borrowBook(bsInfo)) ? LIBRARY_STATUS_BORROW_SUCCESS : LIBRARY_STATUS_BORROW_FAIL;
					oos.writeInt(writeBack);
					oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			//还书
			case LIBRARY_STATUS_RETURN:
				try {
					int writeBack = (lb.returnBook(bsInfo)) ? LIBRARY_STATUS_RETURN_SUCCESS : LIBRARY_STATUS_RETURN_FAIL;
					oos.writeInt(writeBack);
					oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			//借阅记录查询
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
	
	/**
	 * 商店模块
	 * 
	 * @param cmd 接受的消息
	 */
	private void Shop(int cmd) {

		GoodInfo goodInfo = null;
		Shop sp = new Shop();
		
		//商品信息查询（50），对tbGood表进行操作
		if (cmd / 10 == 50) {
			try {
				if (cmd != SHOP_GOODS_QUERY)
					goodInfo = (GoodInfo)ois.readObject();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//根据不同消息，进行不同操作
			switch(cmd) {
			//商品信息查询
			case SHOP_GOODS_QUERY:
				try {
					GoodInfo[] result = sp.queryGoods();
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
				
			//添加商品
			case SHOP_GOODS_ADD:
				try {
					int wb = (sp.addGood(goodInfo)) ? SHOP_GOODS_ADD_SUCCESS : SHOP_GOODS_ADD_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
			
			//删除商品
			case SHOP_GOODS_DELETE:
				try {
					int wb = (sp.deleteGood(goodInfo)) ? SHOP_GOODS_DELETE_SUCCESS : SHOP_GOODS_DELETE_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			//修改商品信息
			case SHOP_GOODS_MODIFY:
				try {
					int wb = (sp.modifyGood(goodInfo)) ? SHOP_GOODS_MODIFY_SUCCESS : SHOP_GOODS_MODIFY_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
			}
		}
		//商品订单查询（51），对tbOrder表进行操作
		else {
			switch (cmd) {
			//订单查询
			case SHOP_ORDER_QUERY_ADMIN:
				try {						
					OrderInfo result[] = sp.queryOrderAdmin();
					
					if (result != null) {
						oos.writeInt(SHOP_ORDER_QUERY_ADMIN_SUCCESS);
						oos.writeObject(result);
					}
					else {
						oos.writeInt(SHOP_ORDER_QUERY_ADMIN_FAIL);
					}
					
					oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			//购买
			case SHOP_ORDER_BUY:
				try {
					OrderInfo order[] = (OrderInfo[])ois.readObject();
					int wb = (sp.buy(order)) ? SHOP_ORDER_BUY_SUCCESS : SHOP_ORDER_BUY_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				break;
				
			//订单记录查询
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
	
	/**
	 * 课程选择模块
	 * 
	 * @param cmd 接受的消息
	 */
	private void Course(int cmd) {
		CourseInfo courseInfo = null;
		CourseStatusInfo csInfo = null;
		Course cs = new Course();
		
		//课程信息查询（60），对tbCourse表进行操作
		if (cmd / 10 == 60) {
			try {
				if (cmd != COURSE_QUERY)
					courseInfo = (CourseInfo)ois.readObject();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//根据不同消息，进行不同操作
			switch(cmd) {
			//课程查询
			case COURSE_QUERY:
				try {
					CourseInfo result[] = cs.queryCourse();
					
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
				
			//添加课程
			case COURSE_ADD:
				try {
					int wb = (cs.addCourse(courseInfo)) ? COURSE_ADD_SUCCESS : COURSE_ADD_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			//删除课程
			case COURSE_DELETE:
				try {
					int wb = (cs.deleteCourse(courseInfo)) ? COURSE_DELETE_SUCCESS : COURSE_DELETE_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			//修改课程信息
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
		//课程选择（61），对tbCourseStatus表进行操作
		else {
			try {
				if (cmd != COURSE_STATUS_QUERY)
					csInfo = (CourseStatusInfo)ois.readObject();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//根据不同消息，进行不同操作
			switch (cmd) {
			//选课
			case COURSE_STATUS_SELECT:
				try {
					int wb = (cs.selectCourse(csInfo)) ? COURSE_STATUS_SELECT_SUCCESS : COURSE_STATUS_SELECT_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			//退课
			case COURSE_STATUS_DESELECT:
				try {
					int wb = (cs.deselectCourse(csInfo)) ? COURSE_STATUS_DESELECT_SUCCESS : COURSE_STATUS_DESELECT_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			//学生查询课表
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
				
			//教师查询选课学生
			case COURSE_STATUS_QUERY:
				try {

					CourseInfo cInfo = (CourseInfo)ois.readObject();
	
					CourseStatusInfo result[] = cs.queryStatus(cInfo);
					if (result != null) {
						oos.writeInt(COURSE_STATUS_QUERY_SUCCESS);
						oos.writeObject(result);
					}
					else {
						oos.writeInt(COURSE_STATUS_QUERY_FAIL);
					}
					
					oos.flush();
					
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				break;
				
			}
		}
	}
	
	/**
	 * 场馆预约模块
	 * 
	 * @param cmd 接受的消息
	 */
	private void Appoint(int cmd) {
		
		AppointInfo apInfo = null;
		AppointStatusInfo apsInfo = null;
		Appoint ap = new Appoint();
		
		//预约项目（70），对tbAppoint表进行操作
		if (cmd / 10 == 70) {
			try {
				if (cmd != APPOINT_ITEM_QUERY)
					apInfo = (AppointInfo)ois.readObject();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//根据不同消息，进行不同操作
			switch(cmd) {
			//查询预约项目
			case APPOINT_ITEM_QUERY:
				try {
					AppointInfo[] result = ap.queryAppointItem();
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
				
			//添加项目
			case APPOINT_ITEM_ADD:
				try {
					int wb = (ap.addItem(apInfo)) ? APPOINT_ITEM_ADD_SUCCESS : APPOINT_ITEM_ADD_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			//删除项目
			case APPOINT_ITEM_DELETE:
				try {
					int wb = (ap.deleteItem(apInfo)) ? APPOINT_ITEM_DELETE_SUCCESS : APPOINT_ITEM_DELETE_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			//修改项目信息
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
		//预约操作（71），对tbAppointStatus表进行操作
		else {
			try {
				apsInfo = (AppointStatusInfo)ois.readObject();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//根据不同消息，进行不同操作
			switch (cmd) {
			//添加预约
			case APPOINT_STATUS_ADD:
				try {
					int wb = (ap.addStatus(apsInfo)) ? APPOINT_STATUS_ADD_SUCCESS : APPOINT_STATUS_ADD_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			//取消预约
			case APPOINT_STATUS_DELETE:
				try {
					int wb = (ap.deleteStatus(apsInfo)) ? APPOINT_STATUS_DELETE_SUCCESS : APPOINT_STATUS_DELETE_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			//修改预约信息
			case APPOINT_STATUS_MODIFY:
				try {
					int wb = (ap.modifyStatus(apsInfo)) ? APPOINT_STATUS_MODIFY_SUCCESS : APPOINT_STATUS_MODIFY_FAIL;
					oos.writeInt(wb);
					
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			//查询预约记录
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
