import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AddGrades extends GenericPannel {
    private static final String CSV_HEADER = "student_id,class_id,p1,p2,p3,lista,seminario,modo_ponderado,mencao,aprovado";
    private static final String[] COLUMN_NAMES = CSV_HEADER.split(",");
    private static final String FILE_PATH = "../.data/grades.csv";
    private static final String MODE = "Notas";

    AddGrades(JFrame parentFrame) {
        super(parentFrame);
    }

    @Override
    protected void showAddDialog() {
        JDialog dialog = new JDialog(parentFrame, "Adicionar Notas", true);
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        JComboBox<String> studentCombo = new JComboBox<>();
        JComboBox<String> classCombo = new JComboBox<>();
        populateCombos(studentCombo, classCombo);
        JTextField p1Field = new JTextField();
        JTextField p2Field = new JTextField();
        JTextField p3Field = new JTextField();
        JTextField lField = new JTextField();
        JTextField sField = new JTextField();
        JCheckBox modeCheck = new JCheckBox("Modo Ponderado");
        panel.add(new JLabel("Aluno:"));
        panel.add(studentCombo);
        panel.add(new JLabel("Turma:"));
        panel.add(classCombo);
        panel.add(new JLabel("P1:"));
        panel.add(p1Field);
        panel.add(new JLabel("P2:"));
        panel.add(p2Field);
        panel.add(new JLabel("P3:"));
        panel.add(p3Field);
        panel.add(new JLabel("Lista:"));
        panel.add(lField);
        panel.add(new JLabel("Seminário:"));
        panel.add(sField);
        panel.add(new JLabel("Modo:"));
        panel.add(modeCheck);

        JButton confirm = new JButton("Confirmar");
        confirm.addActionListener(e -> {
            try {
                Grade grade = createGrade(
                        studentCombo.getSelectedItem().toString(),
                        classCombo.getSelectedItem().toString(),
                        parseGrade(p1Field),
                        parseGrade(p2Field),
                        parseGrade(p3Field),
                        parseGrade(lField),
                        parseGrade(sField),
                        modeCheck.isSelected());

                parsedData.add(new String[] {
                        grade.getStudent().getId(),
                        grade.getClassToBeGraded().getId(),
                        String.valueOf(grade.p1),
                        String.valueOf(grade.p2),
                        String.valueOf(grade.p3),
                        String.valueOf(grade.l),
                        String.valueOf(grade.s),
                        grade.switchMode ? "1" : "0",
                        grade.getFinalGrade(),
                        grade.isApproved ? "1" : "0"
                });

                updateTable();
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(confirm, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }

    private void populateCombos(JComboBox<String> studentCombo, JComboBox<String> classCombo) {
        readFromCSV("../.data/students.csv").forEach(s -> studentCombo.addItem(s[0]));
        readFromCSV("../.data/classes.csv").forEach(c -> classCombo.addItem(c[0]));
    }

    private Grade createGrade(String studentId, String classId,
            float p1, float p2, float p3,
            float l, float s, boolean mode) throws Exception {
        Student student = Course.getAllClasses().stream()
                .flatMap(clazz -> clazz.getStudents().stream())
                .filter(studet -> studet.getId().equals(studentId))
                .findFirst()
                .orElseThrow(() -> new Exception("Aluno não encontrado"));

        Class cls = Course.getAllClasses().stream()
                .filter(c -> c.getId().equals(classId))
                .findFirst()
                .orElseThrow(() -> new Exception("Turma não encontrada"));

        return new GradingMode(student, cls, p1, p2, p3, l, s, mode); // Fixed variable name
    }

    private float parseGrade(JTextField field) throws Exception {
        float value = Float.parseFloat(field.getText());
        if (value < 0 || value > 10)
            throw new Exception("Nota inválida (0-10)");
        return value;
    }

    final void showGradeViewerDialog() {
        JDialog dialog = new JDialog(parentFrame, "Visualizar Notas", true);
        dialog.setLayout(new BorderLayout());

        JComboBox<String> classCombo = new JComboBox<>();
        List<String[]> classes = readFromCSV(FILE_PATH);
        for (String[] cls : classes) {
            classCombo.addItem(cls[0] + " - " + cls[1]); // ID - Subject
        }

        String[] gradeColumns = { "Matrícula", "P1", "P2", "P3", "Lista", "Seminário", "Menção", "Aprovado" };
        DefaultTableModel gradeModel = new DefaultTableModel(gradeColumns, 0);
        JTable gradeTable = new JTable(gradeModel);

        classCombo.addActionListener(e -> {
            String selectedClassId = classes.get(classCombo.getSelectedIndex())[0];
            updateGradeTable(gradeModel, selectedClassId);
        });

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Selecione a Turma:"));
        topPanel.add(classCombo);

        dialog.add(topPanel, BorderLayout.NORTH);
        dialog.add(new JScrollPane(gradeTable), BorderLayout.CENTER);
        dialog.setSize(800, 400);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }

    private void updateGradeTable(DefaultTableModel model, String classId) {
        model.setRowCount(0);
        List<String[]> allGrades = readFromCSV("../.data/grades.csv");

        for (String[] grade : allGrades) {
            if (grade[1].equals(classId)) {
                model.addRow(new Object[] {
                        grade[0],
                        grade[2],
                        grade[3],
                        grade[4],
                        grade[5],
                        grade[6],
                        grade[8],
                        grade[9].equals("1") ? "Sim" : "Não"
                });
            }
        }
    }

    @Override
    protected String getFilePath() {
        return FILE_PATH;
    }

    @Override
    protected String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected String getCSVHeader() {
        return CSV_HEADER;
    }

    @Override
    protected String getMode() {
        return MODE;
    }
}
