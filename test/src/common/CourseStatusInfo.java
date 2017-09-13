package common;

public class CourseStatusInfo {
	
	private int id;
	private String name;
	private String selector;
	
	public CourseStatusInfo(int id, String name, String selector) {
		this.id = id;
		this.name = name;
		this.selector = selector;
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
	public String getSelector() {
		return selector;
	}
	public void setSelector(String selector) {
		this.selector = selector;
	}
}
