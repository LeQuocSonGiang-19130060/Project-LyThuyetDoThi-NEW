package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GUIMain extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_2;
	private JButton btCreatePoint;
	private JButton btEdgeUnDirection;
	private JButton btEdgeDirection;
	private JPanel panel_3;
	private JButton btDeletePoint;
	private JButton btDeleteEdge;
	private JPanel panel_4;
	private JButton btBrowserDFS;
	private JButton btBrowserBFS;
	private JPanel panel_5;
	private JButton btFloyd;
	private JPanel panel_6;
	private MainPanel mainPanel;
	private List<JButton> listButton;
//	private MouseHandling mouseEvent = new MouseHandling();

	private int x, y;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIMain frame = new GUIMain();
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
	public GUIMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 450);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
//		addMouseMotionListener(mouseEvent);
//		addMouseListener(mouseEvent);
//		addMouseWheelListener(mouseEvent);

		panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(180, 180, 180), null,
				SystemColor.activeCaptionBorder, null));
		panel.setBounds(10, 10, 261, 393);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlHighlight);
		panel_1.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Create",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 120, 215)));
		panel_1.setBounds(10, 10, 241, 120);
		panel.add(panel_1);
		panel_1.setLayout(null);

		btCreatePoint = new JButton("Point");
		btCreatePoint.setBounds(13, 20, 215, 25);
		panel_1.add(btCreatePoint);

		panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.controlHighlight);
		panel_2.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Edge",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 51, 221, 59);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		btEdgeUnDirection = new JButton("UnDirection");
		btEdgeUnDirection.setBounds(13, 20, 95, 25);
		panel_2.add(btEdgeUnDirection);

		btEdgeDirection = new JButton("Direction");
		btEdgeDirection.setBounds(113, 20, 95, 25);
		panel_2.add(btEdgeDirection);

		panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.controlHighlight);
		panel_3.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Delete",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 120, 215)));
		panel_3.setBounds(10, 140, 241, 57);
		panel.add(panel_3);
		panel_3.setLayout(null);

		btDeletePoint = new JButton("Point");
		btDeletePoint.setBounds(23, 20, 95, 25);
		panel_3.add(btDeletePoint);

		btDeleteEdge = new JButton("Edge");
		btDeleteEdge.setBounds(123, 20, 95, 25);
		panel_3.add(btDeleteEdge);

		panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Browser",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 120, 215)));
		panel_4.setBackground(SystemColor.controlHighlight);
		panel_4.setBounds(10, 207, 241, 57);
		panel.add(panel_4);

		btBrowserDFS = new JButton("DFS");
		btBrowserDFS.setBounds(23, 20, 95, 25);
		panel_4.add(btBrowserDFS);

		btBrowserBFS = new JButton("BFS");
		btBrowserBFS.setBounds(123, 20, 95, 25);
		panel_4.add(btBrowserBFS);

		panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				"Shortest path", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 120, 215)));
		panel_5.setBackground(SystemColor.controlHighlight);
		panel_5.setBounds(10, 274, 241, 60);
		panel.add(panel_5);
		panel_5.setLayout(null);

		btFloyd = new JButton("Floyd");
		btFloyd.setBounds(52, 20, 137, 25);
		panel_5.add(btFloyd);

		panel_6 = new JPanel();
		panel_6.setBorder(new BevelBorder(BevelBorder.RAISED, SystemColor.windowText, null, null, null));
		panel_6.setBounds(281, 10, 495, 393);
		contentPane.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		mainPanel = new MainPanel(this);
		panel_6.add(mainPanel);

		listButton = new ArrayList<JButton>();
		listButton.add(btBrowserBFS);
		listButton.add(btBrowserDFS);
		listButton.add(btCreatePoint);
		listButton.add(btDeleteEdge);
		listButton.add(btDeletePoint);
		listButton.add(btEdgeDirection);
		listButton.add(btEdgeUnDirection);
		listButton.add(btFloyd);

		for (JButton bt : listButton) {
			bt.addActionListener(this);
			bt.setBackground(new Color(216, 228, 243));
			bt.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		}
//		panel_6.addMouseMotionListener(mouseEvent);
//		panel_6.addMouseListener(mouseEvent);
//		panel_6.addMouseWheelListener(mouseEvent);
//		panel.addMouseMotionListener(mouseEvent);
//		panel.addMouseListener(mouseEvent);
//		panel.addMouseWheelListener(mouseEvent);
	}
//
//	private class MouseHandling extends MouseAdapter {
//		@Override
//		public void mouseMoved(MouseEvent e) {
//			x = e.getX();
//			y = e.getY();
//			if(mainPanel.setInside(x, y)) {
//				mainPanel.isInside = true;
//			}else {
//				mainPanel.isInside = false;
//			}
//				
//			
//		}
//	}
	
	public void resetButton() {
		for(JButton bt :listButton) {
			bt.setBackground(new Color(216, 228, 243));
		}
		mainPanel.setIsCreatePoint(false);
		mainPanel.setIsCreateEdge(false, false);
		mainPanel.setIsDeletePoint(false);
		mainPanel.setIsDeleteEdge(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (JButton bt : listButton) {
			if (e.getSource().equals(bt)) {
				if (!bt.getBackground().equals(new Color(165, 194, 233))) {
					bt.setBackground(new Color(165, 194, 233));
				} else {
					bt.setBackground(new Color(216, 228, 243));
				}

			} else {
				bt.setBackground(new Color(216, 228, 243));
			}
		}

		if (e.getSource().equals(btCreatePoint)) {
			if (btCreatePoint.getBackground().equals(new Color(165, 194, 233))) {
				mainPanel.setIsCreatePoint(true);
			} else {
				mainPanel.setIsCreatePoint(false);
			}
			mainPanel.setIsCreateEdge(false, false);
			mainPanel.setIsDeletePoint(false);
			mainPanel.setIsDeleteEdge(false);
		} else if (e.getSource().equals(btEdgeUnDirection)) {// Edge UnDirection
			if (btEdgeUnDirection.getBackground().equals(new Color(165, 194, 233))) {
				mainPanel.setIsCreateEdge(true, false);
			} else {
				mainPanel.setIsCreateEdge(false, false);
			}

			mainPanel.setIsCreatePoint(false);
			mainPanel.setIsDeletePoint(false);
			mainPanel.setIsDeleteEdge(false);
		} else if (e.getSource().equals(btEdgeDirection)) { // Edge Direction
			if (btEdgeDirection.getBackground().equals(new Color(165, 194, 233))) {
				mainPanel.setIsCreateEdge(true, true);
			} else {
				mainPanel.setIsCreateEdge(false, true);
			}

			mainPanel.setIsCreatePoint(false);
			mainPanel.setIsDeletePoint(false);
			mainPanel.setIsDeleteEdge(false);
		} else if (e.getSource().equals(btDeletePoint)) { // Delete Point
			if (btDeletePoint.getBackground().equals(new Color(165, 194, 233))) {
				mainPanel.setIsDeletePoint(true);
			} else {
				mainPanel.setIsDeletePoint(false);
			}

			mainPanel.setIsCreatePoint(false);
			mainPanel.setIsCreateEdge(false, false);
			mainPanel.setIsDeleteEdge(false);
		} else if (e.getSource().equals(btDeleteEdge)) {
			if (btDeleteEdge.getBackground().equals(new Color(165, 194, 233))) {
				mainPanel.setIsDeleteEdge(true);
			} else {
				mainPanel.setIsDeleteEdge(false);
			}

			mainPanel.setIsCreatePoint(false);
			mainPanel.setIsCreateEdge(false, false);
			mainPanel.setIsDeletePoint(false);
		} else if (e.getSource().equals(btBrowserDFS)) {

			mainPanel.setIsCreatePoint(false);
			mainPanel.setIsCreateEdge(false, false);
			mainPanel.setIsDeletePoint(false);
			mainPanel.setIsDeleteEdge(false);
		} else if (e.getSource().equals(btBrowserBFS)) {

			mainPanel.setIsCreatePoint(false);
			mainPanel.setIsCreateEdge(false, false);
			mainPanel.setIsDeletePoint(false);
			mainPanel.setIsDeleteEdge(false);
		} else if (e.getSource().equals(btFloyd)) {

			mainPanel.setIsCreatePoint(false);
			mainPanel.setIsCreateEdge(false, false);
			mainPanel.setIsDeletePoint(false);
			mainPanel.setIsDeleteEdge(false);
		} else {

			mainPanel.setIsCreatePoint(false);
			mainPanel.setIsCreateEdge(false, false);
			mainPanel.setIsDeletePoint(false);
			mainPanel.setIsDeleteEdge(false);
		}

	}

}
