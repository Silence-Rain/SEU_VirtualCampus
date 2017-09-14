package common;

public class CourseInfo {
	
	private int id;
	private String name;
	private String teacher;
	private String place;
	private String time;
	private int credit;
	
	public CourseInfo(int id, String name, String teacher, String place, String time, int credit) {
		this.id = id;
		this.name = name;
		this.teacher = teacher;
		this.place = place;
		this.time = time;
		this.credit = credit;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
}
