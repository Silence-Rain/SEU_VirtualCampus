package seu.vCampus.view.admin;

import com.sun.awt.AWTUtilities;

import common.StudentRollInfo;

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

//import seu.vCampus.bz.IBankImpl;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;

import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import seu.vCampus.bz.IStudentImpl;
import seu.vCampus.util.SocketHelper;

public class AdminStuInfoView {

    //private static IBankImpl ibank;
	private JFrame frame;
	
	private JTable table;
	private SocketHelper sockethelper;
	private JPanel panel;
	public JPanel librPanel;
	public JPanel shopPanel;
	public JPanel orderPanel;
	public JPanel bankPanel;
	public JPanel classPanel;
	private JTable table_StuInfo;
	JScrollPane scrollPane_StuInfo = new JScrollPane();
	JTextField textField_StuId = new JTextField();
	JButton jb_DeleteStu = new JButton("\u5220\u9664\u5B66\u751F");
	JButton jb_AddStu = new JButton("\u6DFB\u52A0\u5B66\u751F");
	JButton jb_Search = new JButton("\u67E5     \u8BE2");
	JButton jb_ModifyStu = new JButton("\u4FEE\u6539\u4FE1\u606F");
	private final JButton jb_Refresh = new JButton("\u5237  \u65B0");
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public AdminStuInfoView(mainAdminView mview,SocketHelper sockethelper) {
		//this.ibank = ibank;
		this.sockethelper = sockethelper;
		
		initialize(mview);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize(mainAdminView mview) {
		
//		JPanel titlepanel = new JPanel();
//		titlepanel.setBounds(100, 100,744, 50);
//		titlepanel.setLayout(null);
//		frame.getContentPane().add(titlepanel, "cell 0 0,growx,aligny bottom");
//		
//		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
//		frame.getContentPane().add(tabbedPane, "cell 0 1,grow");
//		tabbedPane.setFont(new Font("YouYuan", 1, 14));
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		tabbedPane_2.setBounds(0, 0, 700, 360);
		mview.infoPanel.add(tabbedPane_2);
		
		// the information for student part
//				JPanel infoPanel = new JPanel();
//				infoPanel.setBackground(Color.WHITE);
//				infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
//				infoPanel.setAlignmentY(Component.TOP_ALIGNMENT);
//				infoPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
//				tabbedPane.addTab("\u5b66\u7c4d\u7ba1\u7406",
//						null, infoPanel,
//						null);
//				tabbedPane.setBackgroundAt(0, UIManager.getColor("Button.background"));
//				infoPanel.setLayout(new CardLayout(0, 0));

//				JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
//				tabbedPane_1.setFont(new Font("YouYuan", Font.PLAIN, 14));
//				infoPanel.add(tabbedPane_1, null);
		//tabbedPane_2.add(title, component)
				panel = new JPanel();
		tabbedPane_2.addTab("\u4fee\u6539\u4fe1\u606f", null, panel, null);
				panel.setLayout(null);
				JTextPane textPane_IDSearch = new JTextPane();
				textPane_IDSearch.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				textPane_IDSearch.setBackground(UIManager.getColor("Button.background"));
				textPane_IDSearch.setText("\u6309\u5B66\u53F7\u67E5\u627E\uFF1A");
				textPane_IDSearch.setBounds(10, 20, 116, 27);
				panel.add(textPane_IDSearch);
				textField_StuId.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				
				textField_StuId.setBounds(136, 20, 189, 27);
				panel.add(textField_StuId);
				textField_StuId.setColumns(10);
				
				
				jb_Search.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				
				jb_Search.setBounds(367, 20, 123, 27);
				panel.add(jb_Search);
				scrollPane_StuInfo.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				
				
				scrollPane_StuInfo.setBounds(10, 57, 675, 197);
				panel.add(scrollPane_StuInfo);
				
				table_StuInfo = new JTable();
				scrollPane_StuInfo.setColumnHeaderView(table_StuInfo);
				scrollPane_StuInfo.setViewportView(getAllStuTable());
				
				
				jb_ModifyStu.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				jb_ModifyStu.setBounds(431, 276, 132, 27);
				panel.add(jb_ModifyStu);
				
				
				
				jb_AddStu.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				jb_AddStu.setBounds(33, 277, 123, 25);
				panel.add(jb_AddStu);
				
				
				
				jb_DeleteStu.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				jb_DeleteStu.setBounds(222, 276, 123, 25);
				panel.add(jb_DeleteStu);
				
				
			
			

				
				jb_Refresh.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				jb_Refresh.setBounds(592, 278, 93, 23);
				
				panel.add(jb_Refresh);
				
				scrollPane_StuInfo.setViewportView(getAllStuTable());
				AdminEvent();
			
				
				
	}
	
	protected void AdminEvent(){
		//查找学生
		jb_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("����˲�ѯ");
				String stuId = textField_StuId.getText();
				System.out.println(stuId);
				if(stuId.equals("")){
					JOptionPane.showMessageDialog(null, "请输入学生ID！");
					return;
				}
				scrollPane_StuInfo.setViewportView(getStuTableById(stuId));
				
			}
		});
		//�޸�ѧ����Ϣ����
		jb_ModifyStu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("������޸���Ϣ");
				if (AdminStuInfoView.this.table_StuInfo.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "还未选中任何学生！");
					return;
				}
				StudentRollInfo stu = new StudentRollInfo("","","","","","","","","","","","");
				String str = (String) AdminStuInfoView.this.table_StuInfo
						.getValueAt(AdminStuInfoView.this.table_StuInfo
								.getSelectedRow(), 0);
				String strName = (String) AdminStuInfoView.this.table_StuInfo
						.getValueAt(AdminStuInfoView.this.table_StuInfo
								.getSelectedRow(), 1);
				String strAge = (String) AdminStuInfoView.this.table_StuInfo
						.getValueAt(AdminStuInfoView.this.table_StuInfo
								.getSelectedRow(), 2);
				String strGender = (String) AdminStuInfoView.this.table_StuInfo
						.getValueAt(AdminStuInfoView.this.table_StuInfo
								.getSelectedRow(), 3);
				String strBirthdate = (String) AdminStuInfoView.this.table_StuInfo
						.getValueAt(AdminStuInfoView.this.table_StuInfo
								.getSelectedRow(), 4);
				String strPlace = (String) AdminStuInfoView.this.table_StuInfo
						.getValueAt(AdminStuInfoView.this.table_StuInfo
								.getSelectedRow(), 5);
				String strDepartment = (String) AdminStuInfoView.this.table_StuInfo
						.getValueAt(AdminStuInfoView.this.table_StuInfo
								.getSelectedRow(), 6);
				String strDormitory = (String) AdminStuInfoView.this.table_StuInfo
						.getValueAt(AdminStuInfoView.this.table_StuInfo
								.getSelectedRow(), 7);
				
				stu.setId(str);
				stu.setAge(strAge);
				stu.setBirthday(strBirthdate);
				stu.setBirthPlace(strPlace);
				stu.setDepartment(strDepartment);
				stu.setDormitory(strDormitory);
				stu.setEntranceTime("");
				stu.setGender(strGender);
				stu.setMajor("");
				stu.setName(strName);
				stu.setNation("");
				stu.setPhoto("");
				System.out.println(str);
				ModifyStu modifyStu = new ModifyStu(AdminStuInfoView.this.frame,stu,AdminStuInfoView.this.sockethelper);
				scrollPane_StuInfo.setViewportView(getAllStuTable());
			}
		});
		//���ѧ��
		jb_AddStu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddStu  addStu = new AddStu(AdminStuInfoView.this.frame,AdminStuInfoView.this.sockethelper);
				scrollPane_StuInfo.setViewportView(getAllStuTable());
			}
		});
		//删除学生
		jb_DeleteStu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�����ɾ����ť");
				
				if (AdminStuInfoView.this.table_StuInfo.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "还未选中任何学生~");
				}
				StudentRollInfo stu = new StudentRollInfo("","","","","","","","","","","","");
				String str = (String) AdminStuInfoView.this.table_StuInfo
						.getValueAt(AdminStuInfoView.this.table_StuInfo////////////////zyy
								.getSelectedRow(), 0);
				stu.setId(str);
				System.out.println(str);
				boolean flag = new IStudentImpl(AdminStuInfoView.this.sockethelper).DeleteStu(stu);
				if(flag){
					JOptionPane.showMessageDialog(null, "删除成功！");
					scrollPane_StuInfo.setViewportView(getAllStuTable());
					
				}
				else{
					JOptionPane.showMessageDialog(null, "删除失败！");
					
				}
			}
		});
		
		jb_Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane_StuInfo.setViewportView(getAllStuTable());
			}
		});
	}
	
	protected JTable getStuTableById(String sId){
		table_StuInfo = new JTable();
		String[] columns = {"学生ID", "姓名", "年龄", "性别","出生日期", "籍贯", "学院","宿舍" }; // ������������.
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		table_StuInfo.setModel(model); // ���ñ��ģ��.
		StudentRollInfo stu = new StudentRollInfo(sId,"","","","","","","","","","","");
		List<StudentRollInfo> list = new IStudentImpl(AdminStuInfoView.this.sockethelper).EnquiryStuById(stu);
		
		if(list == null){
			JOptionPane.showMessageDialog(null, "û�и���ѧ����");
			return table_StuInfo;
		}
		
		//List<String> list = {"09015336","����ԣ","20","��","19970721","�㽭����","�����","M3B314"};
		
	    StudentRollInfo courseList = list.get(0);
		Object[] rowData = {courseList.getId(),courseList.getName(),courseList.getAge(),courseList.getGender(),courseList.getBirthday(),courseList.getBirthPlace(),courseList.getDepartment(),courseList.getDormitory()};
		model.addRow(rowData);
		
			
		
		/*Object[] rowData = {"09015335","Ҷ��ǿ","20","��","19961017","�㽭����","�����","M3B314"};
		model.addRow(rowData);*/
		return table_StuInfo;
		
		
	}
	
	protected JTable getAllStuTable(){
		table_StuInfo = new JTable();
		String[] columns = { "学生ID", "姓名", "年龄", "性别","出生日期", "籍贯", "学院","宿舍" }; // ������������.
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		table_StuInfo.setModel(model); // ���ñ��ģ��.
		table_StuInfo.setRowHeight(25);
		table_StuInfo.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		List<StudentRollInfo> list = new IStudentImpl(AdminStuInfoView.this.sockethelper).EnquiryAllStu();
		//List<String> list = {"09015336","����ԣ","20","��","19970721","�㽭����","�����","M3B314"};
		for(int i =0;i<list.size();i++){
			StudentRollInfo courseList = list.get(i);
			Object[] rowData = {courseList.getId(),courseList.getName(),courseList.getAge(),courseList.getGender(),courseList.getBirthday(),courseList.getBirthPlace(),courseList.getDepartment(),courseList.getDormitory()};
			model.addRow(rowData);
		}
		/*Object[] rowData = {"09015336","����ԣ","20","��","19970721","�㽭����","�����","M3B314"};
		model.addRow(rowData);*/
		return table_StuInfo;
		
	}
	
	}

