package seu.vCampus.view.stu;

import java.awt.EventQueue;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import seu.vCampus.bz.IBank;
import seu.vCampus.bz.IBankImpl;
import seu.vCampus.bz.ILibraryImpl;
import seu.vCampus.util.SocketHelper;
import seu.vCampus.view.login.MainUIView_MY;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Dimension;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import common.Bank;
import common.BookInfo;
import common.BookStatusInfo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class LibrView {

	private JFrame frame;
	private JTextField receiveField;
	private JTextField moneyField;
	private JTable table;
	private SocketHelper sockethelper = new SocketHelper();
	private JTable InquiryBook_Table;
	private JTextField InquiryBookName_TextField;
//	JPanel Library_Panel = new JPanel();
	private List<BookInfo[]> LibList = new ArrayList();
	JButton InquiryBookByName_Button = new JButton("查询书名");
	final JScrollPane InquiryBook_ScrollPane = new JScrollPane();
	JButton InquiryBookByAuthor_Button = new JButton("查询作者");
	JButton BorrowThisBook_Button = new JButton("借阅此书");
	//private  JPanel panel_record = new JPanel();
	private final JScrollPane scrollPane_Record = new JScrollPane();
	private final JTable table_Record = new JTable();
	JButton jb_ReturnBook = new JButton("还书");
	private final JButton jb_Refresh = new JButton("刷新");
	private String id = null;
	
	int countSize = 0;
	JButton Button_SanTi;
	JButton Button_FengZheng;
	JButton Button_JiBoLun;
	JButton Button_RenJian;
	JButton Button_TongXin;
	ImageIcon Icon_SanTi;
	ImageIcon Icon_FengZheng;
	ImageIcon Icon_JiBoLun;
	ImageIcon Icon_RenJian;
	ImageIcon Icon_TongXin;
	
	public LibrView(mainView mview,SocketHelper sockethelper,String stuId) {
		//this.ibank = ibank;
		this.sockethelper = sockethelper;
		id = stuId;
		initialize(mview);
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize(mainView mview) {
		//mainView mview = new mainView();
		JTabbedPane Book_TabbedPane = new JTabbedPane(JTabbedPane.TOP);
		Book_TabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		Book_TabbedPane.setBounds(0, 0, 785, 490);
		mview.librPanel.add(Book_TabbedPane);
		//mainView.this.librPanel.add(tabbedPane_3);
		
		
	
//		JTabbedPane Book_TabbedPane = new JTabbedPane(JTabbedPane.TOP);
//		Book_TabbedPane.setToolTipText("查询书籍");
//		Book_TabbedPane.setBounds(0, 0, 608, 349);
//		frame.getContentPane().add(Book_TabbedPane);
		
		JPanel InquiryBook_Panel = new JPanel();
		Book_TabbedPane.addTab("查询书籍", new ImageIcon(LibrView.class.getResource("/images/bank/book.png")), InquiryBook_Panel, null);
		InquiryBook_Panel.setLayout(null);
		
/////////////////////////////////////////////////////////////////////
		
		Icon_SanTi = new ImageIcon(LibrView.class.getResource("/images/book/santi.jpg"));
		Icon_FengZheng = new ImageIcon(LibrView.class.getResource("/images/book/fengzheng.jpg"));
		Icon_JiBoLun = new ImageIcon(LibrView.class.getResource("/images/book/jibolun.jpg"));
		Icon_RenJian = new ImageIcon(LibrView.class.getResource("/images/book/renjian.jpg"));
		Icon_TongXin = new ImageIcon(LibrView.class.getResource("/images/book/tongxin.jpg"));

		Button_SanTi = new JButton("");
		Button_SanTi.setBorderPainted(false);
		Button_SanTi.setContentAreaFilled(false);
		InquiryBook_Panel.add(Button_SanTi);

		Button_FengZheng = new JButton("");
		Button_FengZheng.setBorderPainted(false);
		Button_FengZheng.setContentAreaFilled(false);
		InquiryBook_Panel.add(Button_FengZheng);

		Button_JiBoLun = new JButton("");
		Button_JiBoLun.setBorderPainted(false);
		Button_JiBoLun.setContentAreaFilled(false);
		InquiryBook_Panel.add(Button_JiBoLun);

		Button_RenJian = new JButton("");
		Button_RenJian.setBorderPainted(false);
		Button_RenJian.setContentAreaFilled(false);
		InquiryBook_Panel.add(Button_RenJian);

		Button_TongXin = new JButton("");
		Button_TongXin.setBorderPainted(false);
		Button_TongXin.setContentAreaFilled(false);
		InquiryBook_Panel.add(Button_TongXin);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				int period = countSize % 625;
				int cs = countSize % 50;

				if (0 <= period && period < 25) {
					makeImageBigger(Icon_SanTi, Button_SanTi, period);
					makeImageBigger(Icon_FengZheng, Button_FengZheng, period + 25);
					makeImageSmaller(Icon_JiBoLun, Button_JiBoLun, period);
					makeImageSmaller(Icon_RenJian, Button_RenJian, period + 25);
					makeImageMicroBigger(Icon_TongXin, Button_TongXin, period);
				} else if (125 <= period && period < 150) {
					makeImageBigger(Icon_SanTi, Button_SanTi, period);
					makeImageSmaller(Icon_FengZheng, Button_FengZheng, period + 25);
					makeImageSmaller(Icon_JiBoLun, Button_JiBoLun, period);
					makeImageMicroBigger(Icon_RenJian, Button_RenJian, period);
					makeImageBigger(Icon_TongXin, Button_TongXin, period + 25);
				} else if (250 <= period && period < 275) {
					makeImageSmaller(Icon_SanTi, Button_SanTi, period);
					makeImageSmaller(Icon_FengZheng, Button_FengZheng, period + 25);
					makeImageMicroBigger(Icon_JiBoLun, Button_JiBoLun, period);
					makeImageBigger(Icon_RenJian, Button_RenJian, period);
					makeImageBigger(Icon_TongXin, Button_TongXin, period + 25);
				} else if (375 <= period && period < 400) {
					makeImageSmaller(Icon_SanTi, Button_SanTi, period);
					makeImageMicroBigger(Icon_FengZheng, Button_FengZheng, period);
					makeImageBigger(Icon_JiBoLun, Button_JiBoLun, period + 25);
					makeImageBigger(Icon_RenJian, Button_RenJian, period);
					makeImageSmaller(Icon_TongXin, Button_TongXin, period + 25);
				} else if (500 <= period && period < 525) {
					makeImageMicroBigger(Icon_SanTi, Button_SanTi, period);
					makeImageBigger(Icon_FengZheng, Button_FengZheng, period);
					makeImageBigger(Icon_JiBoLun, Button_JiBoLun, period + 25);
					makeImageSmaller(Icon_RenJian, Button_RenJian, period);
					makeImageSmaller(Icon_TongXin, Button_TongXin, period + 25);
				}
				countSize++;
			}
		}, 0, 20);


/////////////////////////////////////////////////////////////////////

		
		InquiryBook_ScrollPane.setBounds(38, 259, 648, 135);
		InquiryBook_Panel.add(InquiryBook_ScrollPane);
		
		final JLabel bookMoreInformation_Label = new JLabel("");
		bookMoreInformation_Label.setBounds(28, 263, 510, 47);
		InquiryBook_Panel.add(bookMoreInformation_Label);
		//frame.getContentPane().setLayout(groupLayout);
		
		JPanel panel_record = new JPanel();
		Book_TabbedPane.addTab("借阅记录", new ImageIcon(LibrView.class.getResource("/images/bank/jieyuejilu.png")), panel_record , null);
		//Book_TabbedPane.setIconAt(1, new ImageIcon(LibrView.class.getResource("/images/bank/jieyuejilu.png")));
        panel_record.setLayout(null);
        scrollPane_Record.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        scrollPane_Record.setBounds(28,43,565,198);
		panel_record.add(scrollPane_Record);

		InquiryBook_Table = new JTable();
		InquiryBook_Table.setModel(new DefaultTableModel(new Object[][] {
			{null, null, null, null, null},
			{null, null, null, null, null},
			{null, null, null, null, null},
			{null, null, null, null, null},
			{null, null, null, null, null},
			{null, null, null, null, null},
			{null, null, null, null, null},
			{null, null, null, null, null},
			{null, null, null, null, null},
			{null, null, null, null, null},
		}, new String[] {
			"\u4E66\u7C4D\u540D\u79F0", "\u4E66\u7C4D\u4F5C\u8005", "\u51FA\u7248\u793E", "\u5E93\u5B58\u6570\u91CF", "\u53EF\u501F\u6570\u91CF"
		}));
		InquiryBook_ScrollPane.setViewportView(InquiryBook_Table);
		
		BorrowThisBook_Button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		BorrowThisBook_Button.setBounds(593, 221, 93, 23);
		InquiryBook_Panel.add(BorrowThisBook_Button);
		
		InquiryBookName_TextField = new JTextField();
		InquiryBookName_TextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		InquiryBookName_TextField.setBounds(139, 221, 174, 21);
		InquiryBook_Panel.add(InquiryBookName_TextField);
		InquiryBookName_TextField.setColumns(10);

		JLabel InquiryBookInformation_Label = new JLabel("查询信息");
		InquiryBookInformation_Label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		InquiryBookInformation_Label.setBounds(51, 221, 78, 29);
		InquiryBook_Panel.add(InquiryBookInformation_Label);
		InquiryBookByName_Button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		

		
		
		
		InquiryBookByName_Button.setBounds(355, 221, 93, 23);
		InquiryBook_Panel.add(InquiryBookByName_Button);
		InquiryBookByAuthor_Button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		
		
		InquiryBookByAuthor_Button.setBounds(473, 221, 93, 23);
		InquiryBook_Panel.add(InquiryBookByAuthor_Button);
		
        Book_TabbedPane.addTab("借阅记录", null, panel_record, null);
        scrollPane_Record.setBounds(28,68,565,156);
		panel_record.add(scrollPane_Record);
		table_Record.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"\u4E66\u7C4DID", "\u4E66\u540D", "\u501F\u9605\u65F6\u95F4", "\u8FD8\u4E66\u65F6\u95F4", "\u501F\u9605\u72B6\u6001"
			}
		));
		
		scrollPane_Record.setViewportView(table_Record);
		jb_ReturnBook.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		
		jb_ReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (LibrView.this.table_Record.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "未选中任何书籍");
					return;
				}
				if(LibrView.this.table_Record.getValueAt(
						LibrView.this.table_Record.getSelectedRow(), 4).equals("已还书")){
					JOptionPane.showMessageDialog(null, "这本书已经还啦！");
					return;
					
				}
				int tmpId =(int)LibrView.this.table_Record.getValueAt(
						LibrView.this.table_Record.getSelectedRow(), 0);
				String tmpBorrowTime = (String)LibrView.this.table_Record.getValueAt(
						LibrView.this.table_Record.getSelectedRow(), 2);
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date dt2 = null;
				try {
					dt2 = sdf.parse(tmpBorrowTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//继续转换得到秒数的long型
				long lTime = dt2.getTime() / 1000;
				System.out.println("借书的时间戳是"+ lTime);
				BookStatusInfo book = new BookStatusInfo(tmpId,"","",lTime,(Calendar.getInstance().getTimeInMillis())/1000);
				
				boolean flag = new ILibraryImpl(LibrView.this.sockethelper).ReturnBook(book);
				if(flag){
					scrollPane_Record.setViewportView(table_Record);
					JOptionPane.showMessageDialog(null, "还书成功！");
				}
				else{
					JOptionPane.showMessageDialog(null, "还书失败！");
				}
			}
		});
		jb_ReturnBook.setBounds(43, 256, 93, 23);
		panel_record.add(jb_ReturnBook);
		
	//	this.tabbedPane.setSelectedIndex(5);//设置第六个界面，即图书馆界面为初始界面
		
		InquiryBook_ScrollPane.setViewportView(getAllBookTable());
		scrollPane_Record.setViewportView(getRecordTable());
		jb_Refresh.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		jb_Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane_Record.setViewportView(getRecordTable());
			}
		});
		jb_Refresh.setBounds(192, 256, 93, 23);
		
		panel_record.add(jb_Refresh);
		
		StudentEvent();
	}
	
/////////****************************************************************************************
/////////****************************************************************************************
/////////****************************************************************************************
	private void makeImageMicroBigger(ImageIcon ic, JButton button, int period) {
		int cs = period % 25;
		Image image = ic.getImage();
		Image smallImage = image.getScaledInstance(26 + cs, (int) (35 + cs * 1.386), Image.SCALE_FAST);
		ImageIcon smallIcon = new ImageIcon(smallImage);
		button.setIcon(smallIcon);
		button.setBounds(5 + cs, 70 - cs, 150, 208);
	}

	private void makeImageBigger(ImageIcon ic, JButton button, int period) {
		int cs = period % 50;
		Image image = ic.getImage();
		Image smallImage = image.getScaledInstance(51 + cs * 2, (int) (69.333 + cs * 2.7733), Image.SCALE_FAST);
		ImageIcon smallIcon = new ImageIcon(smallImage);
		button.setIcon(smallIcon);
		button.setBounds(30 + cs * 5, 55 - cs, 150, 208);
	}

	private void makeImageSmaller(ImageIcon ic, JButton button, int period) {
		int cs = period % 50;
		Image image = ic.getImage();
		Image smallImage = image.getScaledInstance(151 - cs * 2, (int) (208 - cs * 2.7733), Image.SCALE_FAST);
		ImageIcon smallIcon = new ImageIcon(smallImage);
		button.setIcon(smallIcon);
		button.setBounds(280 + cs * 5, 5 + cs, 150, 208);
	}

/////////****************************************************************************************

	
		private void StudentEvent(){
			InquiryBookByName_Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String tmp = LibrView.this.InquiryBookName_TextField.getText();
					System.out.println(tmp);
					InquiryBook_ScrollPane.setViewportView(getBookByNameTable(tmp));
					System.out.println("查找出来了1");
				}
			});
			
			InquiryBookByAuthor_Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String tmp = LibrView.this.InquiryBookName_TextField.getText();
					System.out.println(tmp);
					InquiryBook_ScrollPane.setViewportView(getBookByAuthorTable(tmp));
					System.out.println("查找出来了2");
				}
			});
			
			BorrowThisBook_Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//借书
					if (LibrView.this.InquiryBook_Table.getSelectedRow()==-1) {
						JOptionPane.showMessageDialog(null, "未选中任何书籍");
						return;
					}
					if(LibrView.this.InquiryBook_Table.getValueAt(LibrView.this.InquiryBook_Table.getSelectedRow(), 5).equals("已被借")){
						JOptionPane.showMessageDialog(null, "这本书已经被借走了!");
					}
					int strId =(int) LibrView.this.InquiryBook_Table
							.getValueAt(LibrView.this.InquiryBook_Table
									.getSelectedRow(), 0);
					//int tmpId = Integer.valueOf(strId);
					System.out.println(strId);
					String strName = (String) LibrView.this.InquiryBook_Table
							.getValueAt(LibrView.this.InquiryBook_Table
									.getSelectedRow(), 2);
					String strBorrower = id;
					//String strBorrower = User.getId();
					long tmpTime = (Calendar.getInstance().getTimeInMillis())/1000;
					System.out.println(tmpTime);
					BookStatusInfo book = new BookStatusInfo(strId,strName,strBorrower,tmpTime,0);
					book.setBorrowDate(tmpTime);
					System.out.println(book.getBorrowDate());
					System.out.println((Calendar.getInstance().getTimeInMillis())/1000);
					boolean flag = new ILibraryImpl(LibrView.this.sockethelper).BorrowBook(book);
					if(flag){
						JOptionPane.showMessageDialog(null, "已成功借阅这本书！");
						LibrView.this.InquiryBook_Table.setValueAt("已被借",
								LibrView.this.InquiryBook_Table.getSelectedRow(), 5);
						
					}
					else{
						JOptionPane.showMessageDialog(null, "借阅失败！");
					}
					
				}
			});
		
		
		
		
	}
	
	private JTable getAllBookTable(){
		InquiryBook_Table.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));//设置表内字体
		InquiryBook_Table.getTableHeader().setFont(new Font("Microsoft YaHei UI", 0, 14));//设置表头字体
		String[] columns = { "书籍ID","ISBN", "书籍名称","书籍作者", "书籍出版商", "书籍状态" }; // 创建列名数组.
		// 创建表格模型.
		InquiryBook_Table.setRowHeight(25);
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		InquiryBook_Table.setModel(model); // 设置表格模型.
		/*TableColumn firstColumn = InquiryBook_Table.getColumnModel().getColumn(0);
		firstColumn.setPreferredWidth(60);
		firstColumn.setMaxWidth(60);
		firstColumn.setMinWidth(60);*/
		TableColumn secondColumn = InquiryBook_Table.getColumnModel().getColumn(1);
		secondColumn.setPreferredWidth(140);
		secondColumn.setMaxWidth(140);
		secondColumn.setMinWidth(140);
		BookInfo book = new BookInfo(0,"","","","",false);
		List<BookInfo> tmpList = new ILibraryImpl(LibrView.this.sockethelper).EnquiryAllBook(book);
		if(tmpList.size() == 0){
			System.out.println("没有东西传过来");
		}
		
		for(int i =0;i<tmpList.size();i++){
			BookInfo tmp = tmpList.get(i);
			if(tmp.isBorrowed())
			{
				Object[] rowData = {tmp.getId(),tmp.getIsbn(),tmp.getName(),tmp.getAuthor(),tmp.getPub(),"已被借"};
				model.addRow(rowData);
			}
			else
			{
				Object[] rowData = {tmp.getId(),tmp.getIsbn(),tmp.getName(),tmp.getAuthor(),tmp.getPub(),"未被借"};
				model.addRow(rowData);
			}
		}
		
		return InquiryBook_Table;
	}
	
	private JTable getBookByNameTable(String bookName){
		InquiryBook_Table.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));//设置表内字体
		InquiryBook_Table.getTableHeader().setFont(new Font("Microsoft YaHei UI", 0, 14));//设置表头字体
		String[] columns = { "书籍ID","ISBN", "书籍名称","书籍作者", "书籍出版商", "书籍状态" }; // 创建列名数组.
		// 创建表格模型.
		InquiryBook_Table.setRowHeight(25);
		InquiryBook_Table.getTableHeader().setFont(new Font("Microsoft YaHei UI", 0, 14));//设置表头字体
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		InquiryBook_Table.setModel(model); // 设置表格模型.
		TableColumn secondColumn = InquiryBook_Table.getColumnModel().getColumn(1);
		secondColumn.setPreferredWidth(120);
		secondColumn.setMaxWidth(120);
		secondColumn.setMinWidth(120);
		BookInfo book = new BookInfo(0,bookName,"","","",false);
		List<BookInfo> tmpList = new ILibraryImpl(LibrView.this.sockethelper).EnquiryAllBook(book);
		if(tmpList.size() == 0){
			JOptionPane.showMessageDialog(null, "没有这本书哦~");
			//return;
		}
		
		for(int i =0;i<tmpList.size();i++){
			BookInfo tmp = tmpList.get(i);
			if(tmp.isBorrowed())
			{
				Object[] rowData = {tmp.getId(),tmp.getIsbn(),tmp.getName(),tmp.getAuthor(),tmp.getPub(),"已被借"};
				model.addRow(rowData);
			}
			else
			{
				Object[] rowData = {tmp.getId(),tmp.getIsbn(),tmp.getName(),tmp.getAuthor(),tmp.getPub(),"未被借"};
				model.addRow(rowData);
			}
		}
		
		return InquiryBook_Table;
		
	}
	
	private JTable getBookByAuthorTable(String author){
		InquiryBook_Table.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));//设置表内字体
		InquiryBook_Table.getTableHeader().setFont(new Font("Microsoft YaHei UI", 0, 14));//设置表头字体
		String[] columns = { "书籍ID","ISBN", "书籍名称","书籍作者", "书籍出版商", "书籍状态" }; // 创建列名数组.
		// 创建表格模型.
		InquiryBook_Table.setRowHeight(25);
		InquiryBook_Table.getTableHeader().setFont(new Font("Microsoft YaHei UI", 0, 14));//设置表头字体
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		InquiryBook_Table.setModel(model); // 设置表格模型.
		TableColumn secondColumn = InquiryBook_Table.getColumnModel().getColumn(1);
		secondColumn.setPreferredWidth(120);
		secondColumn.setMaxWidth(120);
		secondColumn.setMinWidth(120);
		BookInfo book = new BookInfo(0,"","",author,"",false);
		List<BookInfo> tmpList = new ILibraryImpl(LibrView.this.sockethelper).EnquiryAllBook(book);
		if(tmpList.size() == 0){
			JOptionPane.showMessageDialog(null, "没有这本书哦~");
			//return;
		}
		
		for(int i =0;i<tmpList.size();i++){
			BookInfo tmp = tmpList.get(i);
			if(tmp.isBorrowed())
			{
				Object[] rowData = {tmp.getId(),tmp.getIsbn(),tmp.getName(),tmp.getAuthor(),tmp.getPub(),"已被借"};
				model.addRow(rowData);
			}
			else
			{
				Object[] rowData = {tmp.getId(),tmp.getIsbn(),tmp.getName(),tmp.getAuthor(),tmp.getPub(),"未被借"};
				model.addRow(rowData);
			}
		}
		
		return InquiryBook_Table;
		
	}
	private JTable getRecordTable(){
		table_Record.setFont(new Font("微软雅黑", Font.PLAIN, 14));//设置表内字体
		table_Record.getTableHeader().setFont(new Font("微软雅黑", 0, 14));//设置表头字体
		String[] columns = { "书籍ID", "书籍名称","借书时间", "还书时间", "借阅状态" }; // 创建列名数组.
		// 创建表格模型.
		table_Record.setRowHeight(25);
		table_Record.getTableHeader().setFont(new Font("Microsoft YaHei UI", 0, 14));//设置表头字体
		//table3.getTableHeader().setFont(new Font("Microsoft YaHei UI", 0, 14));//设置表头字体

		DefaultTableModel model = new DefaultTableModel(columns, 0);
		table_Record.setModel(model); // 设置表格模型.
		TableColumn secondColumn = table_Record.getColumnModel().getColumn(2);
		secondColumn.setPreferredWidth(140);
		secondColumn.setMaxWidth(140);
		secondColumn.setMinWidth(140);
		TableColumn thirdColumn = table_Record.getColumnModel().getColumn(3);
		thirdColumn.setPreferredWidth(140);
		thirdColumn.setMaxWidth(140);
		thirdColumn.setMinWidth(140);
		
		//String userId = "09015331"; 
		String userId =id;
		BookStatusInfo book = new BookStatusInfo(0,"",userId,0,0);
		List<BookStatusInfo> tmpList = new ILibraryImpl(LibrView.this.sockethelper).EnquiryRecord(book);
		if(tmpList.size() == 0){
			//JOptionPane.showMessageDialog(null, "没有记录哦~");
			System.out.println("没有记录哦");
			//return;
		}
		
		for(int i =0;i<tmpList.size();i++){
			BookStatusInfo tmp = tmpList.get(i);
			{
				if(tmp.getReturnDate() == 0){
					Object[] rowData = {tmp.getId(),tmp.getName(),stampToDate(tmp.getBorrowDate()),"---","未还书"};
					model.addRow(rowData);
				}
				else{
					Object[] rowData = {tmp.getId(),tmp.getName(),stampToDate(tmp.getBorrowDate()),stampToDate(tmp.getReturnDate()),"已还书"};
					model.addRow(rowData);
				}
				
			}
		}
		
		return table_Record;
		
	}
	public static String stampToDate(long l){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(l*1000);
        res = simpleDateFormat.format(date);
        return res;
    }
}

