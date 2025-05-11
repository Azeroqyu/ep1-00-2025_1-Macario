import java.util.ArrayList;
import java.util.List;

public class Course {
	private String subject;
	private String name;
	private String id;
	private String duration;
	private Teacher teacher;
	private String year;
	protected ArrayList<Course> prerequisites;

	public Course(String subject, String schedule, Teacher teacher, String year, String duration) {
		this.subject = subject;
		this.teacher = teacher;
		this.duration = duration;
		this.year = year;

	}

	public String getName() {
		return name;
	}

	public String getYear() {
		return year;
	}

	public String getId() {
		return id;
	}

	public String getSubject() {
		return subject;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public String getDuration() {
		return duration;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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

	public void setYear(String year) {
		this.year = year;
	}

	public ArrayList<Course> getPrerequisites() {
		return prerequisites;
	}

}
