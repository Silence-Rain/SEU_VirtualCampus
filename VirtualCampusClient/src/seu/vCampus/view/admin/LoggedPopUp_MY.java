package seu.vCampus.view.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
//
//import cn.edu.seu.client.bz.ILib;
//import cn.edu.seu.client.bz.ILibImpl;
//import cn.edu.seu.client.bz.IUserImpl;
import common.StudentRollInfo;
import common.UserInfo;
import seu.vCampus.bz.IUserImpl;
//import common.UserInfo;
import seu.vCampus.util.SocketHelper;
import seu.vCampus.view.login.MainUIView_MY;
import seu.vCampus.view.login.RoundedButton_MY;
import seu.vCampus.view.login.ShadowBorder_MY;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoggedPopUp_MY {

	public static JFrame Frame_Logged;
	
	private JPanel Panel_Main;
	private JButton Button_Triangle;
	private RoundedButton_MY Button_Logout;
	
	private JPanel Panel_Header;
	private JLabel Label_WelcomeC;
	private JLabel Label_WelcomeE;
	private JLabel Label_StudentName;
	private JLabel Label_WelcomeWords;
	private JButton Button_Close;
	int xOld;
	int yOld;

	private String StudentId;
	private static SocketHelper sockethelper;
	
	public static void setSocketHelper(SocketHelper sh){
		sockethelper = sh;		
	}	
		
	public void setStudentId(String StudentId){
		this.StudentId = StudentId;
	}
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LoggedPopUp_MY window = new LoggedPopUp_MY();
//					window.Frame_Logged.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public LoggedPopUp_MY(String StudentId) {
		setStudentId(StudentId);
		setHeaderPanel();
		setMainPanel();
		setMainPanelListeners();
	}
//	
	public JFrame getFrame(){
		return this.Frame_Logged;
	}

	private void setHeaderPanel(){
		this.Frame_Logged = new JFrame();
		this.Frame_Logged.getContentPane().setLayout(null);
		this.Frame_Logged.setUndecorated(true);
		this.Frame_Logged.setVisible(true);
	//	this.Frame_Logged.setBounds(745, 192, 250, 300);//�ڵ��������ĵط����������ڴ������������С	
	//	Frame_Login.setBackground(new Color(0,0,0,0));//����͸��������������Ӱ�ĺ����в���Ӱ��
	//	Frame_Login.setOpacity(0.5f);���ô��������͸����

		
		this.Panel_Header = new JPanel();
		this.Panel_Header.setBounds(0, 0, 250, 30);
		this.Panel_Header.setLayout(null);
		this.Frame_Logged.getContentPane().add(Panel_Header);
		
		ImageIcon icon_triangle = new ImageIcon(getClass().getResource("/images/sysbtn/triangle.png"));
		this.Button_Triangle = new JButton(icon_triangle);
		this.Button_Triangle.setBounds(83, 0, 83, 35);
		this.Button_Triangle.setBorderPainted(false);
		this.Button_Triangle.setContentAreaFilled(false);
		this.Panel_Header.add(this.Button_Triangle);
		
	}
	
	private void setMainPanel() {
		this.Panel_Main = new JPanel();
		this.Panel_Main.setBounds(0, 32, 250, 267);
		this.Panel_Main.setLayout(null);
		this.Frame_Logged.getContentPane().add(this.Panel_Main);
		this.Panel_Main.setBorder(BorderFactory.createCompoundBorder(
				ShadowBorder_MY.newBuilder().shadowSize(5).center().build(),
				BorderFactory.createLineBorder(Color.WHITE)
				));//������Ӱ
	
		this.Button_Logout= new RoundedButton_MY("�ǳ�", 0);
		Button_Logout.setText("登出");
		this.Button_Logout.setBounds(50, 205, 150, 30);
		this.Panel_Main.add(Button_Logout);
		
		ImageIcon icon_campus = new ImageIcon(getClass().getResource("/images/sysbtn/campus_big.png"));
		
		ImageIcon icon_close    = new ImageIcon(getClass().getResource("/images/sysbtn/close_bright.png"));
		
		this.Label_WelcomeC = new JLabel("\u6B22\u8FCE\u6765\u5230\u865A\u62DF\u6821\u56ED\uFF01");
		this.Label_WelcomeC.setForeground(SystemColor.textHighlight);
		this.Label_WelcomeC.setFont(new Font("Dialog", Font.PLAIN, 20));
		this.Label_WelcomeC.setBounds(36, 116, 180, 38);
		this.Panel_Main.add(this.Label_WelcomeC);
		
		this.Label_WelcomeE = new JLabel("Welcome to Virtual Campus!");
		this.Label_WelcomeE.setForeground(SystemColor.textHighlight);
		this.Label_WelcomeE.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		this.Label_WelcomeE.setBounds(50, 153, 150, 20);
		this.Panel_Main.add(this.Label_WelcomeE);

		this.Button_Close = new JButton(icon_close);
		this.Button_Close.setBounds(220, 8, 23, 23);
		this.Button_Close.setBorderPainted(false);
		this.Button_Close.setContentAreaFilled(false);
		this.Panel_Main.add(this.Button_Close);
		
		this.Label_StudentName = new JLabel("\u5F20sd");
		this.Label_StudentName.setForeground(Color.DARK_GRAY);
		this.Label_StudentName.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		this.Label_StudentName.setBounds(45, 76, 63, 30);
		String userName = getInfo();
		this.Label_StudentName.setText(userName);
		this.Panel_Main.add(this.Label_StudentName);
		
		this.Label_WelcomeWords = new JLabel("\u540C\u5B66\uFF0C\u65E9\u4E0A\u597D\uFF01");
		this.Label_WelcomeWords.setForeground(Color.DARK_GRAY);
		this.Label_WelcomeWords.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		this.Label_WelcomeWords.setBounds(110, 76, 113, 30);
		this.Panel_Main.add(this.Label_WelcomeWords);
	}

	private String getInfo(){
		IUserImpl iuser = new IUserImpl(MainUIView_MY.getSocketHelper());
		String uId = MainUIView_MY.getStudentId();
		StudentRollInfo sri = new StudentRollInfo(uId,"","","","","","","","","","","");
		StudentRollInfo sriReturn = iuser.getMoreInfo(sri);
		return sriReturn.getName();
	}
	
	private void setMainPanelListeners(){
		this.Button_Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (MainUIView_MY.getIsLogin() == true) {
					SocketHelper sh = MainUIView_MY.getSocketHelper();
					System.out.println(MainUIView_MY.getStudentId());
					UserInfo user = new UserInfo(MainUIView_MY.getStudentId(), "", "", "", "");
					boolean flaggg = new IUserImpl(sh).UserLogout(user);
					if(flaggg == true){
						sh.socketClose();	
						System.out.println("已经成功登出！");
						MainUIView_MY.setIsLogin(false);
						MainUIView_MY.UpdateButtonShowOffline();
						MainUIView_MY.setMinimizeRestore();
						mainAdminView.frame.setVisible(false);
						mainAdminView.frame.dispose();
						Frame_Logged.dispose();
						MainUIView_MY.setMinimizeRestore();
					}
//				}else{
//					JOptionPane.showMessageDialog(null,"�Ѿ��ǳ��������ظ��ǳ���");
				}
			}
		});	
		this.Button_Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Frame_Logged.dispose();
			}
		});
	}
}
