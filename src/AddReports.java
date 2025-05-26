import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddReports extends GenericPannel {
    private static final String[] COLUMNS = { "matricula", "nome", "mencao", "aprovado" };
    private DefaultTableModel model;
    private JTable table;

    public AddReports(JFrame parent) {
        super(parent);
        setupUI();
    }

    private void setupUI() {
        model = new DefaultTableModel(COLUMNS, 0);

        table = new JTable(model);
        table.setBackground(new Color(24, 24, 37));
        JPanel top = (JPanel) mainPanel.getComponent(0);
        top.removeAll();

        JButton btn = new JButton("Gerar Relatório");
        btn.addActionListener(e -> showAddDialog());
        top.add(btn);

        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        mainPanel.revalidate();
    }

    @Override
    protected void showAddDialog() {
        JDialog dialog = new JDialog(parentFrame, "Selecionar Turma", true);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel p = new JPanel(new GridLayout(2, 2, 5, 5));
        JComboBox<String> courseBox = new JComboBox<>();
        JComboBox<Integer> classBox = new JComboBox<>();

        loadCourses(courseBox);
        courseBox.addActionListener(e -> updateClasses(classBox, courseBox));

        p.add(new JLabel("Curso:"));
        p.add(courseBox);
        p.add(new JLabel("Turma:"));
        p.add(classBox);

        JButton gen = new JButton("Gerar");
        gen.addActionListener(e -> handleGeneration(courseBox, classBox, dialog));

        dialog.add(p, BorderLayout.CENTER);
        dialog.add(gen, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }

    private void loadCourses(JComboBox<String> combo) {
        try {
            GenericPannel.readFromCSV(AddClasses.FILE_PATH).stream()
                    .filter(r -> r.length > 3)
                    .map(r -> r[0])
                    .distinct()
                    .forEach(combo::addItem);
        } catch (Exception e) {
            showError("Erro ao carregar cursos");
        }
    }

    private void updateClasses(JComboBox<Integer> classBox, JComboBox<String> courseBox) {
        classBox.removeAllItems();
        String id = (String) courseBox.getSelectedItem();
        if (id == null)
            return;

        try {
            GenericPannel.readFromCSV(AddClasses.FILE_PATH).stream()
                    .filter(r -> r.length > 3 && r[0].equals(id))
                    .map(r -> parseClassNumber(r[3]))
                    .filter(n -> n > 0)
                    .distinct()
                    .forEach(classBox::addItem);
        } catch (Exception e) {
            showError("Erro ao carregar turmas");
        }
    }

    private int parseClassNumber(String val) {
        try {
            return Integer.parseInt(val.trim());
        } catch (Exception e) {
            return -1;
        }
    }

    private void handleGeneration(JComboBox<String> courseBox, JComboBox<Integer> classBox, JDialog dialog) {
        if (courseBox.getSelectedItem() == null || classBox.getSelectedItem() == null) {
            showError("Selecione um curso e turma");
            return;
        }

        String courseId = (String) courseBox.getSelectedItem();
        Integer classNum = (Integer) classBox.getSelectedItem();
        generateReport(courseId, classNum);
        dialog.dispose();
    }

    private void generateReport(String courseId, Integer classNum) {
        model.setRowCount(0);

        try {
            List<String[]> students = GenericPannel.readFromCSV(AddStudent.FILE_PATH);
            List<String[]> grades = GenericPannel.readFromCSV(AddGrades.FILE_PATH);

            students.stream()
                    .filter(s -> s.length >= 4)
                    .filter(s -> isEnrolled(s[3], courseId, classNum))
                    .forEach(s -> addStudentRow(s, courseId, grades));

            if (model.getRowCount() == 0) {
                showInfo("Nenhum aluno encontrado nesta turma");
            }
        } catch (Exception e) {
            showError("Erro ao gerar relatório");
        }

        model.fireTableDataChanged();
    }

    private void addStudentRow(String[] student, String courseId, List<String[]> grades) {
        String[] grade = findGrade(grades, student[0], courseId);
        String mencao = grade.length > 2 ? grade[2] : "SR";

        model.addRow(new Object[] {
                safeGet(student, 0),
                safeGet(student, 1),
                mencao,
                "SR".equals(mencao) ? "Não" : "Sim"
        });
    }

    private String safeGet(String[] arr, int idx) {
        return arr.length > idx ? arr[idx] : "";
    }

    private boolean isEnrolled(String classes, String courseId, Integer classNum) {
        if (classes == null)
            return false;
        String target = courseId + "-T" + classNum;
        return Arrays.asList(classes.split(";")).contains(target);
    }

    private String[] findGrade(List<String[]> grades, String id, String course) {
        return grades.stream()
                .filter(g -> g.length > 1 && g[0].equals(id) && g[1].equals(course))
                .findFirst()
                .orElse(new String[0]);
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(parentFrame, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void showInfo(String msg) {
        JOptionPane.showMessageDialog(parentFrame, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    protected String getFilePath() {
        return "";
    }

    @Override
    protected String[] getColumnNames() {
        return COLUMNS;
    }

    @Override
    protected String getCSVHeader() {
        return "";
    }

    @Override
    protected String getMode() {
        return "Relatório";
    }
}
