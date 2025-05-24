import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericPannel {
	protected JFrame parentFrame;
	protected JPanel mainPanel;
	private JTable dataTable;
	protected List<String[]> parsedData;

	GenericPannel(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		this.mainPanel = new JPanel(new BorderLayout());
		this.parsedData = new ArrayList<>();
		StartUI();
	}

	protected abstract String getFilePath();

	protected abstract String[] getColumnNames();

	protected abstract String getCSVHeader();

	protected abstract void showAddDialog();

	protected abstract String getMode();

	private void StartUI() {

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
		dataTable = new JTable(new Object[0][], getColumnNames());
		dataTable.setBackground(new Color(17, 17, 27));
		dataTable.setForeground(Color.white);
		dataTable.getTableHeader().setBackground(new Color(17, 17, 27));
		mainPanel.add(new JScrollPane(dataTable), BorderLayout.CENTER);

	}

	protected void showFromCSV(String filePath) {
		parsedData = readFromCSV(filePath);
		updateTable();
	}

	protected void updateTable() {
		DefaultTableModel table = new DefaultTableModel(getColumnNames(), 0);
		if (parsedData != null && !parsedData.isEmpty()) {
			for (String[] row : parsedData) {
				table.addRow(row);
			}
		}
		dataTable.setModel(table);
	}

	protected void saveToCSV(String filePath) {
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

	private List<String[]> readFromCSV(String filePath) {
		List<String[]> existingData = new ArrayList<>();
		File csvFile = new File(filePath);
		if (!csvFile.exists()) {
			return existingData;
		}
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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
