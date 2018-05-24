package common;

import java.io.Serializable;

/**
 * 课程信息
 * （即tbCourse表的结构）
 * 
 * @author Silence
 *
 */
public class CourseInfo implements Serializable {

	private static final long serialVersionUID = 1;
	/**
	 * 课程ID
	 */
	private String id;
	/**
	 * 课程名
	 */
	private String name;
	/**
	 * 教师姓名
	 */
	private String teacher;
	/**
	 * 授课教室
	 */
	private String place;
	/**
	 * 上课时间
	 */
	private String time;
	/**
	 * 课程学分
	 */
	private double credit;
	
	public CourseInfo(String id, String name, String teacher, String place, String time, double credit) {
		this.id = id;
		this.name = name;
		this.teacher = teacher;
		this.place = place;
		this.time = time;
		this.credit = credit;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
}
