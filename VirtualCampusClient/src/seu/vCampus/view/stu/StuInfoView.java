package seu.vCampus.view.stu;

import java.awt.EventQueue;

import com.sun.awt.AWTUtilities;

import common.StudentRollInfo;
import seu.vCampus.bz.IStudentImpl;
import seu.vCampus.util.SocketHelper;

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
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;

import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class StuInfoView {
	private JFrame frame;

	private JTable table;

	private JPanel panel;

	public JPanel librPanel;
	public JPanel shopPanel;
	public JPanel orderPanel;
	public JPanel bankPanel;
	public JPanel classPanel;
	JTextField textField_sSex = new JTextField();
	private JTextField textField_sBirthdate;
	private JTextField textField_sPlace;
	private JTextField textField_sDepart;
	private JTextField textField_sDormitory;
	private JTextField textField_sName;
	private JTextField textField_sID;
	private JTextField textField_sAge;
	String id = null;
	List stuList = null;
	JButton jb_uploadIcon = new JButton("");

	private SocketHelper sockethelper;

	/**
	 * Create the application.
	 */
	public StuInfoView(mainView mView, SocketHelper sockethelper,String stuId) {
		// this.ibank = ibank;
		this.sockethelper = sockethelper;
		id = stuId;
		initialize(mView);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize(mainView mview) {
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		tabbedPane_1.setBounds(0, 0,785, 490);
		mview.stuPanel.add(tabbedPane_1);

		//
		// JPanel titlepanel = new JPanel();
		// titlepanel.setBounds(100, 100,744, 50);
		// titlepanel.setLayout(null);
		// frame.getContentPane().add(titlepanel, "cell 0 0,growx,aligny
		// bottom");
		//
		//// JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		// frame.getContentPane().add(tabbedPane, "cell 0 1,grow");
		// tabbedPane.setFont(new Font("YouYuan", 1, 14));

		// the information for student part

		// JPanel infoPanel = new JPanel();
		// infoPanel.setBackground(Color.WHITE);
		// infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		// infoPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		// infoPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		// tabbedPane.addTab("\u5b66\u7c4d\u7ba1\u7406",
		// null, infoPanel,
		// null);
		// tabbedPane.setBackgroundAt(0,
		// UIManager.getColor("Button.background"));
		// infoPanel.setLayout(new CardLayout(0, 0));

		JPanel panel = new JPanel();
		tabbedPane_1.addTab("\u8be6\u7ec6\u4fe1\u606f",
				new ImageIcon(StuInfoView.class.getResource("/images/icon/info.png")), panel, null);

		panel.setLayout(null);
		JTextPane textPane_IDSearch = new JTextPane();
		textPane_IDSearch.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textPane_IDSearch.setBackground(UIManager.getColor("Button.background"));
		textPane_IDSearch.setText("\u6027\u522B\uFF1A");
		textPane_IDSearch.setBounds(37, 254, 77, 27);
		panel.add(textPane_IDSearch);
		textField_sSex.setFont(new Font("微软雅黑", Font.PLAIN, 16));

		textField_sSex.setBounds(171, 254, 172, 27);
		panel.add(textField_sSex);
		textField_sSex.setColumns(10);

		JTextPane textPane = new JTextPane();
		textPane.setText("\u7C4D\u8D2F\uFF1A");
		textPane.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textPane.setBackground(SystemColor.menu);
		textPane.setBounds(37, 328, 77, 27);
		panel.add(textPane);

		textField_sBirthdate = new JTextField();
		textField_sBirthdate.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_sBirthdate.setColumns(10);
		textField_sBirthdate.setBounds(171, 291, 172, 27);
		panel.add(textField_sBirthdate);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("\u51FA\u751F\u65E5\u671F\uFF1A");
		textPane_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textPane_1.setBackground(SystemColor.menu);
		textPane_1.setBounds(37, 291, 91, 27);
		panel.add(textPane_1);

		textField_sPlace = new JTextField();
		textField_sPlace.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_sPlace.setColumns(10);
		textField_sPlace.setBounds(171, 328, 172, 27);
		panel.add(textField_sPlace);

		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("\u5B66\u9662\uFF1A");
		textPane_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textPane_2.setBackground(SystemColor.menu);
		textPane_2.setBounds(386, 254, 54, 27);
		panel.add(textPane_2);

		textField_sDepart = new JTextField();
		textField_sDepart.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_sDepart.setColumns(10);
		textField_sDepart.setBounds(473, 254, 172, 27);
		panel.add(textField_sDepart);
		jb_uploadIcon.setBackground(UIManager.getColor("Button.disabledShadow"));

		
		jb_uploadIcon.setFont(new Font("��Բ", Font.PLAIN, 16));
		jb_uploadIcon.setBounds(106, 32, 152, 135);
		panel.add(jb_uploadIcon);
		ImageIcon icon_01  = new ImageIcon(getClass().getResource("/images/icon/touxiang.png"));
		jb_uploadIcon.setIcon(icon_01);

		textField_sDormitory = new JTextField();
		textField_sDormitory.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_sDormitory.setColumns(10);
		textField_sDormitory.setBounds(473, 291, 172, 27);
		panel.add(textField_sDormitory);

		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText("\u5BBF\u820D\uFF1A");
		textPane_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textPane_3.setBackground(SystemColor.menu);
		textPane_3.setBounds(386, 291, 77, 27);
		panel.add(textPane_3);

		textField_sName = new JTextField();
		textField_sName.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_sName.setColumns(10);
		textField_sName.setBounds(444, 80, 172, 27);
		panel.add(textField_sName);

		JTextPane textPane_4 = new JTextPane();
		textPane_4.setText("\u59D3\u540D\uFF1A");
		textPane_4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textPane_4.setBackground(SystemColor.menu);
		textPane_4.setBounds(357, 80, 77, 27);
		panel.add(textPane_4);

		textField_sID = new JTextField();
		textField_sID.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_sID.setColumns(10);
		textField_sID.setBounds(444, 43, 172, 27);
		panel.add(textField_sID);

		JTextPane textPane_ID = new JTextPane();
		textPane_ID.setText("\u5B66\u53F7\uFF1A");
		textPane_ID.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textPane_ID.setBackground(SystemColor.menu);
		textPane_ID.setBounds(357, 43, 77, 27);
		panel.add(textPane_ID);

		textField_sAge = new JTextField();
		textField_sAge.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_sAge.setColumns(10);
		textField_sAge.setBounds(444, 117, 172, 27);
		panel.add(textField_sAge);

		JTextPane textPane_6 = new JTextPane();
		textPane_6.setText("\u5E74\u9F84\uFF1A");
		textPane_6.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textPane_6.setBackground(SystemColor.menu);
		textPane_6.setBounds(357, 117, 77, 27);
		panel.add(textPane_6);

		showStuInfo();

		AdminEvent();

	}

	protected void AdminEvent() {

		jb_uploadIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser open = new JFileChooser();
				open.setDialogTitle("ѡ��Ҫ�ϴ���ͼƬ");
				open.setCurrentDirectory(new File("D:"));
				String[] opentype = { "jpg" };
				open.setFileFilter(new FileNameExtensionFilter("JPG", opentype));// �������˿���ѡ�񴰿�����Щ��׺���ļ�
				int index = open.showDialog(null, "��");
				if (index == 0) {
					File imagef = open.getSelectedFile();
					String NewPictureFileName = imagef.getPath();

					FileInputStream fin = null;
					try {
						fin = new FileInputStream(new File(NewPictureFileName));// ���ļ�ϵͳ�е�ĳ���ļ��л�������ֽڡ�
						jb_uploadIcon.setIcon(new ImageIcon(NewPictureFileName));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

	}

	protected void showStuInfo() {
		// StudentRollInfo stu = new
		// StudentRollInfo("","","","","","","","","","","","");
		
		StudentRollInfo stu = new StudentRollInfo(id, "", "", "", "", "", "", "", "", "", "", "");
		System.out.println(stu.getId());
		List<StudentRollInfo> stuList = new IStudentImpl(StuInfoView.this.sockethelper).EnquiryStuById(stu);
		int i = 0;
		for(;i<stuList.size();i++){
			StudentRollInfo tmp = stuList.get(i);
			if(tmp.getId().equals(id)){
				break;
			}
		}
	
		StudentRollInfo tmpStu = stuList.get(i);
		this.textField_sID.setText(tmpStu.getId());
		this.textField_sID.setEditable(false);
		this.textField_sName.setText(tmpStu.getName());
		this.textField_sName.setEditable(false);
		this.textField_sAge.setText(tmpStu.getAge());
		this.textField_sAge.setEditable(false);
		this.textField_sSex.setText(tmpStu.getGender());
		this.textField_sSex.setEditable(false);
		this.textField_sBirthdate.setText(tmpStu.getBirthday());
		this.textField_sBirthdate.setEditable(false);
		this.textField_sPlace.setText(tmpStu.getBirthPlace());
		this.textField_sPlace.setEditable(false);
		this.textField_sDepart.setText(tmpStu.getDepartment());
		this.textField_sDepart.setEditable(false);
		this.textField_sDormitory.setText(tmpStu.getDormitory());
		this.textField_sDormitory.setEditable(false);

	}
}
