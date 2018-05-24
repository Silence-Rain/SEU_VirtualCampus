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
//import cn.edu.seu.client.util.CreateIdentity;
import common.User;
import common.UserInfo;
import seu.vCampus.bz.ILogin;
import seu.vCampus.bz.ILoginImpl;
import seu.vCampus.util.SocketHelper;

import com.sun.awt.AWTUtilities;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginView_MY extends JFrame {

	private JFrame Frame_Login;
	
	private JPanel Panel_Header;
	private JLabel Label_Title_VCE;
	private JButton Button_Close;
	private JButton Button_Campus;

	private JPanel Panel_Main;
	private JLabel Label_Cat;
	private JLabel Label_CatPaw;
	private JLabel Label_CatTail;
	private JLabel Label_StudentIcon;
	private JLabel Label_PasswordIcon;
	private JLabel Label_VerificationCode;
	private JPasswordField PasswordField_Password;	
	private JTextField TextField_VerificationCode;
	private JTextField TextField_StudentID;
	private JTextField TextField_Frame;
	private RoundedButton_MY Button_Login;
	private JComboBox ComboBox_Type;

	int xOld;
	int yOld;
	
	private CreateIdentity identity = new CreateIdentity();//��֤��
	private JButton Button_Identity = new JButton(new ImageIcon(this.identity.getIdentity()));
	

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LoginView_MY window = new LoginView_MY();
//					window.Frame_Login.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public LoginView_MY() {
		setHeaderPanel();
		setHeaderPanelListeners();
		setMainPanel();
		setMainPanelListeners();
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
			    Frame_Login.setLocation(xx , yy);//������ק�󣬴��ڵ�λ��
			}
		});
		this.Frame_Login.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			    xOld = e.getX();//��¼��갴��ʱ������
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
		this.Panel_Main.setBorder(BorderFactory.createCompoundBorder(
				ShadowBorder_MY.newBuilder().shadowSize(5).center().build(),
				BorderFactory.createLineBorder(Color.WHITE)
				));//������Ӱ
		this.Frame_Login.getContentPane().add(this.Panel_Main);
		
		this.Label_CatPaw = new JLabel("");
		this.Label_CatPaw.setIcon(new ImageIcon(LoginView_MY.class.getResource("/images/sysbtn/catPaw.png")));
		this.Label_CatPaw.setBounds(65, 55, 150, 23);
		this.Panel_Main.add(this.Label_CatPaw);
		
		this.Label_Cat = new JLabel("");
		this.Label_Cat.setIcon(new ImageIcon(LoginView_MY.class.getResource("/images/sysbtn/cat.png")));
		this.Label_Cat.setBounds(101, 26, 80, 50);
		this.Panel_Main.add(Label_Cat);
		
		this.Button_Identity.setBounds(175, 176, 67, 24);
		this.Panel_Main.add(Button_Identity);
		this.Frame_Login.getLayeredPane().add(Button_Identity,new Integer(Integer.MAX_VALUE));//�Ѳ������ڶ���
				
		String Prompt_StudentID = "\u8BF7\u8F93\u5165\u5B66\u53F7";
		String Prompt_Password  = "\u8BF7\u8F93\u5165\u5BC6\u7801";
		String Prompt_VerificationCode ="\u8BF7\u8F93\u5165\u9A8C\u8BC1\u7801";
		
		this.Label_StudentIcon = new JLabel("");
		this.Label_StudentIcon.setIcon(new ImageIcon(LoginView_MY.class.getResource("/images/sysbtn/studentIcon.png")));
		this.Label_StudentIcon.setBounds(45, 77, 23, 23);
		this.Panel_Main.add(this.Label_StudentIcon);
		
		this.Label_PasswordIcon = new JLabel("");
		this.Label_PasswordIcon.setIcon(new ImageIcon(LoginView_MY.class.getResource("/images/sysbtn/passwordIcon.png")));
		this.Label_PasswordIcon.setBounds(45, 107, 23, 23);
		this.Panel_Main.add(this.Label_PasswordIcon);
		
		this.Label_VerificationCode = new JLabel("");
		this.Label_VerificationCode.setIcon(new ImageIcon(LoginView_MY.class.getResource("/images/sysbtn/verificationCodeIcon.png")));
		this.Label_VerificationCode.setBounds(40, 137, 23, 23);
		this.Panel_Main.add(this.Label_VerificationCode);
	
		this.ComboBox_Type = new JComboBox();
		this.ComboBox_Type.setForeground(Color.DARK_GRAY);
		this.ComboBox_Type.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		this.ComboBox_Type.setBackground(SystemColor.menu);
		this.ComboBox_Type.setModel(new DefaultComboBoxModel(new String[] {"\u5B66\u751F", "\u6559\u5E08", "\u7BA1\u7406\u5458"}));
		this.ComboBox_Type.setBounds(175, 77, 67, 21);
		this.Frame_Login.getLayeredPane().add(this.ComboBox_Type,new Integer(Integer.MAX_VALUE));//�Ѳ������ڶ���
		this.Panel_Main.add(this.ComboBox_Type);
				
		this.TextField_StudentID = new JTextField();
		this.TextField_StudentID.setDropMode(DropMode.INSERT);
		this.TextField_StudentID.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		this.TextField_StudentID.setForeground(SystemColor.controlShadow);
		this.TextField_StudentID.setText(Prompt_StudentID);
		this.TextField_StudentID.setBounds(71, 74, 171, 28);
		this.TextField_StudentID.setColumns(10);
		this.TextField_StudentID.setBorder(null);
		this.Panel_Main.add(this.TextField_StudentID);
		
		this.PasswordField_Password = new JPasswordField();
		this.PasswordField_Password.setEchoChar('\0');	//��������������ʾ
	//	this.passwordField.setEchoChar('��');			//�������벻ֱ����ʾ�����������
		this.PasswordField_Password.setBounds(71, 104, 171, 28);
		this.PasswordField_Password.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		this.PasswordField_Password.setForeground(SystemColor.controlShadow);
		this.PasswordField_Password.setText(Prompt_Password);
		this.PasswordField_Password.setBorder(null);
		this.Panel_Main.add(this.PasswordField_Password);
			
		this.TextField_VerificationCode = new JTextField();
		this.TextField_VerificationCode.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		this.TextField_VerificationCode.setForeground(SystemColor.controlShadow);
		this.TextField_VerificationCode.setText(Prompt_VerificationCode);
		this.TextField_VerificationCode.setColumns(10);
		this.TextField_VerificationCode.setBounds(71, 134, 171, 28);
		this.TextField_VerificationCode.setBorder(null);
		this.Panel_Main.add(this.TextField_VerificationCode);
		
		this.TextField_StudentID.addFocusListener(new MyJTextFieldListener(Prompt_StudentID, this.TextField_StudentID));
		this.TextField_VerificationCode.addFocusListener(new MyJTextFieldListener(Prompt_VerificationCode, this.TextField_VerificationCode));
		this.PasswordField_Password.addFocusListener(new MyJPasswordFieldListener_MY(Prompt_Password,this.PasswordField_Password));
		
		this.Button_Login= new RoundedButton_MY("登录", 0);
		this.Button_Login.setBounds(37, 169, 208, 35);
		this.Panel_Main.add(Button_Login);
					
		this.Label_CatTail = new JLabel("");
		this.Label_CatTail.setIcon(new ImageIcon(LoginView_MY.class.getResource("/images/sysbtn/tailHHH.png")));
		this.Label_CatTail.setBounds(101, 200, 106, 61);
		this.Panel_Main.add(this.Label_CatTail);
		
		this.TextField_Frame = new JTextField();
		this.TextField_Frame.setBackground(Color.WHITE);
		this.TextField_Frame.setBounds(37, 73, 208, 91);
		this.TextField_Frame.setEditable(false);
		this.TextField_Frame.setColumns(10);
		this.Panel_Main.add(this.TextField_Frame);					
	}

	private void setMainPanelListeners(){
		this.Button_Login.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					SocketHelper sockethelper = new SocketHelper();
					sockethelper.getConnection();
					doLogin(sockethelper);
					MainUIView_MY.setSocketHelper(sockethelper);
				}
			}
		});
		this.Panel_Main.getRootPane().setDefaultButton(this.Button_Login);//���»س�����ִ������������Ӧ����
		this.Button_Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SocketHelper sockethelper = new SocketHelper();
				sockethelper.getConnection();
				doLogin(sockethelper);
				MainUIView_MY.setSocketHelper(sockethelper);
			}
		});	
		this.Button_Identity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginView_MY.this.identity();
			}
		});
	}
	
	private void identity() {
		this.Button_Identity.setIcon(new ImageIcon(this.identity.getIdentity()));
		this.Button_Identity.repaint();
	}
	

	private void doLogin(SocketHelper sockethelper){
		ILogin ius = new ILoginImpl(sockethelper);
		String id = this.TextField_StudentID.getText();
		String pwd = String.valueOf(this.PasswordField_Password.getPassword());
		String type = (String) this.ComboBox_Type.getSelectedItem();
		String typeE =null;
		
		switch(type){
		case "学生":
			typeE = "student";
			break;
		case "教师":
			typeE = "teacher";
			break;
		case "管理员":
			typeE = "admin";
			break;
		default:
			JOptionPane.showMessageDialog(null, "登录类型转换失败！");		
		}

		String iden = this.TextField_VerificationCode.getText();
		
		MainUIView_MY.setSocketHelper(sockethelper);
		LoggedPopUp_MY.setSocketHelper(sockethelper);
		
		if (!iden.equals(CreateIdentity.identity)) {//�ж���֤���Ƿ���ȷ  //验证码 要改回去
			JOptionPane.showMessageDialog(null, "验证码错误！");
			return;
		}
		
		UserInfo user = new UserInfo(id,pwd,typeE,"","");
		
		if (ius.Login(user)) {//�ж����ݿ��Ƿ�ƥ�䲢��¼�ɹ�
			System.out.println("登录成功");
			if (type == "学生") {		
				JOptionPane.showMessageDialog(null, "学生界面成功登录！");
				MainUIView_MY.setStudentId(id); //����id����
				MainUIView_MY.setIsLogin(true);
				MainUIView_MY.setType("student");
				MainUIView_MY.UpdateButtonShowOnline();
				MainUIView_MY.setMinimizeRestore();//����������ָ�
				Frame_Login.dispose();			
			} 
			else if (type == "管理员") {
				JOptionPane.showMessageDialog(null, "管理员界面成功登录！");
				MainUIView_MY.setStudentId(id); //����id����
				MainUIView_MY.setIsLogin(true);
				MainUIView_MY.setType("admin");
				MainUIView_MY.UpdateButtonShowOnline();
				MainUIView_MY.setMinimizeRestore();//����������ָ�
				Frame_Login.dispose();	
			}
		} 
		else {
			
			JOptionPane.showMessageDialog(null, "登录失败！");
		}
	}
}

class MyJTextFieldListener implements FocusListener {
	String info;
	JTextField jtf;

	public MyJTextFieldListener(String info, JTextField jtf) {
		this.info = info;
		this.jtf = jtf;
	}

	@Override
	public void focusGained(FocusEvent e) {// ��ý����ʱ��,�����ʾ����
		String temp = jtf.getText();
		if (temp.equals(info)) {
			jtf.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent e) {// ʧȥ�����ʱ��,�ж����Ϊ��,����ʾ��ʾ����
		String temp = jtf.getText();
		if (temp.equals("")) {
			jtf.setText(info);
		}
	}
	

}