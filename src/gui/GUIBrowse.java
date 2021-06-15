package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class GUIBrowse extends JFrame {

	private JPanel contentPane;
	private JLabel jLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIBrowse frame = new GUIBrowse("Browse");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIBrowse(String title) {
		super(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		jLabel = new JLabel("");
		jLabel.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(jLabel);
		if (title.contains("Browse")) {
			browse();
		} else {
			shortestPath();
		}
	}

	public void browse() {
		setSize(450, 83);
	}

	public void shortestPath() {
		setSize(450, 250);
	}

	public void setLabel(String title) {
		jLabel.setText(title);
	}

}
