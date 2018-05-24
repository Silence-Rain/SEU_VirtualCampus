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
import seu.vCampus.bz.IGymImpl;
import seu.vCampus.util.SetTableColor;
import seu.vCampus.util.SocketHelper;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import common.AppointInfo;
import common.AppointStatusInfo;
import common.Bank;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class OrderView {
	private JFrame frame;
	private String t;
	private JTable table;
	// private JPanel orderpane;
	private SocketHelper sockethelper ;

	private JTable table_record;
	private JTable table_tomorrow;
	private JTable table_today = new JTable();
	private JTable table_after;

	private JComboBox comboBox_type;
	String id = mainView.getStudentId();
	private JScrollPane scrollPane_today;
	private JScrollPane scrollPane_tomorrow;
	private JScrollPane scrollPane_after;
	private JScrollPane scrollPane_record;
	JTabbedPane tabbedPane_Play = new JTabbedPane(JTabbedPane.TOP);
	JButton jb_SearchRecord = new JButton("");
	JButton button_check = new JButton("");
	JButton button_appoint = new JButton("");

	public OrderView(mainView mview,SocketHelper sockethelper) {
		this.sockethelper = sockethelper;
		initialize(mview);
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize(mainView mview) {
		
		JTabbedPane tabbedPane_4 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		tabbedPane_4.setBounds(0, 0, 785, 490);
		mview.orderPanel.add(tabbedPane_4);
		
		JPanel panel_1 = new JPanel();
		tabbedPane_4.addTab("场馆预约", new ImageIcon(OrderView.class.getResource("/images/bank/changguanyuyue.png")), panel_1, null);
		panel_1.setLayout(null);

		JTextPane textPane_Item = new JTextPane();
		textPane_Item.setEditable(false);
		textPane_Item.setBackground(UIManager.getColor("Button.background"));
		textPane_Item.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_Item.setText("预约项目：");
		textPane_Item.setBounds(78, 49, 97, 21);
		panel_1.add(textPane_Item);

		comboBox_type = new JComboBox();
		comboBox_type.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		comboBox_type.setModel(new DefaultComboBoxModel(new String[] { "羽毛球",
				"篮球", "乒乓球", "健身房" }));
		comboBox_type.setBounds(185, 49, 75, 21);
		panel_1.add(comboBox_type);

		
		tabbedPane_Play.setFont(new Font("幼圆", Font.PLAIN, 13));
		tabbedPane_Play.setBounds(78, 99, 505, 309);
		panel_1.add(tabbedPane_Play);

		JPanel panel_Today = new JPanel();
		tabbedPane_Play.addTab("--今天--", null, panel_Today, null);
		panel_Today.setLayout(null);

		scrollPane_today = new JScrollPane();
		scrollPane_today.setBounds(0, 0, 500, 280);
		panel_Today.add(scrollPane_today);

		
		
		table_today.setRowMargin(5);
		table_today.setRowHeight(27);

		JPanel panel_Tomorrow = new JPanel();
		tabbedPane_Play.addTab("--明天--", null, panel_Tomorrow, null);
		panel_Tomorrow.setLayout(null);

		scrollPane_tomorrow = new JScrollPane();
		scrollPane_tomorrow.setBounds(0, 0, 500, 280);
		panel_Tomorrow.add(scrollPane_tomorrow);

		JPanel panel_After = new JPanel();
		tabbedPane_Play.addTab("--后天--", null, panel_After, null);
		panel_After.setLayout(null);

		scrollPane_after = new JScrollPane();
		scrollPane_after.setBounds(0, 0, 500, 280);
		panel_After.add(scrollPane_after);
		button_check.setIcon(new ImageIcon(OrderView.class.getResource("/images/btn/check.png")));

		
		
		button_check.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_check.setBounds(341, 37, 93, 40);
		panel_1.add(button_check);
			button_appoint.setIcon(new ImageIcon(OrderView.class.getResource("/images/btn/yuyue.png")));
			button_appoint.setBounds(444, 37, 93, 40);
			panel_1.add(button_appoint);
		
			
			//table_today.getColumnModel().getColumn(0).setResizable(false);
			//table_today.getColumnModel().getColumn(1).setResizable(false);
			//scrollPane_today.setViewportView(table_today);

			
			
			button_appoint.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		JPanel panel_8 = new JPanel();
		tabbedPane_4.addTab("预约记录", new ImageIcon(OrderView.class.getResource("/images/bank/yuyuejilu.png")), panel_8, null);
		panel_8.setLayout(null);

		scrollPane_record = new JScrollPane();
		scrollPane_record.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		scrollPane_record.setBounds(94, 69, 493, 300);
		panel_8.add(scrollPane_record);

		
		//table_record.setRowHeight(25);
		/*table_record.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null }, { null, null, null },
				{ null, null, null }, { null, null, null },
				{ null, null, null }, { null, null, null },
				{ null, null, null }, { null, null, null },
				{ null, null, null }, { null, null, null },
				{ null, null, null }, }, new String[] { "\u65F6\u95F4",
				"\u9884\u7EA6\u9879\u76EE", "\u9884\u7EA6\u4EBA" }));*/
		jb_SearchRecord.setIcon(new ImageIcon(OrderView.class.getResource("/images/btn/check.png")));
		

		
		
		jb_SearchRecord.setFont(new Font("幼圆", Font.PLAIN, 14));
		jb_SearchRecord.setBounds(83, 10, 93, 49);
		panel_8.add(jb_SearchRecord);
		
		StuEvent();
	}

	private JTable getCheckTableToday(String type) {
		table = new JTable(); // 创建表格控件.
		table.setRowHeight(25); // 设置行高.
		String[] columns = { "时间段", "剩余场次", "预约" }; // 创建列名数组.
		// 创建表格模型.
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		table.setModel(model); // 设置表格模型.
		// list_today = new
		// IGymImpl(StudentGymView.this.sockethelper).search_today(type);
		/*for (int i = 0; i < list_today.size(); i++) {
			List<String> tList = list_today.get(i);
			Object[] rowData = { tList.get(1), tList.get(2), false };// socket返回值
			model.addRow(rowData);
		}*/
		table.setEnabled(false);
		SetTableColor.makeFace(table);
		return table;
	}

	private JTable getCheckTableTomorrow(String type) {
		table = new JTable(); // 创建表格控件.
		table.setRowHeight(25); // 设置行高.
		String[] columns = { "时间段", "剩余场次", "预约" }; // 创建列名数组.
		// 创建表格模型.
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		table.setModel(model); // 设置表格模型.
		// list_tomo = new
		// IGymImpl(OrderView.this.sockethelper).search_tomo(type);
		/*for (int i = 0; i < list_tomo.size(); i++) {
			List<String> tList = list_tomo.get(i);
			Object[] rowData = { tList.get(1), tList.get(3), false };// socket返回值
			model.addRow(rowData);
		}*/
		table.setEnabled(false);
		SetTableColor.makeFace(table);
		return table;
	}

	private JTable getCheckTableAfter(String type) {
		table = new JTable(); // 创建表格控件.
		table.setRowHeight(25); // 设置行高.
		String[] columns = { "时间段", "剩余场次", "预约" }; // 创建列名数组.
		// 创建表格模型.
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		table.setModel(model); // 设置表格模型.
		// list_after = new
		// IGymImpl(OrderView.this.sockethelper).search_after(type);
		/*for (int i = 0; i < list_after.size(); i++) {
			List<String> tList = list_after.get(i);
			Object[] rowData = { tList.get(1), tList.get(4), false };// socket返回值
			model.addRow(rowData);
		}*/
		table.setEnabled(false);
		SetTableColor.makeFace(table);
		return table;
	}

	private JTable getRecordTable() {
		table_record = new JTable(); // 创建表格控件.
		table_record.setRowHeight(25); // 设置行高.
		String[] columns = {"预约项目", "预约日期","预约时间","操作时间" }; // 创建列名数组.
		// 创建表格模型.
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		table_record.setModel(model); // 设置表格模型.
		AppointStatusInfo info = new AppointStatusInfo(id,"",0,0,0);
		//AppointStatusInfo info = new AppointStatusInfo(User.getuId(),"",0,0);
		List<AppointStatusInfo> tmpList = new IGymImpl(OrderView.this.sockethelper).EnquiryRecord(info);
		
		for(int i =0;i<tmpList.size();i++){
			AppointStatusInfo tmpInfo = tmpList.get(i);
			String stampTime = stampToDate(tmpInfo.getTimestamp());
			String[] tempStampTime = stampTime.split("-|:");
			String tmpStr = null;
			String tmpTime = null;
			if(tmpInfo.getAppointDate() == 0){
				tmpStr = tempStampTime[1]+"-"+tempStampTime[2];
			}
			if(tmpInfo.getAppointDate() == 1){
				tmpStr = tempStampTime[1]+"-"+(Integer.parseInt(tempStampTime[2])+1);
			}
			if(tmpInfo.getAppointDate() == 2){
				tmpStr = tempStampTime[1]+"-"+(Integer.parseInt(tempStampTime[2])+2);
			}
			
			
				if(tmpInfo.getAppointTime() == 0){
					tmpTime = "9:00-10:00";
				}
				if(tmpInfo.getAppointTime() == 1){
					tmpTime = "10:00-11:00";
				}
				if(tmpInfo.getAppointTime() == 2){
					tmpTime = "14:00-15:00";
				}
				if(tmpInfo.getAppointTime() == 3){
					tmpTime = "15:00-16:00";
				}
				if(tmpInfo.getAppointTime() == 4){
					tmpTime = "16:00-17:00";
				}
				if(tmpInfo.getAppointTime() == 5){
					tmpTime = "17:00-18:00";
				}
			
						
			Object[] rowData = {tmpInfo.getItem(),tmpStr,tmpTime,stampToDate(tmpInfo.getTimestamp())};
			model.addRow(rowData);
		}
		/*
		 * for(int i =0;i<list.size();i++){ List<String> tList = list.get(i);
		 * Object[] rowData =
		 * {tList.get(1),tList.get(2),tList.get(3)};//socket返回值
		 * model.addRow(rowData); }
		 */
		table_record.setEnabled(false);
		SetTableColor.makeFace(table_record);
		return table_record;
		
	}
	
	protected void StuEvent(){
		//查找预约记录
		jb_SearchRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// //////////////////查询
				System.out.println("点击了查询按钮");
				scrollPane_record.setViewportView(getRecordTable());
				System.out.println("fff");
			}
		});
		jb_SearchRecord.setContentAreaFilled(false);
		//显示剩余场次
		button_check.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {// ///////////////////////////////////预约！！！
				System.out.println("点击了查询按钮");
				//scrollPane_today.setViewportView(getTableToday());
				setPlayTable();
				System.out.println("显示了今天的场次");
				
				
			}
		});
		button_check.setContentAreaFilled(false);
		//进行预约
		button_appoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// //////////////进入预约！！！！！！！！
				System.out.println("点击了预约按钮");
				String Item =(String) OrderView.this.comboBox_type.getSelectedItem();
				AppointStatusInfo info = new AppointStatusInfo("","",0,0,0);
				info.setItem(Item);
				//测试09015331
				info.setUserID(id);
				info.setAppointDate(tabbedPane_Play.getSelectedIndex());
				System.out.println(tabbedPane_Play.getSelectedIndex());
				switch(tabbedPane_Play.getSelectedIndex()){
				case 0:
					info.setAppointTime(OrderView.this.table_today.getSelectedRow());
					break;
				case 1:
					info.setAppointTime(OrderView.this.table_tomorrow.getSelectedRow());
					break;
				case 2:
					info.setAppointTime(OrderView.this.table_after.getSelectedRow());
					break;
				}
				info.setTimestamp((Calendar.getInstance().getTimeInMillis())/1000);
				
				//判断时间是否正确
				Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
				int hour = c.get(Calendar.HOUR_OF_DAY); 
				System.out.println(hour);
				if(tabbedPane_Play.getSelectedIndex() == 0){
					switch(OrderView.this.table_today.getSelectedRow()){
					case 0:
						if(hour>=10){
							JOptionPane.showMessageDialog(null, "现在该时间段还无法预约哦！");
							return;
						}
						break;
					case 1:
						if(hour>=11){
							JOptionPane.showMessageDialog(null, "现在该时间段还无法预约哦！");
							return;
						}
						break;
					case 2:
						if(hour>=15){
							JOptionPane.showMessageDialog(null, "现在该时间段还无法预约哦！");
							return;
						}
						break;
					case 3:
						if(hour>=16){
							JOptionPane.showMessageDialog(null, "现在该时间段还无法预约哦！");
							return;
						}
						break;
					case 4:
						if(hour>=17){
							JOptionPane.showMessageDialog(null, "现在该时间段还无法预约哦！");
							return;
						}
						break;
					case 5:
						if(hour>=18){
							JOptionPane.showMessageDialog(null, "现在该时间段还无法预约哦！");
							return;
						}
						break;
					}
				}

				
				
				System.out.println(info.getItem()+" "+info.getAppointDate()+" "+info.getAppointTime()+" "+info.getTimestamp());
				boolean flag = new IGymImpl(OrderView.this.sockethelper).AppointItem(info);
				if(flag){
					JOptionPane.showMessageDialog(null, "已成功预约！");
					
				}
				else{
					JOptionPane.showMessageDialog(null, "预约失败！");
				}
				
				
			}
		});
		button_appoint.setContentAreaFilled(false);
	}
	 public static String stampToDate(long l){
	        String res;
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
	        Date date = new Date(l*1000);
	        res = simpleDateFormat.format(date);
	        return res;
	    }
	 
	 
	protected void setPlayTable(){
		table_today = new JTable();
	    table_today.setRowHeight(28); // 设置行高.
	    table_tomorrow = new JTable();
	    table_tomorrow.setRowHeight(28); // 设置行高.
	    table_after = new JTable();
	    table_after.setRowHeight(28);
		String[] columns = {"时间段", "剩余场次" }; // 创建列名数组.
		// 创建表格模型.
		DefaultTableModel modeltoday = new DefaultTableModel(columns, 0);
		table_today.setModel(modeltoday); // 设置表格模型.
		DefaultTableModel modeltomorrow = new DefaultTableModel(columns, 0);
		table_tomorrow.setModel(modeltomorrow); // 设置表格模型.
		DefaultTableModel modelafter = new DefaultTableModel(columns, 0);
		table_after.setModel(modelafter); // 设置表格模型.
		
		String Item =(String) OrderView.this.comboBox_type.getSelectedItem();
		AppointInfo info = new AppointInfo(Item,"");
		System.out.println(Item);
		AppointInfo[] tmpInfo= new IGymImpl(OrderView.this.sockethelper).EnquiryItem();
		//AppointInfo tmpInfo = tmpinfolist.get(0);
		int j = 0;
		for(;j<4;j++){
			if(tmpInfo[j].getItem().equals(Item))
				break;
		}
		
		for(int i = 0;i<6;i++){
			if(i == 0)
			{
				Object[] rowDataToday = {"09:00-10:00",tmpInfo[j].getItemRemain()[0][i]};
				Object[] rowDataTom = {"09:00-10:00",tmpInfo[j].getItemRemain()[1][i]};
				Object[] rowDataAfter = {"09:00-10:00",tmpInfo[j].getItemRemain()[2][i]};
				modeltoday.addRow(rowDataToday);
				modeltomorrow.addRow(rowDataTom);
				modelafter.addRow(rowDataAfter);
			}
			if(i == 1)
			{
				Object[] rowDataToday = {"10:00-11:00",tmpInfo[j].getItemRemain()[0][i]};
				Object[] rowDataTom = {"10:00-11:00",tmpInfo[j].getItemRemain()[1][i]};
				Object[] rowDataAfter = {"10:00-11:00",tmpInfo[j].getItemRemain()[2][i]};
				modeltoday.addRow(rowDataToday);
				modeltomorrow.addRow(rowDataTom);
				modelafter.addRow(rowDataAfter);
			}
			if(i == 2)
			{
				Object[] rowDataToday = {"14:00-15:00",tmpInfo[j].getItemRemain()[0][i]};
				Object[] rowDataTom = {"14:00-15:00",tmpInfo[j].getItemRemain()[1][i]};
				Object[] rowDataAfter = {"14:00-15:00",tmpInfo[j].getItemRemain()[2][i]};
				modeltoday.addRow(rowDataToday);
				modeltomorrow.addRow(rowDataTom);
				modelafter.addRow(rowDataAfter);
			}
			if(i == 3)
			{
				Object[] rowDataToday = {"15:00-16:00",tmpInfo[j].getItemRemain()[0][i]};
				Object[] rowDataTom = {"15:00-16:00",tmpInfo[j].getItemRemain()[1][i]};
				Object[] rowDataAfter = {"15:00-16:00",tmpInfo[j].getItemRemain()[2][i]};
				modeltoday.addRow(rowDataToday);
				modeltomorrow.addRow(rowDataTom);
				modelafter.addRow(rowDataAfter);
			}
			if(i == 4)
			{
				Object[] rowDataToday = {"16:00-17:00",tmpInfo[j].getItemRemain()[0][i]};
				Object[] rowDataTom = {"16:00-17:00",tmpInfo[j].getItemRemain()[1][i]};
				Object[] rowDataAfter = {"16:00-17:00",tmpInfo[j].getItemRemain()[2][i]};
				modeltoday.addRow(rowDataToday);
				modeltomorrow.addRow(rowDataTom);
				modelafter.addRow(rowDataAfter);
			}
			if(i == 5)
			{
				Object[] rowDataToday = {"17:00-18:00",tmpInfo[j].getItemRemain()[0][i]};
				Object[] rowDataTom = {"17:00-18:00",tmpInfo[j].getItemRemain()[1][i]};
				Object[] rowDataAfter = {"17:00-18:00",tmpInfo[j].getItemRemain()[2][i]};
				modeltoday.addRow(rowDataToday);
				modeltomorrow.addRow(rowDataTom);
				modelafter.addRow(rowDataAfter);
			}
			
		}
		table_today.setEnabled(true);
		SetTableColor.makeFace(table_today);
		
		table_tomorrow.setEnabled(true);
		SetTableColor.makeFace(table_tomorrow);
		
		table_after.setEnabled(true);
		SetTableColor.makeFace(table_after);
		
		scrollPane_today.setViewportView(table_today);
		scrollPane_tomorrow.setViewportView(table_tomorrow);
		scrollPane_after.setViewportView(table_after);
	}
}

