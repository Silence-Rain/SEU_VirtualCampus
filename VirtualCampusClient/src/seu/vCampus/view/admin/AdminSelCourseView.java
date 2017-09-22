package seu.vCampus.view.admin;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.Dimension;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.Rectangle;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.sun.awt.AWTUtilities;
import common.CourseInfo;
import seu.vCampus.bz.ISelectCourseImpl;
import seu.vCampus.util.SocketHelper;



public class AdminSelCourseView {

	private JFrame frame;
	//static Point origin = new Point();
	private JTable table;
	private SocketHelper sockethelper = new SocketHelper();
	private JTable table_5;
	private JTable table_selcourse = new JTable();
	JScrollPane scrollPane_1 = new JScrollPane();
	JButton button_add = new JButton("\u6DFB\u52A0\u8BFE\u7A0B");
	JButton button_delete = new JButton("\u5220\u9664\u8BFE\u7A0B");
	JButton button_modify = new JButton("\u4fee\u6539\u8bfe\u7a0b");
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public AdminSelCourseView(mainAdminView mview,SocketHelper sockethelper) {
		this.sockethelper = sockethelper;
		initialize(mview);
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize(mainAdminView mview) {
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		tabbedPane_2.setBounds(0, 0, 700, 360);
		mview.classPanel.add(tabbedPane_2);
		
//		JFrame.setDefaultLookAndFeelDecorated(true);  
//		frame = new JFrame();
//		// frame.setIconImage(Toolkit.getDefaultToolkit().getImage(BankView.class.getResource("/com/jtattoo/plaf/icons/File.gif")));
//		// this.frame.setResizable(false);
//		frame.setBounds(330, 170, 746, 394);
//		frame.setUndecorated(true);
		/** ����Բ�� */  
//        AWTUtilities.setWindowShape(frame, new RoundRectangle2D.Double(  
//            0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D,  
//            26.0D));  
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		// JTabbedPane jp=new JTabbedPane(JTabbedPane.LEFT)
//		tabbedPane.setBackground(new Color(239, 247, 251));
//		tabbedPane.setForeground(new Color(72, 61, 139));
//		tabbedPane.setFont(new Font("YouYuan", 1, 14));
//				//tablebuy.setLocation(55, 138);
//				//tablebuy.setSize(100, 100);
//				Object[][] cellData = {{"row1-col1", "row1-col2"},{"row2-col1", "row2-col2"}};
//				String[] columnNames = {"col1", "col2"};
//		tabbedPane.setFont(new Font("YouYuan", 1, 14));

//		JPanel classPanel = new JPanel();
//		classPanel.setFont(new Font("��Բ", Font.PLAIN, 14));
//		tabbedPane.addTab("\u9009    \u8BFE",
//				new ImageIcon(AdminSelCourseView.class.getResource("")),
//				classPanel, null);
//		classPanel.setLayout(null);
//		
//		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
//		tabbedPane_2.setFont(new Font("��Բ", Font.PLAIN, 14));
//		tabbedPane_2.setBounds(0, 0, 650, 350);
//		classPanel.add(tabbedPane_2);
		
		JPanel panel_selectCourse = new JPanel();
		panel_selectCourse.setFont(new Font("��Բ", Font.PLAIN, 14));
		tabbedPane_2.addTab("课程管理", new ImageIcon(AdminSelCourseView.class.getResource("")), panel_selectCourse, null);
		panel_selectCourse.setLayout(null);
		
		
		
		
		button_add.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_add.setBounds(36, 245, 103, 37);
		panel_selectCourse.add(button_add);
		
		
		
		
		
		button_delete.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_delete.setBounds(365, 245, 103, 37);
		panel_selectCourse.add(button_delete);
		
		//JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(34, 39, 625, 177);
		panel_selectCourse.add(scrollPane_1);
		
		
		//scrollPane_1.setViewportView(table_selcourse);
		scrollPane_1.setViewportView(getSelectCourseTable());
		table_selcourse.setGridColor(SystemColor.inactiveCaption);
		table_selcourse.setBackground(UIManager.getColor("Button.background"));
		
		table_selcourse.setFont(new Font("��Բ", Font.PLAIN, 14));
		
		
		button_modify.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_modify.setBounds(185, 245, 103, 36);
		panel_selectCourse.add(button_modify);
		
		JButton jb_Refresh = new JButton("\u5237\u65B0\u8BFE\u8868");
		jb_Refresh.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		jb_Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane_1.setViewportView(getSelectCourseTable());
			}
		});
		jb_Refresh.setBounds(512, 245, 93, 37);
		panel_selectCourse.add(jb_Refresh);
		
		
		
		
		
		   
		
//		tabbedPane.setBackgroundAt(0, UIManager.getColor("Button.background"));
//		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
//		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
//				GroupLayout.PREFERRED_SIZE, 737, GroupLayout.PREFERRED_SIZE));
//		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
//				GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE));
//		frame.getContentPane().setLayout(groupLayout);
     	AdminEvent();
	}

	//����Ա��ť��Ӧ�¼�
	private void AdminEvent(){
		//��ӿγ̵İ�ť��Ӧ����
		button_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("10");
				AddCourse addCourse = new AddCourse(AdminSelCourseView.this.frame,AdminSelCourseView.this.sockethelper);	
				System.out.println("11");
				
				scrollPane_1.setViewportView(getSelectCourseTable());
				System.out.println("12");
				}
			}
		);
		//�޸Ŀγ̵İ�ť��Ӧ����
		button_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("������޸İ�ť");
				System.out.println(AdminSelCourseView.this.table_selcourse.getSelectedRow());
				if (AdminSelCourseView.this.table_selcourse.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "还未选中课程！");
					return;
				}
                CourseInfo course = new CourseInfo("","","","","",0);
				
				String sId = (String)AdminSelCourseView.this.table_selcourse.getValueAt(AdminSelCourseView.this.table_selcourse.getSelectedRow(), 0);
				String sName = (String)AdminSelCourseView.this.table_selcourse.getValueAt(AdminSelCourseView.this.table_selcourse.getSelectedRow(), 1);
				String sTeacher = (String)AdminSelCourseView.this.table_selcourse.getValueAt(AdminSelCourseView.this.table_selcourse.getSelectedRow(), 2);
				String sPlace = (String)AdminSelCourseView.this.table_selcourse.getValueAt(AdminSelCourseView.this.table_selcourse.getSelectedRow(), 3);
				String sTime= (String)AdminSelCourseView.this.table_selcourse.getValueAt(AdminSelCourseView.this.table_selcourse.getSelectedRow(), 4);
				double sCredit = (double)AdminSelCourseView.this.table_selcourse.getValueAt(AdminSelCourseView.this.table_selcourse.getSelectedRow(),5);
	
				course.setId(sId);
				course.setName(sName);
				course.setTeacher(sTeacher);
				course.setPlace(sPlace);
				course.setTime(sTime);
				course.setCredit(Double.valueOf(sCredit));
				
				ModifyCourse modifyCourse = new ModifyCourse(AdminSelCourseView.this.frame,course,AdminSelCourseView.this.sockethelper);
				
				System.out.println(21);
				scrollPane_1.setViewportView(getSelectCourseTable());
				
			}
			
		});
		//ɾ���γ�
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("20");
				System.out.println("21");
				if (AdminSelCourseView.this.table_selcourse.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "还未选中课程！");
				}
				
				//����һ��CourseInfo����ȥ
				CourseInfo course = new CourseInfo("","","","","",0);
				
				String sId = (String)AdminSelCourseView.this.table_selcourse.getValueAt(AdminSelCourseView.this.table_selcourse.getSelectedRow(), 0);
				String sName = (String)AdminSelCourseView.this.table_selcourse.getValueAt(AdminSelCourseView.this.table_selcourse.getSelectedRow(), 1);
				String sTeacher = (String)AdminSelCourseView.this.table_selcourse.getValueAt(AdminSelCourseView.this.table_selcourse.getSelectedRow(), 2);
				String sPlace = (String)AdminSelCourseView.this.table_selcourse.getValueAt(AdminSelCourseView.this.table_selcourse.getSelectedRow(), 3);
				String sTime= (String)AdminSelCourseView.this.table_selcourse.getValueAt(AdminSelCourseView.this.table_selcourse.getSelectedRow(), 4);
				double sCredit = (double)AdminSelCourseView.this.table_selcourse.getValueAt(AdminSelCourseView.this.table_selcourse.getSelectedRow(),5);
	
				course.setId(sId);
				course.setName(sName);
				course.setTeacher(sTeacher);
				course.setPlace(sPlace);
				course.setTime(sTime);
				course.setCredit(Double.valueOf(sCredit));
				boolean flag = new ISelectCourseImpl(AdminSelCourseView.this.sockethelper).deleteCourse(course);
				if(flag){
					JOptionPane.showMessageDialog(null, "删除课程成功！");
					scrollPane_1.setViewportView(getSelectCourseTable());
					
				}
				else{
					JOptionPane.showMessageDialog(null, "删除课程失败！");
					
				}
			}
		});
	}
	private JTable getSelectCourseTable() {
		//EnquiryAllCourse(),���ص���List ��601
		 // ������Ƿ�Ϊ��.
		   // table_selcourse = new JTable(); // �������ؼ�.
		    table_selcourse.setRowHeight(25); // �����и�.
			String[] columns = { "课程ID", "课程名称", "授课老师", "上课地点","上课时间", "课程学分"}; // ������������.
			// �������ģ��.
			DefaultTableModel model = new DefaultTableModel(columns, 0);
			table_selcourse.setModel(model); // ���ñ��ģ��.
			
			List<CourseInfo> list = new ISelectCourseImpl(AdminSelCourseView.this.sockethelper).EnquiryAllCourse();
			for(int i =0;i<list.size();i++){
				CourseInfo courseList = list.get(i);
				Object[] rowData = {courseList.getId(),courseList.getName(),courseList.getTeacher(),courseList.getPlace(),courseList.getTime(),courseList.getCredit()};
				model.addRow(rowData);
			}
			return table_selcourse;
	}
}