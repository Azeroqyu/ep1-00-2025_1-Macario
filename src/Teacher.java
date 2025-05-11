import java.util.ArrayList;

public class Teacher {
	private String name;
	private String id;
	private ArrayList<Course> courses;

	public Teacher(String name, String id) {
		this.name = name;
		this.id = id;
		this.courses = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}
}
