import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddStudent extends GenericPannel {

    private static final String[] COLUMN_NAMES = { "ID", "nome", "tipo" };
    private static final String CSV_HEADER = "ID,nome,tipo";
    private static final String FILE_PATH = "../.data/students.csv";
    private static final String MODE = "Alunos";
    private List<Student> students;

    AddStudent(JFrame parentFrame) {
        super(parentFrame);
        showFromCSV(FILE_PATH);
        this.students = new ArrayList<>();
    }

    @Override
    protected void showAddDialog() {

        JComboBox<String> modeSwitcher;
        JPanel panel = new JPanel();
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JLabel specialStudentWarning = new JLabel("Aviso! registrando um estudante especial!");
        panel.setLayout(new GridLayout(0, 1));
        modeSwitcher = new JComboBox<>(new String[] { "Aluno regular", "Aluno especial" });
        panel.add(modeSwitcher);

        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Nome:"));
        panel.add(nameField);
        panel.add(specialStudentWarning);
        specialStudentWarning.setVisible(false);
        int result = JOptionPane.showConfirmDialog(
                parentFrame,
                panel,
                "Adicionar Aluno",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String mode = (String) modeSwitcher.getSelectedItem();
                String Id = idField.getText().trim();
                String Name = nameField.getText().trim();
                validateinput(Id);
                checkforBlank(Id, Name);
                Student student = mode.equals("Especial") ? new SpecialStudent(nameField.getText(), idField.getText())
                        : new Student(nameField.getText(), idField.getText());
                students.add(student);
                parsedData.add(new String[] {
                        student.getId(),
                        student.getName(),
                        mode
                });
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(parentFrame,
                        e.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void validateinput(String id) {
        boolean idInUse = parsedData.stream().anyMatch(row -> row[0].equals(id));
        if (idInUse) {
            throw new IllegalArgumentException("O id:" + id + "Já está registrado!");
        }
    }

    void checkforBlank(String id, String name) {
        if (id.isEmpty() || name.isEmpty()) {
            throw new IllegalArgumentException("Id e nome não podem ser vazios!");
        }
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

}
