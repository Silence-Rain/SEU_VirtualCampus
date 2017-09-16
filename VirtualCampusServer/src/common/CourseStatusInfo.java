package common;

import java.io.Serializable;

public class CourseStatusInfo implements Serializable {

	private static final long serialVersionUID = 2;
	private String id;
	private String selector;
	
	public CourseStatusInfo(String id, String selector) {
		this.id = id;
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
