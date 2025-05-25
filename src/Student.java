import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Student {
	private String name;
	private String id;
	protected List<Class> classes;
	protected List<Grade> grades;
	protected List<Class> withdrawnClasses;
	boolean withdrawn;

	public Student(String name, String id) {
		this.name = name;
		this.id = id;
		this.classes = new ArrayList<>();
		this.withdrawnClasses = new ArrayList<>();
		this.grades = new ArrayList<>();
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

	public List<Class> getClasses() {
		return classes;
	}

	public void CheckRegistedclasses(ArrayList<Class> courses) {

	}

	public void lisClasses(List<Class> classes, Class class1) {

	}

	public void listWithdrawn() {

	}

	public void signClass(Class class1) {
		classes.add(class1);
	}

	public void withdrawClass(Class class1) {
		if (classes.contains(class1)) {
			classes.remove(class1);
			withdrawnClasses.add(class1);

		}
	}

	public void withdrawSemester(String semester, List<Class> classes) {
		for (Class class1 : classes) {
			if (class1.getSemester().equals(semester)) {
				class1.setWithdraw(true);
			}
		}
	}

	public List<Class> getWithdrawnClasses() {
		return withdrawnClasses;
	}

	String toCSV() {
		String classIds = classes.stream().map(Class::getId).collect(Collectors.joining(";"));
		String withdrawid = withdrawnClasses.stream().map(Class::getId).collect(Collectors.joining(";"));
		return String.join(",",
				id,
				name,
				(this instanceof SpecialStudent) ? "Especial" : "Regular",
				classIds,
				withdrawid);
	}

	void withdrawSemester() {
	}

}
