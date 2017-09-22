package seu.vCampus.view.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import com.sun.awt.AWTUtilities;

import seu.vCampus.util.SocketHelper;
import seu.vCampus.view.stu.mainView;

import java.io.IOException;
import java.io.ObjectOutputStream;


public class MainUIView_MY extends JFrame {
	// 整体框架
	private static JFrame Frame_VC;//VC，virtual campus

	private JPanel Panel_Frame;
	// 头部界面和控件
	private JPanel  Panel_Header;
	private JButton Button_Minimize;
	private JButton Button_Maximize;
	private JButton Button_Close;
	private JButton Button_Campus;
	private JLabel Label_Title_VCE;// virtual campus in English
	private JLabel Label_Upright;  // 竖线（标题栏）
	private static JButton Button_Login;

	// 主体界面和控件
	private JPanel Panel_Main;
	private JButton Button_Library;
	private JLabel Label_Library;
	private JButton Button_StuRoll;
	private JLabel Label_StuRoll;
	private JButton Button_Shop;
	private JLabel  Label_Shop;
	private JButton Button_Bank;
	private JLabel  Label_Bank;
	private JButton Button_Course;
	private JLabel  Label_Course;
	private JButton Button_Venue;
	private JLabel  Label_Venue;
	
	// 登录弹框界面
	LoginPopUp_MY LoginPopUpViewWindow;
	LoggedPopUp_MY LoggedPopUpViewWindow;

	// 移动框架的相关坐标
	int xOld = 0;
	int yOld = 0;	
	int xx = 360;
	int yy = 150;
	
	private static String StudentId = null;
	private static boolean isLogin = false;
	private static SocketHelper sockethelper;
	
	public static void setIsLogin(boolean il){
		isLogin = il;
	}
	
	public static boolean getIsLogin(){
		return isLogin;
	}
	
	public static void setSocketHelper(SocketHelper sh){
		sockethelper = sh;		
	}	
	
	public static SocketHelper getSocketHelper(){
		return sockethelper;
	}
	
	public static void UpdateButtonShowOnline(){
		Button_Login.setText(StudentId);//登陆后更新登录按钮
		Button_Login.setToolTipText("");	
	}
	
	public static void UpdateButtonShowOffline(){
		Button_Login.setText("未登录");//登陆后更新登录按钮
		Button_Login.setToolTipText("请登录");	
	}
		
	public static void setStudentId(String id){
		StudentId = id;
	}
	
	public static String getStudentId(){
		return StudentId;
	}
	
	public static void setMinimizeRestore(){//设置最小化的还原
		Frame_VC.setVisible(true);
		Frame_VC.setExtendedState(JFrame.NORMAL);
		Frame_VC.toFront();		
	}
	
	public static void setMinimize(){//设置最小化
		Frame_VC.dispose();	
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("mainui");
					MainUIView_MY window = new MainUIView_MY();
					window.Frame_VC.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainUIView_MY() {
		setHeaderPanel();
		setHeaderPanelListeners();
		setMainPanel();
		setMainPanelListeners();
	}

	private void setHeaderPanel(){//设置头部面板
		this.Frame_VC = new JFrame();	
		this.Frame_VC.setBounds(360, 150, 640, 357);
		this.Frame_VC.setUndecorated(true);
		this.Frame_VC.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.Frame_VC.getContentPane().setLayout(null);
	//	this.Frame_VC.setDefaultLookAndFeelDecorated(true);  去你妈的流氓代码！！！去你妈的全局设置！！！
		AWTUtilities.setWindowShape(this.Frame_VC, new RoundRectangle2D.Double(0.0D, 0.0D, this.Frame_VC.getWidth(), this.Frame_VC.getHeight(), 26.0D, 26.0D));
		
		this.Panel_Header = new JPanel();
		this.Panel_Header.setBackground(new Color(0, 120, 215));
		this.Panel_Header.setBounds(0, 0, 640, 41);
		this.Frame_VC.getContentPane().add(this.Panel_Header);
		this.Panel_Header.setLayout(null);		
		
		ImageIcon icon_minimize = new ImageIcon(getClass().getResource("/images/sysbtn/minimize.png"));
		ImageIcon icon_maxmize  = new ImageIcon(getClass().getResource("/images/sysbtn/maximize.png"));
		ImageIcon icon_close    = new ImageIcon(getClass().getResource("/images/sysbtn/close.png"));
		ImageIcon icon_campus   = new ImageIcon(getClass().getResource("/images/sysbtn/campus.png"));
		ImageIcon icon_login    = new ImageIcon(getClass().getResource("/images/sysbtn/login.png"));
				
		this.Button_Minimize = new JButton(icon_minimize);
		this.Button_Minimize.setToolTipText("\u6700\u5C0F\u5316");
		this.Button_Minimize.setBounds(554, 8, 23, 23);
		this.Button_Minimize.setBorderPainted(false);
		this.Button_Minimize.setContentAreaFilled(false);
		this.Panel_Header.add(this.Button_Minimize);
		
		this.Button_Maximize = new JButton(icon_maxmize);
		this.Button_Maximize.setToolTipText("\u6700\u5927\u5316\uFF08\u529F\u80FD\u6682\u65E0\uFF09");
		this.Button_Maximize.setBounds(582, 8, 23, 23);
		this.Button_Maximize.setBorderPainted(false);
		this.Button_Maximize.setContentAreaFilled(false);
		this.Panel_Header.add(this.Button_Maximize);
		
		this.Button_Close = new JButton(icon_close);
		this.Button_Close.setToolTipText("\u5173\u95ED\u9875\u9762");
		this.Button_Close.setBounds(610, 8, 23, 23);
		this.Button_Close.setBorderPainted(false);
		this.Button_Close.setContentAreaFilled(false);
		this.Panel_Header.add(this.Button_Close);
		
		this.Button_Campus= new JButton(icon_campus);
		this.Button_Campus.setBounds(20, 8, 30, 30);
		this.Button_Campus.setBorderPainted(false);
		this.Button_Campus.setContentAreaFilled(false);
		this.Panel_Header.add(Button_Campus);
		
		this.Label_Title_VCE = new JLabel("Virtual Campus");
		this.Label_Title_VCE.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		this.Label_Title_VCE.setForeground(SystemColor.text);
		this.Label_Title_VCE.setBounds(60, 9, 186, 31);
		this.Panel_Header.add(this.Label_Title_VCE);
		
		this.Button_Login = new JButton(" \u672A\u767B\u5F55");
		this.Button_Login.setIcon(icon_login);
		this.Button_Login.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		this.Button_Login.setForeground(SystemColor.controlHighlight);
		this.Button_Login.setToolTipText("请登录");
		this.Button_Login.setBounds(427, 6, 118, 30);
		this.Button_Login.setBorderPainted(false);
		this.Button_Login.setContentAreaFilled(false);
		this.Panel_Header.add(this.Button_Login);
		
		this.Label_Upright = new JLabel("|");
		this.Label_Upright.setFont(new Font("幼圆", Font.BOLD, 18));
		this.Label_Upright.setForeground(UIManager.getColor("ComboBox.selectionBackground"));
		this.Label_Upright.setBounds(537, 6, 50, 30);
		this.Panel_Header.add(this.Label_Upright);		
	}
	
	private void setHeaderPanelListeners(){
		this.Frame_VC.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
			    int xOnScreen = e.getXOnScreen();
			    int yOnScreen = e.getYOnScreen();
			    xx = xOnScreen - xOld;
			    yy = yOnScreen - yOld;
			    Frame_VC.setLocation(xx, yy);//设置拖拽后，窗口的位置
			    if( LoginPopUpViewWindow != null){//设置登录界面跟着主窗口一起拖动
			    	LoginPopUpViewWindow.getFrame().setLocation(xx + 385, yy + 42);
			    	LoginPopUpViewWindow.getFrame().setAlwaysOnTop(true);//置于顶层
			    }
			    if( LoggedPopUpViewWindow != null){//设置登录界面跟着主窗口一起拖动
			    	LoggedPopUpViewWindow.getFrame().setLocation(xx + 385, yy + 42);
			    	LoggedPopUpViewWindow.getFrame().setAlwaysOnTop(true);//置于顶层
			    }
			}
		});
		this.Frame_VC.addMouseListener(new MouseAdapter() {
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
		this.Button_Close.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if(isLogin == true){
					Object[] options ={ "好啊！", "不用了，返回界面" };  
					int m = JOptionPane.showOptionDialog(null, "您尚未登出(〃'▽'〃)\n请问是否需要帮您登出并关闭页面？\n(～￣▽￣)～ ", "(╬￣皿￣)",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
					if(m==0){
						sockethelper.socketClose();	
						System.out.println("已经成功登出！");
						MainUIView_MY.setIsLogin(false);
						MainUIView_MY.UpdateButtonShowOffline();
					}
				} else
					System.exit(0);
			}

			public void mousePressed(MouseEvent e) {
				MainUIView_MY.this.Button_Close.setBorder(BorderFactory.createLoweredBevelBorder());
			}

			public void mouseReleased(MouseEvent e) {
				MainUIView_MY.this.Button_Close.setBorder(null);
			}

			public void mouseEntered(MouseEvent e) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/close_bright.png"));
				MainUIView_MY.this.Button_Close.setIcon(icon);
			}

			public void mouseExited(MouseEvent e) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/close.png"));
				MainUIView_MY.this.Button_Close.setIcon(icon);
			}
		});
		
		this.Button_Minimize.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			//	MainUIView_MY.this.setExtendedState(JFrame.ICONIFIED);注意不要写成这一行这样
				MainUIView_MY.this.Frame_VC.setExtendedState(JFrame.ICONIFIED);
			}

			public void mousePressed(MouseEvent e) {
				MainUIView_MY.this.Button_Minimize.setBorder(BorderFactory.createLoweredBevelBorder());
			}

			public void mouseReleased(MouseEvent e) {
				MainUIView_MY.this.Button_Minimize.setBorder(null);
			}

			public void mouseEntered(MouseEvent e) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/minimize_bright.png"));
				MainUIView_MY.this.Button_Minimize.setIcon(icon);
			}

			public void mouseExited(MouseEvent e) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/minimize.png"));
				MainUIView_MY.this.Button_Minimize.setIcon(icon);
			}
		});
		
		this.Button_Maximize.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			//	LoginView_MY.this.Frame_VC.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}

			public void mousePressed(MouseEvent e) {
				MainUIView_MY.this.Button_Maximize.setBorder(BorderFactory.createLoweredBevelBorder());
			}

			public void mouseReleased(MouseEvent e) {
				MainUIView_MY.this.Button_Maximize.setBorder(null);
			}

			public void mouseEntered(MouseEvent e) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/maximize_bright.png"));
				MainUIView_MY.this.Button_Maximize.setIcon(icon);
			}

			public void mouseExited(MouseEvent e) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/maximize.png"));
				MainUIView_MY.this.Button_Maximize.setIcon(icon);
			}
		});
		
		this.Button_Login.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if(isLogin == false)
					buildLoginPopUpView();//函数体在最下方
				else
					buildLoggedPopUpView();
			}

			public void mousePressed(MouseEvent e) {
				MainUIView_MY.this.Button_Login.setBorder(BorderFactory.createLoweredBevelBorder());
			}

			public void mouseReleased(MouseEvent e) {
				MainUIView_MY.this.Button_Login.setBorder(null);
			}

			public void mouseEntered(MouseEvent e) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/login_bright.png"));
				MainUIView_MY.this.Button_Login.setIcon(icon);
				MainUIView_MY.this.Button_Login.setForeground(SystemColor.text);
			}

			public void mouseExited(MouseEvent e) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/login.png"));
				MainUIView_MY.this.Button_Login.setIcon(icon);
				MainUIView_MY.this.Button_Login.setForeground(SystemColor.controlHighlight);
			}
		});
	}
		
	private void setMainPanel() {//设置主体面板
		this.Panel_Main = new JPanel();
//暂不设置Panel_Main的拖动功能
//		this.Panel_Main.addMouseMotionListener(new MouseMotionAdapter() {
//			@Override
//			public void mouseDragged(MouseEvent e) {
//			    int xOnScreen = e.getXOnScreen();
//			    int yOnScreen = e.getYOnScreen();
//			    int xx = xOnScreen - xOld;
//			    int yy = yOnScreen - yOld;
//			    Frame_VC.setLocation(xx , yy - 40);//设置拖拽后，窗口的位置
//			}
//		});
//		this.Panel_Main.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent e) {
//			    xOld = e.getX();//记录鼠标按下时的坐标
//			    yOld = e.getY();
//			}
//		});
		this.Panel_Main.setToolTipText("");
		this.Panel_Main.setFont(new Font("幼圆", Font.PLAIN, 36));
		this.Panel_Main.setForeground(UIManager.getColor("Button.darkShadow"));
		this.Panel_Main.setBackground(UIManager.getColor("Button.background"));
		this.Panel_Main.setBounds(0, 40, 640, 317);
		this.Frame_VC.getContentPane().add(this.Panel_Main);
		this.Panel_Main.setLayout(null);
				
		ImageIcon icon_library  = new ImageIcon(getClass().getResource("/images/sysbtn/library.png"));
		ImageIcon icon_stuRoll  = new ImageIcon(getClass().getResource("/images/sysbtn/stuRoll.png"));
		ImageIcon icon_bank     = new ImageIcon(getClass().getResource("/images/sysbtn/bank.png"));
		ImageIcon icon_shop     = new ImageIcon(getClass().getResource("/images/sysbtn/shop.png"));
		ImageIcon icon_course   = new ImageIcon(getClass().getResource("/images/sysbtn/course.png"));
		ImageIcon icon_venue    = new ImageIcon(getClass().getResource("/images/sysbtn/venue.png"));
		
		this.Button_Library = new JButton(icon_library);
		this.Button_Library.setBackground(UIManager.getColor("Button.background"));
		this.Button_Library.setBounds(80, 10, 100, 100);
		this.Button_Library.setBorderPainted(false);
		this.Button_Library.setContentAreaFilled(false);
		this.Panel_Main.add(this.Button_Library);
				
		this.Label_Library = new JLabel("\u56FE\u4E66\u9986");
		this.Label_Library.setFont(new Font("幼圆", Font.BOLD, 18));
		this.Label_Library.setForeground(SystemColor.textHighlight);
		this.Label_Library.setBounds(104, 110, 59, 27);
		this.Panel_Main.add(this.Label_Library);
		
		this.Button_StuRoll = new JButton(icon_stuRoll);
		this.Button_StuRoll.setBackground(UIManager.getColor("Button.background"));
		this.Button_StuRoll.setBounds(80, 170, 100, 100);
		this.Button_StuRoll.setBorderPainted(false);
		this.Button_StuRoll.setContentAreaFilled(false);
		this.Panel_Main.add(this.Button_StuRoll);
		
		this.Label_StuRoll = new JLabel("\u5B66\u7C4D\u7BA1\u7406");
		this.Label_StuRoll.setFont(new Font("幼圆", Font.BOLD, 18));
		this.Label_StuRoll.setForeground(SystemColor.textHighlight);
		this.Label_StuRoll.setBounds(95, 270, 76, 27);
		this.Panel_Main.add(this.Label_StuRoll);
		
		this.Button_Shop = new JButton(icon_shop);
		this.Button_Shop.setBackground(UIManager.getColor("Button.background"));
		this.Button_Shop.setBounds(280, 10, 100, 100);
		this.Button_Shop.setBorderPainted(false);
		this.Button_Shop.setContentAreaFilled(false);
		this.Panel_Main.add(this.Button_Shop);
		
		this.Label_Shop = new JLabel("\u5546\u5E97\u7BA1\u7406");
		this.Label_Shop.setFont(new Font("幼圆", Font.BOLD, 18));
		this.Label_Shop.setForeground(SystemColor.textHighlight);
		this.Label_Shop.setBounds(290, 110, 81, 27);
		this.Panel_Main.add(this.Label_Shop);
				
		this.Button_Bank = new JButton(icon_bank);
		this.Button_Bank.setBackground(UIManager.getColor("Button.background"));
		this.Button_Bank.setBounds(280, 170, 100, 100);
		this.Button_Bank.setBorderPainted(false);
		this.Button_Bank.setContentAreaFilled(false);
		this.Panel_Main.add(this.Button_Bank);
		
		this.Label_Bank = new JLabel("\u94F6\u884C\u7BA1\u7406");
		this.Label_Bank.setFont(new Font("幼圆", Font.BOLD, 18));
		this.Label_Bank.setForeground(SystemColor.textHighlight);
		this.Label_Bank.setBounds(295, 270, 76, 27);
		this.Panel_Main.add(this.Label_Bank);
		
		this.Button_Course = new JButton(icon_course);
		this.Button_Course.setBackground(UIManager.getColor("Button.background"));
		this.Button_Course.setBounds(480, 10, 100, 100);
		this.Button_Course.setBorderPainted(false);
		this.Button_Course.setContentAreaFilled(false);
		this.Panel_Main.add(this.Button_Course);
		
		this.Label_Course = new JLabel("\u9009\u8BFE\u7BA1\u7406");
		this.Label_Course.setFont(new Font("幼圆", Font.BOLD, 18));
		this.Label_Course.setForeground(SystemColor.textHighlight);
		this.Label_Course.setBounds(490, 110, 81, 27);
		this.Panel_Main.add(this.Label_Course);
		
		this.Button_Venue = new JButton(icon_venue);
		this.Button_Venue.setBackground(UIManager.getColor("Button.background"));
		this.Button_Venue.setBounds(480, 170, 100, 100);
		this.Button_Venue.setBorderPainted(false);
		this.Button_Venue.setContentAreaFilled(false);
		this.Panel_Main.add(this.Button_Venue);
		
		this.Label_Venue = new JLabel("\u573A\u9986\u9884\u7EA6");
		this.Label_Venue.setFont(new Font("幼圆", Font.BOLD, 18));
		this.Label_Venue.setForeground(SystemColor.textHighlight);
		this.Label_Venue.setBounds(495, 270, 76, 27);
		this.Panel_Main.add(this.Label_Venue);
	}

	private void setMainPanelListeners(){
		this.Panel_Main.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(LoginPopUp_MY.Frame_Login != null)//当鼠标离开LoginPopUp_MY弹框并点击的时候，设置LoginPopUp_MY消失
					LoginPopUp_MY.Frame_Login.dispose();
				if(LoggedPopUp_MY.Frame_Logged != null)//当鼠标离开LoggedPopUp_MY弹框并点击的时候，设置LoggedPopUp_MY消失
					LoggedPopUp_MY.Frame_Logged.dispose();
			}
		});
		this.Button_Library.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if(isLogin == true){
					mainView mv = new mainView(sockethelper,StudentId,5);
					mv.UpdateButtonShowOnline();
					Frame_VC.dispose();
				}
				else
					JOptionPane.showMessageDialog(null, "您尚未登录，无法进入图书馆模块！\n请先登录！");
			}
			public void mousePressed(MouseEvent e) {
				MainUIView_MY.this.Button_Library.setBorder(BorderFactory.createLoweredBevelBorder());
			}

			public void mouseReleased(MouseEvent e) {
				MainUIView_MY.this.Button_Library.setBorder(null);
			}

			public void mouseEntered(MouseEvent e) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/library_big.png"));
				MainUIView_MY.this.Button_Library.setIcon(icon);
			}

			public void mouseExited(MouseEvent e) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/library.png"));
				MainUIView_MY.this.Button_Library.setIcon(icon);
			}
		});
		
		this.Button_StuRoll.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if(isLogin == true){
					mainView mv = new mainView(sockethelper,StudentId,0);
					mv.UpdateButtonShowOnline();
					Frame_VC.dispose();
				}
				else
					JOptionPane.showMessageDialog(null, "您尚未登录，无法进入学籍管理模块！\n请先登录！");
			}
			public void mousePressed(MouseEvent e) {
				MainUIView_MY.this.Button_StuRoll.setBorder(BorderFactory.createLoweredBevelBorder());
			}

			public void mouseReleased(MouseEvent e) {
				MainUIView_MY.this.Button_StuRoll.setBorder(null);
			}

			public void mouseEntered(MouseEvent e) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/stuRoll_big.png"));
				MainUIView_MY.this.Button_StuRoll.setIcon(icon);
			}

			public void mouseExited(MouseEvent e) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/stuRoll.png"));
				MainUIView_MY.this.Button_StuRoll.setIcon(icon);
			}
		});
		
		this.Button_Course.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if(isLogin == true){
					mainView mv = new mainView(sockethelper,StudentId,3);
					mv.UpdateButtonShowOnline();
					System.out.println("dispose() 111");
					Frame_VC.dispose();
					System.out.println("dispose() 222");
					Frame_VC.dispose();
					System.out.println("dispose() 333");
					//mv.setTabbedPane(3);
					//sockethelper.setStuId(StudentId);
				    //mainView.setStudentId(StudentId);
				}
				else
					JOptionPane.showMessageDialog(null, "您尚未登录，无法进入选课模块！\n请先登录！");
			}

			public void mousePressed(MouseEvent e) {
				MainUIView_MY.this.Button_Course.setBorder(BorderFactory.createLoweredBevelBorder());
			}

			public void mouseReleased(MouseEvent e) {
				MainUIView_MY.this.Button_Course.setBorder(null);
			}

			public void mouseEntered(MouseEvent e) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/course_big.png"));
				MainUIView_MY.this.Button_Course.setIcon(icon);
			}

			public void mouseExited(MouseEvent e) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/course.png"));
				MainUIView_MY.this.Button_Course.setIcon(icon);
			}
		});
		
		this.Button_Shop.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if(isLogin == true){
					mainView mv = new mainView(sockethelper,StudentId,2);
					mv.UpdateButtonShowOnline();
					Frame_VC.dispose();
				}
				else
					JOptionPane.showMessageDialog(null, "您尚未登录，无法进入商店模块！\n请先登录！");
			}

			public void mousePressed(MouseEvent e) {
				MainUIView_MY.this.Button_Shop.setBorder(BorderFactory.createLoweredBevelBorder());
			}

			public void mouseReleased(MouseEvent e) {
				MainUIView_MY.this.Button_Shop.setBorder(null);
			}

			public void mouseEntered(MouseEvent e) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/shop_big.png"));
				MainUIView_MY.this.Button_Shop.setIcon(icon);
			}

			public void mouseExited(MouseEvent e) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/shop.png"));
				MainUIView_MY.this.Button_Shop.setIcon(icon);
			}
		});
		
		this.Button_Venue.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if(isLogin == true){
					mainView mv = new mainView(sockethelper,StudentId,4);
					mv.UpdateButtonShowOnline();
					Frame_VC.dispose();
				}
				else
					JOptionPane.showMessageDialog(null, "您尚未登录，无法进入场馆预约模块！\n请先登录！");
			}

			public void mousePressed(MouseEvent e) {
				MainUIView_MY.this.Button_Venue.setBorder(BorderFactory.createLoweredBevelBorder());
			}

			public void mouseReleased(MouseEvent e) {
				MainUIView_MY.this.Button_Venue.setBorder(null);
			}

			public void mouseEntered(MouseEvent e) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/venue_big.png"));
				MainUIView_MY.this.Button_Venue.setIcon(icon);
			}

			public void mouseExited(MouseEvent e) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/venue.png"));
				MainUIView_MY.this.Button_Venue.setIcon(icon);
			}
		});
		
		this.Button_Bank.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if(isLogin == true){
					mainView mv = new mainView(sockethelper,StudentId,1);
					mv.UpdateButtonShowOnline();
					Frame_VC.dispose();
				}
				else
					JOptionPane.showMessageDialog(null, "您尚未登录，无法进入银行模块！\n请先登录！");
			}

			public void mousePressed(MouseEvent e) {
				MainUIView_MY.this.Button_Bank.setBorder(BorderFactory.createLoweredBevelBorder());
			}

			public void mouseReleased(MouseEvent e) {
				MainUIView_MY.this.Button_Bank.setBorder(null);
			}

			public void mouseEntered(MouseEvent e) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/bank_big.png"));
				MainUIView_MY.this.Button_Bank.setIcon(icon);
			}

			public void mouseExited(MouseEvent e) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/bank.png"));
				MainUIView_MY.this.Button_Bank.setIcon(icon);
			}
		});
	}

	private void buildLoggedPopUpView(){//展示已登录弹框界面
		this.LoggedPopUpViewWindow = new LoggedPopUp_MY(StudentId);
		this.LoggedPopUpViewWindow.getFrame().setBounds(xx + 385, yy + 42, 250, 300);
	}
	
	private void buildLoginPopUpView(){//展示未登录弹框界面
		this.LoginPopUpViewWindow = new LoginPopUp_MY();
		this.LoginPopUpViewWindow.getFrame().setBounds(xx + 385, yy + 42, 250, 300);
	}
	
	private void showLibraryView() {//展示图书馆界面
//		try {
//			LibraryView_MY LibraryViewWindow = new LibraryView_MY();
//	//		LibraryViewWindow.getFrame().dispose();
//			LibraryViewWindow.getFrame().setUndecorated(true);
//			LibraryViewWindow.getFrame().setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
