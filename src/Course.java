import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Course {
	protected String subject;
	public String name;
	public String id;
	public String duration;
	private ArrayList<Class> classes;
	protected ArrayList<Course> prerequisites;
	static ArrayList<Class> AllClasses = new ArrayList<>();

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

	public static List<Class> getAllClasses() {
		return new ArrayList<>(AllClasses);
	}

	public static void addClasses(Class class1) {
		if (!AllClasses.contains(class1)) {
			AllClasses.add(class1);
		}
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

	public ArrayList<Course> getPrerequisites() {
		return prerequisites;
	}

	public static void reloadClasses() {
		AllClasses.clear();
		loadClassesFromCSV();
	}

	private static void loadClassesFromCSV() {
		List<String[]> classData = GenericPannel.readFromCSV(AddClasses.FILE_PATH);
		for (String[] row : classData) {
			try {
				if (row.length < 9) {
					System.err.println("ignorando coluna incompleta: " + Arrays.toString(row));
					continue;
				}
				Class casls = new Class(
						row[1],
						row[5],
						row[4],
						Integer.parseInt(row[7]),
						row[2],
						row[0],
						String.valueOf(Year.now()),
						Integer.parseInt(row[6]));
				casls.setClass_number(Integer.parseInt(row[3]));
				Course.addClasses(casls);
			} catch (Exception e) {
				System.err.println("Erro ao ler turmas: " + Arrays.toString(row));
			}
		}
	}

}
