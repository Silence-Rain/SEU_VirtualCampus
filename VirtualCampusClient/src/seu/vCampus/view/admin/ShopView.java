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
import seu.vCampus.bz.IShop;
import seu.vCampus.bz.IShopAdmin;
import seu.vCampus.bz.IShopAdminImpl;
import seu.vCampus.bz.IShopImpl;
import seu.vCampus.util.SocketHelper;
import seu.vCampus.view.stu.BankView;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
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

import common.Bank;
import common.GoodInfo;
import common.OrderInfo;
import common.GoodInfo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Panel;

public class ShopView {
	private SocketHelper sockethelper;
	
	private JFrame frame;
	
	private JTextField textField_add_ID;
	private JTextField textField_add_name;
	private JComboBox comboBox_ProductTabAdmin;
	private JScrollPane scrollPane_Order;
	private JTextField textField_add_supplier;
	private JTextField textField_add_price;
	private JTextField textField_add_remainNum;
	private JTextField textField_delete_ID;
	private JTextField textField_modify_ID;
	private JTextField textField_modify_name;
	private JTextField textField_modify_supplier;
	private JTextField textField_modify_price;
	private JTextField textField_modify_remainNum;

	private JComboBox comboBox_add_tab;

	private JComboBox comboBox_modify_tab;
	
	public ShopView(mainAdminView mview,SocketHelper sockethelper) {
		//this.ibank = ibank;
		this.sockethelper = sockethelper;
		initialize(mview);
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize(mainAdminView mview) {
		
		// the shop part----------------------------------------------------

		//mainView mview = new mainView();
		
		JTabbedPane shoptab = new JTabbedPane(JTabbedPane.TOP);
		shoptab.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		shoptab.setBounds(0, 0, 700, 360);
		mview.shopPanel.add(shoptab);
//the buy buy buy part-----------------------------------------------------------------
		JPanel Panel_AddProduct = new JPanel();
		Panel_AddProduct.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		shoptab.addTab("添加商品",
				null, Panel_AddProduct, null);
		Panel_AddProduct.setLayout(null);

		JTextPane textPane_5 = new JTextPane();
		textPane_5.setBackground(SystemColor.control);
		textPane_5.setDisabledTextColor(UIManager.getColor("Button.background"));
		textPane_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_5.setText("商品名称：");
		textPane_5.setBounds(33, 87, 90, 24);
		Panel_AddProduct.add(textPane_5);

		textField_add_name = new JTextField();
		textField_add_name.setBounds(133, 89, 114, 22);
		Panel_AddProduct.add(textField_add_name);
		textField_add_name.setColumns(10);

		JTextPane textPane_8 = new JTextPane();
		textPane_8.setBackground(SystemColor.control);
		textPane_8.setDisabledTextColor(UIManager.getColor("Button.background"));
		textPane_8.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_8.setText("标签：");
		textPane_8.setBounds(33, 121, 70, 24);
		Panel_AddProduct.add(textPane_8);
		Object[][] cellData = { { "row1-col1", "row1-col2" }, { "row2-col1", "row2-col2" } };
		String[] columnNames = { "col1", "col2" };

		comboBox_add_tab = new JComboBox();
		comboBox_add_tab.setEditable(true);
		comboBox_add_tab.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		comboBox_add_tab.setModel(
				new DefaultComboBoxModel(new String[] {"衣", "食", "住", "行", "其他"}));
		comboBox_add_tab.setBounds(135, 121, 114, 24);
		Panel_AddProduct.add(comboBox_add_tab);
		
		JTextPane textPane_9 = new JTextPane();
		textPane_9.setText("商品ID：");
		textPane_9.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_9.setDisabledTextColor(SystemColor.menu);
		textPane_9.setBackground(SystemColor.menu);
		textPane_9.setBounds(33, 53, 90, 24);
		textPane_9.setEditable(false);
		Panel_AddProduct.add(textPane_9);
		
		textField_add_ID = new JTextField();
		textField_add_ID.setColumns(10);
		textField_add_ID.setBounds(133, 57, 114, 22);
		Panel_AddProduct.add(textField_add_ID);
		
		JButton button_addProduct = new JButton("确定添加");
		button_addProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=textField_add_ID.getText();
				String name=textField_add_name.getText();
				String tab=(String) comboBox_add_tab.getSelectedItem();
				String supplier=textField_add_supplier.getText();
				double p=Double.valueOf(textField_add_price.getText());
				BigDecimal temp=new BigDecimal(p);
				double price=temp.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				int remainNum=Integer.parseInt(textField_add_remainNum.getText());
				
				if(id.equals("")){
					JOptionPane.showMessageDialog(null,  "请输入商品ID");
					return;
				}
				if(name.equals("")){
					JOptionPane.showMessageDialog(null,  "请输入商品名称");
					return;
				}
				if(supplier.equals("")){
					JOptionPane.showMessageDialog(null,  "请输入生产商");
					return;
				}
				if(Double.valueOf(textField_add_price.getText())<=0) {
					textField_add_price.setText("");
					JOptionPane.showMessageDialog(null,  "商品单价应大于0，请重新输入");
					return;
				}
				if(Integer.parseInt(textField_add_remainNum.getText())<0||Integer.parseInt(textField_add_remainNum.getText())!=Double.valueOf(textField_add_remainNum.getText()).intValue()) {
					textField_add_remainNum.setText("");
					JOptionPane.showMessageDialog(null, "商品应为正整数，请重新输入");
					return;
				}
				
				System.out.println(id+" "+name+" "+ tab+" "+ supplier+ " " + price+" "+remainNum);
				//IShopAdmin ishopadmin=new ;
				boolean flag = new IShopAdminImpl(ShopView.this.sockethelper).addProductAdmin(id, name, price, remainNum, supplier, tab);
				System.out.println("flag is "+flag);
				if(flag) {
					JOptionPane.showMessageDialog(null, "添加成功！");	
				}
				else {
					JOptionPane.showMessageDialog(null, "错误", "添加库存商品失败", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button_addProduct.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_addProduct.setBounds(440, 227, 103, 22);
		Panel_AddProduct.add(button_addProduct);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("生产商：");
		textPane.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane.setDisabledTextColor(SystemColor.menu);
		textPane.setBackground(SystemColor.menu);
		textPane.setBounds(33, 155, 70, 24);
		Panel_AddProduct.add(textPane);
		
		textField_add_supplier = new JTextField();
		textField_add_supplier.setColumns(10);
		textField_add_supplier.setBounds(135, 157, 114, 22);
		Panel_AddProduct.add(textField_add_supplier);
		
		textField_add_price = new JTextField();
		textField_add_price.setColumns(10);
		textField_add_price.setBounds(135, 193, 114, 22);
		Panel_AddProduct.add(textField_add_price);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("商品单价：");
		textPane_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_1.setDisabledTextColor(SystemColor.menu);
		textPane_1.setBackground(SystemColor.menu);
		textPane_1.setBounds(33, 191, 90, 24);
		Panel_AddProduct.add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("商品库存量：");
		textPane_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_2.setDisabledTextColor(SystemColor.menu);
		textPane_2.setBackground(SystemColor.menu);
		textPane_2.setBounds(33, 225, 90, 24);
		Panel_AddProduct.add(textPane_2);
		
		textField_add_remainNum = new JTextField();
		textField_add_remainNum.setColumns(10);
		textField_add_remainNum.setBounds(135, 227, 114, 22);
		Panel_AddProduct.add(textField_add_remainNum);
		
		Panel panel_Delete = new Panel();
		panel_Delete.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		shoptab.addTab("删除商品", null, panel_Delete, null);
		panel_Delete.setLayout(null);
		
		JTextPane txtpnid = new JTextPane();
		txtpnid.setBounds(80, 120, 76, 26);
		txtpnid.setText("商品ID：");
		txtpnid.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtpnid.setDisabledTextColor(SystemColor.menu);
		txtpnid.setBackground(SystemColor.menu);
		panel_Delete.add(txtpnid);
		
		textField_delete_ID = new JTextField();
		textField_delete_ID.setColumns(10);
		textField_delete_ID.setBounds(166, 124, 114, 22);
		panel_Delete.add(textField_delete_ID);
		
		JButton button_deleteProduct = new JButton("确定删除");
		button_deleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=textField_delete_ID.getText();
				if(id.equals("")){
					JOptionPane.showMessageDialog(null, "请输入商品ID");
					return;
				}
				
				boolean flag=new IShopAdminImpl(ShopView.this.sockethelper).deleteProductAdmin(id);
				if(flag) {
					JOptionPane.showMessageDialog(null, "删除成功！");
				}
				else {
					JOptionPane.showMessageDialog(null,  "删除库存商品失败");
				}
			}
		});
		button_deleteProduct.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_deleteProduct.setBounds(456, 124, 103, 22);
		panel_Delete.add(button_deleteProduct);
		
		JPanel panel_Modify = new JPanel();
		shoptab.addTab("商品信息修改", null, panel_Modify, null);
		panel_Modify.setLayout(null);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setBounds(70, 31, 90, 24);
		textPane_3.setText("商品ID：");
		textPane_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_3.setDisabledTextColor(SystemColor.menu);
		textPane_3.setBackground(SystemColor.menu);
		panel_Modify.add(textPane_3);
		
		textField_modify_ID = new JTextField();
		textField_modify_ID.setBounds(170, 31, 114, 22);
		textField_modify_ID.setColumns(10);
		panel_Modify.add(textField_modify_ID);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setBounds(70, 127, 90, 24);
		textPane_4.setText("商品名称：");
		textPane_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_4.setDisabledTextColor(SystemColor.menu);
		textPane_4.setBackground(SystemColor.menu);
		panel_Modify.add(textPane_4);
		
		textField_modify_name = new JTextField();
		textField_modify_name.setBounds(170, 129, 114, 22);
		textField_modify_name.setEditable(false);
		textField_modify_name.setColumns(10);
		panel_Modify.add(textField_modify_name);
		
		
		JTextPane textPane_6 = new JTextPane();
		textPane_6.setBounds(70, 195, 70, 24);
		textPane_6.setText("标签：");
		textPane_6.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_6.setDisabledTextColor(SystemColor.menu);
		textPane_6.setBackground(SystemColor.menu);
		panel_Modify.add(textPane_6);
		
		comboBox_modify_tab = new JComboBox();
		comboBox_modify_tab.setBounds(170, 195, 114, 24);
		comboBox_modify_tab.setModel(new DefaultComboBoxModel(new String[] {"衣", "食", "住", "行", "其他"}));
		comboBox_modify_tab.setEditable(true);
		comboBox_modify_tab.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		panel_Modify.add(comboBox_modify_tab);
		
		JTextPane textPane_7 = new JTextPane();
		textPane_7.setBounds(70, 161, 70, 24);
		textPane_7.setText("生产商：");
		textPane_7.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_7.setDisabledTextColor(SystemColor.menu);
		textPane_7.setBackground(SystemColor.menu);
		panel_Modify.add(textPane_7);
		
		textField_modify_supplier = new JTextField();
		textField_modify_supplier.setBounds(170, 163, 114, 22);
		textField_modify_supplier.setEditable(false);
		textField_modify_supplier.setColumns(10);
		panel_Modify.add(textField_modify_supplier);
		
		JTextPane textPane_10 = new JTextPane();
		textPane_10.setBounds(70, 229, 90, 24);
		textPane_10.setText("商品单价：");
		textPane_10.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_10.setDisabledTextColor(SystemColor.menu);
		textPane_10.setBackground(SystemColor.menu);
		panel_Modify.add(textPane_10);
		
		textField_modify_price = new JTextField();
		textField_modify_price.setBounds(170, 231, 114, 22);
		textField_modify_price.setColumns(10);
		panel_Modify.add(textField_modify_price);
		
		JTextPane textPane_12 = new JTextPane();
		textPane_12.setBounds(70, 263, 90, 24);
		textPane_12.setText("商品库存量：");
		textPane_12.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_12.setDisabledTextColor(SystemColor.menu);
		textPane_12.setBackground(SystemColor.menu);
		panel_Modify.add(textPane_12);
		
		textField_modify_remainNum = new JTextField();
		textField_modify_remainNum.setBounds(170, 265, 114, 22);
		textField_modify_remainNum.setColumns(10);
		panel_Modify.add(textField_modify_remainNum);
		
		JButton button_modifyProduct = new JButton("确定修改");
		button_modifyProduct.setBounds(459, 265, 103, 22);
		button_modifyProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=textField_modify_ID.getText();
				String name=textField_modify_name.getText();
				String supplier=textField_modify_supplier.getText();
				String tab=(String) comboBox_modify_tab.getSelectedItem();
				if(Double.valueOf(textField_modify_price.getText())<=0) {
					textField_modify_price.setText("");
					JOptionPane.showMessageDialog(null,  "商品单价应大于0，请重新输入");
				}
				double p=Double.valueOf(textField_modify_price.getText());
				BigDecimal temp=new BigDecimal(p);
				double price=temp.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				if(Integer.parseInt(textField_modify_remainNum.getText())<0||Integer.parseInt(textField_modify_remainNum.getText())!=Double.valueOf(textField_modify_remainNum.getText()).intValue()) {
					textField_modify_remainNum.setText("");
					JOptionPane.showMessageDialog(null,  "商品库存量应为正整数，请重新输入");
					return;
				}
				int remainNum=Integer.parseInt(textField_modify_remainNum.getText());
				
				boolean flag=new IShopAdminImpl(ShopView.this.sockethelper).changeProduct(id,name,supplier, price, remainNum, tab);
				if(flag) {
					JOptionPane.showMessageDialog(null, "修改成功！");
				}
				else {
					JOptionPane.showMessageDialog(null,"修改商品信息失败");
				}
			}
		});
		button_modifyProduct.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		panel_Modify.add(button_modifyProduct);
		
		JButton button_modify_inquirProduct = new JButton("商品查询");
		button_modify_inquirProduct.setBounds(459, 31, 103, 22);
		button_modify_inquirProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=textField_modify_ID.getText();
				if(id.equals("")){
					JOptionPane.showMessageDialog(null, "请输入商品ID");
					return;
				}
				
				List<GoodInfo> p=new IShopAdminImpl(ShopView.this.sockethelper).inquireProduct();
				int i =0;
				for(;i<p.size();i++) {
					GoodInfo tmp = p.get(i);
					if(tmp.getId() == Integer.valueOf(id)) {
						break;
					}
				}
				if(i==p.size()) {
					JOptionPane.showMessageDialog(null, "查询商品失败");
					return;
				}
				else {
					JOptionPane.showMessageDialog(null, "查询成功");
				}
				
				GoodInfo g=p.get(i);
				textField_modify_name.setText(g.getName());
				textField_modify_ID.setText(String.valueOf(g.getId()));
				int index = 0;
				if(g.getTag().equals("食")){
					index = 1;
				}
				else if(g.getTag().equals("住")){
					index = 2;
				}
				else if(g.getTag().equals("行")){
					index = 3;
				}
				else if(g.getTag().equals("其他")){
					index = 4;
				}
				comboBox_modify_tab.setSelectedIndex(index);
				textField_modify_supplier.setText(g.getSupplier());
				textField_modify_price.setText(String.valueOf(g.getPrice()));
				textField_modify_remainNum.setText(String.valueOf(g.getRemainNum()));
			}
		});
		button_modify_inquirProduct.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		panel_Modify.add(button_modify_inquirProduct);
	}
}
