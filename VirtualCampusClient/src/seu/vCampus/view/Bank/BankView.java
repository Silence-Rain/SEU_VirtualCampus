package seu.vCampus.view.Bank;

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

import seu.vCampus.util.SocketHelper;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.IOException;
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

public class BankView {

	private JFrame frame;
	private JTextField receiveField;
	private JTextField moneyField;
	private JTable table;
	private SocketHelper sockethelper;
	private JTextField textField;
	private JTextField textField_1;
	private JTextPane textPane_3;
	private JTextField textField_2;
	private JTable table_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table_2;
	private JTable table_3;
	private JTable table_4;
	private JTable table_5;
	private JTable table_6;
	private JTable table_7;
	private JTable table_8;
	private JTable table_9;
	private JTextField textField_5;
	private JTable table_10;
	private JTable table_11;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
//				String windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
//				try {
//					UIManager.setLookAndFeel(windows);
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (InstantiationException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (UnsupportedLookAndFeelException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				try {
					BankView window = new BankView();
					window.frame.dispose();
					window.frame.setUndecorated(true);
					window.frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BankView() {
		this.sockethelper = sockethelper;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 744, 394);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//the main frame the first tab!
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(10, 10, 50, 20);
		tabbedPane.setBackground(new Color(239, 247, 251));
		tabbedPane.setForeground(new Color(72, 61, 139));
		tabbedPane.setFont(new Font("YouYuan", 1, 14));
		
		// the information for student part

		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		infoPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		tabbedPane.addTab("\u5B66\u7C4D\u7BA1\u7406\r\n",
				new ImageIcon(BankView.class.getResource("/images/infoIcon.png")), infoPanel,
				null);
//		tabbedPane.setDisabledIconAt(0,
//				new ImageIcon(BankView.class.getResource("/com/jtattoo/plaf/icons/CheckSymbol.gif")));
		tabbedPane.setBackgroundAt(0, UIManager.getColor("Button.background"));
		infoPanel.setLayout(new CardLayout(0, 0));

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		infoPanel.add(tabbedPane_1, null);

		JPanel panel = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel, null);

		JPanel panel_1 = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel_1, null);
		tabbedPane.setForegroundAt(0, new Color(0, 0, 128));

		// the bank part
		JPanel bankPanel = new JPanel();
		bankPanel.setSize(new Dimension(30, 30));
		bankPanel.setPreferredSize(new Dimension(20, 20));
		bankPanel.setMinimumSize(new Dimension(30, 30));
		bankPanel.setFont(new Font("YouYuan", Font.PLAIN, 12));
		bankPanel.setForeground(new Color(85, 107, 47));
		bankPanel.setBackground(new Color(239, 247, 251));
		tabbedPane.addTab("\u94F6    \u884C",
				new ImageIcon(BankView.class.getResource("/images/bankIcon.png")), bankPanel,
				null);
		tabbedPane.setBackgroundAt(1, UIManager.getColor("Button.background"));

		JPanel recordPanel = new JPanel();
		JTabbedPane tab1 = new JTabbedPane(JTabbedPane.TOP);
		tab1.setBounds(0, 0, 700, 360);
		tab1.setFont(new Font("YouYuan", 1, 14));
		
		// bank------------------------------------transfer part
		JPanel transPanel = new JPanel();
		transPanel.setFont(new Font("YouYuan", Font.PLAIN, 12));
		transPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		tab1.addTab("\u8F6C  \u8D26", new ImageIcon(BankView.class.getResource("/images/bankIcon.png")),
				transPanel, "do nothing!");
		tab1.setForegroundAt(0, UIManager.getColor("Button.disabledForeground"));
		tab1.setBackgroundAt(0, UIManager.getColor("Button.background"));

		JLabel label = new JLabel("New label");
		label.setBackground(Color.LIGHT_GRAY);
		label.setIconTextGap(20);
		label.setIcon(new ImageIcon(getClass().getResource("/images/IMG_1968.JPG")));
		label.setBounds(186, 11, 166, 249);

		JTextPane textPane = new JTextPane();
		textPane.setBackground(SystemColor.menu);
		textPane.setFont(new Font("YouYuan", Font.PLAIN, 14));
		textPane.setText("\u8F6C\u8D26\u91D1\u989D\uFF1A");

		JTextField moneyField_1 = new JTextField();
		moneyField_1.setFont(new Font("monofur", Font.PLAIN, 14));
		moneyField_1.setColumns(10);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setFont(new Font("YouYuan", Font.PLAIN, 14));
		textPane_1.setText("\u8F6C\u8D26\u5BF9\u8C61\u8D26\u53F7\uFF1A");

		JTextField receiveField_1 = new JTextField();
		receiveField_1.setFont(new Font("monofur", Font.PLAIN, 14));
		receiveField_1.setColumns(10);

		JButton btnOk = new JButton("\u786E\u8BA4");// ActionListener----------------
		// btnOk.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		//
		// // String password = BankView.this.pwdField.getText();
		// String money = BankView.this.moneyField.getText();
		// String recieve = BankView.this.receiveField.getText();
		// if ((money.isEmpty()) || (recieve.isEmpty())) {
		// bankFailes();
		// }
		// }
		// });
		btnOk.setFont(new Font("YouYuan", Font.PLAIN, 14));

		JButton btnCancle = new JButton("\u53D6\u6D88");
		btnCancle.setFont(new Font("YouYuan", Font.PLAIN, 14));
// check Account part-------------------------------------------------------------------
		
		JPanel checkPanel = new JPanel();
		tab1.addTab("\u4F59\u989D\u67E5\u8BE2", new ImageIcon(BankView.class.getResource("/images/yuechaxun.png")), checkPanel,
				"do nothing!");
		checkPanel.setLayout(null);

		textPane_3 = new JTextPane();
		textPane_3.setBackground(UIManager.getColor("Button.background"));
		textPane_3.setFont(new Font("YouYuan", Font.PLAIN, 14));
		textPane_3.setText("\u94F6\u884C\u5361\u53F7\uFF1A");
		textPane_3.setBounds(32, 35, 83, 24);
		checkPanel.add(textPane_3);

		textField = new JTextField();
		textField.setBounds(127, 37, 114, 22);
		checkPanel.add(textField);
		textField.setColumns(10);

		JTextPane textPane_4 = new JTextPane();
		textPane_4.setBackground(UIManager.getColor("Button.background"));
		textPane_4.setFont(new Font("YouYuan", Font.PLAIN, 14));
		textPane_4.setText("\u5BC6\u7801\uFF1A");
		textPane_4.setBounds(32, 89, 83, 24);
		checkPanel.add(textPane_4);

		textField_1 = new JTextField();
		textField_1.setBounds(127, 91, 114, 22);
		checkPanel.add(textField_1);
		textField_1.setColumns(10);

		JButton button = new JButton("\u786E\u8BA4");
		button.setFont(new Font("YouYuan", Font.PLAIN, 14));
		button.setBounds(60, 179, 55, 22);
		checkPanel.add(button);

		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.setFont(new Font("YouYuan", Font.PLAIN, 14));
		button_1.setBounds(172, 179, 55, 22);
		checkPanel.add(button_1);

		// transfer record part-------------------------------------------------------------------------
		tab1.addTab("\u8BB0\u5F55\u67E5\u8BE2", new ImageIcon(BankView.class.getResource("/images/lookfor.png")),
				recordPanel, "do nothing!");
		recordPanel.setLayout(null);

		JTextPane textPane_2 = new JTextPane();
		textPane_2.setBackground(UIManager.getColor("Button.background"));
		textPane_2.setFont(new Font("YouYuan", Font.PLAIN, 14));
		textPane_2.setText("\u65E5\u671F\uFF1A");
		textPane_2.setBounds(43, 29, 50, 24);
		recordPanel.add(textPane_2);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "2017", "2016", "2015" }));//year----------------------
		comboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		comboBox.setBounds(96, 29, 73, 24);
		recordPanel.add(comboBox);

		JTextPane txtpnYear = new JTextPane();
		txtpnYear.setBackground(UIManager.getColor("Button.background"));
		txtpnYear.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		txtpnYear.setText("\u5E74\r\n");
		txtpnYear.setBounds(174, 29, 18, 24);
		recordPanel.add(txtpnYear);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		comboBox_1.setModel(new DefaultComboBoxModel(
				new String[] { "12", "11", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1" }));//month----------------------
		comboBox_1.setBounds(204, 29, 57, 24);
		recordPanel.add(comboBox_1);

		JTextPane textPane_6 = new JTextPane();
		textPane_6.setBackground(UIManager.getColor("Button.background"));
		textPane_6.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		textPane_6.setText("\u6708");
		textPane_6.setBounds(273, 29, 18, 24);
		recordPanel.add(textPane_6);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",//day---------
						"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));
		comboBox_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		comboBox_2.setBounds(304, 29, 59, 24);
		recordPanel.add(comboBox_2);

		JTextPane textPane_7 = new JTextPane();
		textPane_7.setBackground(UIManager.getColor("Button.background"));
		textPane_7.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		textPane_7.setText("\u65E5");
		textPane_7.setBounds(375, 29, 23, 24);
		recordPanel.add(textPane_7);

		JButton button_2 = new JButton("\u786E\u8BA4\u67E5\u8BE2");
		button_2.addActionListener(new ActionListener() {//addActionListener-------------------------
			public void actionPerformed(ActionEvent e) {
			}
		});
		bankPanel.setLayout(null);
		button_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		button_2.setBounds(43, 117, 100, 22);
		recordPanel.add(button_2);
		GroupLayout gl_transPanel = new GroupLayout(transPanel);
		gl_transPanel.setHorizontalGroup(gl_transPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_transPanel
				.createSequentialGroup()
				.addComponent(label, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_transPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_transPanel.createSequentialGroup()
								.addComponent(textPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(moneyField_1,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_transPanel.createSequentialGroup()
								.addGroup(gl_transPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(textPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnOk))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_transPanel.createParallelGroup(Alignment.LEADING).addComponent(btnCancle)
										.addComponent(receiveField_1, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(247, Short.MAX_VALUE)));
		gl_transPanel.setVerticalGroup(gl_transPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_transPanel
				.createSequentialGroup()
				.addGroup(gl_transPanel.createParallelGroup(Alignment.LEADING, false).addComponent(label)
						.addGroup(gl_transPanel.createSequentialGroup().addGap(55)
								.addGroup(gl_transPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(moneyField_1, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(32)
								.addGroup(gl_transPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(textPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(receiveField_1, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_transPanel.createParallelGroup(Alignment.BASELINE).addComponent(btnOk)
										.addComponent(btnCancle))
								.addGap(48)))
				.addContainerGap(66, Short.MAX_VALUE)));
		transPanel.setLayout(gl_transPanel);
		bankPanel.add(tab1);

		// the shop part----------------------------------------------------

		JPanel shopPanel = new JPanel();
		shopPanel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		tabbedPane.addTab("\u5546    \u5E97",
				new ImageIcon(BankView.class.getResource("/images/shopping.png")), shopPanel,
				null);
		shopPanel.setLayout(null);
//shop panel
		JTabbedPane tab2 = new JTabbedPane(JTabbedPane.TOP);
		tab2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		tab2.setBounds(0, 0, 700, 360);
		shopPanel.add(tab2);
//the buy buy buy part-----------------------------------------------------------------
		JPanel buyPanel = new JPanel();
		buyPanel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		tab2.addTab("\u5728\u7EBF\u8D2D\u4E70",
				new ImageIcon(BankView.class.getResource("/images/buying.png")), buyPanel, null);
		buyPanel.setLayout(null);

		JTextPane textPane_5 = new JTextPane();
		textPane_5.setDisabledTextColor(UIManager.getColor("Button.background"));
		textPane_5.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		textPane_5.setText("\u6309\u5546\u54C1\u540D\u79F0\uFF1A");
		textPane_5.setBounds(33, 32, 90, 24);
		buyPanel.add(textPane_5);

		textField_2 = new JTextField();
		textField_2.setBounds(135, 34, 114, 22);
		buyPanel.add(textField_2);
		textField_2.setColumns(10);

		JTextPane textPane_8 = new JTextPane();
		textPane_8.setDisabledTextColor(UIManager.getColor("Button.background"));
		textPane_8.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		textPane_8.setText("\u6309\u6807\u7B7E\uFF1A");
		textPane_8.setBounds(33, 87, 70, 24);
		buyPanel.add(textPane_8);

		JButton button_3 = new JButton("\u5546\u54C1\u67E5\u8BE2");
		button_3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		button_3.setBounds(341, 59, 103, 22);
		buyPanel.add(button_3);
		Object[][] cellData = { { "row1-col1", "row1-col2" }, { "row2-col1", "row2-col2" } };
		String[] columnNames = { "col1", "col2" };

		JButton button_5 = new JButton("\u52A0\u5165\u8D2D\u7269\u8F66");
		button_5.setBounds(405, 269, 119, 22);
		buyPanel.add(button_5);

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		comboBox_3.setModel(
				new DefaultComboBoxModel(new String[] { "\u8863", "\u98DF", "\u4F4F", "\u884C", "\u5176\u4ED6" }));
		comboBox_3.setBounds(135, 87, 114, 24);
		buyPanel.add(comboBox_3);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(33, 144, 488, 115);
		buyPanel.add(scrollPane_2);

		JTable tablebuy = new JTable();
		tablebuy.setRowHeight(25);
		scrollPane_2.setViewportView(tablebuy);
		tablebuy.setBackground(UIManager.getColor("Button.background"));
		tablebuy.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		tablebuy.setModel(new DefaultTableModel(
				new Object[][] { { "", "", "", "", "", null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null }, },
				new String[] { "\u5546\u54C1\u540D\u79F0", "\u751F\u4EA7\u5546", "\u6807\u7B7E", "\u5E93\u5B58/\u4EF6",
						"\u5355\u4EF7/\u5143", "\u662F\u5426\u52A0\u5165\u8D2D\u7269\u8F66" }) {
			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class,
					Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablebuy.getColumnModel().getColumn(4).setPreferredWidth(69);
		tablebuy.getColumnModel().getColumn(5).setPreferredWidth(99);
//the shopcat part---------------------------------------------------------------------------------------------
		JPanel shopcatPanel = new JPanel();
		shopcatPanel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		tab2.addTab("\u8D2D\u7269\u8F66",
				new ImageIcon(BankView.class.getResource("/images/shopcat.png")), shopcatPanel, null);
		shopcatPanel.setLayout(null);
		
		JButton button_6 = new JButton("\u7ED3\u7B97");
		button_6.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		button_6.setBounds(391, 189, 74, 33);
		shopcatPanel.add(button_6);
		
		JTextPane textPane_9 = new JTextPane();
		textPane_9.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		textPane_9.setText("\u5408\u8BA1\uFF1A");
		textPane_9.setBounds(151, 190, 48, 24);
		shopcatPanel.add(textPane_9);
		
		textField_3 = new JTextField();
		textField_3.setBounds(208, 190, 87, 22);
		shopcatPanel.add(textField_3);
		textField_3.setColumns(10);
		
		JTextPane textPane_10 = new JTextPane();
		textPane_10.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		textPane_10.setText("\u5143");
		textPane_10.setBounds(305, 190, 23, 24);
		shopcatPanel.add(textPane_10);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(-13, 0, 650, 300);
		shopcatPanel.add(layeredPane);
		
		JButton button_9 = new JButton("\u5220\u9664");
		button_9.setBounds(539, 51, 62, 22);
		layeredPane.add(button_9);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(45, 25, 484, 150);
		layeredPane.add(scrollPane_3);
		
		table_1 = new JTable();
		table_1.setRowHeight(25);
		scrollPane_3.setViewportView(table_1);
		table_1.setBackground(UIManager.getColor("Button.background"));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{Boolean.FALSE, "", "", "", "", "", ""},
				{Boolean.FALSE, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"\u9009\u62E9", "\u5546\u54C1\u540D\u79F0", "\u751F\u4EA7\u5546", "\u6807\u7B7E", "\u5E93\u5B58/\u4EF6", "\u5355\u4EF7/\u5143", "\u8D2D\u4E70\u6570\u91CF"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_1.getColumnModel().getColumn(0).setMinWidth(20);
		table_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
// the check shop part-----------------------------------------------------------------------------------
		JPanel checkshopPanel = new JPanel();
		checkshopPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		checkshopPanel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		tab2.addTab("\u8BA2\u5355\u67E5\u8BE2",
				new ImageIcon(BankView.class.getResource("/images/lookfor.png")),
				checkshopPanel, null);
		checkshopPanel.setLayout(null);
		
		JTextPane textPane_11 = new JTextPane();
		textPane_11.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_11.setText("下单日期:");
		textPane_11.setBounds(171, 12, 79, 24);
		checkshopPanel.add(textPane_11);
		
		textField_4 = new JTextField();
		textField_4.setBounds(260, 14, 114, 22);
		checkshopPanel.add(textField_4);
		textField_4.setColumns(10);
		
		JButton button_7 = new JButton("\u67E5\u8BE2");
		button_7.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		button_7.setBounds(417, 14, 86, 22);
		checkshopPanel.add(button_7);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(54, 75, 508, 150);
		checkshopPanel.add(scrollPane_4);
		
		table_2 = new JTable();
		table_2.setRowHeight(25);
		scrollPane_4.setViewportView(table_2);
		table_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		table_2.setBackground(UIManager.getColor("Button.background"));
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{"", "", "", "", "", "", "", ""},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"\u8BA2\u5355\u53F7", "\u5546\u54C1\u540D\u79F0", "\u751F\u4EA7\u5546", "\u6807\u7B7E", "\u5355\u4EF7/\u5143", "\u6570\u91CF/\u4EF6", "\u603B\u91D1\u989D/\u5143", "\u8D2D\u4E70\u65F6\u95F4"
			}
		));
		tabbedPane.setFont(new Font("YouYuan", 1, 14));

		JPanel orderPanel = new JPanel();
		tabbedPane.addTab("\u573A\u9986\u9884\u7EA6",
				new ImageIcon(BankView.class.getResource("/images/playroom.png")),
				orderPanel, null);
		orderPanel.setLayout(null);
// the order Pane-----------------------------------------------------------------------------------		
		JTabbedPane orderTab = new JTabbedPane(JTabbedPane.TOP);
		orderTab.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		orderTab.setBounds(0, 0, 700, 360);
		orderPanel.add(orderTab);
//the yuyue part----------------------------------------------------------------		
		JPanel yuyuePanel = new JPanel();
		orderTab.addTab("预约\r\n", null, yuyuePanel, null);
		yuyuePanel.setLayout(null);
		
		JTabbedPane Apptab = new JTabbedPane(JTabbedPane.TOP);
		Apptab.setBounds(10, 10, 500, 300);
		yuyuePanel.add(Apptab);
		
		JPanel todayPanel = new JPanel();
		Apptab.addTab("今天", null, todayPanel, null);
		todayPanel.setLayout(null);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(10, 10, 400, 150);
		todayPanel.add(scrollPane_5);
		
		table_3 = new JTable();
		scrollPane_5.setViewportView(table_3);
		table_3.setBackground(UIManager.getColor("Button.background"));
		table_3.setGridColor(SystemColor.inactiveCaption);
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
				{"\u7FBD\u6BDB\u7403", null, null, null},
				{"\u7BEE\u7403", null, null, null},
				{"\u4E52\u4E53\u7403", null, null, null},
				{"\u5065\u8EAB", null, null, null},
			},
			new String[] {
				"", "09:00-11:00", "2:00-4:00", "6:00-8:00"
			}
		));
		table_3.getColumnModel().getColumn(1).setMinWidth(20);
		table_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JPanel tomorrowPanel = new JPanel();
		Apptab.addTab("明天", null, tomorrowPanel, null);
		tomorrowPanel.setLayout(null);
		
		table_4 = new JTable();
		table_4.setGridColor(SystemColor.inactiveCaption);
		table_4.setBackground(UIManager.getColor("Button.background"));
		table_4.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "09:00-11:00", "2:00-4:00", "6:00-8:00"},
				{"\u7FBD\u6BDB\u7403", null, null, null},
				{"\u7BEE\u7403", null, null, null},
				{"\u4E52\u4E53\u7403", null, null, null},
				{"\u5065\u8EAB", null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		table_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		table_4.setBounds(0, 0, 495, 200);
		tomorrowPanel.add(table_4);
		
		JPanel afterPanel = new JPanel();
		Apptab.addTab("后天", null, afterPanel, null);
		afterPanel.setLayout(null);
		
		table_5 = new JTable();
		table_5.setGridColor(SystemColor.inactiveCaption);
		table_5.setBackground(UIManager.getColor("Button.background"));
		table_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		table_5.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "09:00-11:00", "2:00-4:00", "6:00-8:00"},
				{"\u7FBD\u6BDB\u7403", null, null, null},
				{"\u7BEE\u7403", null, null, null},
				{"\u4E52\u4E53\u7403", null, null, null},
				{"\u5065\u8EAB", null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		table_5.setBounds(0, 0, 495, 200);
		afterPanel.add(table_5);
		
		JPanel yuyueRedPanel = new JPanel();
		orderTab.addTab("预约记录", null, yuyueRedPanel, null);
//choose class!!!-----------------------------------------------------------------------
		
		JPanel classPanel = new JPanel();
		classPanel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		tabbedPane.addTab("\u9009    \u8BFE",
				new ImageIcon(BankView.class.getResource("/images/chooseclass.png")),
				classPanel, null);
		classPanel.setLayout(null);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		tabbedPane_2.setBounds(0, 0, 700, 360);
		classPanel.add(tabbedPane_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		tabbedPane_2.addTab("选  课", new ImageIcon(BankView.class.getResource("/images/chooseclass.png")), panel_4, null);
		panel_4.setLayout(null);
		
		JButton button_12 = new JButton("选择");
		button_12.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_12.setBounds(82, 169, 93, 23);
		panel_4.add(button_12);
		
		JButton button_13 = new JButton("退选");
		button_13.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_13.setBounds(334, 169, 93, 23);
		panel_4.add(button_13);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(37, 31, 500, 100);
		panel_4.add(scrollPane_1);
		
		table_6 = new JTable();
		scrollPane_1.setViewportView(table_6);
		table_6.setGridColor(SystemColor.inactiveCaption);
		table_6.setBackground(UIManager.getColor("Button.background"));
		table_6.setModel(new DefaultTableModel(
			new Object[][] {
				{"", "", "", "", "", ""},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"\u8BFE\u7A0BID", "\u8BFE\u7A0B\u540D\u79F0", "\u6388\u8BFE\u6559\u5E08", "\u4E0A\u8BFE\u65F6\u95F4", "\u8BFE\u7A0B\u5B66\u5206", "\u8BFE\u7A0B\u72B6\u6001"
			}
		));
		table_6.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		table_6.setRowHeight(25);
//the check grade part---------------------------------------------------------------------------		
		JPanel panel_2 = new JPanel();
		tabbedPane_2.addTab("成绩查询", new ImageIcon(BankView.class.getResource("/images/checkgrade.png")), panel_2, null);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		scrollPane.setBounds(31, 45, 528, 140);
		panel_2.add(scrollPane);
		
		table_7 = new JTable();
		table_7.setRowHeight(25);
		table_7.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		table_7.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"\u8BFE\u7A0BID", "\u8BFE\u7A0B\u540D\u79F0", "\u6388\u8BFE\u6559\u5E08", "\u8BFE\u7A0B\u5B66\u5206", "\u6210\u7EE9"
			}
		));
		scrollPane.setViewportView(table_7);
		
		JPanel panel_3 = new JPanel();
		tabbedPane_2.addTab("课表查询", new ImageIcon(BankView.class.getResource("/images/chooseclassResult.png")), panel_3, null);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(166, 28, 417, 200);
		panel_3.add(scrollPane_6);
		
		table_8 = new JTable();
		table_8.setRowHeight(25);
		table_8.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane_6.setViewportView(table_8);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		scrollPane_7.setBounds(10, 28, 120, 200);
		panel_3.add(scrollPane_7);
		
		table_9 = new JTable();
		table_9.setRowHeight(25);
		table_9.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		table_9.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"\u8BFE\u7A0B\u540D\u79F0", "\u5B66\u5206"
			}
		));
		table_9.getColumnModel().getColumn(0).setPreferredWidth(85);
		table_9.getColumnModel().getColumn(1).setPreferredWidth(70);
		scrollPane_7.setViewportView(table_9);
		
		   
		
		tabbedPane.setBackgroundAt(4, UIManager.getColor("Button.background"));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.PREFERRED_SIZE, 737, GroupLayout.PREFERRED_SIZE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE));
//the libirary!!!-----------------------------------------------------------------------------		
		JPanel librPanel = new JPanel();
		tabbedPane.addTab("图 书 馆\r\n", new ImageIcon(BankView.class.getResource("/images/libr.png")), librPanel, null);
		librPanel.setLayout(null);
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		tabbedPane_3.setBounds(0, 0, 700, 360);
		librPanel.add(tabbedPane_3);
		
		JPanel LookforPanel = new JPanel();
		tabbedPane_3.addTab("查询书籍", null, LookforPanel, null);
		LookforPanel.setLayout(null);
		
		JTextPane textPane_12 = new JTextPane();
		textPane_12.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_12.setText("书名：");
		textPane_12.setBounds(116, 28, 62, 21);
		LookforPanel.add(textPane_12);
		
		textField_5 = new JTextField();
		textField_5.setBounds(201, 28, 142, 21);
		LookforPanel.add(textField_5);
		textField_5.setColumns(10);
		
		JButton button_4 = new JButton("查询");
		button_4.setBounds(397, 26, 93, 23);
		LookforPanel.add(button_4);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(43, 97, 437, 147);
		LookforPanel.add(scrollPane_8);
		
		table_10 = new JTable();
		table_10.setRowHeight(25);
		table_10.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"\u4E66\u540D", "\u51FA\u7248\u793E", "\u4F5C\u8005", "\u5E93\u5B58/\u672C", "\u5DF2\u501F/\u672C"
			}
		));
		scrollPane_8.setViewportView(table_10);
//borrrowed	books part-------------------------------------------------------------	
		JPanel borrowedPanel = new JPanel();
		tabbedPane_3.addTab("已借书籍", null, borrowedPanel, null);
		borrowedPanel.setLayout(null);
		
		JScrollPane scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(49, 62, 493, 168);
		borrowedPanel.add(scrollPane_9);
		
		table_11 = new JTable();
		table_11.setRowHeight(25);
		table_11.setModel(new DefaultTableModel(
			new Object[][] {
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
			},
			new String[] {
				"\u4E66\u540D", "\u51FA\u7248\u793E", "\u4F5C\u8005", "\u501F\u9605\u65F6\u95F4", "\u5230\u671F\u65F6\u95F4"
			}
		));
		scrollPane_9.setViewportView(table_11);
		frame.getContentPane().setLayout(groupLayout);
		
	}

	private void bankFailes() {
		// TODO Auto-generated method stub

	}
}
