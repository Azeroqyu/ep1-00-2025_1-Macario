import java.util.ArrayList;
import java.util.List;

public class Course {
	protected String subject;
	public String name;
	public String id;
	public String duration;
	private ArrayList<Class> classes;
	protected ArrayList<Course> prerequisites;

	public Course() {
	}

	public Course(String id, String subject, String schedule, String duration) {
		this.id = id;
		this.subject = subject;
		this.duration = duration;
	}

	public String getName() {
		name = id + " " + " " + subject + "(" + duration + ")";
		return name;
	}

	public String getId() {
		return id;
	}

	public void addClasses(Class class1) {
		if (classes == null) {
			classes = new ArrayList<>();
		}
		classes.add(class1);
	}

	public ArrayList<Class> getClasses() {
		return classes;
	}

	public String getSubject() {
		return subject;
	}

	public String getDuration() {
		return duration;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setClasses(ArrayList<Class> classes) {
		this.classes = classes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Course> getPrerequisites() {
		return prerequisites;
	}
}
