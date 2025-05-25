import java.util.ArrayList;
import java.util.List;

public class Course {
	protected String subject;
	public String name;
	public String id;
	public String duration;
	private ArrayList<Class> classes;
	protected ArrayList<Course> prerequisites;
	private static ArrayList<Class> AllClasses = new ArrayList<>();

	public Course() {
	}

	public Course(String id, String subject, String duration) {
		this.id = id;
		this.subject = subject;
		this.duration = duration;
		this.classes = new ArrayList<>();
	}

	public String getName() {
		name = id + " " + " " + subject + "(" + duration + ")";
		return name;
	}

	public String getId() {
		return id;
	}

	public void addClasses(Class class1) {
		classes.add(class1);
		AllClasses.add(class1);
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

	public void setName(String name) {
		this.name = name;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static List<Class> getAllClasses() {
		return AllClasses;

	}

	public ArrayList<Course> getPrerequisites() {
		return prerequisites;
	}
}
