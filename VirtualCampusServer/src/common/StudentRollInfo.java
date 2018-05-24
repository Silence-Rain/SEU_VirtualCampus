package common;

import java.io.Serializable;

/**
 * 学生学籍信息
 * （即tbStudentRoll表的结构）
 * 
 * @author Silence
 *
 */
public class StudentRollInfo implements Serializable{

	private static final long serialVersionUID = 10;
	/**
	 * 学号
	 */
	private String id;
	/**
	 * 学生姓名
	 */
	private String name;
	/**
	 * 年龄
	 */
	private String age;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 生日
	 */
	private String birthday;
	/**
	 * 出生地
	 */
	private String birthPlace;
	/**
	 * 入学时间
	 */
	private String entranceTime;
	/**
	 * 照片URL
	 */
	private String photo;
	/**
	 * 国籍
	 */
	private String nation;
	/**
	 * 院系
	 */
	private String department;
	/**
	 * 专业
	 */
	private String major;
	/**
	 * 宿舍
	 */
	private String dormitory;
	
	public StudentRollInfo(String id, String name, String age, String gender, String birthday, String birthPlace,
			String entranceTime, String photo, String nation, String department, String major, String dormitory) {

		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.birthday = birthday;
		this.birthPlace = birthPlace;
		this.entranceTime = entranceTime;
		this.photo = photo;
		this.nation = nation;
		this.department = department;
		this.major = major;
		this.dormitory = dormitory;
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	public String getEntranceTime() {
		return entranceTime;
	}
	public void setEntranceTime(String entranceTime) {
		this.entranceTime = entranceTime;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getDormitory() {
		return dormitory;
	}
	public void setDormitory(String dormitory) {
		this.dormitory = dormitory;
	}

	
}
