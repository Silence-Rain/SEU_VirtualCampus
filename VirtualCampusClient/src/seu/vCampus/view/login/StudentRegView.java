package seu.vCampus.view.login;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.DropMode;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;
import java.awt.Insets;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;

//import cn.edu.seu.client.bz.ILogin;
//import cn.edu.seu.client.bz.ILoginImpl;
//import cn.edu.seu.client.bz.IUserImpl;
//import cn.edu.seu.client.util.CreateIdentity;
//import cn.edu.seu.client.util.SocketHelper;
import common.UserInfo;
import seu.vCampus.bz.IUserImpl;
import seu.vCampus.util.SocketHelper;

import com.sun.awt.AWTUtilities;
public class StudentRegView extends JFrame{

		private JFrame Frame_Login;
		
		private JPanel Panel_Header;
		private JLabel Label_Title_VCE;
		private JButton Button_Close;
		private JButton Button_Campus;

		private JPanel Panel_Main;
		private JLabel Label_Cat;
		private JLabel Label_CatPaw;
		private JLabel Label_CatTail;
		private SocketHelper sockethelper = new SocketHelper();

		int xOld;
		int yOld;
		
		private CreateIdentity identity = new CreateIdentity();//
		private JTextField textField_ID;
		private JTextField textField_Name;
		private JPasswordField passwordField;
		private JPasswordField passwordField_Re;
		JButton jb_Register = new JButton("");
		JButton jb_Cancel = new JButton("");
//		ImageIcon icon1 = new ImageIcon(getClass().getResource("/images/btn/cancle_13.png"));
//		jb_Register.setContentAreaFilled(false);
//		jb_Register.setIcon(icon1);
//		jb_Register.setMargin(new Insets(0, 0, 0, 0));
//		
//		
//		jb_Cancel.setContentAreaFilled(false);
		
		
     	public StudentRegView() {
			sockethelper.getConnection();
			MainUIView_MY.setSocketHelper(sockethelper);
			initialize();
			
		}
		public static void main(String[] args){
			StudentRegView stu = new StudentRegView();
		}
		private void initialize(){
			setHeaderPanel();
			setHeaderPanelListeners();
			setMainPanel();
			DoRegister();
		}
		private void setHeaderPanel(){
			this.Frame_Login = new JFrame();
			this.Frame_Login.setBounds(545, 192, 284, 311);
			this.Frame_Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.Frame_Login.getContentPane().setLayout(null);
			this.Frame_Login.setUndecorated(true);
			this.Frame_Login.setVisible(true);
			this.Frame_Login.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
				    int xOnScreen = e.getXOnScreen();
				    int yOnScreen = e.getYOnScreen();
				    int xx = xOnScreen - xOld;
				    int yy = yOnScreen - yOld;
				    Frame_Login.setLocation(xx , yy);//脡猫脰脙脥脧脳搂潞贸拢卢麓掳驴脷碌脛脦禄脰脙
				}
			});
			this.Frame_Login.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
				    xOld = e.getX();//录脟脗录脢贸卤锚掳麓脧脗脢卤碌脛脳酶卤锚
				    yOld = e.getY();
				}
			});
			
			AWTUtilities.setWindowShape(this.Frame_Login, new RoundRectangle2D.Double(0.0D, 0.0D, this.Frame_Login.getWidth(), this.Frame_Login.getHeight(), 26.0D, 26.0D));
			
			this.Panel_Header = new JPanel();
			this.Panel_Header.setBackground(SystemColor.textHighlight);
			this.Panel_Header.setForeground(SystemColor.textHighlight);
			this.Panel_Header.setBounds(0, 0, 284, 40);
			this.Panel_Header.setLayout(null);
			this.Frame_Login.getContentPane().add(this.Panel_Header);

			ImageIcon icon_close = new ImageIcon(getClass().getResource("/images/sysbtn/close.png"));
			ImageIcon icon_campus   = new ImageIcon(getClass().getResource("/images/sysbtn/campus.png"));
			
			this.Button_Close = new JButton(icon_close);
			this.Button_Close.setToolTipText("\u5173\u95ED\u9875\u9762");
			this.Button_Close.setBounds(249, 12, 23, 23);
			this.Button_Close.setBorderPainted(false);
			this.Button_Close.setContentAreaFilled(false);
			this.Panel_Header.add(this.Button_Close);
			

			this.Button_Campus= new JButton(icon_campus);
			this.Button_Campus.setBounds(10, 8, 30, 30);
			this.Button_Campus.setBorderPainted(false);
			this.Button_Campus.setContentAreaFilled(false);
			this.Panel_Header.add(this.Button_Campus);
			
			this.Label_Title_VCE = new JLabel("Virtual Campus");
			this.Label_Title_VCE.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			this.Label_Title_VCE.setForeground(SystemColor.text);
			this.Label_Title_VCE.setBounds(49, 7, 186, 31);
			this.Panel_Header.add(this.Label_Title_VCE);
		}

		private void setHeaderPanelListeners(){
			this.Button_Close.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {
					Frame_Login.dispose();
				}

				public void mousePressed(MouseEvent e) {
					Button_Close.setBorder(BorderFactory.createLoweredBevelBorder());
				}

				public void mouseReleased(MouseEvent e) {
					Button_Close.setBorder(null);
				}

				public void mouseEntered(MouseEvent e) {
					ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/close_bright.png"));
					Button_Close.setIcon(icon);
				}

				public void mouseExited(MouseEvent e) {
					ImageIcon icon = new ImageIcon(getClass().getResource("/images/sysbtn/close.png"));
					Button_Close.setIcon(icon);
				}
			});		
		}
		
		private void setMainPanel() {
			this.Panel_Main = new JPanel();
			this.Panel_Main.setBounds(0, 40, 284, 271);
			this.Panel_Main.setLayout(null);
		
			/*this.Panel_Main.setBorder(BorderFactory.createCompoundBorder(
					ShadowBorder_MY.newBuilder().shadowSize(5).center().build(),
					BorderFactory.createLineBorder(Color.WHITE)
					));//*/	
		    this.Frame_Login.getContentPane().add(this.Panel_Main);
			
			this.Label_CatPaw = new JLabel("");
			this.Label_CatPaw.setIcon(new ImageIcon(StudentRegView.class.getResource("/images/sysbtn/catPaw.png")));
			this.Label_CatPaw.setBounds(66, 25, 150, 23);
			this.Panel_Main.add(this.Label_CatPaw);
			//this.Frame_Login.getLayeredPane().add(Panel_Main,new Integer(Integer.MIN_VALUE));
			
			this.Label_Cat = new JLabel("");
			this.Label_Cat.setIcon(new ImageIcon(StudentRegView.class.getResource("/images/sysbtn/cat.png")));
			this.Label_Cat.setBounds(101, 0, 80, 50);
			this.Panel_Main.add(Label_Cat);
				
			this.Label_CatTail = new JLabel("");
			this.Label_CatTail.setIcon(new ImageIcon(StudentRegView.class.getResource("/images/sysbtn/tailHHH.png")));
			this.Label_CatTail.setBounds(110, 160, 106, 61);
			this.Panel_Main.add(this.Label_CatTail);
			
			JLabel lb_ID = new JLabel("学号");
			lb_ID.setFont(new Font("骞煎渾", Font.PLAIN, 16));
			lb_ID.setBounds(10, 58, 71, 23);
			Panel_Main.add(lb_ID);
			
			textField_ID = new JTextField();
			textField_ID.setBounds(111, 58, 140, 23);
			Panel_Main.add(textField_ID);
			textField_ID.setColumns(10);
			
			JLabel lb_Name = new JLabel("姓名");
			lb_Name.setFont(new Font("骞煎渾", Font.PLAIN, 16));
			lb_Name.setBounds(10, 88, 71, 23);
			Panel_Main.add(lb_Name);
			
			textField_Name = new JTextField();
			textField_Name.setColumns(10);
			textField_Name.setBounds(111, 85, 140, 23);
			Panel_Main.add(textField_Name);
			
			passwordField = new JPasswordField();
			passwordField.setBounds(111, 115, 140, 21);
			Panel_Main.add(passwordField);
			
			JLabel lb_Pwd = new JLabel("设置密码");
			lb_Pwd.setFont(new Font("骞煎渾", Font.PLAIN, 16));
			lb_Pwd.setBounds(10, 110, 71, 32);
			Panel_Main.add(lb_Pwd);
			
			passwordField_Re = new JPasswordField();
			passwordField_Re.setBounds(110, 141, 140, 21);
			Panel_Main.add(passwordField_Re);
			
			JLabel label = new JLabel("确认密码");
			label.setFont(new Font("骞煎渾", Font.PLAIN, 16));
			label.setBounds(10, 135, 81, 32);
			Panel_Main.add(label);
			jb_Register.setIcon(new ImageIcon(StudentRegView.class.getResource("/images/btn/resiger.png")));
			
			
			
			jb_Register.setFont(new Font("骞煎渾", Font.PLAIN, 16));
			jb_Register.setBounds(41, 190, 66, 32);
			Panel_Main.add(jb_Register);
			jb_Cancel.setIcon(new ImageIcon(StudentRegView.class.getResource("/images/btn/cancle_13.png")));
			
			
			
			jb_Cancel.setFont(new Font("骞煎渾", Font.PLAIN, 16));
			jb_Cancel.setBounds(195, 190, 66, 32);
			//this.Frame_Login.getContentPane().add(this.textField_Name);
			Panel_Main.add(jb_Cancel);
		}
		
	protected void DoRegister() {
		// 纭娉ㄥ唽
		jb_Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cId = StudentRegView.this.textField_ID.getText();
				String cName = StudentRegView.this.textField_Name.getText();
				String cPwd = StudentRegView.this.passwordField.getText();
				System.out.println(cPwd);
				String cRePwd = StudentRegView.this.passwordField_Re.getText();
				if ((cId.length() != 0) && (cName.length() != 0) && (cPwd.length() != 0) && (cRePwd.length() != 0)) {
					if ((cPwd.equals(cRePwd))) {
						UserInfo s = new UserInfo("", "", "", "", "");
						s.setStuId(cId);
						s.setName(cName);
						s.setPwd(cPwd);
						s.setType("student");
						boolean isSave = new IUserImpl(sockethelper).UserRegister(s);

						if (isSave) {
							JOptionPane.showMessageDialog(null, "注册成功！");
							JOptionPane.showMessageDialog(null, "成功登录！");
							MainUIView_MY.setStudentId(cId); //设置id更新
							MainUIView_MY.setIsLogin(true);
							MainUIView_MY.UpdateButtonShowOnline();
							MainUIView_MY.setMinimizeRestore();//设置主窗体恢复	
							Frame_Login.dispose();
							return;
						} else {
							JOptionPane.showMessageDialog(null, "注册失败！");
							Frame_Login.dispose();
							return;
						}
					} else {
						JOptionPane.showMessageDialog(null, "请重新确认密码！");
					}
				} else {
					JOptionPane.showMessageDialog(null, "还有空白项未输入！");
					return;
				}
			}
		});
		// 鍙栨秷
		jb_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
		


