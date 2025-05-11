import java.util.ArrayList;

public class Class extends Course {
	protected int class_number;
	protected float Tests[];
	protected float List, Seminar;
	protected String year;
	protected String classroom;
	protected String semester;
	protected String schedule;
	private int class_size;
	protected ArrayList<Student> students;

	Class(String subject, String schedule, Teacher teacher, String semester, String year, int class_number,
			int class_size) {
		super(subject, schedule, teacher, year, duration);
		this.semester = semester;
		this.class_number = class_number;
		this.students = new ArrayList<>();
	}

	public ArrayList<Student> getAllStudents() {
		ArrayList<Student> allStudents = new ArrayList<Student>();
		return students;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public String getSemester() {
		return semester;
	}

	public String getYear() {
		return year;
	}

	public int getClass_size() {
		return class_size;
	}

}
