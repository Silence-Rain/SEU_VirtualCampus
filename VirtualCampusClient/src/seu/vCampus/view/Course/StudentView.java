package seu.vCampus.view.Course;


import seu.vCampus.common.Course;
import seu.vCampus.common.MsgType;
import seu.vCampus.main.main;

import java.awt.*;

import javax.naming.InitialContext;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.INITIALIZE;


public class StudentView extends JFrame implements MsgType{
	private JFrame frame;
	
	//public static void main(String [] args){
	//	StudentView student = new StudentView();
	//	System.out.println("StudentView OK");
	//}
	public StudentView()
	{
		initialize();
	}
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 575, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(10, 10, 50, 20);
		
		
		JTabbedPane jp=new JTabbedPane(JTabbedPane.LEFT) ;
		tabbedPane.setBackground(new Color(239, 247, 251));
	    tabbedPane.setForeground(new Color(72, 61, 139));
	    tabbedPane.setFont(new Font("YouYuan", 1, 14));
		this.frame.getContentPane().add(tabbedPane);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		infoPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		tabbedPane.addTab("\u5B66\u7C4D\u7BA1\u7406\r\n", new ImageIcon(StudentView.class.getResource("")), infoPanel, null);
		tabbedPane.setDisabledIconAt(0, new ImageIcon(StudentView.class.getResource("")));
		tabbedPane.setBackgroundAt(0, Color.PINK);
		infoPanel.setLayout(new CardLayout(0, 0));
		tabbedPane.setForegroundAt(0, new Color(0, 0, 128));
		
		JPanel bankPanel = new JPanel();
		bankPanel.setSize(new Dimension(30, 30));
		bankPanel.setPreferredSize(new Dimension(20, 20));
		bankPanel.setMinimumSize(new Dimension(30, 30));
		bankPanel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		bankPanel.setForeground(new Color(85, 107, 47));
	    bankPanel.setBackground(new Color(239, 247, 251));
		tabbedPane.addTab("银行系统", new ImageIcon(StudentView.class.getResource("")), bankPanel, null);
		tabbedPane.setFont(new Font("YouYuan", 1, 14));
		bankPanel.setLayout(new CardLayout(0, 0));
		
//		JLabel label_1 = new JLabel("New label");
//		label_1.setIcon(new ImageIcon(getClass().getResource(
//	      "/images/IMG_1968.JPG")));
//		label_1.setBounds(186, 11, 166, 249);
//		bankPanel.add(label_1);  
		
	    JPanel transPanel=new JPanel();
	    transPanel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
	    transPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
	    FlowLayout flowLayout_3 = (FlowLayout) transPanel.getLayout();
	    JPanel checkPanel=new JPanel();
	    JPanel recordPanel=new JPanel();
     	//tabbedPane1.addTab("转账",null,transPanel,null);
	    JTabbedPane tab1 = new JTabbedPane(JTabbedPane.TOP);
	    bankPanel.add(tab1);
	    tab1.setFont(new Font("YouYuan", 1, 14));
	    tab1.addTab(" 转账 ",new ImageIcon(StudentView.class.getResource("")),transPanel,"do nothing!");
	    tab1.setForegroundAt(0, Color.DARK_GRAY);
	    tab1.setBackgroundAt(0, Color.LIGHT_GRAY);
	    tab1.addTab(" 余额查询 ",new ImageIcon(StudentView.class.getResource("")),checkPanel,"do nothing!");
	   tab1.addTab("转账记录",new ImageIcon(StudentView.class.getResource("")),recordPanel,"do nothing!");
	    
	    //选课
	    JPanel coursePanel = new JPanel();
	    coursePanel.setSize(new Dimension(30, 30));
	    coursePanel.setPreferredSize(new Dimension(20, 20));
	    coursePanel.setMinimumSize(new Dimension(30, 30));
	    coursePanel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
	    coursePanel.setForeground(new Color(85, 107, 47));
	    coursePanel.setBackground(new Color(239, 247, 251));
		tabbedPane.addTab("银行系统", new ImageIcon(StudentView.class.getResource("")), coursePanel, null);
		tabbedPane.setFont(new Font("YouYuan", 1, 14));
		coursePanel.setLayout(new CardLayout(0, 0));
	 //   FlowLayout fl_coursePanel = (FlowLayout) coursePanel.getLayout();
	  //  fl_coursePanel.setAlignment(FlowLayout.LEFT);
	    JPanel selectPanel = new JPanel();
	    JPanel gradePanel = new JPanel();
	    JPanel coursetimePanel = new JPanel();
	    JTabbedPane courseTab = new JTabbedPane(JTabbedPane.TOP);
	    coursePanel.add(courseTab);
	    courseTab.setFont(new Font("YouYuan", 1, 14));
	    courseTab.addTab(" 选课 ",new ImageIcon(StudentView.class.getResource("")),selectPanel,"do nothing!");
	    
	    List list = new List();
	    selectPanel.add(list);
	    courseTab.setForegroundAt(0, Color.DARK_GRAY);
	    courseTab.setBackgroundAt(0, Color.LIGHT_GRAY);
	    courseTab.addTab(" 成绩查询 ",new ImageIcon(StudentView.class.getResource("")),gradePanel,"do nothing!");
	    courseTab.addTab(" 课表查询 ",new ImageIcon(StudentView.class.getResource("")),coursetimePanel,"do nothing!");
	   
	    
	    tabbedPane.addTab("\u9009\u8BFE\u7CFB\u7EDF", new ImageIcon(StudentView.class.getResource("")), coursePanel, null);
		
	
	}
	
}

