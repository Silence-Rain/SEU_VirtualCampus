package seu.vCampus.view.admin;

import common.Student;
import common.StudentRollInfo;
import seu.vCampus.bz.IStudentImpl;
import seu.vCampus.util.SocketHelper;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class AddStu {
	

	
	  private JFrame frame;
	  private JFrame frame_main;
	  private SocketHelper sockethelper;
	  private JTextField textField_sID;
	  private JTextField textField_sAge;
	  private JTextField textField_sBirthdate;
	  private JTextField textField_sName;
	  private JTextField textField_sSex;
	  private JTextField textField_sPlace;
	  private JButton jb_ConfirmAdd;
	  private JTextField textField_sDepart;
	  private JTextField textField_sDormitory;

	  public AddStu(JFrame frame_main, SocketHelper sockethelper)
	  {
	    this.frame_main = frame_main;
	    this.sockethelper = sockethelper;
	    initialize();
	  }

	  private void initialize()
	  {
	    this.frame = new JFrame();
	    this.frame.getContentPane().setBackground(new Color(240,240,240));
	    this.frame.getContentPane().setLayout(null);
	    /*this.frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
	      "D:\\�����\\Java\\pic\\logo.jpg"));*/
	    this.frame.setTitle("添加学生");
	    this.frame.setBounds(100, 100, 600, 452);

	    JLabel lb_courseID = new JLabel("\u5B66\u53F7\uFF1A");
	    lb_courseID.setFont(new Font("微软雅黑", Font.PLAIN, 16));
	    lb_courseID.setForeground(Color.BLACK);
	    lb_courseID.setBounds(22, 47, 69, 14);
	    this.frame.getContentPane().add(lb_courseID);

	    JLabel lb_Name = new JLabel("\u5E74\u9F84\uFF1A");
	    lb_Name.setForeground(Color.BLACK);
	    lb_Name.setFont(new Font("微软雅黑", Font.PLAIN, 16));
	    lb_Name.setBounds(22, 114, 91, 26);
	    this.frame.getContentPane().add(lb_Name);

	    JLabel lb_Teacher = new JLabel("\u51FA\u751F\u65E5\u671F\uFF1A");
	    lb_Teacher.setForeground(Color.BLACK);
	    lb_Teacher.setFont(new Font("微软雅黑", Font.PLAIN, 16));
	    lb_Teacher.setBounds(22, 183, 91, 26);
	    this.frame.getContentPane().add(lb_Teacher);

	    JLabel lb_place = new JLabel("\u59D3\u540D\uFF1A");
	    lb_place.setForeground(Color.BLACK);
	    lb_place.setFont(new Font("微软雅黑", Font.PLAIN, 16));
	    lb_place.setBounds(308, 43, 104, 23);
	    this.frame.getContentPane().add(lb_place);

	    JLabel lb_Time = new JLabel("\u6027\u522B\uFF1A");
	    lb_Time.setForeground(Color.BLACK);
	    lb_Time.setFont(new Font("微软雅黑", Font.PLAIN, 16));
	    lb_Time.setBounds(308, 114, 104, 26);
	    this.frame.getContentPane().add(lb_Time);

	    JLabel lb_Credit = new JLabel("\u7C4D\u8D2F\uFF1A");
	    lb_Credit.setForeground(Color.BLACK);
	    lb_Credit.setFont(new Font("微软雅黑", Font.PLAIN, 16));
	    lb_Credit.setBounds(309, 183, 91, 26);
	    this.frame.getContentPane().add(lb_Credit);

	    this.textField_sID = new JTextField();
	    textField_sID.setFont(new Font("微软雅黑", Font.PLAIN, 14));
	    this.textField_sID.setBounds(112, 40, 164, 30);
	    this.frame.getContentPane().add(this.textField_sID);
	    this.textField_sID.setColumns(10);

	    this.textField_sAge = new JTextField();
	    textField_sAge.setFont(new Font("微软雅黑", Font.PLAIN, 14));
	    this.textField_sAge.setColumns(10);
	    this.textField_sAge.setBounds(112, 113, 162, 30);
	    this.frame.getContentPane().add(this.textField_sAge);

	    this.textField_sBirthdate = new JTextField();
	    textField_sBirthdate.setFont(new Font("微软雅黑", Font.PLAIN, 14));
	    this.textField_sBirthdate.setColumns(10);
	    this.textField_sBirthdate.setBounds(112, 182, 162, 30);
	    this.frame.getContentPane().add(this.textField_sBirthdate);

	    this.textField_sName = new JTextField();
	    textField_sName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
	    this.textField_sName.setColumns(10);
	    this.textField_sName.setBounds(412, 40, 162, 30);
	    this.frame.getContentPane().add(this.textField_sName);

	    this.textField_sSex = new JTextField();
	    textField_sSex.setFont(new Font("微软雅黑", Font.PLAIN, 14));
	    this.textField_sSex.setColumns(10);
	    this.textField_sSex.setBounds(412, 113, 162, 30);
	    this.frame.getContentPane().add(this.textField_sSex);

	    this.textField_sPlace = new JTextField();
	    textField_sPlace.setFont(new Font("微软雅黑", Font.PLAIN, 14));
	    this.textField_sPlace.setColumns(10);
	    this.textField_sPlace.setBounds(412, 182, 162, 30);
	    this.frame.getContentPane().add(this.textField_sPlace);

	    this.jb_ConfirmAdd = new JButton("\u786E\u8BA4\u6DFB\u52A0");
	    jb_ConfirmAdd.setFont(new Font("微软雅黑", Font.PLAIN, 14));
	    this.jb_ConfirmAdd.setBounds(66, 358, 120, 30);

	    this.frame.getContentPane().add(this.jb_ConfirmAdd);
	    
	    JButton jb_Cancel = new JButton("\u53D6\u6D88");
	    jb_Cancel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
	    jb_Cancel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dialogClose();
	    	}
	    });
	    jb_Cancel.setBounds(384, 358, 120, 30);
	    frame.getContentPane().add(jb_Cancel);
	    
	    JLabel label_Depart = new JLabel("\u5B66\u9662\uFF1A");
	    label_Depart.setForeground(Color.BLACK);
	    label_Depart.setFont(new Font("微软雅黑", Font.PLAIN, 16));
	    label_Depart.setBounds(22, 255, 91, 26);
	    frame.getContentPane().add(label_Depart);
	    
	    textField_sDepart = new JTextField();
	    textField_sDepart.setFont(new Font("微软雅黑", Font.PLAIN, 14));
	    textField_sDepart.setColumns(10);
	    textField_sDepart.setBounds(114, 254, 162, 30);
	    frame.getContentPane().add(textField_sDepart);
	    
	    textField_sDormitory = new JTextField();
	    textField_sDormitory.setFont(new Font("微软雅黑", Font.PLAIN, 14));
	    textField_sDormitory.setColumns(10);
	    textField_sDormitory.setBounds(412, 254, 162, 30);
	    frame.getContentPane().add(textField_sDormitory);
	    
	    JLabel label = new JLabel("\u5BBF\u820D\uFF1A");
	    label.setForeground(Color.BLACK);
	    label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
	    label.setBounds(308, 255, 91, 26);
	    frame.getContentPane().add(label);
	    this.jb_ConfirmAdd.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e) {
	        String sId = AddStu.this.textField_sID.getText();
	        String sAge = AddStu.this.textField_sAge.getText();
	        String sBirthdate = AddStu.this.textField_sBirthdate.getText();
	        String sName = AddStu.this.textField_sName.getText();
	        String sSex = AddStu.this.textField_sSex.getText();
	        String sPlace = AddStu.this.textField_sPlace.getText();
	        String sDepart = AddStu.this.textField_sDepart.getText();
	        String sDormitory = AddStu.this.textField_sDormitory.getText();
	        if ((sId.length() != 0) && (sName.length() != 0) && 
	          (sAge.length() != 0) && (sBirthdate.length() != 0) && 
	          (sSex.length() != 0) && (sPlace.length() != 0)&&(sDepart.length() != 0)&&(sDormitory.length() != 0)) {
	          StudentRollInfo s = new StudentRollInfo("","","","","","","","","","","","");
	          s.setId(sId);
	          s.setName(sName);
	          s.setGender(sSex);
	          s.setAge(sAge);
	          s.setBirthday(sBirthdate);
	          s.setDepartment(sDepart);
	          s.setDormitory(sDormitory);
	          s.setBirthPlace(sPlace);
	          s.setEntranceTime("");
	          s.setMajor("");
	          s.setNation("");
	          s.setPhoto("");
	          boolean isSave = new IStudentImpl(AddStu.this.sockethelper).AddStu(s);
	          
	          if (isSave)
	          {
	        	  JOptionPane.showMessageDialog(null, "保存成功！");
	        	  dialogClose();
	          }
	          else
	            JOptionPane.showMessageDialog(null, "保存失败！");
	        }
	        else {
	          JOptionPane.showMessageDialog(null, "还有空项未输入！");
	        }
	      }
	    });
	    this.frame.setVisible(true);
	    this.frame.addWindowListener(new WindowAdapter()
	    {
	      public void windowClosing(WindowEvent e) {
	        AddStu.this.backMainView();
	      }
	    });
	  }

	  protected void backMainView()
	  {
	    this.frame.dispose();
	    this.frame_main.setVisible(true);
	  }
	  protected void dialogClose(){
		  this.frame.dispose();
	  }
}
