package common;

public class CourseStatusInfo {
	
	private String id;
	private String name;
	private String selector;
	
	public CourseStatusInfo(String id, String name, String selector) {
		this.id = id;
		this.name = name;
		this.selector = selector;
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
	public String getSelector() {
		return selector;
	}
	public void setSelector(String selector) {
		this.selector = selector;
	}
}
