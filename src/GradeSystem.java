import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GradeSystem {
	private static final String PATH = "../.data/grades.csv";
	private static List<Grade> grades = new ArrayList<>();

	public static void addGrade(Grade grade) {
		grades.add(grade);
	}

	public void saveGradesToCSV() {
		try (FileWriter writer = new FileWriter(PATH)) {
			writer.write("Matricula,Materia,Mencao\n");
			for (Grade grade : grades) {
				writer.write(grade.toCSV() + "\n");
			}
		} catch (IOException e) {
			System.err.println("Erro ao Salvar notas: " + e.getMessage());
		}
	}
}
