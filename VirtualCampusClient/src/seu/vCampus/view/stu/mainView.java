package seu.vCampus.view.stu;

import java.awt.EventQueue;
import com.sun.awt.AWTUtilities;

import common.Bank;

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
import seu.vCampus.util.SocketHelper;
import seu.vCampus.view.login.LoggedPopUp_MY;
import seu.vCampus.view.login.LoginPopUp_MY;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.awt.Dimension;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class mainView extends JFrame {
	// private static IBankImpl ibank;
	public static JFrame frame;

	private JTable table;
	private SocketHelper sockethelper = null;
	
	private JButton Button_Minimize;
	private JButton Button_Maximize;
	private JButton Button_Close;
	private JButton Button_Campus;
	private JLabel Label_Title_VCE;// virtual campus in English
	private JLabel Label_Upright;  // 竖线（标题栏）
	private static JButton Button_Login;

	private JPanel panel;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	public JPanel librPanel;
	public JPanel shopPanel;
	public JPanel orderPanel;
	public JPanel bankPanel;
	public JPanel classPanel;
	private JPanel header;
	private JButton btnNewButton_1;
	public JPanel stuPanel;
	
	private JButton Button_Library;
	private JButton Button_StuRoll;
	private JButton Button_Shop;
	private JButton Button_Bank;
	private JButton Button_Course;
	private JButton Button_Venue;
	JPanel Panel_LeftBranch;
	JPanel Panel_RightBranch;
	JButton Button_RightLine;
	JButton Button_LeftLine;
	
	LoggedPopUp_MY LoggedPopUpViewWindow;
	
	ImageIcon Icon_Library_White = new ImageIcon(getClass().getResource("/images/branch/library_w_b.png"));
	ImageIcon Icon_Library_Blue  = new ImageIcon(getClass().getResource("/images/branch/library_b_w.png"));
	ImageIcon Icon_StuRoll_White = new ImageIcon(getClass().getResource("/images/branch/stuRoll_w_b.png"));
	ImageIcon Icon_StuRoll_Blue  = new ImageIcon(getClass().getResource("/images/branch/stuRoll_b_w.png"));
	ImageIcon Icon_Shop_White = new ImageIcon(getClass().getResource("/images/branch/shop_w_b.png"));
	ImageIcon Icon_Shop_Blue  = new ImageIcon(getClass().getResource("/images/branch/shop_b_w.png"));
	ImageIcon Icon_Bank_White = new ImageIcon(getClass().getResource("/images/branch/bank_w_b.png"));
	ImageIcon Icon_Bank_Blue  = new ImageIcon(getClass().getResource("/images/branch/bank_b_w.png"));
	ImageIcon Icon_Course_White = new ImageIcon(getClass().getResource("/images/branch/course_w_b.png"));
	ImageIcon Icon_Course_Blue  = new ImageIcon(getClass().getResource("/images/branch/course_b_w.png"));
	ImageIcon Icon_Venue_White  = new ImageIcon(getClass().getResource("/images/branch/venue_w_b.png"));
	ImageIcon Icon_Venue_Blue   = new ImageIcon(getClass().getResource("/images/branch/venue_b_w.png"));

	private int index;
	private static String StudentId;
	private JTabbedPane tabbedPane;
	int xOld;
	int yOld;
	
	int xx = 605;
	int yy = 112;
	
	public void UpdateButtonShowOnline(){
		Button_Login.setText(StudentId);//登陆后更新登录按钮
		Button_Login.setToolTipText("");	
	}
	
	public static void UpdateButtonShowOffline(){
		Button_Login.setText("未登录");//登陆后更新登录按钮
		Button_Login.setToolTipText("请登录");	
	}
	
	public void setTabbedPane(int index){
		this.index = index;		
	}
		
	public static void setStudentId(String Id){
		System.out.println("set了 studentid");
		StudentId = Id;
		System.out.println("mainView 里面"+StudentId);
	}
	
	public static String getStudentId(){
		return StudentId;
	}
	
	/*public static void main(String[] args) {
		// try {
		// UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		// } catch (Throwable e) {
		// e.printStackTrace();
		// }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				String windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
				try {
					UIManager.setLookAndFeel(windows);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				try {
					// IBank ibank = new IBankImpl(sockethelper);
					// System.out.println(1);
					mainView window = new mainView();
					// System.out.println(2);
					window.frame.dispose();
					// System.out.println(3);
					window.frame.setUndecorated(true);
					window.frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public mainView(SocketHelper sockethelper,String stuId,int ix) {
		String windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try {
			UIManager.setLookAndFeel(windows);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		System.out.println("打开mainview！！！");
		this.sockethelper = sockethelper;
//		sockethelper.getConnection();
		System.out.println("链接成功");
		index = ix;
		StudentId = stuId;
		System.out.println(stuId);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 70, 785, 490);
		frame.setUndecorated(true);
		frame.setVisible(true);
		/** 设置圆角 */
		AWTUtilities.setWindowShape(frame,
				new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		this.frame.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
			    int xOnScreen = e.getXOnScreen();
			    int yOnScreen = e.getYOnScreen();
			    xx = xOnScreen - xOld;
			    yy = yOnScreen - yOld;
			    frame.setLocation(xx, yy);//设置拖拽后，窗口的位置
			    if( LoggedPopUpViewWindow != null){//设置登录界面跟着主窗口一起拖动
			    	LoggedPopUpViewWindow.getFrame().setLocation(xx + 435, yy + 45);
			    	LoggedPopUpViewWindow.getFrame().setAlwaysOnTop(true);//置于顶层
			    }
			}
		});
		this.frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			    xOld = e.getX();//记录鼠标按下时的坐标
			    yOld = e.getY();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(LoginPopUp_MY.Frame_Login != null)//当鼠标离开LoginPopUp_MY弹框并点击的时候，设置LoginPopUp_MY消失
					LoginPopUp_MY.Frame_Login.dispose();
				if(LoggedPopUp_MY.Frame_Logged != null)//当鼠标离开LoggedPopUp_MY弹框并点击的时候，设置LoggedPopUp_MY消失
					LoggedPopUp_MY.Frame_Logged.dispose();
			}
		});
		
		setLeftBranch();
		setLeftBranchListener();
		
		header = new JPanel();
		header.setBackground(SystemColor.textHighlight);
		header.setBounds(0, 0, 785, 40);
		frame.getContentPane().add(header);
		header.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBackground(new Color(102, 153, 204));
		tabbedPane.setBounds(0, 39, 785, 490);
		frame.getContentPane().add(tabbedPane);
		tabbedPane.setFont(new Font("YouYuan", 1, 14));

		
//		ImageIcon icon_minimize = new ImageIcon(getClass().getResource("/images/mainView/minimize.png"));
		ImageIcon icon_maxmize  = new ImageIcon(getClass().getResource("/images/mainView/maximize.png"));
		ImageIcon icon_close    = new ImageIcon(getClass().getResource("/images/mainView/close.png"));
		ImageIcon icon_campus   = new ImageIcon(getClass().getResource("/images/icon/campus.png"));
		ImageIcon icon_login    = new ImageIcon(getClass().getResource("/images/icon/login.png"));
				
		this.Button_Minimize = new JButton(new ImageIcon(getClass().getResource("/images/mainView/minimize.png")));
		Button_Minimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//最小化的实现
				frame.setExtendedState(JFrame.ICONIFIED);
			}
		});
		this.Button_Minimize.setToolTipText("\u6700\u5C0F\u5316");
		this.Button_Minimize.setBounds(689, 12, 23, 23);
		this.Button_Minimize.setBorderPainted(false);
		this.Button_Minimize.setContentAreaFilled(false);
		mainView.this.header.add(mainView.this.Button_Minimize);
		
		this.Button_Maximize = new JButton(icon_maxmize);
		this.Button_Maximize.setToolTipText("\u6700\u5927\u5316\uFF08\u529F\u80FD\u6682\u65E0\uFF09");
		this.Button_Maximize.setBounds(722, 12, 23, 23);
		this.Button_Maximize.setBorderPainted(false);
		this.Button_Maximize.setContentAreaFilled(false);
		header.add(Button_Maximize);
		
		this.Button_Close = new JButton(icon_close);
		Button_Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//最大化的实现！
				frame.dispose();
			}
		});
		this.Button_Close.setToolTipText("\u5173\u95ED\u9875\u9762");
		this.Button_Close.setBounds(752, 12, 23, 23);
		this.Button_Close.setBorderPainted(false);
		this.Button_Close.setContentAreaFilled(false);
		this.header.add(this.Button_Close);
		
		this.Button_Campus= new JButton(icon_campus);
		this.Button_Campus.setBounds(10, 10, 30, 30);
		this.Button_Campus.setBorderPainted(false);
		this.Button_Campus.setContentAreaFilled(false);
		this.header.add(Button_Campus);
		
		this.Label_Title_VCE = new JLabel("Virtual Campus");
		this.Label_Title_VCE.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		this.Label_Title_VCE.setForeground(SystemColor.text);
		this.Label_Title_VCE.setBounds(60, 9, 186, 31);
		this.header.add(this.Label_Title_VCE);
		
		this.Button_Login = new JButton(" \u672A\u767B\u5F55");
		Button_Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildLoggedPopUpView();				
			}
		});
		this.Button_Login.setText(StudentId);
		this.Button_Login.setIcon(icon_login);
		this.Button_Login.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		this.Button_Login.setForeground(SystemColor.controlHighlight);
		this.Button_Login.setToolTipText("请登录");
		this.Button_Login.setBounds(525, 8, 133, 30);
		this.Button_Login.setBorderPainted(false);
		this.Button_Login.setContentAreaFilled(false);
		this.header.add(this.Button_Login);
		
		this.Label_Upright = new JLabel("|");
		this.Label_Upright.setFont(new Font("幼圆", Font.BOLD, 18));
		this.Label_Upright.setForeground(SystemColor.scrollbar);
		this.Label_Upright.setBounds(674, 10, 20, 30);
		this.header.add(this.Label_Upright);		
		

		//学籍系统！
		stuPanel = new JPanel();
		tabbedPane.addTab("000", null, stuPanel, null);
		stuPanel.setLayout(null);
		StuInfoView stuview = new StuInfoView(this, sockethelper,StudentId);
		
		// // 银行panel
		bankPanel = new JPanel();
		bankPanel.setSize(new Dimension(30, 30));
		bankPanel.setFont(new Font("YouYuan", Font.PLAIN, 12));
		// bankPanel.setForeground(new Color(85, 107, 47));
		//// bankPanel.setBackground(new Color(239, 247, 251));
		tabbedPane.addTab("111", null, bankPanel, null);
		bankPanel.setLayout(null);
		BankView bview = new BankView(this, sockethelper,StudentId);
		
		// 商店panel
		shopPanel = new JPanel();
		shopPanel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		tabbedPane.addTab("222", null,
				shopPanel, null);// 放在main里面
		shopPanel.setLayout(null);
		ShopView sview = new ShopView(this,sockethelper,StudentId);
		
		//选课
		classPanel = new JPanel();
		tabbedPane.addTab("6666666", null, classPanel, null);
		classPanel.setLayout(null);
		System.out.println("before class view id is "+StudentId);
		ClassView cview = new ClassView(this, sockethelper,StudentId);
		
		// 场馆预约panel
		orderPanel = new JPanel();
		tabbedPane.addTab("333", null,
				orderPanel, null);
		orderPanel.setLayout(null);
		OrderView oview = new OrderView(this,sockethelper);

		// 图书馆panel
		librPanel = new JPanel();
		tabbedPane.addTab("4444444",null, librPanel, null);
		librPanel.setLayout(null);
		LibrView lview = new LibrView(this, sockethelper,StudentId);

		tabbedPane.setSelectedIndex(index);
		
	}

	private void setLeftBranch(){
		this.Panel_LeftBranch = new JPanel();
		this.Panel_LeftBranch.setBounds(0, 40, 75, 450);
		this.Panel_LeftBranch.setLayout(null);
		this.frame.getContentPane().add(this.Panel_LeftBranch);
		
		ImageIcon icon_RightLine = new ImageIcon(getClass().getResource("/images/branch/rightLine.png"));
		this.Button_LeftLine = new JButton(icon_RightLine);
		this.Button_LeftLine.setBackground(UIManager.getColor("Button.background"));
		this.Button_LeftLine.setBounds(0, -2, 2, 450);
		this.Button_LeftLine.setBorderPainted(false);
		this.Button_LeftLine.setContentAreaFilled(false);
		this.Panel_LeftBranch.add(this.Button_LeftLine);
		
		ImageIcon icon_library  = new ImageIcon(getClass().getResource("/images/branch/library_b_w.png"));
		ImageIcon icon_stuRoll  = new ImageIcon(getClass().getResource("/images/branch/stuRoll_b_w.png"));
		ImageIcon icon_bank     = new ImageIcon(getClass().getResource("/images/branch/bank_b_w.png"));
		ImageIcon icon_shop     = new ImageIcon(getClass().getResource("/images/branch/shop_b_w.png"));
		ImageIcon icon_course   = new ImageIcon(getClass().getResource("/images/branch/course_b_w.png"));
		ImageIcon icon_venue    = new ImageIcon(getClass().getResource("/images/branch/venue_b_w.png"));
		
		this.Button_Library = new JButton(icon_library);
		this.Button_Library.setBackground(UIManager.getColor("Button.background"));
		this.Button_Library.setBounds(0, 375, 75, 75);
		this.Button_Library.setBorderPainted(false);
		this.Button_Library.setContentAreaFilled(false);
		this.Panel_LeftBranch.add(this.Button_Library);
				
		this.Button_StuRoll = new JButton(icon_stuRoll);
		this.Button_StuRoll.setBackground(UIManager.getColor("Button.background"));
		this.Button_StuRoll.setBounds(0, 0, 75, 75);
		this.Button_StuRoll.setBorderPainted(false);
		this.Button_StuRoll.setContentAreaFilled(false);
		this.Panel_LeftBranch.add(this.Button_StuRoll);
		
		this.Button_Shop = new JButton(icon_shop);
		this.Button_Shop.setBackground(UIManager.getColor("Button.background"));
		this.Button_Shop.setBounds(0, 150, 75, 75);
		this.Button_Shop.setBorderPainted(false);
		this.Button_Shop.setContentAreaFilled(false);
		this.Panel_LeftBranch.add(this.Button_Shop);
			
		this.Button_Bank = new JButton(icon_bank);
		this.Button_Bank.setBackground(UIManager.getColor("Button.background"));
		this.Button_Bank.setBounds(0, 75, 75, 75);
		this.Button_Bank.setBorderPainted(false);
		this.Button_Bank.setContentAreaFilled(false);
		this.Panel_LeftBranch.add(this.Button_Bank);
		
		this.Button_Course = new JButton(icon_course);
		this.Button_Course.setBackground(UIManager.getColor("Button.background"));
		this.Button_Course.setBounds(0, 225, 75, 75);
		this.Button_Course.setBorderPainted(false);
		this.Button_Course.setContentAreaFilled(false);
		this.Panel_LeftBranch.add(this.Button_Course);
		
		this.Button_Venue = new JButton(icon_venue);
		this.Button_Venue.setBackground(UIManager.getColor("Button.background"));
		this.Button_Venue.setBounds(0, 300, 75, 75);
		this.Button_Venue.setBorderPainted(false);
		this.Button_Venue.setContentAreaFilled(false);
		this.Panel_LeftBranch.add(this.Button_Venue);		
	}

	private void setWhichButtonDisplay(int index){
		switch(index){
		case 0:
			Button_Library.setIcon(Icon_Library_Blue);
			Button_StuRoll.setIcon(Icon_StuRoll_White);//
			Button_Shop.   setIcon(Icon_Shop_Blue);
			Button_Venue.  setIcon(Icon_Venue_Blue);
			Button_Course. setIcon(Icon_Course_Blue);
			Button_Bank.   setIcon(Icon_Bank_Blue);
			break;
		case 1:
			Button_Library.setIcon(Icon_Library_Blue);
			Button_StuRoll.setIcon(Icon_StuRoll_Blue);
			Button_Shop.   setIcon(Icon_Shop_Blue);
			Button_Venue.  setIcon(Icon_Venue_Blue);
			Button_Course. setIcon(Icon_Course_Blue);
			Button_Bank.   setIcon(Icon_Bank_White);//
			break;
		case 2:
			Button_Library.setIcon(Icon_Library_Blue);
			Button_StuRoll.setIcon(Icon_StuRoll_Blue);
			Button_Shop.   setIcon(Icon_Shop_White);//
			Button_Venue.  setIcon(Icon_Venue_Blue);
			Button_Course. setIcon(Icon_Course_Blue);
			Button_Bank.   setIcon(Icon_Bank_Blue);
			break;
		case 3:
			Button_Library.setIcon(Icon_Library_Blue);
			Button_StuRoll.setIcon(Icon_StuRoll_Blue);
			Button_Shop.   setIcon(Icon_Shop_Blue);
			Button_Venue.  setIcon(Icon_Venue_Blue);
			Button_Course. setIcon(Icon_Course_White);//
			Button_Bank.   setIcon(Icon_Bank_Blue);
			break;
		case 4:
			Button_Library.setIcon(Icon_Library_Blue);
			Button_StuRoll.setIcon(Icon_StuRoll_Blue);
			Button_Shop.   setIcon(Icon_Shop_Blue);
			Button_Venue.  setIcon(Icon_Venue_White);//
			Button_Course. setIcon(Icon_Course_Blue);
			Button_Bank.   setIcon(Icon_Bank_Blue);
			break;
		case 5:
			Button_Library.setIcon(Icon_Library_White);//
			Button_StuRoll.setIcon(Icon_StuRoll_Blue);
			Button_Shop.   setIcon(Icon_Shop_Blue);
			Button_Venue.  setIcon(Icon_Venue_Blue);
			Button_Course. setIcon(Icon_Course_Blue);
			Button_Bank.   setIcon(Icon_Bank_Blue);
			break;
		default:
			JOptionPane.showMessageDialog(null,"setWhichButtonDisplay(int index) error");			
		}
		
	}
	
	int isClicked = 0;//0未进入，1进入了未点击并出去，2进入并点击在出去
	
	private void setLeftBranchListener(){
		this.Button_Library.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
	//			System.out.println("mouseClicked is called!");
				tabbedPane.setSelectedIndex(5);
				setWhichButtonDisplay(5);
				tabbedPane.repaint();
				isClicked = 2;
			}

			public void mousePressed(MouseEvent e) {
	//			System.out.println("mousePressed is called!");
	//			Button_Library.setBorder(BorderFactory.createLoweredBevelBorder());
			}

			public void mouseReleased(MouseEvent e) {
	//			System.out.println("mouseReleased is called!");
	//			Button_Library.setBorder(null);
			}

			public void mouseEntered(MouseEvent e) {
				isClicked = 1;
	//			System.out.println("mouseEntered is called!");
				Button_Library.setIcon(Icon_Library_White);
			}

			public void mouseExited(MouseEvent e) {
	//			System.out.println("mouseExited is called!\n");
				if(isClicked == 1 && tabbedPane.getSelectedIndex() != 5)
					Button_Library.setIcon(Icon_Library_Blue);
				isClicked = 0;
			}
		});
		this.Button_StuRoll.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setSelectedIndex(0);
				setWhichButtonDisplay(0);
				isClicked = 2;
			//	tabbedPane.repaint();
			//	LoginView_MY.this.Frame_VC.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}

			public void mousePressed(MouseEvent e) {
				Button_StuRoll.setBorder(BorderFactory.createLoweredBevelBorder());
			}

			public void mouseReleased(MouseEvent e) {
				Button_StuRoll.setBorder(null);
			}

			public void mouseEntered(MouseEvent e) {
				isClicked = 1;
				Button_StuRoll.setIcon(Icon_StuRoll_White);
			}

			public void mouseExited(MouseEvent e) {
				if(isClicked == 1 && tabbedPane.getSelectedIndex() != 0)
					Button_StuRoll.setIcon(Icon_StuRoll_Blue);
				isClicked = 0;
			}
		});
		this.Button_Shop.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setSelectedIndex(2);
				setWhichButtonDisplay(2);
				isClicked = 2;
			//	LoginView_MY.this.Frame_VC.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}

			public void mousePressed(MouseEvent e) {
				Button_Shop.setBorder(BorderFactory.createLoweredBevelBorder());
			}

			public void mouseReleased(MouseEvent e) {
				Button_Shop.setBorder(null);
			}

			public void mouseEntered(MouseEvent e) {
				isClicked = 1;
				Button_Shop.setIcon(Icon_Shop_White);
			}

			public void mouseExited(MouseEvent e) {
				if(isClicked == 1 && tabbedPane.getSelectedIndex() != 2)
					Button_Shop.setIcon(Icon_Shop_Blue);
				isClicked = 0;
			}
		});		
		this.Button_Bank.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setSelectedIndex(1);
				setWhichButtonDisplay(1);
				isClicked = 2;
			//	LoginView_MY.this.Frame_VC.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}

			public void mousePressed(MouseEvent e) {
				Button_Bank.setBorder(BorderFactory.createLoweredBevelBorder());
			}

			public void mouseReleased(MouseEvent e) {
				Button_Bank.setBorder(null);
			}

			public void mouseEntered(MouseEvent e) {
				isClicked = 1;
				Button_Bank.setIcon(Icon_Bank_White);
			}

			public void mouseExited(MouseEvent e) {
				if(isClicked == 1 && tabbedPane.getSelectedIndex() != 1)
					Button_Bank.setIcon(Icon_Bank_Blue);
				isClicked = 0;
			}
		});
		this.Button_Course.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setSelectedIndex(3);
				setWhichButtonDisplay(3);
				isClicked = 2;
			//	LoginView_MY.this.Frame_VC.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}

			public void mousePressed(MouseEvent e) {
				Button_Course.setBorder(BorderFactory.createLoweredBevelBorder());
			}

			public void mouseReleased(MouseEvent e) {
				Button_Course.setBorder(null);
			}

			public void mouseEntered(MouseEvent e) {
				isClicked = 1;
				Button_Course.setIcon(Icon_Course_White);
			}

			public void mouseExited(MouseEvent e) {
				if(isClicked == 1 && tabbedPane.getSelectedIndex() != 3)
					Button_Course.setIcon(Icon_Course_Blue);
				isClicked = 0;
			}
		});
		this.Button_Venue.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setSelectedIndex(4);
				setWhichButtonDisplay(4);
				isClicked = 2;
			//	LoginView_MY.this.Frame_VC.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}

			public void mousePressed(MouseEvent e) {
				Button_Venue.setBorder(BorderFactory.createLoweredBevelBorder());
			}

			public void mouseReleased(MouseEvent e) {
				Button_Venue.setBorder(null);
			}

			public void mouseEntered(MouseEvent e) {
				isClicked = 1;
				Button_Venue.setIcon(Icon_Venue_White);
			}

			public void mouseExited(MouseEvent e) {
				if(isClicked == 1 && tabbedPane.getSelectedIndex() != 4)
					Button_Venue.setIcon(Icon_Venue_Blue);
				isClicked = 0;
			}
		});
	}
	
	private void buildLoggedPopUpView(){//展示已登录弹框界面
		this.LoggedPopUpViewWindow = new LoggedPopUp_MY(StudentId);
		this.LoggedPopUpViewWindow.getFrame().setBounds(xx + 435, yy + 42, 250, 300);
	}
	
}
