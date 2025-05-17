import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Starter {

	public static void main(String[] args) {
		// Set GTK look and feel
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			System.setProperty("jdk.gtk.version", "3");
			System.setProperty("jdk.gtk.verbose", "true");
		} catch (Exception e) {
			try {
				// Fallback to default if gtk is not available
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
		JFrame frame = new JFrame("Sistema Acadêmico v_1.0");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1980, 1080);
		frame.setLayout(new BorderLayout(100, 100));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Add padding around the content
		JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
		contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Add university header
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

		JLabel titleLabel = new JLabel("Sistema Academico", JLabel.CENTER);
		titleLabel.setFont(new Font("Sans-serif", Font.BOLD, 24));

		JLabel uniNameLabel = new JLabel("V_1.0", JLabel.CENTER);
		uniNameLabel.setFont(new Font("Sans-serif", Font.BOLD, 28));
		uniNameLabel.setForeground(new Color(0, 70, 130)); // Dark blue color

		headerPanel.add(titleLabel);
		headerPanel.add(Box.createVerticalStrut(10));
		headerPanel.add(uniNameLabel);

		contentPanel.add(headerPanel, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel(new GridLayout(5, 5, 20, 20));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// creating buttons
		JButton studentButton = createModeButton("Modo Aluno:", "../assets/student.png");
		JButton classButton = createModeButton("Modo Turma:", "../assets/teacher_icon.png");
		JButton adminButton = createModeButton("Modo Administrador", "../assets/admin_icon.png");
		JButton teacherButton = createModeButton("Modo professor", "../assets/teacher_icon.png");
		JButton exitButton = createModeButton("Sair", "../assets/exit_icon.png");

		// Add action listeners
		studentButton.addActionListener(e -> openStudentMode(frame));
		classButton.addActionListener(e -> OpenClassMode(frame));
		adminButton.addActionListener(e -> openAdminMode(frame));
		teacherButton.addActionListener(e -> openAdminMode(frame));
		exitButton.addActionListener(e -> System.exit(0));

		buttonPanel.add(studentButton);
		buttonPanel.add(classButton);
		buttonPanel.add(adminButton);
		buttonPanel.add(exitButton);

		contentPanel.add(buttonPanel, BorderLayout.CENTER);

		// Add footer
		JLabel footerLabel = new JLabel("<<<------------------------------------------------>>>>",
				JLabel.CENTER);
		footerLabel.setFont(new Font("Sans-serif", Font.PLAIN, 12));
		contentPanel.add(footerLabel, BorderLayout.SOUTH);

		frame.add(contentPanel);
		frame.setLocationRelativeTo(null); // Center on screen
		frame.setVisible(true);
	}

	private static JButton createModeButton(String text, String iconPath) {
		JButton button = new JButton(text);
		button.setFont(new Font("Sans-serif", Font.BOLD, 16));
		button.setPreferredSize(new Dimension(200, 60));
		button.setFocusPainted(false);

		try {
			ImageIcon icon = new ImageIcon(iconPath);
			if (icon.getImage() != null) {
				button.setIcon(icon);
				button.setHorizontalTextPosition(SwingConstants.LEFT);
				button.setIconTextGap(15);
			}
		} catch (Exception e) {
			// Icon not found, proceed without it
		}

		return button;
	}

	private static void openStudentMode(JFrame parent) {
		// implement student register here
	}

	private static void OpenClassMode(JFrame parent) {
	// :TODO: fix addclasses not saving to csv !!!
	// parent.getContentPane().removeAll();
	// 	AddClasses classMode = new AddClasses(parent);
	//	parent.add(classMode.getPanel());
	//	parent.revalidate();

	}

	private static void openAdminMode(JFrame parent) {
		// implement amdin mode here
	}
}
