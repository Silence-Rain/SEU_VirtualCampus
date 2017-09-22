package seu.vCampus.view.admin;
import common.CourseInfo;
import seu.vCampus.bz.ISelectCourseImpl;
import seu.vCampus.util.SocketHelper;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ModifyCourse
{
  private JFrame frame;
  private JFrame frame_main;
  private SocketHelper sockethelper;
  private JTextField textField_ID;
  private JTextField textField_Name;
  private JTextField textField_Teacher;
  private JTextField textField_Place;
  private JTextField textField_Time;
  private JTextField textField_Credit;
  private JButton jb_ConfirmModify;
  private CourseInfo course;
  List courseInfo = null;
  public ModifyCourse(JFrame frame_main, CourseInfo course, SocketHelper sockethelper)
  {
    this.frame_main = frame_main;
    this.course = course;
    this.sockethelper = sockethelper;
    //List courseInfo = new ISelectCourseImpl(this.sockethelper).EnquiryCourseById(cId);
    initialize();
  }

  private void initialize()
  {
    this.frame = new JFrame();
    frame.getContentPane().setFont(new Font("微软雅黑", Font.PLAIN, 16));
    this.frame.getContentPane().setBackground(new Color(240,240,240));
    this.frame.getContentPane().setLayout(null);
    /*this.frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
      "D:\\�����\\Java\\pic\\logo.jpg"));*/
    this.frame.setTitle("修改课程信息");
    this.frame.setBounds(100, 100, 600, 452);

    JLabel lb_courseID = new JLabel("\u8BFE\u7A0BID\uFF1A");
    lb_courseID.setFont(new Font("微软雅黑", Font.PLAIN, 16));
    lb_courseID.setForeground(Color.BLACK);
    lb_courseID.setBounds(22, 47, 69, 14);
    this.frame.getContentPane().add(lb_courseID);

    JLabel lb_Name = new JLabel("\u8BFE\u7A0B\u540D\u79F0\uFF1A");
    lb_Name.setForeground(Color.BLACK);
    lb_Name.setFont(new Font("微软雅黑", Font.PLAIN, 16));
    lb_Name.setBounds(22, 151, 91, 26);
    this.frame.getContentPane().add(lb_Name);

    JLabel lb_Teacher = new JLabel("\u6388\u8BFE\u6559\u5E08\uFF1A");
    lb_Teacher.setForeground(Color.BLACK);
    lb_Teacher.setFont(new Font("微软雅黑", Font.PLAIN, 16));
    lb_Teacher.setBounds(22, 277, 91, 26);
    this.frame.getContentPane().add(lb_Teacher);

    JLabel lb_place = new JLabel("\u4E0A\u8BFE\u5730\u70B9\uFF1A");
    lb_place.setForeground(Color.BLACK);
    lb_place.setFont(new Font("微软雅黑", Font.PLAIN, 16));
    lb_place.setBounds(308, 43, 104, 23);
    this.frame.getContentPane().add(lb_place);

    JLabel lb_Time = new JLabel("\u4E0A\u8BFE\u65F6\u95F4\uFF1A");
    lb_Time.setForeground(Color.BLACK);
    lb_Time.setFont(new Font("微软雅黑", Font.PLAIN, 16));
    lb_Time.setBounds(308, 151, 104, 26);
    this.frame.getContentPane().add(lb_Time);

    JLabel lb_Credit = new JLabel("\u8BFE\u7A0B\u5B66\u5206\uFF1A");
    lb_Credit.setForeground(Color.BLACK);
    lb_Credit.setFont(new Font("微软雅黑", Font.PLAIN, 16));
    lb_Credit.setBounds(308, 277, 91, 26);
    this.frame.getContentPane().add(lb_Credit);

    this.textField_ID = new JTextField();
    textField_ID.setFont(new Font("微软雅黑", Font.PLAIN, 14));
    this.textField_ID.setBounds(112, 40, 164, 30);
    this.frame.getContentPane().add(this.textField_ID);
    this.textField_ID.setColumns(10);

    this.textField_Name = new JTextField();
    textField_Name.setFont(new Font("微软雅黑", Font.PLAIN, 14));
    this.textField_Name.setColumns(10);
    this.textField_Name.setBounds(112, 151, 162, 30);
    this.frame.getContentPane().add(this.textField_Name);

    this.textField_Teacher = new JTextField();
    textField_Teacher.setFont(new Font("微软雅黑", Font.PLAIN, 14));
    this.textField_Teacher.setColumns(10);
    this.textField_Teacher.setBounds(112, 277, 162, 30);
    this.frame.getContentPane().add(this.textField_Teacher);

    this.textField_Place = new JTextField();
    textField_Place.setFont(new Font("微软雅黑", Font.PLAIN, 14));
    this.textField_Place.setColumns(10);
    this.textField_Place.setBounds(412, 40, 162, 30);
    this.frame.getContentPane().add(this.textField_Place);

    this.textField_Time = new JTextField();
    textField_Time.setFont(new Font("微软雅黑", Font.PLAIN, 14));
    this.textField_Time.setColumns(10);
    this.textField_Time.setBounds(412, 150, 162, 30);
    this.frame.getContentPane().add(this.textField_Time);

    this.textField_Credit = new JTextField();
    textField_Credit.setFont(new Font("微软雅黑", Font.PLAIN, 14));
    this.textField_Credit.setColumns(10);
    this.textField_Credit.setBounds(412, 276, 162, 30);
    this.frame.getContentPane().add(this.textField_Credit);

    this.jb_ConfirmModify = new JButton("\u786E\u8BA4\u4FEE\u6539");
    jb_ConfirmModify.setFont(new Font("微软雅黑", Font.PLAIN, 14));
    this.jb_ConfirmModify.setBounds(66, 358, 120, 30);

    this.frame.getContentPane().add(this.jb_ConfirmModify);
    
  
    
    
    
    
    JButton jb_Cancel = new JButton("\u53D6\u6D88");
    jb_Cancel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
    jb_Cancel.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		dialogClose();
    	}
    });
    jb_Cancel.setBounds(384, 358, 120, 30);
    frame.getContentPane().add(jb_Cancel);
    showCourseInfo();
    this.jb_ConfirmModify.addActionListener(new ActionListener()
    {
    	
      public void actionPerformed(ActionEvent e) {
    	
        String cId = ModifyCourse.this.textField_ID.getText();
        String cName = ModifyCourse.this.textField_Name.getText();
        String cTeacher = ModifyCourse.this.textField_Teacher.getText();
        String cPlace = ModifyCourse.this.textField_Place.getText();
        String cTime = ModifyCourse.this.textField_Time.getText();
        String cCredit = ModifyCourse.this.textField_Credit.getText();
        if ((cId.length() != 0) && (cName.length() != 0) && 
          (cTeacher.length() != 0) && (cPlace.length() != 0) && 
          (cTime.length() != 0) && (cCredit.length() != 0)) {
          CourseInfo s = new CourseInfo("","","","","",0);
          s.setId(cId);
          s.setName(cName);
          s.setTeacher(cTeacher);
          s.setPlace(cPlace);
          s.setTime(cTime);
          s.setCredit(Double.parseDouble(cCredit));
          boolean isModified = new ISelectCourseImpl(ModifyCourse.this.sockethelper).modifyCourse(s);
           
          if (isModified)
            {
        	  JOptionPane.showMessageDialog(null, "修改成功！");
        	  dialogClose();
            }
          else
            JOptionPane.showMessageDialog(null, "修改失败！");
        }
        else {
          JOptionPane.showMessageDialog(null, "还有空白项未输入！");
        }
      }
    });
    this.frame.setVisible(true);
    this.frame.addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent e) {
        ModifyCourse.this.backMainView();
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
  protected void showCourseInfo(){
	  System.out.println("showCourseInfo");
	  this.textField_ID.setText(course.getId());
	  this.textField_Name.setText(course.getName());
	  this.textField_Teacher.setText(course.getTeacher());
	  this.textField_Place.setText(course.getPlace());
	  this.textField_Time.setText(course.getTime());
	  this.textField_Credit.setText(String.valueOf(course.getCredit()) );
	  
	  
  }
}