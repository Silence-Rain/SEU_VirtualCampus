package seu.vCampus.common;
import java.io.Serializable;
public class Course {
	private static final long serialVersionID = 1L;
	private String courseID;//这门课ID
	private String courseName;//这门课名称
	private String courseTeacher;//这门课授课老师
	private String coursePlace;//这门课上课地点
	private String courseTime;//这门课上课的时间
	private double courseCredit;//这么课的成绩
	private boolean courseStatus;//true这门课被选，false这门课未被选
	int courseMaxNum;//这门课能选的最大人数
	public Course(String courseID,String courseName,String courseTeacher,String coursePlace,String courseTime,double courseCredit,boolean courseStatus,int courseMaxNum){
		setCourseID(courseID);
		setCourseName(courseName);
		setCourseTeacher(courseTeacher);
		setCoursePlace(coursePlace);
		setCourseTime(courseTime);
		setCourseCredit(courseCredit);
		setCourseStatus(courseStatus);
		setCourseMaxNum(courseMaxNum);	
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCoureseTeacher() {
		return courseTeacher;
	}
	public void setCourseTeacher(String coureseTeacher) {
		this.courseTeacher = coureseTeacher;
	}
	public String getCoursePlace() {
		return coursePlace;
	}
	public void setCoursePlace(String coursePlace) {
		this.coursePlace = coursePlace;
	}
	public String getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}
	public double getCourseCredit() {
		return courseCredit;
	}
	public void setCourseCredit(double courseCredit) {
		this.courseCredit = courseCredit;
	}
	public boolean isCourseStatus() {
		return courseStatus;
	}
	public void setCourseStatus(boolean courseStatus) {
		this.courseStatus = courseStatus;
	}
	public int getCourseMaxNum() {
		return courseMaxNum;
	}
	public void setCourseMaxNum(int courseMaxNum) {
		this.courseMaxNum = courseMaxNum;
	}
	
	
	
	
	
}
