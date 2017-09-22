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

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import seu.vCampus.bz.AbstractTableModelTest;
import seu.vCampus.bz.IBank;
import seu.vCampus.bz.IBankImpl;
import seu.vCampus.bz.IShopImpl;
import seu.vCampus.util.ColorIcon_MY;
import seu.vCampus.util.SetTableColor;
import seu.vCampus.util.SocketHelper;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import common.Bank;
import common.GoodInfo;
import common.OrderInfo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ShopView {
	private JFrame frame;
	private JTable table;
	private JTable table_2;
	private JTable table_cat;
	// private JTable table_shopcat;
	private JTable table_record;// tables
	private SocketHelper sockethelper;

	private JTextField byNameField;
	private JTextField textField_sum = new JTextField();

	private JComboBox bytagBox;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_record;
	private JScrollPane scrollPane_shopcat;
	List<GoodInfo> shoplist = new ArrayList();// 注意变量的初始化！！！
	List<GoodInfo> catlist = new ArrayList();// 购物车
	List<OrderInfo> orderlist = new ArrayList();// 订单！！！
	// GoodInfo orderlist = new GoodInfo(0, null, 0, 0, null, null);

	String totalnum = null;// 购买数量
	double sum = 0;// 总价格

	private JPopupMenu jpopupMenu;
	private JTextField textField;
	private JTextField textField_1;
	private String Stuid =null;

	public ShopView(mainView mview,SocketHelper sockethelper,String stuId ) {
		this.sockethelper = sockethelper;
		//sockethelper.getConnection();
		Stuid = stuId;
		initialize(mview);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize(mainView mview) {
		//frame = new JFrame();
		//frame.setVisible(true);
		
		// the shop part----------------------------------------------------
		JTabbedPane shoptab = new JTabbedPane(JTabbedPane.TOP);
		shoptab.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		shoptab.setBounds(0, 0, 785, 490);
		//frame.add(shoptab);
		mview.shopPanel.add(shoptab);
		// the buy buy buy
		// part-----------------------------------------------------------------
		JPanel buyPanel = new JPanel();
		buyPanel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		shoptab.addTab("\u5728\u7EBF\u8D2D\u4E70", new ImageIcon(BankView.class.getResource("/images/icon/buying.png")),
				buyPanel, null);
		buyPanel.setLayout(null);

		JTextPane textPane_5 = new JTextPane();
		textPane_5.setBackground(UIManager.getColor("Button.background"));
		textPane_5.setDisabledTextColor(UIManager.getColor("Button.background"));
		textPane_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_5.setText("\u6309\u5546\u54C1\u540D\u79F0\uFF1A");
		textPane_5.setBounds(33, 32, 90, 24);
		buyPanel.add(textPane_5);

		JTextPane textPane_8 = new JTextPane();
		textPane_8.setBackground(UIManager.getColor("Button.background"));
		textPane_8.setDisabledTextColor(UIManager.getColor("Button.background"));
		textPane_8.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_8.setText("\u6309\u6807\u7B7E\uFF1A");
		textPane_8.setBounds(407, 32, 70, 24);
		buyPanel.add(textPane_8);

		JButton button_check1 = new JButton("");
		// button_check1.setBorderPainted(false);
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/images/shopView/checkgood_btn.png"));
		button_check1.setContentAreaFilled(false);
		button_check1.setIcon(icon1);
		button_check1.setMargin(new Insets(0, 0, 0, 0));
		button_check1.addActionListener(new ActionListener() { /////////////////////////// check
																/////////////////////////// by
																/////////////////////////// name
			public void actionPerformed(ActionEvent e) {
				
				String name = ShopView.this.byNameField.getText();
				if(name.equals(""))
					{
					JOptionPane.showMessageDialog(null, "亲，请输入查询内容！");
					return;
					}

					
				scrollPane_2.setViewportView(getShopCheckTableByName(name));
			}
		});
		button_check1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_check1.setBounds(278, 25, 42, 37);
		buyPanel.add(button_check1);
		Object[][] cellData = { { "row1-col1", "row1-col2" }, { "row2-col1", "row2-col2" } };
		String[] columnNames = { "col1", "col2" };

		ImageIcon icon123 = new ImageIcon(getClass().getResource("/images/shopView/123.png"));
		JButton button_5 = new JButton("");
		button_5.setForeground(SystemColor.textHighlight);
		button_5.setIcon(icon123);

		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = (String) ShopView.this.table.getValueAt(ShopView.this.table.getSelectedRow(), 0);
				int remainNum = ((Integer) ShopView.this.table.getValueAt(ShopView.this.table.getSelectedRow(), 3));
				double price = ((Double) ShopView.this.table.getValueAt(ShopView.this.table.getSelectedRow(), 4));
				String supplier = (String) ShopView.this.table.getValueAt(ShopView.this.table.getSelectedRow(), 1);
				String tag = (String) ShopView.this.table.getValueAt(ShopView.this.table.getSelectedRow(), 2);
				GoodInfo good = new GoodInfo(0, name, remainNum, price, supplier, tag);
				catlist.add(good);
				// System.out.println(catlist.size());
				JOptionPane.showMessageDialog(null, "亲，添加成功！");
			}
		});
		button_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_5.setBounds(496, 319, 126, 32);
		buyPanel.add(button_5);

		bytagBox = new JComboBox();
		bytagBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		bytagBox.setModel(
				new DefaultComboBoxModel(new String[] { "\u8863", "\u98DF", "\u4F4F", "\u884C", "\u5176\u4ED6" }));
		bytagBox.setBounds(489, 32, 133, 28);
		buyPanel.add(bytagBox);

		scrollPane_2 = new JScrollPane();// the first page
		scrollPane_2.setBounds(109, 72, 500, 237);
		buyPanel.add(scrollPane_2);

		JButton button_check2 = new JButton("");
		ImageIcon icon2 = new ImageIcon(getClass().getResource("/images/shopView/checkgood_btn.png"));
		button_check2.setContentAreaFilled(false);
		button_check2.setIcon(icon2);
		button_check2.setMargin(new Insets(0, 0, 0, 0));

		button_check2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { ////////////////// check
															////////////////// by
															////////////////// tag
				String tag = ShopView.this.bytagBox.getSelectedItem().toString();
				
				scrollPane_2.setViewportView(getShopCheckTableByTag(tag));
			}
		});
		button_check2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_check2.setBackground(UIManager.getColor("Button.background"));
		button_check2.setBounds(637, 25, 42, 34);
		buyPanel.add(button_check2);

		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBounds(133, 32, 130, 28);
		buyPanel.add(layeredPane_1);
		// buyPanel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(0, 0, 130, 26);
		layeredPane_1.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(2, -1, 126, 30);
		layeredPane_1.add(lblNewLabel);
		layeredPane_1.setLayer(lblNewLabel, 1);
		lblNewLabel.setIcon(new ImageIcon(ShopView.class.getResource("/images/shopView/taobao.png")));
		lblNewLabel.setBackground(new Color(255, 255, 255));

		byNameField = new JTextField();
		byNameField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		byNameField.setBounds(25, 2, 102, 22);
		layeredPane_1.add(byNameField);
		layeredPane_1.setLayer(byNameField, 2);
		byNameField.setBorder(null);
		byNameField.setColumns(10);
		// the shopcat
		// part---------------------------------------------------------------------------------------------
		JPanel shopcatPanel = new JPanel();
		shopcatPanel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		shoptab.addTab("\u8D2D\u7269\u8F66", new ImageIcon(BankView.class.getResource("/images/icon/shopcat.png")),
				shopcatPanel, null);
		shopcatPanel.setLayout(null);
		textField_sum.setBorder(null);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 770, 434);
		shopcatPanel.add(layeredPane);

		scrollPane_shopcat = new JScrollPane();
		scrollPane_shopcat.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		scrollPane_shopcat.setBounds(73, 56, 493, 304);
		layeredPane.add(scrollPane_shopcat);

		// table_shopcat = new JTable();
		// table_shopcat.setRowHeight(25);

		System.out.println("进入了第二个界面");
		scrollPane_shopcat.setViewportView(getShopCatTable(catlist.size()));

		// table_shopcat.setBackground(UIManager.getColor("Button.background"));

		// private JTable table_cancle;
		ImageIcon icon_calcu = new ImageIcon(getClass().getResource("/images/shopView/calculate.png"));
		JButton button_calcu = new JButton("");
		button_calcu.setIcon(icon_calcu);
		button_calcu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {////////////// 结算////////////////
				boolean flag = false;
				int id = 0;

				for (int i = 0; i < catlist.size(); i++) {
					System.out.println("是否选择：？" + (Boolean) table_cat.getValueAt(i, 0));
					if ((Boolean) table_cat.getValueAt(i, 0)) {
						id++;
						totalnum = String.valueOf(table_cat.getValueAt(i, 6));// ()内加的是自己本身的数据类型！！！
						System.out.println("购买数量：" + totalnum);
						if (Integer.valueOf(totalnum) <= catlist.get(i).getRemainNum()) {
							int n = Integer.valueOf(totalnum);
							OrderInfo order = new OrderInfo(0, catlist.get(i).getName(), Stuid, n,
									System.currentTimeMillis() / 1000);
							orderlist.add(order);
							// System.out.println("flag is"+flag);
							sum += n * (double) table_cat.getValueAt(i, 5);
						} else {
							JOptionPane.showMessageDialog(null, "亲，库存不足哦！");
						}
					}
				}
				textField_sum.setText(String.valueOf(sum));
				System.out.println("总价值：" + sum);
				if (id == 0)
					JOptionPane.showMessageDialog(null, "亲，请选择要购买的商品哦！");

				final int size = orderlist.size();
				OrderInfo[] arr = new OrderInfo[size];
				for (int i = 0; i < orderlist.size(); i++) {
					arr[i] = orderlist.get(i);
					System.out.println(arr[i].getName());
				}
				System.out.println("old flag is " + flag);
				flag = new IShopImpl(sockethelper).addOrder(arr);
				System.out.println("new flag is " + flag);
				if (flag) {
					// textField_sum.setText(String.valueOf(sum));
					JOptionPane.showMessageDialog(null, "亲，购买成功！");
				} else {
					JOptionPane.showMessageDialog(null, "亲，请重新购买~");
				}
			}
		});
		button_calcu.setBounds(415, 370, 130, 36);
		layeredPane.add(button_calcu);
		button_calcu.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		JTextPane textPane_9 = new JTextPane();
		textPane_9.setBounds(73, 377, 48, 24);
		layeredPane.add(textPane_9);
		textPane_9.setBackground(UIManager.getColor("Button.background"));
		textPane_9.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textPane_9.setText("\u5408\u8BA1\uFF1A");

		JButton btnNewButton = new JButton("");
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setIcon(new ImageIcon(ShopView.class.getResource("/images/btn/refresh.png")));
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("进入了第二个界面");
				scrollPane_shopcat.setViewportView(getShopCatTable(catlist.size()));
			}
		});
		btnNewButton.setBounds(73, 10, 73, 36);
		layeredPane.add(btnNewButton);

		JLayeredPane layeredPane_2 = new JLayeredPane();
		layeredPane_2.setBounds(131, 374, 140, 30);
		layeredPane.add(layeredPane_2);

		ImageIcon icon4 = new ImageIcon(getClass().getResource("/images/shopView/rmb.png"));
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(icon4);
		layeredPane_2.setLayer(lblNewLabel_3, 1);
		lblNewLabel_3.setBounds(8, 3, 130, 27);
		layeredPane_2.add(lblNewLabel_3);
		layeredPane_2.setLayer(textField_sum, 3);
		textField_sum.setBounds(32, 2, 104, 26);
		layeredPane_2.add(textField_sum);
		textField_sum.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(1, 1, 138, 28);
		layeredPane_2.add(textField_1);
		textField_1.setColumns(10);

		// 需要一个JPopupMenu 来添加右键菜单的内容
		jpopupMenu = new JPopupMenu();
		JMenuItem downMenuItem = new JMenuItem();
		// 设置菜单的名字
		downMenuItem.setText("删除");
		jpopupMenu.add(downMenuItem);
		downMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// 你点击下载操作所 需要做的事
				System.out.print("选中的行是：" + table_cat.getSelectedRow());
				table_cat.remove(table_cat.getSelectedRow());// row is 行
				for (int i = 0; i < catlist.size(); i++) {
					if (ShopView.this.table_cat.getValueAt(ShopView.this.table_cat.getSelectedRow(), 1).// name
					equals(catlist.get(i).getName()))
						catlist.remove(i);
				}
			}
		});
		jpopupMenu.add(downMenuItem); // 添加进去

		table_cat.addMouseListener(new MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
					// 通过点击位置找到点击为表格中的行
					int focusedRowIndex = table_cat.rowAtPoint(evt.getPoint());
					if (focusedRowIndex == -1) {
						return;
					}
					// 将表格所选项设为当前右键点击的行
					table_cat.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
					jpopupMenu.show(table_cat, evt.getX(), evt.getY());
				}
			}
		});

		// the check shop
		// part-----------------------------------------------------------------------------------
		JPanel checkshopPanel = new JPanel();
		checkshopPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		checkshopPanel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		shoptab.addTab("我的订单", new ImageIcon(BankView.class.getResource("/images/icon/lookfor.png")), checkshopPanel,
				null);
		checkshopPanel.setLayout(null);

		JButton button_7 = new JButton("");
		button_7.setContentAreaFilled(false);
		button_7.setIcon(new ImageIcon(ShopView.class.getResource("/images/btn/check.png")));
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane_record.setViewportView(getShopRecordTable());
			}
		});
		button_7.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_7.setBounds(56, 24, 69, 34);
		checkshopPanel.add(button_7);

		scrollPane_record = new JScrollPane();
		scrollPane_record.setBounds(56, 68, 606, 290);
		checkshopPanel.add(scrollPane_record);
	}

	private JTable getShopCheckTableByName(String name) {
		// 检查表格是否为空.
		table = new JTable(); // 创建表格控件.
		table.setRowHeight(25); // 设置行高.
		String[] columns = { "商品名称", "生产商", "标签", "库存/件", "单价/元" }; // 创建列名数组.
		// 创建表格模型.
		table.getTableHeader().setFont(new Font("Microsoft YaHei UI", 0, 14));//设置表头字体
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		table.setModel(model); // 设置表格模型.
		System.out.println("shop 1");
		List<GoodInfo> all_list = new IShopImpl(this.sockethelper).checkGoods();
		System.out.println("shop 2");
		for (int j = 0; j < all_list.size(); j++) {
			GoodInfo list = all_list.get(j);
			if (list.getName().equals(name)) {
				System.out.println("有可乐");
				shoplist.add(list);
				System.out.println("进来了");
				Object[] rowData = { list.getName(), list.getSupplier(), list.getTag(), list.getRemainNum(),
						list.getPrice(), false };// socket返回值
				model.addRow(rowData);
			}

		}
		table.setEnabled(true);
		SetTableColor.makeFace(table);
		return table;
	}

	private JTable getShopCheckTableByTag(String tag) {
		// 检查表格是否为空.
		table = new JTable(); // 创建表格控件.
		table.setRowHeight(25); // 设置行高.
		String[] columns = { "商品名称", "生产商", "标签", "库存/件", "单价/元" }; // 创建列名数组.
		// 创建表格模型.
		table.getTableHeader().setFont(new Font("Microsoft YaHei UI", 0, 14));//设置表头字体
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		table.setModel(model); // 设置表格模型.
		List<GoodInfo> all_list = new IShopImpl(ShopView.this.sockethelper).checkGoods();
		for (int j = 0; j < all_list.size(); j++) {
			GoodInfo list = all_list.get(j);
			if (list.getTag().equals(tag)) {
				shoplist.add(list);
				Object[] rowData = { list.getName(), list.getSupplier(), list.getTag(), list.getRemainNum(),
						list.getPrice(), Boolean.FALSE };// socket返回值
				model.addRow(rowData);
			}
		}
		table.setEnabled(true);
		SetTableColor.makeFace(table);
		return table;
	}

	private JTable getShopCatTable(int n) {
		// 检查表格是否为空.

		table_cat = new JTable(); // 创建表格控件.
		table_cat.setRowHeight(25); // 设置行高.
		String[] columns = { "选择", "商品名称", "生产商", "标签", "库存/件", "单价/元", "购买数量" }; // 创建列名数组.
		// 创建表格模型.
		table_cat.getTableHeader().setFont(new Font("Microsoft YaHei UI", 0, 14));//设置表头字体
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		table_cat.setModel(model); // 设置表格模型.
		System.out.println("进入购物车");
		System.out.println(n);

		// TableModel tcm = (TableModel) table_cat.getColumnModel();
		// ((TableColumnModel) tcm).getColumn(0).setHeaderRenderer(new
		// DefaultCellEditor(new JCheckBox()));
		TableColumn aColumn = table_cat.getColumnModel().getColumn(0);
		aColumn.setCellEditor(table_cat.getDefaultEditor(Boolean.class));
		aColumn.setCellRenderer(table_cat.getDefaultRenderer(Boolean.class));// 复选框的出现！！！

		for (int i = 0; i < n; i++) {
			// System.out.println("购物车显示");
			GoodInfo list = catlist.get(i);
			Object[] rowData = { Boolean.FALSE, list.getName(), list.getSupplier(), list.getTag(), list.getRemainNum(),
					list.getPrice(), 1 };// socket返回值
			model.addRow(rowData);
		}
		// table_cat.getColumnModel().getColumn(1).setEnabled(true);
		// table_cat.getColumnModel().getColumn(2).setEnabled(false);
		// table_cat.getColumnModel().getColumn(3).setEnabled(false);
		// table_cat.getColumnModel().getColumn(4).setEnabled(false);
		// table_cat.getColumnModel().getColumn(5).setEnabled(false);
		table_cat.getColumnModel().getColumn(1).setResizable(false);
		table_cat.getColumnModel().getColumn(2).setResizable(false);
		table_cat.getColumnModel().getColumn(3).setResizable(false);
		table_cat.getColumnModel().getColumn(4).setResizable(false);
		table_cat.getColumnModel().getColumn(5).setResizable(false);
		table_cat.getColumnModel().getColumn(6).setResizable(false);
		table_cat.addMouseListener(new MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
					// 通过点击位置找到点击为表格中的行
					int focusedRowIndex = table_cat.rowAtPoint(evt.getPoint());
					if (focusedRowIndex == -1) {
						return;
					}
					// 将表格所选项设为当前右键点击的行
					table_cat.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
					jpopupMenu.show(table_cat, evt.getX(), evt.getY());
				}
			}
		});
		SetTableColor.makeFace(table_cat);
		return table_cat;
	}
	private JTable getShopRecordTable() {
		// 检查表格是否为空.
		table_record = new JTable(); // 创建表格控件.
		table_record.setRowHeight(25); // 设置行高.
		String[] columns = { "序号", "商品名称", "购买人", "数量/件", "购买时间" }; // 创建列名数组.
		// 创建表格模型.
		table_record.getTableHeader().setFont(new Font("Microsoft YaHei UI", 0, 14));//设置表头字体
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		table_record.setModel(model); // 设置表格模型.
		List<OrderInfo> list = new IShopImpl(ShopView.this.sockethelper).record(Stuid);
		for (int i = 0; i < list.size(); i++) {
			OrderInfo List = list.get(i);
			Long time = List.getBuyTime() * 1000;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date(time);
			String rs = simpleDateFormat.format(date);
			Object[] rowData = { i + 1, List.getName(), List.getBuyer(), List.getBuyNum(), rs };// socket返回值
			model.addRow(rowData);
		}
		SetTableColor.makeFace(table_record);
		table_record.setEnabled(false);
		
		return table_record;
	}
}
