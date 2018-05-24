package seu.vCampus.view.login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPopUp_MY {

	public static JFrame Frame_Login;
	
	private JPanel Panel_Login;
	private JButton Button_Campus;
	private JButton Button_Triangle;
	private RoundedButton_MY Button_Register;//�Զ���Բ�ǰ�ť�࣬�̳а�ť��
	private RoundedButton_MY Button_Login;
	
	private JPanel Panel_Header;
	private JLabel Label_WelcomeC;
	private JLabel Label_WelcomeE;
	private JButton Button_Close;
	int xOld;
	int yOld;
	
	public LoginPopUp_MY() {
		setHeaderPanel();
		setMainPanel();
		setMainPanelListeners();
	}
	
	public JFrame getFrame(){
		return this.Frame_Login;
	}

	private void setHeaderPanel(){
		this.Frame_Login = new JFrame();
		this.Frame_Login.getContentPane().setLayout(null);
		this.Frame_Login.setUndecorated(true);
		this.Frame_Login.setVisible(true);
	//	this.Frame_Login.setBounds(745, 192, 250, 300);�ڵ��������ĵط����������ڴ������������С	
	//	Frame_Login.setBackground(new Color(0,0,0,0));//����͸��������������Ӱ�ĺ����в���Ӱ��
	//	Frame_Login.setOpacity(0.5f);���ô��������͸����

		
//�ݲ���Ҫ����Panel_Login���϶�����
//		this.Panel_Login.addMouseMotionListener(new MouseMotionAdapter() {
//			@Override
//			public void mouseDragged(MouseEvent e) {
//			    int xOnScreen = e.getXOnScreen();
//			    int yOnScreen = e.getYOnScreen();
//			    int xx = xOnScreen - xOld;
//			    int yy = yOnScreen - yOld;
//			    Frame_Login.setLocation(xx , yy);//������ק�󣬴��ڵ�λ��
//			}
//		});
//		this.Panel_Login.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent e) {
//			    xOld = e.getX();//��¼��갴��ʱ������
//			    yOld = e.getY();
//			}
//		});
		this.Panel_Header = new JPanel();
		this.Panel_Header.setBounds(0, 0, 250, 30);
		this.Panel_Header.setLayout(null);
		this.Frame_Login.getContentPane().add(Panel_Header);
		
		ImageIcon icon_triangle = new ImageIcon(getClass().getResource("/images/sysbtn/triangle.png"));
		this.Button_Triangle = new JButton(icon_triangle);
		this.Button_Triangle.setBounds(83, 0, 83, 35);
		this.Button_Triangle.setBorderPainted(false);
		this.Button_Triangle.setContentAreaFilled(false);
		this.Panel_Header.add(this.Button_Triangle);
		
	}
	
	private void setMainPanel() {
		this.Panel_Login = new JPanel();
		this.Panel_Login.setBounds(0, 32, 250, 267);
		this.Panel_Login.setLayout(null);
		this.Frame_Login.getContentPane().add(this.Panel_Login);
		this.Panel_Login.setBorder(BorderFactory.createCompoundBorder(
				ShadowBorder_MY.newBuilder().shadowSize(5).center().build(),
				BorderFactory.createLineBorder(Color.WHITE)
				));//������Ӱ
	
		this.Button_Login= new RoundedButton_MY("��¼", 0);
		Button_Login.setText("登录");
		this.Button_Login.setBounds(50, 165, 150, 30);
		this.Panel_Login.add(Button_Login);
		
		this.Button_Register = new RoundedButton_MY("ע��", 0);
		Button_Register.setText("注册");
		this.Button_Register.setBounds(50, 210, 150, 30);	
		this.Panel_Login.add(Button_Register);
		
		ImageIcon icon_campus = new ImageIcon(getClass().getResource("/images/sysbtn/campus_big.png"));	
		this.Button_Campus = new JButton(icon_campus);
		this.Button_Campus.setBounds(80, 10, 80, 80);
		this.Button_Campus.setBorderPainted(false);
		this.Button_Campus.setContentAreaFilled(false);
		this.Panel_Login.add(this.Button_Campus);
		
		ImageIcon icon_close    = new ImageIcon(getClass().getResource("/images/sysbtn/close_bright.png"));
		
		this.Label_WelcomeC = new JLabel("\u6B22\u8FCE\u767B\u5F55\u865A\u62DF\u6821\u56ED\uFF01");
		this.Label_WelcomeC.setForeground(SystemColor.textHighlight);
		this.Label_WelcomeC.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		this.Label_WelcomeC.setBounds(55, 95, 150, 35);
		this.Panel_Login.add(this.Label_WelcomeC);
		
		this.Label_WelcomeE = new JLabel("Welcome to Virtual Campus!");
		this.Label_WelcomeE.setForeground(SystemColor.textHighlight);
		this.Label_WelcomeE.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		this.Label_WelcomeE.setBounds(55, 122, 150, 20);
		this.Panel_Login.add(this.Label_WelcomeE);

		this.Button_Close = new JButton(icon_close);
		this.Button_Close.setBounds(220, 8, 23, 23);
		this.Button_Close.setBorderPainted(false);
		this.Button_Close.setContentAreaFilled(false);
		this.Panel_Login.add(this.Button_Close);
	}

	private void setMainPanelListeners(){
		this.Button_Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					LoginView_MY window = new LoginView_MY();
				//	window.Frame_Login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Frame_Login.dispose();
				MainUIView_MY.setMinimize();
			}
		});
		this.Button_Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					StudentRegView stu = new StudentRegView();
					// window.Frame_Login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Frame_Login.dispose();
				MainUIView_MY.setMinimize();
			}
		});
		this.Button_Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Frame_Login.dispose();
			}
		});
	}
	
}
