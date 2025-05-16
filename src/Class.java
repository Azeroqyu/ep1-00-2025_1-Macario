import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Class extends Course {
	protected int class_number;
	protected String classroom;
	protected String schedule;
	protected String semester;
	protected String year;
	protected boolean is_withdrawn;
	private int max_students;
	protected Set<Student> students;

	public Class() {
	};

	public Class(String subject, String schedule, int semester, int class_number,
			int max_students, String duration, String id, String year) {
		super(id, subject, schedule, duration);
		this.class_number = class_number;
		this.schedule = schedule;
		this.year = year + semester;
		this.max_students = max_students;
		this.students = new HashSet<>();
		this.is_withdrawn = false;
		addClasses((Class) this);
	}

	public void signStudent(Student student, Class class1) {
		student.signClass(class1);
		students.add(student);

	}

	public Set<Student> getStudents() {
		return students;
	}

	public int getmax_students() {
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
				super.name.replace(",", ";"),
				String.valueOf(class_number),
				classroom != null ? classroom.replace(",", ";") : " ",
				schedule.replace(",", ";"),
				semester,
				String.valueOf(max_students),
				String.valueOf(students.size()));

	}

	public static void saveClassToCSV(ArrayList<Class> classes, String filename) {
		try (FileWriter writer = new FileWriter(filename)) {
			writer.write("ID,materia,nome,turma,sala,horario,vagas,matriculados\n");
			for (Class cls : classes) {
				writer.write(cls.toCSV() + "\n");
			}
		} catch (IOException e) {
			System.err.println("Erro passando para csv: " + e.getMessage());
		}
	}
}
