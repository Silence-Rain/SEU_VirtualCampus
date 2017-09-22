package seu.vCampus.view.admin;

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
import seu.vCampus.util.SocketHelper;

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
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import javax.swing.table.DefaultTableModel;

import common.Bank;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import seu.vCampus.view.admin.*;
import seu.vCampus.view.stu.BankView;

public class mainAdminView {
	
    //private static IBankImpl ibank;
	private JFrame frame;
	
	private JTable table;
	private SocketHelper sockethelper = new SocketHelper();

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
	public JPanel infoPanel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				String windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
//				String windows = "org.jvnet.substance.skin.SubstanceGreenMagicLookAndFeel";
				try {
					UIManager.setLookAndFeel(windows);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					//IBank ibank = new IBankImpl(sockethelper);
					//System.out.println(1);
					mainAdminView window = new mainAdminView();
					//System.out.println(2);
					window.frame.dispose();
					//System.out.println(3);
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
	public mainAdminView() {
		//this.ibank = ibank;
		this.sockethelper = sockethelper;
		sockethelper.getConnection();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 795, 471);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 30, 300};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel titlepanel = new JPanel();
		titlepanel.setBounds(100, 100,744, 50);
		titlepanel.setLayout(null);
		//FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.anchor = GridBagConstraints.SOUTH;
		gbc_panel_5.gridwidth = 2;
		gbc_panel_5.insets = new Insets(0, 0, 2, 0);
		gbc_panel_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 0;
		frame.getContentPane().add(titlepanel, gbc_panel_5);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridheight = 2;
		gbc_tabbedPane.gridwidth = 2;
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 1;
		frame.getContentPane().add(tabbedPane, gbc_tabbedPane);
		tabbedPane.setFont(new Font("YouYuan", 1, 14));
		
		
				//场馆预约panel
				orderPanel = new JPanel();
				tabbedPane.addTab("\u573A\u9986\u9884\u7EA6",
						null,
						orderPanel, null);
				orderPanel.setLayout(null);
				OrderView oview = new OrderView(mainAdminView.this, mainAdminView.this.sockethelper);
				//商店panel
				shopPanel = new JPanel();
				shopPanel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				tabbedPane.addTab("\u5546    \u5E97",
						null, shopPanel,
						null);//放在main里面
				shopPanel.setLayout(null);
				ShopView sview = new ShopView(mainAdminView.this,mainAdminView.this.sockethelper);
				//图书馆panel
				librPanel = new JPanel();
				tabbedPane.addTab("\u56fe\u0020\u4e66\u0020\u9986",null, librPanel, null);
				librPanel.setLayout(null);
				LibrView lview = new LibrView(mainAdminView.this,mainAdminView.this.sockethelper);
				//选课
				classPanel = new JPanel();
				tabbedPane.addTab("选课管理",null, classPanel, null);
				classPanel.setLayout(null);
				AdminSelCourseView cview = new AdminSelCourseView(mainAdminView.this,mainAdminView.this.sockethelper);
				
				
				
				//学籍管理
				
				infoPanel = new JPanel();
				System.out.println(1);
				tabbedPane.addTab("学籍管理",null, infoPanel, null);
				infoPanel.setLayout(null);
				System.out.println(3);
				AdminStuInfoView aview = new AdminStuInfoView(mainAdminView.this,mainAdminView.this.sockethelper);
				System.out.println(2);
				
				
	}
}

