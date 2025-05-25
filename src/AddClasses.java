import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.awt.*;
import java.time.Year;
import java.util.ArrayList;

public class AddClasses extends GenericPannel {
    private static final String CSV_HEADER = "ID,materia,duracao,numero_turma,sala,horario,semestre,vagas,matriculados";
    private static final String[] COLUMN_NAMES = { "ID", "materia", "duracao", "numero_turma", "sala", "horario",
            "semestre",
            "vagas", "matriculados" };
    private static final String MODE = "Turmas";
    public static final String FILE_PATH = "../.data/classes.csv";

    public AddClasses(JFrame parentFrame) {
        super(parentFrame);
        showFromCSV(FILE_PATH);
    }

    @Override
    protected String getFilePath() {
        return FILE_PATH;
    }

    @Override
    protected String getMode() {
        return MODE;
    }

    @Override
    protected String getCSVHeader() {
        return CSV_HEADER;
    }

    @Override
    protected String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected void showAddDialog() {
        JTextField idField = new JTextField();
        JTextField subjectField = new JTextField();
        JTextField classroomField = new JTextField();
        JTextField maxStudentsField = new JTextField();
        JTextField semesterField = new JTextField();
        JTextField scheduleField = new JTextField();
        JTextField durationField = new JTextField();
        JTextField classNumberField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Matéria:"));
        panel.add(subjectField);
        panel.add(new JLabel("Vagas:"));
        panel.add(maxStudentsField);
        panel.add(new JLabel("Horário:"));
        panel.add(scheduleField);
        panel.add(new JLabel("Semestre:"));
        panel.add(semesterField);
        panel.add(new JLabel("Duração:"));
        panel.add(durationField);
        panel.add(new JLabel("Sala:"));
        panel.add(classroomField);
        panel.add(new JLabel("Turma numero:"));
        panel.add(classNumberField);
        int result = JOptionPane.showConfirmDialog(
                parentFrame,
                panel,
                "Adicionar Turma",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                Class newClass = new Class(
                        subjectField.getText(),
                        scheduleField.getText(),
                        classroomField.getText(),
                        Integer.parseInt(maxStudentsField.getText()),
                        durationField.getText(),
                        idField.getText(),
                        String.valueOf(Year.now()),
                        Integer.parseInt(semesterField.getText()));
                newClass.setClass_number(Integer.parseInt(classNumberField.getText()));
                int semester = Integer.parseInt(semesterField.getText());
                parsedData.add(new String[] {
                        newClass.getId(),
                        newClass.getSubject(),
                        newClass.getDuration(),
                        String.valueOf(newClass.getClass_number()),
                        newClass.getClassroom(),
                        newClass.getSchedule(),
                        String.valueOf(semester),
                        String.valueOf(newClass.getmax_students()),
                        "0" });
                Course.addClasses(newClass);
                Course.reloadClasses();
                updateTable();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        parentFrame,
                        "Valores invalidos, tente novamente.",
                        "Erro!",
                        JOptionPane.ERROR_MESSAGE);
            }

            catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(
                        parentFrame,
                        e.getMessage(),
                        "Turma Já Cadastrada!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
