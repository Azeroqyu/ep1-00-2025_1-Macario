import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddStudent extends GenericPannel {
    private static final String[] COLUMN_NAMES = { "ID", "nome", "tipo", "turmas_matriculadas", "materias_trancadas" };
    private static final String CSV_HEADER = "ID,nome,tipo,turmas_matriculadas,turmas_trancadas";
    private static final String FILE_PATH = "../.data/students.csv";
    private static final String MODE = "Alunos";
    private List<Student> students;

    public AddStudent(JFrame parentFrame) {
        super(parentFrame);
        this.students = new ArrayList<>();
        viewStudentsFromCsv();
        showFromCSV(FILE_PATH);
    }

    @Override
    protected void StartUI() {
        super.StartUI();
        JButton signButtn = new JButton("Matricular Aluno em turma:");
        signButtn.addActionListener(e -> showEnrollmentDialog());
        ((JPanel) mainPanel.getComponent(0)).add(signButtn);
    }

    private void showEnrollmentDialog() {
        JDialog dialog = new JDialog(parentFrame, "Matricular Aluno em turma", true);
        dialog.setLayout(new BorderLayout(10, 10));
        DefaultTableModel studentModel = new DefaultTableModel(COLUMN_NAMES, 0);

        JTable studentTable = new JTable(studentModel);
        studentTableFill(studentModel);
        studentTable.setBackground(new Color(24, 24, 27));
        JComboBox<Class> classCombo = new JComboBox<>();
        classCombo.setRenderer(new renderClass());
        Course.reloadClasses();
        Course.getAllClasses().forEach(classCombo::addItem);

        JPanel content = new JPanel(new GridLayout(2, 1));
        content.add(new JScrollPane(studentTable));
        content.add(classCombo);

        JButton confirmButton = new JButton("Confirmar Matrícula");
        confirmButton.addActionListener(e -> {
            signforClass(studentTable, classCombo);
            dialog.dispose();
        });

        dialog.add(content, BorderLayout.CENTER);
        dialog.add(confirmButton, BorderLayout.SOUTH);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }

    @Override
    protected String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected String getMode() {
        return MODE;
    }

    @Override
    protected String getFilePath() {
        return FILE_PATH;
    }

    @Override
    protected String getCSVHeader() {
        return CSV_HEADER;
    }

    @Override
    protected void showAddDialog() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JComboBox<String> modeSwitcher = new JComboBox<>(new String[] { "Aluno regular", "Aluno especial" });
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTable studentTable = new JTable();
        JTableHeader header = studentTable.getTableHeader();
        header.setBackground(new Color(24, 24, 37));
        panel.add(new JLabel("Tipo:"));
        panel.add(modeSwitcher);
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Nome:"));
        panel.add(nameField);

        int result = JOptionPane.showConfirmDialog(parentFrame, panel, "Adicionar Aluno", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String mode = (String) modeSwitcher.getSelectedItem();
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                validateinput(id);
                checkforBlank(id, name);

                Student student = mode.equals("Aluno especial")
                        ? new SpecialStudent(name, id)
                        : new Student(name, id);

                students.add(student);
                parsedData.add(new String[] { student.getId(), student.getName(), mode, "", "" });
                updateTable();
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(parentFrame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    protected void updateTable() {
        DefaultTableModel model = new DefaultTableModel(getColumnNames(), 0);
        for (Student student : students) {
            model.addRow(new Object[] {
                    student.getId(),
                    student.getName(),
                    (student instanceof SpecialStudent) ? "Especial" : "Regular",
                    student.getClasses().stream().map(c -> c.getId() + "-T" + c.class_number)
                            .collect(Collectors.joining(";")),
                    student.getWithdrawnClasses().stream().map(c -> c.getId() + "-T" + c.class_number)
                            .collect(Collectors.joining(";"))
            });
        }
        dataTable.setModel(model);
    }

    private void studentTableFill(DefaultTableModel model) {
        model.setRowCount(0);
        for (Student student : students) {
            model.addRow(new Object[] {
                    student.getId(),
                    student.getName(),
                    (student instanceof SpecialStudent) ? "Especial" : "Regular",
                    student.getClasses().stream().map(c -> c.getId() + "-T" + c.class_number)
                            .collect(Collectors.joining(";")),
                    student.getWithdrawnClasses().stream().map(c -> c.getId() + "-T" + c.class_number)
                            .collect(Collectors.joining(";"))
            });
        }
    }

    private void signforClass(JTable studentTable, JComboBox<Class> classCombo) {
        int selectedRow = studentTable.getSelectedRow();
        Class selectedClass = (Class) classCombo.getSelectedItem();
        if (selectedRow == -1 || selectedClass == null) {
            JOptionPane.showMessageDialog(parentFrame, "Selecione um aluno e uma turma!", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student = students.get(selectedRow);
        try {
            if (student.getClasses().contains(selectedClass)) {
                throw new IllegalStateException("Aluno já está matriculado nesta turma:");
            }

            student.signClass(selectedClass);
            selectedClass.signStudent(student, selectedClass);
            updateStudentRecord(student);
            updateTable();
            JOptionPane.showMessageDialog(parentFrame, "Matrícula realizada com sucesso!\nTurma: "
                    + selectedClass.getId() + "-T" + selectedClass.class_number, "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(parentFrame, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudentRecord(Student student) {
        parsedData.removeIf(r -> r[0].equals(student.getId()));
        parsedData.add(new String[] {
                student.getId(),
                student.getName(),
                (student instanceof SpecialStudent) ? "Especial" : "Regular",
                student.getClasses().stream().map(c -> c.getId() + "-T" + c.class_number)
                        .collect(Collectors.joining(";")),
                student.getWithdrawnClasses().stream().map(c -> c.getId() + "-T" + c.class_number)
                        .collect(Collectors.joining(";"))
        });
    }

    private void validateinput(String id) {
        boolean idInUse = parsedData.stream().anyMatch(row -> row[0].equals(id));
        if (idInUse)
            throw new IllegalArgumentException("O id:" + id + "Já está registrado!");
    }

    private void checkforBlank(String id, String name) {
        if (id.isEmpty() || name.isEmpty())
            throw new IllegalArgumentException("Id e nome não podem ser vazios!");
    }

    private void viewStudentsFromCsv() {
        List<String[]> studentData = readFromCSV(FILE_PATH);
        for (String[] row : studentData) {
            String[] aux = Arrays.copyOf(row, 5);
            for (int i = 0; i < aux.length; i++)
                if (aux[i] == null)
                    aux[i] = "";
            if (aux[0].isEmpty() || aux[1].isEmpty())
                continue;

            Student student = aux[2].equals("Especial")
                    ? new SpecialStudent(aux[1], aux[0])
                    : new Student(aux[1], aux[0]);

            if (!aux[3].isEmpty())
                Arrays.stream(aux[3].split(";")).forEach(classId -> {
                    Class class1 = searchByID(classId.split("-")[0]);
                    if (class1 != null) {
                        student.signClass(class1);
                        class1.signStudent(student, class1);
                    }
                });

            if (!aux[4].isEmpty())
                Arrays.stream(aux[4].split(";")).forEach(classId -> {
                    Class class1 = searchByID(classId.split("-")[0]);
                    if (class1 != null) {
                        student.withdrawClass(class1);
                        class1.getStudents().remove(student);
                    }
                });
            students.add(student);
        }
    }

    private Class searchByID(String id) {
        return Course.getAllClasses().stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    private static class renderClass extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Class) {
                Class class1 = (Class) value;
                setText(class1.getId() + "-T" + class1.class_number + " - " + class1.getSubject() + " ("
                        + class1.getSchedule()
                        + ")");
            }
            return this;
        }
    }
}
