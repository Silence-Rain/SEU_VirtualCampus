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

import seu.vCampus.bz.IAppointAdmin;
import seu.vCampus.bz.IAppointAdminImpl;
import seu.vCampus.bz.IBank;
import seu.vCampus.bz.IBankImpl;
import seu.vCampus.bz.IShopAdmin;
import seu.vCampus.bz.IShopAdminImpl;
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

import common.AppointInfo;
import common.Bank;
import common.GoodInfo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class OrderView {

    //private static IBankImpl ibank;
	private JFrame frame;
	
	private JTable table;
	private SocketHelper sockethelper;

	private JTextField textField_2;

	private JTextField textField_3;

	private JTable table_1;

	private JTextField textField_4;

	private JTable table_2;
	private JTextField textField_appoint_add_name;
	private JTextField textField_appoint_add_num;
	private JTextField textField_appoint_delete_name;
	private JTextField textField_appoint_modify_day1_index0;
	private JTextField textField_appoint_modify_day1_index1;
	private JTextField textField_appoint_modify_day1_index2;
	private JTextField textField_appoint_modify_day1_index3;
	private JTextField textField_appoint_modify_day1_index4;
	private JTextField textField_appoint_modify_day1_index5;
	private JTextField textField_appoint_modify_day2_index0;
	private JTextField textField_appoint_modify_day2_index1;
	private JTextField textField_appoint_modify_day2_index2;
	private JTextField textField_appoint_modify_day2_index3;
	private JTextField textField_appoint_modify_day2_index4;
	private JTextField textField_appoint_modify_day2_index5;
	private JTextField textField_appoint_modify_day3_index0;
	private JTextField textField_appoint_modify_day3_index1;
	private JTextField textField_appoint_modify_day3_index2;
	private JTextField textField_appoint_modify_day3_index3;
	private JTextField textField_appoint_modify_day3_index4;
	private JTextField textField_appoint_modify_day3_index5;

	private JComboBox comboBox_appoint_query_index;
	
	
	public OrderView(mainAdminView mview,SocketHelper sockethelper) {
		//this.ibank = ibank;
		this.sockethelper = sockethelper;
		initialize(mview);
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize(mainAdminView mview) {
		
		JTabbedPane tabbedPane_4 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		tabbedPane_4.setBounds(0, 0, 700, 360);
		
		mview.orderPanel.add(tabbedPane_4);

		JPanel panel_appoint_add = new JPanel();
		tabbedPane_4.addTab("项目添加", null, panel_appoint_add, null);
		panel_appoint_add.setLayout(null);
		
		JTextPane textPane_31 = new JTextPane();
		textPane_31.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_31.setBackground(SystemColor.control);
		textPane_31.setText("项目名称");
		textPane_31.setBounds(67, 93, 98, 21);
		panel_appoint_add.add(textPane_31);
		
		textField_appoint_add_name = new JTextField();
		textField_appoint_add_name.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_add_name.setBounds(175, 93, 81, 21);
		panel_appoint_add.add(textField_appoint_add_name);
		textField_appoint_add_name.setColumns(10);
		
		JTextPane textPane_32 = new JTextPane();
		textPane_32.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_32.setBackground(SystemColor.control);
		textPane_32.setText("剩余场地");
		textPane_32.setBounds(67, 145, 81, 21);
		panel_appoint_add.add(textPane_32);
		
		textField_appoint_add_num = new JTextField();
		textField_appoint_add_num.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_add_num.setColumns(10);
		textField_appoint_add_num.setBounds(175, 145, 42, 21);
		panel_appoint_add.add(textField_appoint_add_num);
		
		JTextPane textPane_35 = new JTextPane();
		textPane_35.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_35.setText("场");
		textPane_35.setBackground(SystemColor.menu);
		textPane_35.setBounds(227, 145, 21, 21);
		panel_appoint_add.add(textPane_35);
		
		comboBox_appoint_query_index = new JComboBox();
		comboBox_appoint_query_index.setModel(new DefaultComboBoxModel(new String[] {"羽毛球", "篮球", "乒乓球", "健身房"}));
		comboBox_appoint_query_index.setToolTipText("");
		comboBox_appoint_query_index.setBounds(175, 21, 75, 21);
		comboBox_appoint_query_index.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton btnNewButton = new JButton("确认添加");
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=textField_appoint_add_name.getText();
				String num=textField_appoint_add_num.getText();
				
				if(name.equals("")){
					JOptionPane.showMessageDialog(null, "请输入项目名称");
					return;
				}
				
				if(num.equals("")||Integer.valueOf(num)<=0||Integer.valueOf(num)!=Double.valueOf(num).intValue()){
					JOptionPane.showMessageDialog(null, "剩余场次应为正整数，请重新输入");
					return;
				}
				System.out.println(name+" "+num);
				
				boolean flag=new IAppointAdminImpl(OrderView.this.sockethelper).addItemAdmin(name, num);
				System.out.println(flag);
				if(flag) {
					comboBox_appoint_query_index.addItem(name);
					JOptionPane.showMessageDialog(null, "添加成功！");
				}
				else {
					JOptionPane.showMessageDialog(null, "添加失败");
				}
			}
		});
		btnNewButton.setBounds(488, 143, 93, 23);
		panel_appoint_add.add(btnNewButton);
		
		JPanel panel_appoint_delete = new JPanel();
		tabbedPane_4.addTab("项目删除", null, panel_appoint_delete, null);
		panel_appoint_delete.setLayout(null);
		
		JTextPane textPane_43 = new JTextPane();
		textPane_43.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_43.setText("项目名称");
		textPane_43.setBackground(SystemColor.menu);
		textPane_43.setBounds(69, 135, 85, 21);
		panel_appoint_delete.add(textPane_43);
		
		textField_appoint_delete_name = new JTextField();
		textField_appoint_delete_name.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_delete_name.setColumns(10);
		textField_appoint_delete_name.setBounds(164, 135, 81, 21);
		panel_appoint_delete.add(textField_appoint_delete_name);
		
		JButton button = new JButton("确认删除");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=textField_appoint_delete_name.getText();
				if(name.equals("")){
					JOptionPane.showMessageDialog(null, "请输入项目名称");
					return;
				}
				
				boolean flag=new IAppointAdminImpl(OrderView.this.sockethelper).deleteItemAdmin(name);
				if(flag) {
					comboBox_appoint_query_index.removeItem(name);
					JOptionPane.showMessageDialog(null, "删除成功！");
				}
				else {
					JOptionPane.showMessageDialog(null, "删除失败！");
				}
			}
		});
		button.setBounds(480, 135, 93, 23);
		panel_appoint_delete.add(button);
		
		JPanel panel_appoint_modify = new JPanel();
		tabbedPane_4.addTab("项目信息修改", null, panel_appoint_modify, null);
		panel_appoint_modify.setLayout(null);
		
		JTextPane textPane_50 = new JTextPane();
		textPane_50.setBounds(68, 21, 97, 21);
		textPane_50.setText("预约项目");
		textPane_50.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_50.setBackground(SystemColor.menu);
		panel_appoint_modify.add(textPane_50);
		
		/*
		comboBox_appoint_query_index = new JComboBox();
		comboBox_appoint_query_index.setModel(new DefaultComboBoxModel(new String[] {"羽毛球", "篮球", "乒乓球", "健身房"}));
		comboBox_appoint_query_index.setToolTipText("");
		comboBox_appoint_query_index.setBounds(175, 21, 75, 21);
		comboBox_appoint_query_index.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		*/
		panel_appoint_modify.add(comboBox_appoint_query_index);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(68, 52, 431, 252);
		tabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		panel_appoint_modify.add(tabbedPane);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("--今天--", null, panel_3, null);
		panel_3.setLayout(null);
		
		JTextPane textPane_51 = new JTextPane();
		textPane_51.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_51.setText("09:00-10:00剩余");
		textPane_51.setBackground(SystemColor.menu);
		textPane_51.setBounds(53, 10, 101, 21);
		panel_3.add(textPane_51);
		
		textField_appoint_modify_day1_index0 = new JTextField();
		textField_appoint_modify_day1_index0.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_modify_day1_index0.setColumns(10);
		textField_appoint_modify_day1_index0.setBounds(164, 10, 66, 21);
		panel_3.add(textField_appoint_modify_day1_index0);
		
		JTextPane textPane_52 = new JTextPane();
		textPane_52.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_52.setText("场");
		textPane_52.setBackground(SystemColor.menu);
		textPane_52.setBounds(240, 10, 21, 21);
		panel_3.add(textPane_52);
		
		JTextPane textPane_53 = new JTextPane();
		textPane_53.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_53.setText("10:00-11:00剩余");
		textPane_53.setBackground(SystemColor.menu);
		textPane_53.setBounds(53, 41, 101, 21);
		panel_3.add(textPane_53);
		
		textField_appoint_modify_day1_index1 = new JTextField();
		textField_appoint_modify_day1_index1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_modify_day1_index1.setColumns(10);
		textField_appoint_modify_day1_index1.setBounds(164, 41, 66, 21);
		panel_3.add(textField_appoint_modify_day1_index1);
		
		JTextPane textPane_54 = new JTextPane();
		textPane_54.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_54.setText("场");
		textPane_54.setBackground(SystemColor.menu);
		textPane_54.setBounds(240, 41, 21, 21);
		panel_3.add(textPane_54);
		
		JTextPane textPane_55 = new JTextPane();
		textPane_55.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_55.setText("14:00-15:00剩余");
		textPane_55.setBackground(SystemColor.menu);
		textPane_55.setBounds(53, 72, 101, 21);
		panel_3.add(textPane_55);
		
		textField_appoint_modify_day1_index2 = new JTextField();
		textField_appoint_modify_day1_index2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_modify_day1_index2.setColumns(10);
		textField_appoint_modify_day1_index2.setBounds(165, 78, 66, 21);
		panel_3.add(textField_appoint_modify_day1_index2);
		
		JTextPane textPane_56 = new JTextPane();
		textPane_56.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_56.setText("场");
		textPane_56.setBackground(SystemColor.menu);
		textPane_56.setBounds(240, 72, 21, 21);
		panel_3.add(textPane_56);
		
		JTextPane textPane_57 = new JTextPane();
		textPane_57.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_57.setText("15:00-16:00剩余");
		textPane_57.setBackground(SystemColor.menu);
		textPane_57.setBounds(53, 103, 101, 21);
		panel_3.add(textPane_57);
		
		textField_appoint_modify_day1_index3 = new JTextField();
		textField_appoint_modify_day1_index3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_modify_day1_index3.setColumns(10);
		textField_appoint_modify_day1_index3.setBounds(165, 109, 66, 21);
		panel_3.add(textField_appoint_modify_day1_index3);
		
		JTextPane textPane_58 = new JTextPane();
		textPane_58.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_58.setText("场");
		textPane_58.setBackground(SystemColor.menu);
		textPane_58.setBounds(240, 103, 21, 21);
		panel_3.add(textPane_58);
		
		JTextPane textPane_59 = new JTextPane();
		textPane_59.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_59.setText("16:00-17:00剩余");
		textPane_59.setBackground(SystemColor.menu);
		textPane_59.setBounds(53, 134, 101, 21);
		panel_3.add(textPane_59);
		
		textField_appoint_modify_day1_index4 = new JTextField();
		textField_appoint_modify_day1_index4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_modify_day1_index4.setColumns(10);
		textField_appoint_modify_day1_index4.setBounds(165, 140, 66, 21);
		panel_3.add(textField_appoint_modify_day1_index4);
		
		JTextPane textPane_60 = new JTextPane();
		textPane_60.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_60.setText("场");
		textPane_60.setBackground(SystemColor.menu);
		textPane_60.setBounds(240, 134, 21, 21);
		panel_3.add(textPane_60);
		
		JTextPane textPane_61 = new JTextPane();
		textPane_61.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_61.setText("17:00-18:00剩余");
		textPane_61.setBackground(SystemColor.menu);
		textPane_61.setBounds(53, 165, 101, 21);
		panel_3.add(textPane_61);
		
		textField_appoint_modify_day1_index5 = new JTextField();
		textField_appoint_modify_day1_index5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_modify_day1_index5.setColumns(10);
		textField_appoint_modify_day1_index5.setBounds(165, 171, 66, 21);
		panel_3.add(textField_appoint_modify_day1_index5);
		
		JTextPane textPane_62 = new JTextPane();
		textPane_62.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_62.setText("场");
		textPane_62.setBackground(SystemColor.menu);
		textPane_62.setBounds(240, 165, 21, 21);
		panel_3.add(textPane_62);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("--明天--", null, panel_4, null);
		panel_4.setLayout(null);
		
		JTextPane textPane_63 = new JTextPane();
		textPane_63.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_63.setText("09:00-10:00剩余");
		textPane_63.setBackground(SystemColor.menu);
		textPane_63.setBounds(49, 10, 101, 21);
		panel_4.add(textPane_63);
		
		textField_appoint_modify_day2_index0 = new JTextField();
		textField_appoint_modify_day2_index0.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_modify_day2_index0.setColumns(10);
		textField_appoint_modify_day2_index0.setBounds(160, 10, 66, 21);
		panel_4.add(textField_appoint_modify_day2_index0);
		
		JTextPane textPane_64 = new JTextPane();
		textPane_64.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_64.setText("场");
		textPane_64.setBackground(SystemColor.menu);
		textPane_64.setBounds(236, 10, 21, 21);
		panel_4.add(textPane_64);
		
		JTextPane textPane_65 = new JTextPane();
		textPane_65.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_65.setText("10:00-11:00剩余");
		textPane_65.setBackground(SystemColor.menu);
		textPane_65.setBounds(49, 41, 101, 21);
		panel_4.add(textPane_65);
		
		textField_appoint_modify_day2_index1 = new JTextField();
		textField_appoint_modify_day2_index1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_modify_day2_index1.setColumns(10);
		textField_appoint_modify_day2_index1.setBounds(160, 41, 66, 21);
		panel_4.add(textField_appoint_modify_day2_index1);
		
		JTextPane textPane_66 = new JTextPane();
		textPane_66.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_66.setText("场");
		textPane_66.setBackground(SystemColor.menu);
		textPane_66.setBounds(236, 41, 21, 21);
		panel_4.add(textPane_66);
		
		JTextPane textPane_67 = new JTextPane();
		textPane_67.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_67.setText("14:00-15:00剩余");
		textPane_67.setBackground(SystemColor.menu);
		textPane_67.setBounds(49, 72, 101, 21);
		panel_4.add(textPane_67);
		
		textField_appoint_modify_day2_index2 = new JTextField();
		textField_appoint_modify_day2_index2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_modify_day2_index2.setColumns(10);
		textField_appoint_modify_day2_index2.setBounds(160, 72, 66, 21);
		panel_4.add(textField_appoint_modify_day2_index2);
		
		JTextPane textPane_68 = new JTextPane();
		textPane_68.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_68.setText("场");
		textPane_68.setBackground(SystemColor.menu);
		textPane_68.setBounds(236, 72, 21, 21);
		panel_4.add(textPane_68);
		
		JTextPane textPane_69 = new JTextPane();
		textPane_69.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_69.setText("15:00-16:00剩余");
		textPane_69.setBackground(SystemColor.menu);
		textPane_69.setBounds(49, 103, 101, 21);
		panel_4.add(textPane_69);
		
		textField_appoint_modify_day2_index3 = new JTextField();
		textField_appoint_modify_day2_index3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_modify_day2_index3.setColumns(10);
		textField_appoint_modify_day2_index3.setBounds(160, 103, 66, 21);
		panel_4.add(textField_appoint_modify_day2_index3);
		
		JTextPane textPane_70 = new JTextPane();
		textPane_70.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_70.setText("场");
		textPane_70.setBackground(SystemColor.menu);
		textPane_70.setBounds(236, 103, 21, 21);
		panel_4.add(textPane_70);
		
		JTextPane textPane_71 = new JTextPane();
		textPane_71.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_71.setText("16:00-17:00剩余");
		textPane_71.setBackground(SystemColor.menu);
		textPane_71.setBounds(49, 134, 101, 21);
		panel_4.add(textPane_71);
		
		textField_appoint_modify_day2_index4 = new JTextField();
		textField_appoint_modify_day2_index4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_modify_day2_index4.setColumns(10);
		textField_appoint_modify_day2_index4.setBounds(160, 134, 66, 21);
		panel_4.add(textField_appoint_modify_day2_index4);
		
		JTextPane textPane_72 = new JTextPane();
		textPane_72.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_72.setText("场");
		textPane_72.setBackground(SystemColor.menu);
		textPane_72.setBounds(236, 134, 21, 21);
		panel_4.add(textPane_72);
		
		JTextPane textPane_73 = new JTextPane();
		textPane_73.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_73.setText("17:00-18:00剩余");
		textPane_73.setBackground(SystemColor.menu);
		textPane_73.setBounds(49, 165, 101, 21);
		panel_4.add(textPane_73);
		
		textField_appoint_modify_day2_index5 = new JTextField();
		textField_appoint_modify_day2_index5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_modify_day2_index5.setColumns(10);
		textField_appoint_modify_day2_index5.setBounds(160, 165, 66, 21);
		panel_4.add(textField_appoint_modify_day2_index5);
		
		JTextPane textPane_74 = new JTextPane();
		textPane_74.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_74.setText("场");
		textPane_74.setBackground(SystemColor.menu);
		textPane_74.setBounds(236, 165, 21, 21);
		panel_4.add(textPane_74);
		
		JPanel panel_9 = new JPanel();
		tabbedPane.addTab("--后天--", null, panel_9, null);
		panel_9.setLayout(null);
		
		JTextPane textPane_75 = new JTextPane();
		textPane_75.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_75.setText("09:00-10:00剩余");
		textPane_75.setBackground(SystemColor.menu);
		textPane_75.setBounds(53, 10, 101, 21);
		panel_9.add(textPane_75);
		
		textField_appoint_modify_day3_index0 = new JTextField();
		textField_appoint_modify_day3_index0.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_modify_day3_index0.setColumns(10);
		textField_appoint_modify_day3_index0.setBounds(164, 10, 66, 21);
		panel_9.add(textField_appoint_modify_day3_index0);
		
		JTextPane textPane_76 = new JTextPane();
		textPane_76.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_76.setText("场");
		textPane_76.setBackground(SystemColor.menu);
		textPane_76.setBounds(240, 10, 21, 21);
		panel_9.add(textPane_76);
		
		JTextPane textPane_77 = new JTextPane();
		textPane_77.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_77.setText("10:00-11:00剩余");
		textPane_77.setBackground(SystemColor.menu);
		textPane_77.setBounds(53, 41, 101, 21);
		panel_9.add(textPane_77);
		
		textField_appoint_modify_day3_index1 = new JTextField();
		textField_appoint_modify_day3_index1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_modify_day3_index1.setColumns(10);
		textField_appoint_modify_day3_index1.setBounds(164, 41, 66, 21);
		panel_9.add(textField_appoint_modify_day3_index1);
		
		JTextPane textPane_78 = new JTextPane();
		textPane_78.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_78.setText("场");
		textPane_78.setBackground(SystemColor.menu);
		textPane_78.setBounds(240, 41, 21, 21);
		panel_9.add(textPane_78);
		
		JTextPane textPane_79 = new JTextPane();
		textPane_79.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_79.setText("14:00-15:00剩余");
		textPane_79.setBackground(SystemColor.menu);
		textPane_79.setBounds(53, 72, 101, 21);
		panel_9.add(textPane_79);
		
		textField_appoint_modify_day3_index2 = new JTextField();
		textField_appoint_modify_day3_index2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_modify_day3_index2.setColumns(10);
		textField_appoint_modify_day3_index2.setBounds(164, 72, 66, 21);
		panel_9.add(textField_appoint_modify_day3_index2);
		
		JTextPane textPane_80 = new JTextPane();
		textPane_80.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_80.setText("场");
		textPane_80.setBackground(SystemColor.menu);
		textPane_80.setBounds(240, 72, 21, 21);
		panel_9.add(textPane_80);
		
		JTextPane textPane_81 = new JTextPane();
		textPane_81.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_81.setText("15:00-16:00剩余");
		textPane_81.setBackground(SystemColor.menu);
		textPane_81.setBounds(53, 103, 101, 21);
		panel_9.add(textPane_81);
		
		textField_appoint_modify_day3_index3 = new JTextField();
		textField_appoint_modify_day3_index3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_modify_day3_index3.setColumns(10);
		textField_appoint_modify_day3_index3.setBounds(164, 103, 66, 21);
		panel_9.add(textField_appoint_modify_day3_index3);
		
		JTextPane textPane_82 = new JTextPane();
		textPane_82.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_82.setText("场");
		textPane_82.setBackground(SystemColor.menu);
		textPane_82.setBounds(240, 103, 21, 21);
		panel_9.add(textPane_82);
		
		JTextPane textPane_83 = new JTextPane();
		textPane_83.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_83.setText("16:00-17:00剩余");
		textPane_83.setBackground(SystemColor.menu);
		textPane_83.setBounds(53, 134, 101, 21);
		panel_9.add(textPane_83);
		
		textField_appoint_modify_day3_index4 = new JTextField();
		textField_appoint_modify_day3_index4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_modify_day3_index4.setColumns(10);
		textField_appoint_modify_day3_index4.setBounds(164, 134, 66, 21);
		panel_9.add(textField_appoint_modify_day3_index4);
		
		JTextPane textPane_84 = new JTextPane();
		textPane_84.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_84.setText("场");
		textPane_84.setBackground(SystemColor.menu);
		textPane_84.setBounds(240, 134, 21, 21);
		panel_9.add(textPane_84);
		
		JTextPane textPane_85 = new JTextPane();
		textPane_85.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_85.setText("17:00-18:00剩余");
		textPane_85.setBackground(SystemColor.menu);
		textPane_85.setBounds(53, 165, 101, 21);
		panel_9.add(textPane_85);
		
		textField_appoint_modify_day3_index5 = new JTextField();
		textField_appoint_modify_day3_index5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_appoint_modify_day3_index5.setColumns(10);
		textField_appoint_modify_day3_index5.setBounds(164, 165, 66, 21);
		panel_9.add(textField_appoint_modify_day3_index5);
		
		JTextPane textPane_86 = new JTextPane();
		textPane_86.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_86.setText("场");
		textPane_86.setBackground(SystemColor.menu);
		textPane_86.setBounds(240, 165, 21, 21);
		panel_9.add(textPane_86);
		
		JButton btnNewButton_1 = new JButton("确认修改");
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=comboBox_appoint_query_index.getSelectedItem().toString();
				String[][] array=new String[3][6];
				array[0][0]=textField_appoint_modify_day1_index0.getText();
				array[0][1]=textField_appoint_modify_day1_index1.getText();
				array[0][2]=textField_appoint_modify_day1_index2.getText();
				array[0][3]=textField_appoint_modify_day1_index3.getText();
				array[0][4]=textField_appoint_modify_day1_index4.getText();
				array[0][5]=textField_appoint_modify_day1_index5.getText();
				array[1][0]=textField_appoint_modify_day2_index0.getText();
				array[1][1]=textField_appoint_modify_day2_index1.getText();
				array[1][2]=textField_appoint_modify_day2_index2.getText();
				array[1][3]=textField_appoint_modify_day2_index3.getText();
				array[1][4]=textField_appoint_modify_day2_index4.getText();
				array[1][5]=textField_appoint_modify_day2_index5.getText();
				array[2][0]=textField_appoint_modify_day3_index0.getText();
				array[2][1]=textField_appoint_modify_day3_index1.getText();
				array[2][2]=textField_appoint_modify_day3_index2.getText();
				array[2][3]=textField_appoint_modify_day3_index3.getText();
				array[2][4]=textField_appoint_modify_day3_index4.getText();
				array[2][5]=textField_appoint_modify_day3_index5.getText();
				for(int i=0;i<3;i++){
					for(int j=0;j<6;j++){
						if(Integer.valueOf(array[i][j])<=0||Integer.valueOf(array[i][j])!=Double.valueOf(array[i][j]).intValue()){
							JOptionPane.showMessageDialog(null, "剩余场次应为正整数，请重新输入");
							return;
						}
					}
				}
				
				boolean flag=new IAppointAdminImpl(OrderView.this.sockethelper).modifyItemAdmin(name, array);
				if(flag) {
					JOptionPane.showMessageDialog(null, "修改成功！");
				}else {
					JOptionPane.showMessageDialog(null, "修改失败！");
				}
			}
		});
		btnNewButton_1.setBounds(558, 281, 93, 23);
		panel_appoint_modify.add(btnNewButton_1);
		
		JButton button_2 = new JButton("确认查询");
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=comboBox_appoint_query_index.getSelectedItem().toString();
				
				AppointInfo[] array=new IAppointAdminImpl(OrderView.this.sockethelper).inquireItemAdmin();
				int i=0;
				for(;i<3;i++) {
						if(array[i].getItem().equals(name)) {
							break;
					}
				}
				String[][] a=array[i].getItemRemain();
				textField_appoint_modify_day1_index0.setText(a[0][0]);
				textField_appoint_modify_day1_index1.setText(a[0][1]);
				textField_appoint_modify_day1_index2.setText(a[0][2]);
				textField_appoint_modify_day1_index3.setText(a[0][3]);
				textField_appoint_modify_day1_index4.setText(a[0][4]);
				textField_appoint_modify_day1_index5.setText(a[0][5]);
				textField_appoint_modify_day2_index0.setText(a[1][0]);
				textField_appoint_modify_day2_index1.setText(a[1][1]);
				textField_appoint_modify_day2_index2.setText(a[1][2]);
				textField_appoint_modify_day2_index3.setText(a[1][3]);
				textField_appoint_modify_day2_index4.setText(a[1][4]);
				textField_appoint_modify_day2_index5.setText(a[1][5]);
				textField_appoint_modify_day3_index0.setText(a[2][0]);
				textField_appoint_modify_day3_index1.setText(a[2][1]);
				textField_appoint_modify_day3_index2.setText(a[2][2]);
				textField_appoint_modify_day3_index3.setText(a[2][3]);
				textField_appoint_modify_day3_index4.setText(a[2][4]);
				textField_appoint_modify_day3_index5.setText(a[2][5]);
			}
		});
		button_2.setBounds(406, 21, 93, 23);
		panel_appoint_modify.add(button_2);
		
	}
	}

