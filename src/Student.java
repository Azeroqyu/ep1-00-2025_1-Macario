import java.util.ArrayList;
import java.util.List;

public class Student {
	private String name;
	private String id;
	protected List<Class> classes;
	boolean withdrawn;

	public Student(String name, String id) {
		this.name = name;
		this.id = id;
		this.classes = new ArrayList<>();
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

	public void CheckRegistedclasses(ArrayList<Class> courses) {

	}

	public void lisClasses(List<Class> classes, Class class1) {
		classes.add(class1);
	}

	public void signClass(Class class1) {
		classes.add(class1);
	}

	public void withdrawClass(List<Class> classes, Class class1) {
		classes.remove(class1);
	}

	public void withdrawSemester(String semester, List<Class> classes) {
		for (Class class1 : classes) {
			if (class1.getSemester().equals(semester)) {
				class1.setWithdrawn(true);
			}
		}
	}

	void withdrawSemester() {
	}

}
