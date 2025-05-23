import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericPannel {
	protected JFrame parentFrame;
	protected JPanel mainPanel;
	private JTable classTable;
	protected List<String[]> parsedData;

	GenericPannel(JFrame parentFrame, String mode) {
		this.parentFrame = parentFrame;
		this.mainPanel = new JPanel(new BorderLayout());
		this.parsedData = new ArrayList<>();
		StartUI(mode);
	}

	protected abstract String getFilePath();

	protected abstract String[] getColumnNames();

	protected abstract String getCSVHeader();

	protected abstract void showAddDialog();

	protected abstract String getMode();

	private void StartUI(String mode) {

		JButton viewButton = new JButton("Ver" + getMode());
		JButton addButton = new JButton("Adicionar" + getMode());
		JButton saveButton = new JButton("Salvar");
		JButton backButton = new JButton("voltar");

		viewButton.addActionListener(e -> showFromCSV(getFilePath()));
		addButton.addActionListener(e -> showAddDialog());
		saveButton.addActionListener(e -> saveToCSV(getFilePath()));
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

		classTable = new JTable(new Object[0][], new String[] { getCSVHeader() });
		classTable.setBackground(new Color(17, 17, 27));
		classTable.setForeground(Color.white);
		classTable.getTableHeader().setBackground(new Color(17, 17, 27));
		mainPanel.add(new JScrollPane(classTable), BorderLayout.CENTER);

	}

	protected void showFromCSV(String PATH) {
		parsedData = readFromCSV(PATH);
		updateTable();
	}

	protected void updateTable() {
		if (parsedData != null && !parsedData.isEmpty()) {
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

			Object[][] data = new Object[parsedData.size()][];
			for (int i = 0; i < parsedData.size(); i++) {
				data[i] = parsedData.get(i);
			}
			classTable.setModel(new javax.swing.table.DefaultTableModel(
					parsedData.toArray(new String[0][]),
					columnNames));
		}
	}

	protected void saveToCSV(String path) {
		if (parsedData == null || parsedData.isEmpty()) {
			JOptionPane.showMessageDialog(parentFrame, "Nada a salvar");
			return;
		}
		try (FileWriter writer = new FileWriter(getFilePath())) {
			writer.write(getCSVHeader() + "\n");
			for (String[] data : parsedData) {
				writer.write(String.join(",", data) + "\n");
			}
			JOptionPane.showMessageDialog(parentFrame, "Dados salvos com sucesso!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(parentFrame,
					"Erro ao Salvar!: " + e.getMessage(),
					"Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private List<String[]> readFromCSV(String PATH) {
		List<String[]> existingData = new ArrayList<>();
		File csvFile = new File(PATH);
		if (!csvFile.exists()) {
			return existingData;
		}
		try (BufferedReader br = new BufferedReader(new FileReader(getFilePath()))) {
			br.readLine();
			String line;
			while ((line = br.readLine()) != null) {
				existingData.add(line.split(","));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(parentFrame,
					"Erro Lendo CSV: " + e.getMessage(),
					"Erro",
					JOptionPane.ERROR_MESSAGE);
		}
		return existingData;
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
