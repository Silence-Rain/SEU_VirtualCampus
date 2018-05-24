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
import seu.vCampus.bz.ILibraryAdminImpl;
import seu.vCampus.bz.IShopAdminImpl;
import seu.vCampus.util.SocketHelper;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
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

import common.Bank;
import common.BookInfo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class LibrView {

    //private static IBankImpl ibank;
	private SocketHelper sockethelper;
	
	private JFrame frame;
	private JTextField textField_lib_add_name;
	private JTextField textField_lib_add_author;
	private JTextField textField_lib_add_pub;
	private JTextField textField_lib_delete_id;
	private JTextField textField_lib_modify_id;
	private JTextField textField_lib_add_num;
	private JTextField textField_lib_add_isbn;
	private JTextField textField_lib_modify_isbn;
	private JTextField textField_lib_modify_name;
	private JTextField textField_lib_modify_author;
	private JTextField textField_lib_modify_pub;
	private JComboBox comboBox_lib_modify_ifBorrowed;
	

	
	
	public LibrView(mainAdminView mview,SocketHelper sockethelper) {
		//this.ibank = ibank;
		this.sockethelper = sockethelper;
		initialize(mview);
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize(mainAdminView mview) {
		//mainView mview = new mainView();
		comboBox_lib_modify_ifBorrowed=new JComboBox();
		comboBox_lib_modify_ifBorrowed.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		tabbedPane_3.setBounds(0, 0, 700, 360);
		mview.librPanel.add(tabbedPane_3);
		
		JPanel panel_lib_delete = new JPanel();
		tabbedPane_3.addTab("添加书籍", null, panel_lib_delete, null);
		panel_lib_delete.setLayout(null);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_1.setBackground(UIManager.getColor("Button.background"));
		textPane_1.setText("书籍名称：");
		textPane_1.setBounds(70, 112, 66, 21);
		panel_lib_delete.add(textPane_1);
		
		textField_lib_add_name = new JTextField();
		textField_lib_add_name.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_lib_add_name.setBounds(146, 112, 112, 21);
		panel_lib_delete.add(textField_lib_add_name);
		textField_lib_add_name.setColumns(10);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_2.setBackground(UIManager.getColor("Button.background"));
		textPane_2.setText("作者：");
		textPane_2.setBounds(70, 144, 66, 21);
		panel_lib_delete.add(textPane_2);
		
		textField_lib_add_author = new JTextField();
		textField_lib_add_author.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_lib_add_author.setColumns(10);
		textField_lib_add_author.setBounds(146, 143, 112, 21);
		panel_lib_delete.add(textField_lib_add_author);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_3.setBackground(UIManager.getColor("Button.background"));
		textPane_3.setText("出版社：");
		textPane_3.setBounds(70, 175, 66, 21);
		panel_lib_delete.add(textPane_3);
		
		textField_lib_add_pub = new JTextField();
		textField_lib_add_pub.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_lib_add_pub.setColumns(10);
		textField_lib_add_pub.setBounds(146, 174, 112, 21);
		panel_lib_delete.add(textField_lib_add_pub);
		
		//JComboBox comboBox_lib_modify_ifBorrowed = new JComboBox();
		comboBox_lib_modify_ifBorrowed.setEditable(true);
		comboBox_lib_modify_ifBorrowed.setModel(new DefaultComboBoxModel(new String[] {"是", "否"}));
		comboBox_lib_modify_ifBorrowed.setBounds(139, 216, 112, 21);
		
		
		JButton button_1 = new JButton("确认添加");
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String isbn=textField_lib_add_isbn.getText();
				String name=textField_lib_add_name.getText();
				String author=textField_lib_add_author.getText();
				String pub=textField_lib_add_pub.getText();
				int num=Integer.valueOf(textField_lib_add_num.getText());
				if(isbn.equals("")){
					JOptionPane.showMessageDialog(null, "请输入ISBN");
					return;
				}
				if(name.equals("")){
					JOptionPane.showMessageDialog(null, "请输入书籍名称");
					return;
				}
				if(author.equals("")){
					JOptionPane.showMessageDialog(null, "请输入作者");
					return;
				}
				if(pub.equals("")){
					JOptionPane.showMessageDialog(null, "请输入出版社");
					return;
				}
				if(num<=0||Integer.valueOf(textField_lib_add_num.getText())!=Double.valueOf(textField_lib_add_num.getText()).intValue()){
					JOptionPane.showMessageDialog(null, "库存量应该是正整数，请重新输入");
					return;
				}
				System.out.println(isbn+" "+name+" "+author+" "+pub+" "+num);
				
				boolean flag = new ILibraryAdminImpl(LibrView.this.sockethelper).addBook(isbn, name, author, pub, num);
				if(flag) {
					JOptionPane.showMessageDialog(null, "添加成功！");	
				}
				else {
					JOptionPane.showMessageDialog(null, "添加库存商品失败");
				}
			}
		});
		button_1.setBounds(530, 204, 93, 23);
		panel_lib_delete.add(button_1);
		
		JTextPane textPane_11 = new JTextPane();
		textPane_11.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_11.setBackground(UIManager.getColor("Button.background"));
		textPane_11.setText("库存量：");
		textPane_11.setBounds(70, 206, 66, 21);
		panel_lib_delete.add(textPane_11);
		
		textField_lib_add_num = new JTextField();
		textField_lib_add_num.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_lib_add_num.setColumns(10);
		textField_lib_add_num.setBounds(146, 206, 112, 21);
		panel_lib_delete.add(textField_lib_add_num);
		
		JTextPane txtpnIsbn = new JTextPane();
		txtpnIsbn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtpnIsbn.setBackground(UIManager.getColor("Button.background"));
		txtpnIsbn.setText("ISBN：");
		txtpnIsbn.setBounds(70, 81, 66, 21);
		panel_lib_delete.add(txtpnIsbn);
		
		textField_lib_add_isbn = new JTextField();
		textField_lib_add_isbn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_lib_add_isbn.setColumns(10);
		textField_lib_add_isbn.setBounds(146, 81, 112, 21);
		panel_lib_delete.add(textField_lib_add_isbn);
		//borrrowed	books part-------------------------------------------------------------	
		JPanel panel_lib_add = new JPanel();
		tabbedPane_3.addTab("删除书籍", null, panel_lib_add, null);
		panel_lib_add.setLayout(null);
		
		JButton button = new JButton("确认删除");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.valueOf(textField_lib_delete_id.getText());
				if(id<=0||id!=Double.valueOf(textField_lib_delete_id.getText()).intValue()){
					JOptionPane.showMessageDialog(null, "书籍ID应为正整数，请重新输入");
					return;
				}
				
				boolean flag=new ILibraryAdminImpl(LibrView.this.sockethelper).deleteBook(id);
				if(flag) {
					JOptionPane.showMessageDialog(null, "删除成功！");
				}
				else {
					JOptionPane.showMessageDialog(null,  "删除库存书籍失败");
				}
			}
		});
		button.setBounds(487, 139, 93, 23);
		panel_lib_add.add(button);
		
		JTextPane txtpnid = new JTextPane();
		txtpnid.setBackground(UIManager.getColor("Button.background"));
		txtpnid.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtpnid.setText("书籍ID：");
		txtpnid.setBounds(73, 139, 66, 21);
		panel_lib_add.add(txtpnid);
		
		textField_lib_delete_id = new JTextField();
		textField_lib_delete_id.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_lib_delete_id.setColumns(10);
		textField_lib_delete_id.setBounds(149, 138, 112, 21);
		panel_lib_add.add(textField_lib_delete_id);
		
		JPanel panel_lib_modify = new JPanel();
		tabbedPane_3.addTab("修改书籍信息", null, panel_lib_modify, null);
		panel_lib_modify.setLayout(null);
		
		JTextPane txtpnid_1 = new JTextPane();
		txtpnid_1.setBackground(UIManager.getColor("Button.background"));
		txtpnid_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtpnid_1.setText("书籍ID：");
		txtpnid_1.setBounds(63, 25, 66, 21);
		panel_lib_modify.add(txtpnid_1);
		
		textField_lib_modify_id = new JTextField();
		textField_lib_modify_id.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_lib_modify_id.setColumns(10);
		textField_lib_modify_id.setBounds(139, 25, 112, 21);
		panel_lib_modify.add(textField_lib_modify_id);
		
		JButton button_2 = new JButton("确认查询");
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.valueOf(textField_lib_modify_id.getText());
				if(id<=0||id!=Double.valueOf(textField_lib_delete_id.getText()).intValue()){
					JOptionPane.showMessageDialog(null, "书籍ID应为正整数，请重新输入");
					return;
				}
				
				List<BookInfo> list = new ILibraryAdminImpl(LibrView.this.sockethelper).inquireBook();
				int i=0;
				for(;i<list.size();i++) {
					if(list.get(i).getId()==id) {
						break;
					}
				}
				if(i==list.size()){
					JOptionPane.showMessageDialog(null, "查询失败");	
				}
				textField_lib_modify_isbn.setText(list.get(i).getIsbn());
				textField_lib_modify_name.setText(list.get(i).getName());
				textField_lib_modify_author.setText(list.get(i).getAuthor());
				textField_lib_modify_pub.setText(list.get(i).getPub());
				System.out.println(list.get(i).getIsbn()+" "+list.get(i).getName()+" "+list.get(i).getAuthor()+" "+list.get(i).getPub());
				
				System.out.println(String.valueOf(list.get(i).isBorrowed()));
				if(String.valueOf(list.get(i).isBorrowed()).equals("true")) {
					comboBox_lib_modify_ifBorrowed.setSelectedItem("否");;
				}
				else {
					comboBox_lib_modify_ifBorrowed.setSelectedItem("是");
				}
			}
		});
		button_2.setBounds(489, 24, 93, 23);
		panel_lib_modify.add(button_2);
		panel_lib_modify.add(comboBox_lib_modify_ifBorrowed);
		JTextPane textPane_10 = new JTextPane();
		textPane_10.setBackground(UIManager.getColor("Button.background"));
		textPane_10.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_10.setText("是否可借：");
		textPane_10.setBounds(63, 216, 66, 21);
		panel_lib_modify.add(textPane_10);
		
		JButton button_3 = new JButton("确认修改");
		button_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(textField_lib_modify_id.getText());
				int id=Integer.valueOf(textField_lib_modify_id.getText());
				
				System.out.println(textField_lib_modify_isbn.getText());
				String isbn=textField_lib_modify_isbn.getText();
				
				System.out.println(textField_lib_modify_name.getText());
				String name=textField_lib_modify_name.getText();
				
				System.out.println(textField_lib_modify_author.getText());
				String author=textField_lib_modify_author.getText();
				
				System.out.println(textField_lib_modify_pub.getText());
				String pub=textField_lib_modify_pub.getText();
				
				int index=comboBox_lib_modify_ifBorrowed.getSelectedIndex();
				boolean isBorrowed=false;
				if(index==0) {
					isBorrowed=false;
				}
				else if(index==1) {
					isBorrowed=true;
				}
				System.out.println(index);
				System.out.println(String.valueOf(isBorrowed));
				
				boolean flag = new ILibraryAdminImpl(LibrView.this.sockethelper).modifyBook(id,name,isbn,author,pub,isBorrowed);
				System.out.println(flag);
				if(flag) {
					JOptionPane.showMessageDialog(null, "修改成功！");	
				}
				else {
					JOptionPane.showMessageDialog(null, "错误", "修改信息失败", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button_3.setBounds(489, 216, 93, 23);
		panel_lib_modify.add(button_3);
		
		
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(UIManager.getColor("Button.background"));
		textPane.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane.setText("ISBN：");
		textPane.setBounds(63, 56, 66, 21);
		panel_lib_modify.add(textPane);
		
		textField_lib_modify_isbn = new JTextField();
		textField_lib_modify_isbn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_lib_modify_isbn.setEditable(false);
		textField_lib_modify_isbn.setColumns(10);
		textField_lib_modify_isbn.setBounds(139, 56, 112, 21);
		panel_lib_modify.add(textField_lib_modify_isbn);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setBackground(UIManager.getColor("Button.background"));
		textPane_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_4.setText("书籍名称：");
		textPane_4.setBounds(63, 87, 66, 21);
		panel_lib_modify.add(textPane_4);
		
		textField_lib_modify_name = new JTextField();
		textField_lib_modify_name.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_lib_modify_name.setEditable(false);
		textField_lib_modify_name.setColumns(10);
		textField_lib_modify_name.setBounds(139, 87, 112, 21);
		panel_lib_modify.add(textField_lib_modify_name);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setBackground(UIManager.getColor("Button.background"));
		textPane_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_5.setText("作者：");
		textPane_5.setBounds(63, 119, 66, 21);
		panel_lib_modify.add(textPane_5);
		
		textField_lib_modify_author = new JTextField();
		textField_lib_modify_author.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_lib_modify_author.setEditable(false);
		textField_lib_modify_author.setColumns(10);
		textField_lib_modify_author.setBounds(139, 118, 112, 21);
		panel_lib_modify.add(textField_lib_modify_author);
		
		JTextPane textPane_6 = new JTextPane();
		textPane_6.setBackground(UIManager.getColor("Button.background"));
		textPane_6.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_6.setText("出版社：");
		textPane_6.setBounds(63, 150, 66, 21);
		panel_lib_modify.add(textPane_6);
		
		textField_lib_modify_pub = new JTextField();
		textField_lib_modify_pub.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_lib_modify_pub.setEditable(false);
		textField_lib_modify_pub.setColumns(10);
		textField_lib_modify_pub.setBounds(139, 149, 112, 21);
		panel_lib_modify.add(textField_lib_modify_pub);
		//frame.getContentPane().setLayout(groupLayout);
	
		
	}
	}

