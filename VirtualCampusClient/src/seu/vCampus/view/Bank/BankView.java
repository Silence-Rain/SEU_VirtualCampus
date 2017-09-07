package seu.vCampus.view.Bank;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.border.LineBorder;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.awt.Dimension;

public class BankView {

	private JFrame frame;

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
				String windows="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
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
					BankView window = new BankView();
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
    
	private void initialize() {
//		try {
//			Image image = ImageIO.read(getClass().getResource("/images/IMG_1968.JPG"));
//			setIconImage(image);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		frame = new JFrame();
		//frame.setIconImage(Toolkit.getDefaultToolkit().getImage(BankView.class.getResource("/com/jtattoo/plaf/icons/File.gif")));
		// this.frame.setResizable(false);
		frame.setBounds(100, 100, 575, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(10, 10, 50, 20);
		
		
		//JTabbedPane jp=new JTabbedPane(JTabbedPane.LEFT) 
		tabbedPane.setBackground(new Color(239, 247, 251));
	    tabbedPane.setForeground(new Color(72, 61, 139));
	    tabbedPane.setFont(new Font("YouYuan", 1, 14));
		this.frame.getContentPane().add(tabbedPane);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		infoPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		tabbedPane.addTab("\u5B66\u7C4D\u7BA1\u7406\r\n", new ImageIcon(BankView.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")), infoPanel, null);
		tabbedPane.setDisabledIconAt(0, new ImageIcon(BankView.class.getResource("/com/jtattoo/plaf/icons/CheckSymbol.gif")));
		tabbedPane.setBackgroundAt(0, Color.LIGHT_GRAY);
		infoPanel.setLayout(new CardLayout(0, 0));
		tabbedPane.setForegroundAt(0, new Color(0, 0, 128));
		
		JPanel bankPanel = new JPanel();
		bankPanel.setSize(new Dimension(30, 30));
		bankPanel.setPreferredSize(new Dimension(20, 20));
		bankPanel.setMinimumSize(new Dimension(30, 30));
		bankPanel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		bankPanel.setForeground(new Color(85, 107, 47));
	    bankPanel.setBackground(new Color(239, 247, 251));
		tabbedPane.addTab("ÒøÐÐÏµÍ³", new ImageIcon(BankView.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")), bankPanel, null);
		tabbedPane.setFont(new Font("YouYuan", 1, 14));
		bankPanel.setLayout(new CardLayout(0, 0));
		
//		JLabel label_1 = new JLabel("New label");
//		label_1.setIcon(new ImageIcon(getClass().getResource(
//	      "/images/IMG_1968.JPG")));
//		label_1.setBounds(186, 11, 166, 249);
//		bankPanel.add(label_1);  
		
	    JPanel transPanel=new JPanel();
	    transPanel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
	    transPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
	    FlowLayout flowLayout_3 = (FlowLayout) transPanel.getLayout();
	    JPanel checkPanel=new JPanel();
	    JPanel recordPanel=new JPanel();
//		tabbedPane1.addTab("×ªÕË",null,transPanel,null);
	    JTabbedPane tab1 = new JTabbedPane(JTabbedPane.TOP);
	    bankPanel.add(tab1);
	    tab1.setFont(new Font("YouYuan", 1, 14));
	    tab1.addTab(" ×ªÕË ",new ImageIcon(BankView.class.getResource("/com/jtattoo/plaf/icons/JavaCup.gif")),transPanel,"do nothing!");
	    tab1.setForegroundAt(0, Color.DARK_GRAY);
	    tab1.setBackgroundAt(0, Color.LIGHT_GRAY);
	    tab1.addTab(" Óà¶î²éÑ¯ ",new ImageIcon(BankView.class.getResource("/com/jtattoo/plaf/icons/Home.gif")),checkPanel,"do nothing!");
	    tab1.addTab("×ªÕË¼ÇÂ¼",new ImageIcon(BankView.class.getResource("/com/jtattoo/plaf/acryl/icons/CheckSymbol.gif")),recordPanel,"do nothing!");

	    JLabel label = new JLabel("New label");
	    label.setBackground(Color.LIGHT_GRAY);
	    label.setToolTipText("555");
	    label.setIconTextGap(20);
	    label.setIcon(new ImageIcon(getClass().getResource(
	      "/images/IMG_1968.JPG")));
	    label.setBounds(186, 11, 166, 249);
	    transPanel.add(label);
	    
	    JPanel librPanel = new JPanel();
	    FlowLayout flowLayout_1 = (FlowLayout) librPanel.getLayout();
	    flowLayout_1.setAlignment(FlowLayout.LEFT);
	    tabbedPane.addTab("Í¼Êé¹Ý", new ImageIcon(BankView.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")), librPanel, null);
	    
	    JPanel shopPanel = new JPanel();
	    tabbedPane.addTab("ÉÌµê", new ImageIcon(BankView.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")), shopPanel, null);
	    
	    JPanel classPanel = new JPanel();
	    FlowLayout flowLayout_2 = (FlowLayout) classPanel.getLayout();
	    flowLayout_2.setAlignment(FlowLayout.LEFT);
	    tabbedPane.addTab("\u9009\u8BFE", new ImageIcon(BankView.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")), classPanel, null);
		
	}
}
