import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Starter {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			System.setProperty("jdk.gtk.version", "3");
			System.setProperty("jdk.gtk.verbose", "true");
		} catch (Exception e) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		// Atually starting the gui
		SwingUtilities.invokeLater(() -> {
			Starter starter = new Starter();
			starter.Startgui();
		});
	}

	public void Startgui() {
		JFrame frame = new JFrame("Sistema Academico");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1980, 1080);
		frame.setLayout(new BorderLayout(100, 100));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPanel = new JPanel(new BorderLayout(5, 5));
		contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.PAGE_AXIS));

		JLabel titleLabel = new JLabel("Sistema Academico FCTE", JLabel.CENTER);
		titleLabel.setFont(new Font("Sans-serif", Font.BOLD, 24));

		JLabel uniNameLabel = new JLabel("V_1_0", JLabel.CENTER);
		uniNameLabel.setFont(new Font("Sans-serif", Font.BOLD, 28));
		uniNameLabel.setForeground(new Color(24, 24, 37));

		headerPanel.add(titleLabel);
		headerPanel.add(Box.createVerticalStrut(5));
		headerPanel.add(uniNameLabel);

		contentPanel.add(headerPanel, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel(new GridLayout(5, 5, 20, 20));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// creating buttons, dont know if ill have time to do icons
		JButton studentButton = createModeButton("Gerenciamento de Alunos", "../assets/student.png");
		JButton classButton = createModeButton("Gerenciamento de disciplinas", "../assets/teacher_icon.png");
		JButton exitButton = createModeButton("Sair", "../assets/exit_icon.png");
		JButton GradeSystemButton = createModeButton("Gerenciamento de notas", "../assets/grade_icon.png");
		JButton reportsButton = createModeButton("Relatórios", "../assets/report_icon.png");

		studentButton.addActionListener(e -> openStudentMode(frame));
		classButton.addActionListener(e -> OpenClassMode(frame));
		GradeSystemButton.addActionListener(e -> openGradeSystem(frame));
		exitButton.addActionListener(e -> System.exit(0));
		buttonPanel.add(studentButton);
		buttonPanel.add(classButton);
		buttonPanel.add(GradeSystemButton);
		buttonPanel.add(reportsButton);
		buttonPanel.add(exitButton);

		contentPanel.add(buttonPanel, BorderLayout.CENTER);

		frame.add(contentPanel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private static JButton createModeButton(String text, String iconPath) {
		JButton button = new JButton(text, new ImageIcon(iconPath));
		button.setVerticalTextPosition(SwingConstants.BOTTOM);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setFont(new Font("Sans-serif", Font.BOLD, 16));
		button.setPreferredSize(new Dimension(200, 60));
		button.setFocusPainted(false);
		return button;
	}

	private static void openStudentMode(JFrame parent) {
		parent.getContentPane().removeAll();
		AddStudent studentMOde = new AddStudent(parent);
		parent.add(studentMOde.getPanel());
		parent.revalidate();
	}

	private static void OpenClassMode(JFrame parent) {
		parent.getContentPane().removeAll();
		AddClasses classMode = new AddClasses(parent);
		parent.add(classMode.getPanel());
		parent.revalidate();

	}

	private static void openGradeSystem(JFrame parent) {
		parent.getContentPane().removeAll();
		AddGrades gradeMode = new AddGrades(parent);
		parent.add(gradeMode.getPanel());
		parent.revalidate();

	}

	private static void openReportsMode(JFrame parent) {

	}
}
