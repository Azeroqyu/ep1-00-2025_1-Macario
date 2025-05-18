import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Class extends Course {
	protected static final String PATH = "../.data/classes.csv";
	protected Integer class_number;
	protected String classroom;
	protected String schedule;
	protected String semester;
	protected String year;
	protected boolean is_withdrawn;
	private Integer max_students;
	protected Set<Student> students;
	private Set<Grade> grades = new HashSet<>();

	public Class() {
	}

	public Class(String subject, String schedule, Integer class_number,
			String classroom, Integer max_students, String duration,
			String id, String year, Integer semester) {
		super(id, subject, duration);
		this.class_number = class_number;
		this.year = year + semester;
		this.max_students = max_students;
		this.students = new HashSet<>();
		this.is_withdrawn = false;
		this.schedule = schedule;
		this.classroom = classroom;
		addClasses((Class) this);
	}

	public void addGrade(Grade grade) {
		grades.add(grade);
		GradeSystem.addGrade(grade);
	}

	public Set<Grade> getGrades() {
		return grades;
	}

	public void signStudent(Student student, Class class1) {
		student.signClass(class1);
		students.add(student);

	}

	public Set<Student> getStudents() {
		return students;
	}

	public Integer getmax_students() {
		return max_students;
	}

	public String getSemester() {
		return semester;
	}

	public boolean getWithdraw() {
		return is_withdrawn;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public void setWithdraw(boolean is_withdrawn) {
		this.is_withdrawn = is_withdrawn;
	}

	public String toCSV() {
		return String.join(",",
				super.id,
				super.subject,
				String.valueOf(class_number),
				classroom != null ? classroom.replace(",", ";") : " ",
				schedule.replace(",", ";"),
				semester,
				String.valueOf(max_students),
				String.valueOf(students.size()));

	}

	public static void saveClassToCSV(ArrayList<Class> classes, String filename) {
		try (FileWriter writer = new FileWriter(filename)) {
			writer.write("ID,materia,turma,sala,horario,trancado,vagas,matriculados,semestre\n");
			for (Class cls : classes) {
				writer.write(cls.toCSV() + "\n");
			}
		} catch (IOException e) {
			System.err.println("Erro passando para csv: " + e.getMessage());
		}
	}
}
