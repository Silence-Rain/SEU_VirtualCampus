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
import seu.vCampus.bz.ISelectCourseImpl;
import seu.vCampus.util.SetTableColor;
import seu.vCampus.util.SocketHelper;

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

import seu.vCampus.util.HandleCourseList;
import common.Bank;
import common.CourseInfo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ClassView {


	private JFrame frame;
	//static Point origin = new Point();
	private JTable table;
	private JTable table3;// 选课界面
	private JTable table4;// 查看课表
	private SocketHelper sockethelper = new SocketHelper();
	private JTable table_5;
	private JTable table_selcourse;
	private JTable table_curriculum;
	private JTable table_credit;
	private String StudentId;
	
	JScrollPane scrollPane_credit = new JScrollPane();
	JScrollPane scrollPane_curriculum = new JScrollPane();
	JButton button_checkoutCurriculurm = new JButton("");
	
	JButton button_select = new JButton("");
	JButton button_cancel = new JButton("");

	public ClassView(mainView mview,SocketHelper sockethelper,String StudentId) {
		// this.ibank = ibank;
		this.sockethelper = sockethelper;
		this.StudentId = StudentId;
		System.out.println("studentid is "+ StudentId);
		initialize(mview);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param mview
	 */

	private void initialize(mainView mview) {
		// choose
		// class!!!-----------------------------------------------------------------------
		// mainView mview = new mainView();

		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		tabbedPane_2.setBounds(0, 0,785, 490);
		mview.classPanel.add(tabbedPane_2);

		Object[][] cellData = { { "row1-col1", "row1-col2" }, { "row2-col1", "row2-col2" } };
		String[] columnNames = { "col1", "col2" };

		JPanel classPanel = new JPanel();
		classPanel.setFont(new Font("幼圆", Font.PLAIN, 14));

		classPanel.setLayout(null);

		JPanel panel_curriculum = new JPanel();
		tabbedPane_2.addTab("课表查看", new ImageIcon(ClassView.class.getResource("/images/bank/checktable.png")), panel_curriculum, null);
		panel_curriculum.setLayout(null);

		// JScrollPane scrollPane_curriculum = new JScrollPane();
		scrollPane_curriculum.setBounds(141, 29, 497, 279);
		panel_curriculum.add(scrollPane_curriculum);
		// scrollPane_curriculum.setViewportView(table_curriculum);
		// scrollPane_curriculum.setViewportView(getCurriculumTable());

		// JScrollPane scrollPane_credit = new JScrollPane();
		scrollPane_credit.setFont(new Font("幼圆", Font.PLAIN, 14));
		scrollPane_credit.setBounds(10, 29, 121, 218);
		// scrollPane_credit.setViewportView(getTable());
		panel_curriculum.add(scrollPane_credit);
		button_checkoutCurriculurm.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		ImageIcon icon_btn1  = new ImageIcon(getClass().getResource("/images/btn/dianjichakan.png"));
		button_checkoutCurriculurm.setIcon(icon_btn1);
		button_checkoutCurriculurm.setContentAreaFilled(false);
		button_checkoutCurriculurm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println("31");
			}
		});

		button_checkoutCurriculurm.setBounds(36, 275, 66, 33);
		panel_curriculum.add(button_checkoutCurriculurm);

		JPanel panel_selectCourse = new JPanel();
		panel_selectCourse.setFont(new Font("幼圆", Font.PLAIN, 14));
		tabbedPane_2.addTab("选  课", new ImageIcon(ClassView.class.getResource("/images/bank/choose.png")), panel_selectCourse, null);
		panel_selectCourse.setLayout(null);
		button_select.setIcon(new ImageIcon(ClassView.class.getResource("/images/btn/xuanze.png")));

		button_select.setFont(new Font("幼圆", Font.PLAIN, 14));
		button_select.setBounds(126, 299, 93, 36);
		panel_selectCourse.add(button_select);
		button_cancel.setIcon(new ImageIcon(ClassView.class.getResource("/images/btn/tuixuan.png")));

		button_cancel.setFont(new Font("幼圆", Font.PLAIN, 14));
		button_cancel.setBounds(340, 299, 93, 36);
		panel_selectCourse.add(button_cancel);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(42, 24, 625, 252);
		panel_selectCourse.add(scrollPane_1);

		table_selcourse = new JTable();
		// scrollPane_1.setViewportView(table_selcourse);
		scrollPane_1.setViewportView(getSelectCourseTable());
		table_selcourse.setGridColor(SystemColor.inactiveCaption);
		table_selcourse.setBackground(UIManager.getColor("Button.background"));
		table_selcourse.setModel(new DefaultTableModel(
				new Object[][] { { "", "", "", "", "", "" }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, },
				new String[] { "\u8BFE\u7A0BID", "\u8BFE\u7A0B\u540D\u79F0", "\u6388\u8BFE\u6559\u5E08",
						"\u4E0A\u8BFE\u65F6\u95F4", "\u8BFE\u7A0B\u5B66\u5206", "\u8BFE\u7A0B\u72B6\u6001" }));
		table_selcourse.setFont(new Font("幼圆", Font.PLAIN, 14));

		table_curriculum = new JTable();
		table_curriculum.setModel(new DefaultTableModel(
				new Object[][] { { "上午1-2", null, null, null, null, null, null, null },
						{ "上午3-4", null, null, null, null, null, null, null },
						{ "下午1-2", null, null, null, null, null, null, null },
						{ "下午3-4", null, null, null, null, null, null, null },
						{ "晚上1-2", null, null, null, null, null, null, null },
						{ "晚上3-4", null, null, null, null, null, null, null }, },
				new String[] { "时间", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日" }));
		table_curriculum.setRowHeight(42);

		table_credit = new JTable();
		table_credit.setFont(new Font("幼圆", Font.PLAIN, 9));
		table_credit.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, },
				new String[] { "\u5E8F\u53F7", "\u8BFE\u7A0B\u540D\u79F0", "\u5B66\u5206" }));
		table_credit.getColumnModel().getColumn(0).setPreferredWidth(45);
		table_credit.getColumnModel().getColumn(1).setPreferredWidth(83);
		table_credit.getColumnModel().getColumn(2).setPreferredWidth(45);
		// scrollPane_credit.setViewportView(table_credit);
		table_credit.setRowHeight(25);

//		tabbedPane.setBackgroundAt(0, UIManager.getColor("Button.background"));
//		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
//		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
//				GroupLayout.PREFERRED_SIZE, 737, GroupLayout.PREFERRED_SIZE));
//		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
//				GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE));
//		frame.getContentPane().setLayout(groupLayout);
		stuEvent();
	}

	// 学生按钮相应事件
	private void stuEvent() {
		// 选择课程的按钮相应函数
		button_select.setContentAreaFilled(false);
		button_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("10");
				if (ClassView.this.table3.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "未选中任何课程");
					return;
				}
				String str = (String) ClassView.this.table3
						.getValueAt(ClassView.this.table3.getSelectedRow(), 0);
				System.out.println(str);
				String status = (String) ClassView.this.table3
						.getValueAt(ClassView.this.table3.getSelectedRow(), 6);
				if (status.equals("已选")) {
					JOptionPane.showMessageDialog(null, "你已经选择了该课程！");
					return;
				}
				System.out.println("11");
				boolean flag = new ISelectCourseImpl(ClassView.this.sockethelper).selectCourse(str);
				if (flag) {
					JOptionPane.showMessageDialog(null, "已成功选择该课程");
					ClassView.this.table3.setValueAt("已选", ClassView.this.table3.getSelectedRow(), 6);
					// StuSelCourseView.this.button_select.setEnabled(false);
					// StuSelCourseView.this.button_cancel.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "选择课程失败");
				}
			}
		}

		);

		// 退选课程的按钮相应函数
		button_cancel.setContentAreaFilled(false);
		button_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("20");
				if (ClassView.this.table3.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "未选中任何课程");
					return;
				}
				String str = (String) ClassView.this.table3
						.getValueAt(ClassView.this.table3.getSelectedRow(), 0);
				System.out.println("21");
				String status = (String) ClassView.this.table3
						.getValueAt(ClassView.this.table3.getSelectedRow(), 6);
				if (status.equals("未选")) {
					JOptionPane.showMessageDialog(null, "你还没有选择这个课程！");
					return;
				}
				boolean flag = new ISelectCourseImpl(ClassView.this.sockethelper).cancelCourse(str);
				if (flag) {
					JOptionPane.showMessageDialog(null, "已成功退选该课程");

					ClassView.this.table3.setValueAt("未选", ClassView.this.table3.getSelectedRow(), 6);

					// StuSelCourseView.this.button_cancel.setEnabled(false);
					// StuSelCourseView.this.button_select.setEnabled(true);

				} else {
					JOptionPane.showMessageDialog(null, "退选失败");

				}
			}
		});

		// 查看课表 按钮相应函数

		button_checkoutCurriculurm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ISelectCourseImpl isc = new
				 * ISelectCourseImpl(StudentView.this.sockethelper); List list =
				 * new ISelectCourseImpl(StudentView.this.sockethelper).
				 * EnquirySelectCourse();
				 * 
				 * List myCourse = new ArrayList(); for(int i =
				 * 0;i<list.size();i++){ String courseID = (String)
				 * ((List)list.get(i)).get(1); List tmp = (List)new
				 * ISelectCourseImpl
				 * (StudentView.this.sockethelper).getStuCourse(
				 * courseID).get(0); myCourse.add(tmp); } Object obj = new
				 * VectorCreate().create(myCourse); DefaultTableModel dtm = new
				 * DefaultTableModel((Object[][])obj,null);
				 */
				// table_curriculum.add(dtm);
				// table_curriculum.addRow()

				getCurriculumTable();

			}
		});
	}

	private JTable getSelectCourseTable() {
		// EnquiryAllCourse(),返回的是List ，601
		// 检查表格是否为空.
		table3 = new JTable(); // 创建表格控件.
		table3.setRowHeight(25); // 设置行高.
		table3.setShowGrid(false);// 设置表格无边框
		table3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));// 设置表内字体
		table3.getTableHeader().setFont(new Font("Microsoft YaHei UI", 0, 14));// 设置表头字体
		String[] columns = { "课程ID", "课程名称", "授课教师", "上课地点", "上课时间", "课程学分", "课程状态" }; // 创建列名数组.
		// 创建表格模型.
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		table3.setModel(model); // 设置表格模型.
		TableColumn forthColumn = table3.getColumnModel().getColumn(4);
		forthColumn.setPreferredWidth(120);
		forthColumn.setMaxWidth(120);
		forthColumn.setMinWidth(120);
		List<CourseInfo> courselist = new ISelectCourseImpl(ClassView.this.sockethelper).EnquirySelectCourse(StudentId);

		List<CourseInfo> list = new ISelectCourseImpl(ClassView.this.sockethelper).EnquiryAllCourse();

		for (int i = 0; i < list.size(); i++) {
			CourseInfo courseList = list.get(i);
			boolean flag = false;

			for (int j = 0; j < courselist.size(); j++) {
				CourseInfo tmpList = courselist.get(j);
				if (tmpList.getId().equals(courseList.getId()))
					flag = true;

			}
			if (flag) {
				Object[] rowData = { courseList.getId(), courseList.getName(), courseList.getTeacher(),
						courseList.getPlace(), courseList.getTime(), courseList.getCredit(), "已选" };
				model.addRow(rowData);
			} else {
				Object[] rowData = { courseList.getId(), courseList.getName(), courseList.getTeacher(),
						courseList.getPlace(), courseList.getTime(), courseList.getCredit(), "未选" };
				model.addRow(rowData);
			}

		}

		// List<String> Course = getSelectCourse(); // 利用方法传递list集合对象.
		// List<List<String>> Course =new ArrayList();
		// for ( : Course) { // 遍历学生集合对象.
		// String[] args = course.split(","); // 把学生信息拆分为数组,传递给args数组.
		// model.addRow(course);
		// }
		// table3.setEnabled(false);
		SetTableColor.makeFace(table3);
		return table3;
	}

	private void getCurriculumTable() {
		// 检查表格是否为空.
		table4 = new JTable(); // 创建表格控件.
		// table4.setRowHeight(40); // 设置行高.
		String[] columns = { "时间", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日" }; // 创建列名数组.
		// 创建表格模型.
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		table4.setModel(model); // 设置表格模型.
		table4.setRowHeight(50);
		table4.setFont(new Font("Dialog", 0, 9));
		/*
		 * TableColumn secColumn = table.getColumnModel().getColumn(1);
		 * secColumn.setPreferredWidth(60); secColumn.setMaxWidth(60);
		 * secColumn.setMinWidth(60);
		 */
		// List<String> Curriculum = getCurriculum(); // 利用方法传递list集合对象.
		// EnquirySelectCourse
		/*
		 * for (String curriculum : Curriculum) { // 遍历学生集合对象. String[] args =
		 * curriculum.split(","); // 把学生信息拆分为数组,传递给args数组. model.addRow(args);
		 */
		table = new JTable(); // 创建表格控件.
		table.setRowHeight(25); // 设置行高.
		String[] columns2 = { "ID", "课程", "学分" }; // 创建列名数组.
		// 创建表格模型.
		table.getTableHeader().setFont(new Font("Microsoft YaHei UI", 0, 11));//设置表头字体
		DefaultTableModel model2 = new DefaultTableModel(columns2, 0);
		table.setModel(model2); // 设置表格模型.
		table.setEnabled(false);
		// 设置第二列的列宽
		TableColumn secColumn = table.getColumnModel().getColumn(1);
		secColumn.setPreferredWidth(50);
		secColumn.setMaxWidth(50);
		secColumn.setMinWidth(50);

		List<CourseInfo> courselist = new ISelectCourseImpl(ClassView.this.sockethelper).EnquirySelectCourse(StudentId);

		for (int i = 0; i < courselist.size(); i++) {
			CourseInfo courseList = courselist.get(i);
			Object[] rowData = { courseList.getId(), courseList.getName(), courseList.getCredit() };
			model2.addRow(rowData);
		}
		SetTableColor.makeFace(table);
		scrollPane_credit.setViewportView(table);

		HandleCourseList handle = new HandleCourseList(courselist);

		List<List<String>> tmpList = handle.getHandledCourseList();
		for (int i = 0; i < tmpList.size(); i++) {
			List<String> courseList = tmpList.get(i);

			if (i == 0) {
				Object[] rowData = { "上午12", courseList.get(0), courseList.get(1), courseList.get(2), courseList.get(3),
						courseList.get(4), courseList.get(5) };
				model.addRow(rowData);
			}
			if (i == 1) {
				Object[] rowData = { "上午34", courseList.get(0), courseList.get(1), courseList.get(2), courseList.get(3),
						courseList.get(4), courseList.get(5) };
				model.addRow(rowData);
			}
			if (i == 2) {
				Object[] rowData = { "下午12", courseList.get(0), courseList.get(1), courseList.get(2), courseList.get(3),
						courseList.get(4), courseList.get(5) };
				model.addRow(rowData);
			}
			if (i == 3) {
				Object[] rowData = { "下午34", courseList.get(0), courseList.get(1), courseList.get(2), courseList.get(3),
						courseList.get(4), courseList.get(5) };
				model.addRow(rowData);
			}
			if (i == 4) {
				Object[] rowData = { "晚上12", courseList.get(0), courseList.get(1), courseList.get(2), courseList.get(3),
						courseList.get(4), courseList.get(5) };
				model.addRow(rowData);
			}

		}

		table4.setEnabled(false);
		SetTableColor.makeFace(table4);
		scrollPane_curriculum.setViewportView(table4);

	}

}
