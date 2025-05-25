import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GradeSystem {
	private static List<Grade> grades = new ArrayList<>();

	public static void addGrade(Grade grade) {
		grades.add(grade);
	}
}
