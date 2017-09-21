package common;

import java.io.Serializable;

/**
 * 课程选择信息
 * （即tbCourseStatus表的结构）
 * 
 * @author Silence
 *
 */
public class CourseStatusInfo implements Serializable {

	private static final long serialVersionUID = 2;
	/**
	 * 课程ID
	 */
	private String id;
	/**
	 * 选择该课程的学生ID
	 */
	private String selector;
	
	public CourseStatusInfo(String i, String selector) {
		this.id = i;
		this.selector = selector;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSelector() {
		return selector;
	}
	public void setSelector(String selector) {
		this.selector = selector;
	}
}
