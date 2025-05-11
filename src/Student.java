import java.util.ArrayList;
import java.util.List;

public class Student {
	private String name;
	private String id;
	private ArrayList<Course> courses;
	boolean withdrawn;

	public Student(String name, String id) {
		this.name = name;
		this.id = id;
		this.courses = new ArrayList<>();
	}

	public void setId(String id) {
		this.id = id;
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

	void signClass() {

	}

	void withdrawClass() {

	}

}
