import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class AddClasses extends Starter {
	private final String PATH = "../.data/classes.csv";
	private JFrame parentFrame;
	private JPanel mainPanel;
	private JTable classTable;
	private List<String[]> classesData;
	private ArrayList<Class> classesObjects = new ArrayList<>();

	public AddClasses(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		this.mainPanel = new JPanel(new BorderLayout());
		this.classesObjects = new ArrayList<>();
		this.classesData = new ArrayList<>();
		StrUI();
	}

	private void StrUI() {

		JButton viewButton = new JButton("Ver turmas");
		JButton addButton = new JButton("Adicionar turmas");
		JButton saveButton = new JButton("Salvar");
		JButton backButton = new JButton("voltar");

		viewButton.addActionListener(e -> showClassesFromCSV());
		addButton.addActionListener(e -> showAddClassDialog());
		saveButton.addActionListener(e -> saveClassesToCSV(PATH));
		backButton.addActionListener(e -> returnToMainMenu());

		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setBackground(new Color(17, 17, 27));
		buttonPanel.setBackground(Color.black);
		buttonPanel.setForeground(Color.red);
		buttonPanel.add(viewButton);
		buttonPanel.add(addButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(backButton);

		mainPanel.add(buttonPanel, BorderLayout.NORTH);

		classTable = new JTable(new Object[0][], new String[] { "ID", "Materia" });
		classTable.setBackground(new Color(17, 17, 27));
		classTable.setForeground(Color.white);
		classTable.getTableHeader().setBackground(new Color(17, 17, 27));
		mainPanel.add(new JScrollPane(classTable), BorderLayout.CENTER);

	}

	private void showAddClassDialog() {
		JTextField idField = new JTextField();
		JTextField subjectField = new JTextField();
		JTextField classroomField = new JTextField();
		JTextField maxStudentsField = new JTextField();
		JTextField semesterField = new JTextField();
		JTextField scheduleField = new JTextField();
		JTextField durationField = new JTextField();
		JTextField classNumberField = new JTextField();

		JPanel panel = new JPanel(new GridLayout(1, 1));
		panel.add(new JLabel("ID:"));
		panel.add(idField);
		panel.add(new JLabel("Materia:"));
		panel.add(subjectField);
		panel.add(new JLabel("Vagas:"));
		panel.add(maxStudentsField);
		panel.add(new JLabel("Horario:"));
		panel.add(scheduleField);
		panel.add(new JLabel("Semestre:"));
		panel.add(semesterField);
		panel.add(new JLabel("Duração:"));
		panel.add(durationField);
		panel.add(new JLabel("Sala(): "));
		panel.add(classroomField);
		panel.add(new JLabel("Turma numero:"));
		panel.add(classNumberField);

		int result = JOptionPane.showConfirmDialog(
				parentFrame,
				panel,
				"Adicionar nova turma",
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			Class newClass = new Class(
					subjectField.getText(),
					scheduleField.getText(),
					Integer.parseInt(classNumberField.getText()),
					classroomField.getText(),
					Integer.parseInt(maxStudentsField.getText()),
					durationField.getText(),
					idField.getText(),
					String.valueOf(Year.now().getValue()),
					Integer.parseInt(semesterField.getText()));
			String[] parsedData = newClass.toCSV().split(",");
			classesObjects.add(newClass);
			classesData.add(parsedData);
		}
		updateTable();
	}

	private void showClassesFromCSV() {
		classesData = readClassesFromCSV();
		updateTable();
	}

	private void updateTable() {
		if (classesData != null && !classesData.isEmpty()) {
			String[] columnNames = {
					"ID",
					"Materia",
					"Nome",
					"Turma_numero",
					"sala",
					"horario",
					"Semestre",
					"vagas",
					"matriculados" };

			Object[][] data = new Object[classesData.size()][];
			for (int i = 0; i < classesData.size(); i++) {
				data[i] = classesData.get(i);
			}
			classTable.setModel(new javax.swing.table.DefaultTableModel(
					classesData.toArray(new String[0][]),
					columnNames));
		}
	}

	private void saveClassesToCSV(String path) {
		if (classesData == null || classesData.isEmpty()) {
			JOptionPane.showMessageDialog(parentFrame, "Sem turmas para salvar");
			return;
		}
		try (FileWriter writer = new FileWriter(path)) {
			writer.write("Materia,horario,semestre,turma,vagas,duracao,id,ano\n");
			for (String[] classData : classesData) {
				writer.write(String.join(",", classData) + "\n");
			}
			JOptionPane.showMessageDialog(parentFrame, "Turmas salvas");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(parentFrame,
					"Erro ao Salvar turmas!: " + e.getMessage(),
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private List<String[]> readClassesFromCSV() {
		List<String[]> existingClasses = new ArrayList<>();
		File csvFile = new File(PATH);
		if (!csvFile.exists()) {
			return existingClasses;
		}
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			br.readLine();
			String line;
			while ((line = br.readLine()) != null) {
				existingClasses.add(line.split(","));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(parentFrame,
					"Erro Lendo CSV: " + e.getMessage(),
					"Erro",
					JOptionPane.ERROR_MESSAGE);
		}
		return existingClasses;
	}

	private void returnToMainMenu() {
		parentFrame.getContentPane().removeAll();
		new Starter().Startgui();
		parentFrame.revalidate();
	}

	public JPanel getPanel() {
		return mainPanel;
	}

}
