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
import seu.vCampus.bz.IUserImpl;
import seu.vCampus.util.SetTableColor;
import seu.vCampus.util.SocketHelper;
import seu.vCampus.view.login.MainUIView_MY;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Vector;
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
import common.BankInfo;
import common.OrderInfo;
import common.StudentRollInfo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BankView {

	// private static IBankImpl ibank;
	JFrame frame;

	private JTable table;
	private SocketHelper sockethelper;
	private JTextField moneyField;
	private JTextField recieverField;
	private JTextField cardIDField;

	private JScrollPane scrollPane;
	private JTextField textField_Balance;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_pwd;
	private JTextField textField_pwd2;
	private JTextField textField_2;
	private JTextField textField_3;
	String id = null;
	public BankView(mainView mView, SocketHelper sockethelper,String stuId) {

		this.sockethelper = sockethelper;
		id = stuId;
		initialize(mView);
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize(mainView mView) {
		// the bank part

		JPanel recordPanel = new JPanel();
		JTabbedPane tab1 = new JTabbedPane(JTabbedPane.TOP);
		tab1.setBounds(0, 0, 785, 490);
		tab1.setFont(new Font("YouYuan", 1, 14));
		mView.bankPanel.add(tab1);
		// bank------------------------------------transfer part

		JPanel transferPanel = new JPanel();
		tab1.addTab("快速转账", new ImageIcon(BankView.class.getResource("/images/bank/transfer.png")), transferPanel, null);
		transferPanel.setLayout(null);
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBackground(UIManager.getColor("Button.background"));
		textPane_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textPane_1.setText("转账金额：");
		textPane_1.setBounds(42, 229, 93, 21);
		transferPanel.add(textPane_1);
		//splitline
		ImageIcon icon_01  = new ImageIcon(getClass().getResource("/images/spilitLines/splitLine_2.png"));
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 150, 350, 48);
		transferPanel.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(icon_01);
		//bankcard
		ImageIcon icon_02  = new ImageIcon(getClass().getResource("/images/bank/bankcard.png"));
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(421, 213, 130, 130);
		transferPanel.add(lblNewLabel);
		lblNewLabel.setIcon(icon_02);
		

		moneyField = new JTextField();
		moneyField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		moneyField.setBackground(UIManager.getColor("Button.disabledShadow"));
		moneyField.setBounds(187, 229, 156, 22);
		transferPanel.add(moneyField);
		moneyField.setColumns(10);

		JTextPane textPane_33 = new JTextPane();
		textPane_33.setBackground(UIManager.getColor("Button.background"));
		textPane_33.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textPane_33.setText("对方账号：\r\n");
		textPane_33.setBounds(42, 260, 93, 21);
		transferPanel.add(textPane_33);

		recieverField = new JTextField();
		recieverField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		recieverField.setBounds(187, 260, 156, 22);
		transferPanel.add(recieverField);
		recieverField.setColumns(10);
		ImageIcon icon_btn1  = new ImageIcon(getClass().getResource("/images/btn/btn_ok.png"));

		JButton btnOk = new JButton("");// ActionListener----------------
		btnOk.setBackground(UIManager.getColor("Button.highlight"));
		btnOk.setContentAreaFilled(false);
		btnOk.setIcon(new ImageIcon(BankView.class.getResource("/images/btn/ok_13.png")));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = BankView.this.textField_2.getText();
				
				String receiver = BankView.this.recieverField.getText();
				//System.out.println(password + " " + money + " " + reciever);
				//System.out.println(money+" "+ password+" "+ receiver);
				if ((moneyField.getText().equals("")) || (textField_2.getText().equals(""))||(password.equals(""))){
					JOptionPane.showMessageDialog(null, "亲，请输入哦~");
					return;
				}
				double money = Double.valueOf(BankView.this.moneyField.getText());
				double test = new IBankImpl(BankView.this.sockethelper).checkAccount(id);
				if(test < money){
					JOptionPane.showMessageDialog(null, "亲，余额不足哦！");
					return;
				}
				long time = Calendar.getInstance().getTimeInMillis()/1000;
				boolean flag = new IBankImpl(BankView.this.sockethelper).transferAccount(money, receiver, password,time , test);
				System.out.println("flag is " + flag);
					
				if (flag)///////////
				{
					JOptionPane.showMessageDialog(null, "恭喜你，转账成功！");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "转账不成功！");
				}
				
			}
		});

		btnOk.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		transferPanel.add(btnOk);
		btnOk.setBounds(68, 345, 70, 32);
		JButton btnCancle = new JButton("");
		btnCancle.setContentAreaFilled(false);
		btnCancle.setIcon(new ImageIcon(BankView.class.getResource("/images/btn/cancle_13.png")));
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 BankView.this.textField_2.setText("");
				BankView.this.moneyField.setText("");
				BankView.this.recieverField.setText("");
				
				
			}
		});
		btnCancle.setBackground(UIManager.getColor("Button.background"));
		btnCancle.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		transferPanel.add(btnCancle);
		btnCancle.setBounds(240, 345, 70, 32);
		
		JTextPane textPane_7 = new JTextPane();
		textPane_7.setBackground(UIManager.getColor("Button.background"));
		textPane_7.setForeground(SystemColor.textHighlight);
		textPane_7.setFont(new Font("微软雅黑", Font.PLAIN, 70));
		
		//判断时间是否正确
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int hour = c.get(Calendar.HOUR_OF_DAY); 
		System.out.println(hour);
		if(0<=hour&&hour<=6){
			textPane_7.setText("上午好！");
		}
		if(6<hour&&hour<=11){
			textPane_7.setText("上午好！");
		}
		if(11<hour&&hour<=13){
			textPane_7.setText("中午好！");
		}
		if(13<hour&&hour<=18){
			textPane_7.setText("下午好！");
		}
		if(18<hour&&hour<=23){
			textPane_7.setText("晚上好！");
		}
		
		textPane_7.setBounds(42, 40, 268, 130);
		transferPanel.add(textPane_7);
		
		textField_1 = new JTextField();
		textField_1.setBackground(UIManager.getColor("Button.background"));
		textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		textField_1.setBounds(307, 99, 130, 41);
		String userName = getInfo();
		this.textField_1.setText(userName);
		textField_1.setBorder(null);
		//textField_1.setText("");
		transferPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(UIManager.getColor("Button.background"));
		textPane.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textPane.setText("密码：");
		textPane.setBounds(42, 294, 78, 21);
		transferPanel.add(textPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(187, 292, 156, 23);
		transferPanel.add(layeredPane);
		
		textField_2 = new JTextField();
		textField_2.setBorder(null);
		layeredPane.setLayer(textField_2, 3);
		textField_2.setBounds(23, 1, 131, 20);
		layeredPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("");
		ImageIcon icon_04  = new ImageIcon(getClass().getResource("/images/bank/pwd.png"));
		lblNewLabel_3.setIcon(icon_04);
		lblNewLabel_3.setBounds(4, 1, 156, 20);
		layeredPane.add(lblNewLabel_3);
		
		textField_pwd = new JTextField();
		textField_pwd.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_pwd.setBounds(187, 292, 156, 22);
		transferPanel.add(textField_pwd);
		textField_pwd.setColumns(10);
		
		
		JPanel checkPanel = new JPanel();
		tab1.addTab("\u4F59\u989D\u67E5\u8BE2", new ImageIcon(BankView.class.getResource("/images/icon/yuechaxun.png")),
				checkPanel, "do nothing!");
		checkPanel.setLayout(null);
		//lines
		ImageIcon icon_03  = new ImageIcon(getClass().getResource("/images/spilitLines/splitLine_1.png"));
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(40, 199, 369, 48);
		checkPanel.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(icon_03);
		
		JTextPane textPane_3 = new JTextPane();// 用户提示文字;
		textPane_3.setBackground(UIManager.getColor("Button.background"));
		textPane_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textPane_3.setText("\u94F6\u884C\u5361\u53F7\uFF1A");
		textPane_3.setBounds(49, 61, 97, 24);
		checkPanel.add(textPane_3);

		cardIDField = new JTextField();
		cardIDField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		cardIDField.setBounds(193, 61, 156, 22);
		checkPanel.add(cardIDField);
		cardIDField.setColumns(10);

		JButton button = new JButton("");
		button.setIcon(new ImageIcon(BankView.class.getResource("/images/btn/ok_13.png")));
		button.setContentAreaFilled(false);
		//button.setIcon(icon1);
		button.setMargin(new Insets(0, 0, 0, 0));
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = BankView.this.cardIDField.getText();
				 String pwd = BankView.this.textField_pwd2.getText();
				System.out.println("id in bankView" + id);
				if(id.equals("")||pwd.equals(""))
				{
					JOptionPane.showMessageDialog(null, "亲，请输入内容！");
					return;
				}
				
				Double test = new IBankImpl(BankView.this.sockethelper).checkAccount(id);//////

				BankView.this.textField_Balance.setText(String.valueOf(test));
				BankView.this.textField_Balance.setEditable(false);
				/*
				 * if(test ==null){ JOptionPane.showMessageDialog(null,
				 * "帐号或密码错误！"); BankView.this.cardIDField.setText("");
				 * BankView.this.pwdField.setText(""); } else{
				 * JOptionPane.showMessageDialog(null,""); }
				 */

			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button.setBounds(61, 162, 83, 49);
		checkPanel.add(button);

		JButton button_1 = new JButton("");
		button_1.setContentAreaFilled(false);
		//button.setIcon(icon1);
		button_1.setMargin(new Insets(0, 0, 0, 0));
		button_1.setIcon(new ImageIcon(BankView.class.getResource("/images/btn/cancle_13.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_1.setBounds(248, 148, 83, 75);
		checkPanel.add(button_1);

		JTextPane textPane_5 = new JTextPane();
		textPane_5.setText("余额：");
		textPane_5.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textPane_5.setBackground(SystemColor.menu);
		textPane_5.setBounds(49, 295, 83, 24);
		checkPanel.add(textPane_5);

		textField_Balance = new JTextField();
		textField_Balance.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_Balance.setBackground(UIManager.getColor("Button.background"));
		textField_Balance.setColumns(10);
		textField_Balance.setBounds(204, 297, 145, 22);
		checkPanel.add(textField_Balance);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setText("密码：");
		textPane_4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textPane_4.setBackground(SystemColor.menu);
		textPane_4.setBounds(48, 109, 78, 21);
		checkPanel.add(textPane_4);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBounds(193, 108, 156, 22);
		checkPanel.add(layeredPane_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(0, 0, 156, 22);
		layeredPane_1.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("");
		ImageIcon icon_5  = new ImageIcon(getClass().getResource("/images/bank/pwd.png"));
		lblNewLabel_4.setIcon(icon_5);
		layeredPane_1.setLayer(lblNewLabel_4, 1);
		lblNewLabel_4.setBounds(4, 1, 150, 21);
		layeredPane_1.add(lblNewLabel_4);
		
		textField_pwd2 = new JTextField();
		textField_pwd2.setBorder(null);
		layeredPane_1.setLayer(textField_pwd2, 3);
		textField_pwd2.setBounds(27, 1, 127, 20);
		layeredPane_1.add(textField_pwd2);
		textField_pwd2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_pwd2.setColumns(10);

		// transfer record
		// part-------------------------------------------------------------------------
		tab1.addTab("\u8BB0\u5F55\u67E5\u8BE2", new ImageIcon(BankView.class.getResource("/images/icon/lookfor.png")),
				recordPanel, "do nothing!");
		recordPanel.setLayout(null);

		JButton button_record = new JButton("");
		button_record.setContentAreaFilled(false);
		ImageIcon icon_22  = new ImageIcon(getClass().getResource("/images/btn/check.png"));
		button_record.setIcon(icon_22);
		button_record.addActionListener(new ActionListener() {// addActionListener-------------------------
			public void actionPerformed(ActionEvent e) {
				try {
					scrollPane.setViewportView(getBankRecordTable());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// bankPanel.setLayout(null);
		button_record.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_record.setBounds(432, 32, 65, 38);
		recordPanel.add(button_record);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(67, 99, 430, 237);
		recordPanel.add(scrollPane);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setBackground(UIManager.getColor("Button.background"));
		textPane_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textPane_2.setText("亲爱的：");
		textPane_2.setBounds(67, 39, 82, 33);
		recordPanel.add(textPane_2);
		
		textField = new JTextField();
		textField.setBorder(null);
		textField.setBackground(UIManager.getColor("Button.background"));
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField.setText(userName);
		textField.setBounds(159, 39, 106, 31);
		recordPanel.add(textField);
		textField.setColumns(10);
		
		JTextPane textPane_6 = new JTextPane();
		textPane_6.setBackground(UIManager.getColor("Button.background"));
		textPane_6.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textPane_6.setText("欢迎你~");
		textPane_6.setBounds(283, 38, 85, 32);
		recordPanel.add(textPane_6);

		// bankPanel.add(tab1);
	}

	

	private JTable getBankRecordTable() throws ClassNotFoundException {
		// 检查表格是否为空.
		table = new JTable(); // 创建表格控件.
		table.setRowHeight(25); // 设置行高.
		String[] columns = { "日期", "收款账号", "金额" }; // 创建列名数组.
		// 创建表格模型.
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		table.setModel(model); // 设置表格模型.
		// 设置第二列的列宽
		TableColumn secColumn = table.getColumnModel().getColumn(1);
		// secColumn.setPreferredWidth(55);
		// secColumn.setMaxWidth(55);
		// secColumn.setMinWidth(55);
		List<BankInfo> creditlist = new IBankImpl(BankView.this.sockethelper).record();
		for (int i = 0; i < creditlist.size(); i++) {
			BankInfo bList = creditlist.get(i);
			// OrderInfo List = blist.get(i);
			Long time = bList.getTransferDate() * 1000;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date(time);
			String rs = simpleDateFormat.format(date);
			Object[] rowData = { rs, bList.getTransferTo(), bList.getTransferAmount() };// socket返回值
			model.addRow(rowData);
		}
		table.setEnabled(false);
		SetTableColor.makeFace(table);
		return table;
	}
	private String getInfo(){
		IUserImpl iuser = new IUserImpl(MainUIView_MY.getSocketHelper());
		String uId = MainUIView_MY.getStudentId();
		StudentRollInfo sri = new StudentRollInfo(uId,"","","","","","","","","","","");
		StudentRollInfo sriReturn = iuser.getMoreInfo(sri);
		return sriReturn.getName();
	}
}
