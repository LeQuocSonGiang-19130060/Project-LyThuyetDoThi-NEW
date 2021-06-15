package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import control.ConvertBetWeenGraphAndMainPanel;
import control.ConvertBetweenShapAndGUI;
import shape.Edge;

import shape.*;

public class MainPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Point> listPoint;
	private List<Edge> listEdge;

	private Point pointSelecting; // Chi diem dang duoc chon
	private Edge edgeSelecting;

	private int x, y; // dung de chi toa do chuot,......
	private int nameOfPoint = 0; // ten cua 1 dinh bat ky
	private boolean isCreatingEdge = false;
	private boolean isCreatingPoint = false;
	private boolean isDirectionOfLineToMouse = false;
	private boolean isDirection = false;
	// private boolean isDeletePoint = false;
	private boolean isCreatingEdgeByGUI = false;
	private boolean isCreatingPointByGUI = false;
	private boolean isDeletePointByGUI = false;
	private boolean isDeleteEdgeByGUI = false;
	private GUIMain guiMain;
	private boolean isBrowseDFS = false;
	private boolean isBrowseBFS = false;

	private MouseHandling mouseEvent; // tong hop su ly cac su kien chuot

	private JPopupMenu menuOfPoint, menuOfEdge, menuOfPanel;

	private ConvertBetweenShapAndGUI convert;
	private ConvertBetWeenGraphAndMainPanel convertGraph;
	private GUIBrowse guiBrowse;

	public MainPanel(GUIMain gui) {
		guiMain = gui;
		setBackground(new Color(224, 224, 224));

		listPoint = new ArrayList<Point>();
		listEdge = new ArrayList<Edge>();

		mouseEvent = new MouseHandling();
		addMouseListener(mouseEvent);
		addMouseMotionListener(mouseEvent);
		addMouseWheelListener(mouseEvent);

		convert = new ConvertBetweenShapAndGUI();
		convertGraph = new ConvertBetWeenGraphAndMainPanel();

		/* Them cac POPUMENU */
		addPopuMenu();
		// demo();
	}

//	public boolean inside(int x, int y) {
//		return this.getBounds().contains(x, y);
//	}

	/**
	 * Dung de thu nghiem : DEMO
	 */
	public void demo() {

		Point a = new Point(300, 300, "11");
		Point b = new Point(200, 200, "29");
		Point c = new Point(300, 200, "2");
		listPoint.add(a);
		listPoint.add(b);
		listPoint.add(c);
	}

	/**
	 * Dinh nghia cac menu cho cac Point, Edge, Panel
	 */
	public void addPopuMenu() {
		// Menu danh cho Panel, khi nhan phai chuot vao panel thi menu se duoc kich hoat
		menuOfPanel = new JPopupMenu();
		JMenuItem addPoint = new JMenuItem("New Point");
		menuOfPanel.add(addPoint);
		JMenu addEdge = new JMenu("New Edgd");
		menuOfPanel.add(addEdge);
		JMenuItem addEdgeNoDirection = new JMenuItem("DirectionLess");
		addEdge.add(addEdgeNoDirection);
		JMenuItem addEdgeDirection = new JMenuItem("Direction");
		addEdge.add(addEdgeDirection);
		JMenu browser = new JMenu("Browser");
		menuOfPanel.add(browser);
		JMenuItem bfs = new JMenuItem("By BFS");
		browser.add(bfs);
		JMenuItem dfs = new JMenuItem("By DFS");
		browser.add(dfs);
		JMenuItem floyed = new JMenuItem("Floyed");
		menuOfPanel.add(floyed);

		/** Event for MainPanel */
		addPoint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createNewPoint(x, y);
			}
		});
		/** Create Edge UnDirection */
		addEdgeNoDirection.addActionListener(new ActionListener() {
			// Khong the tao duoc canh vi chua biet duoc 2 dinh, vi the lay no lam cong tac
			@Override
			public void actionPerformed(ActionEvent e) {
				isCreatingEdge = true;
				isDirectionOfLineToMouse = false;
				isDirection = false;
			}
		});
		/** create Edge Direction */
		addEdgeDirection.addActionListener(new ActionListener() {
			// Khong the tao duoc canh vi chua biet duoc 2 dinh, vi the lay no lam cong tac
			@Override
			public void actionPerformed(ActionEvent e) {
				isCreatingEdge = true;
				isDirectionOfLineToMouse = true;
				isDirection = true;
			}
		});
		/** BrowseDFS */
		dfs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isBrowseDFS = true;
				guiBrowse = new GUIBrowse("Browse DFS");
				guiBrowse.setVisible(true);
			}
		});
		/** BrowseBFS */
		bfs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isBrowseBFS = true;
			}
		});

		// Menu danh cho Point, khi nhan phai chuot vao Point thi menu se duoc kich hoat
		menuOfPoint = new JPopupMenu();
		JMenuItem delete = new JMenuItem("Delete");
		menuOfPoint.add(delete);
		JMenu addE = new JMenu("New Edge");
		menuOfPoint.add(addE);
		JMenuItem addEdgeNoDirection2 = new JMenuItem("DirectionLess");
		addE.add(addEdgeNoDirection2);
		JMenuItem addEdgeDirection2 = new JMenuItem("Direction");
		addE.add(addEdgeDirection2);
		JMenu browser2 = new JMenu("Browser");
		menuOfPoint.add(browser2);
		JMenuItem bfs2 = new JMenuItem("By BFS");
		browser2.add(bfs2);
		JMenuItem dfs2 = new JMenuItem("By DFS");
		browser2.add(dfs2);
		JMenuItem floyed2 = new JMenuItem("Floyed");
		menuOfPoint.add(floyed2);

		/** Event for POINT */
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deletePoint(pointSelecting);
			}
		});
		addEdgeNoDirection2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				isCreatingEdge = true;
				isDirectionOfLineToMouse = false;
				isDirection = false;
			}
		});
		dfs2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DFS(pointSelecting);
			}
		});
		bfs2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BFS(pointSelecting);
			}
		});
		/** create Edge Direction */
		addEdgeDirection2.addActionListener(new ActionListener() {
			// Khong the tao duoc canh vi chua biet duoc 2 dinh, vi the lay no lam cong tac
			@Override
			public void actionPerformed(ActionEvent e) {
				isCreatingEdge = true;
				isDirectionOfLineToMouse = true;
				isDirection = true;
			}
		});

		// Menu danh cho Point, khi nhan phai chuot vao Edge thi menu se duoc kich hoat
		menuOfEdge = new JPopupMenu();
		JMenuItem delEdge = new JMenuItem("Delete");
		menuOfEdge.add(delEdge);
		JMenuItem addWeight = new JMenuItem("Set Weight");
		menuOfEdge.add(addWeight);

		/** Delete Edge */
		delEdge.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteEdge(edgeSelecting);
				edgeSelecting = null;
			}
		});
		addWeight.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean valid = true;
				while (valid) {
					try {
						valid = false;
						int w = Integer.parseInt(JOptionPane.showInputDialog("Enter weight for this Edge"));
						setWeightForEdge(w);
					} catch (Exception ex) {
						valid = true;
					}
				}
			}
		});
	}

	/*
	 * neu khong co repaint, thi setBackground cho Panel k co hieu luc
	 */
	public void paint(Graphics g) {
		super.paint(g);

		for (Point p : listPoint) {
			paintNewPoint(g, p);
			if (!p.inside(x, y) && isCreatingPointByGUI) {
				paintPointFollowMouse(g, x, y);
			}
		}
		for (Edge e : listEdge) {
			paintNewEdge(g, e);
			if (e.getWeight() != 0) {
				paintWeighForEdge(g, e);
			}
			e.update();
		}

		if (pointSelecting != null && (isCreatingEdge || isCreatingEdgeByGUI)) {
			paintLineToMouse(g, x, y, isDirectionOfLineToMouse);
		}

		repaint();
	}

	/**
	 * Ve 1 diem moi Can bổ sung vẽ các đỉnh có số thứ tự lớn hơn 9
	 */
	public void paintNewPoint(Graphics g, Point p) {

		convert.paintNewPoint(g, p);
	}

	/**
	 * ve mot canh/ co the vo huong hoac co huong
	 */
	public void paintNewEdge(Graphics g, Edge e) {

		convert.paintNewEdge(g, e);
	}

	/**
	 * tao mot dinh moi. this name of Point maybe smaller than Point last
	 */
	public void createNewPoint(int x, int y) {

		Point result = new Point(x, y, nameOfPoint + "");
		if (!listPoint.contains(result)) {
			listPoint.add(result);
			nameOfPoint++;
			convertGraph.addPoint();
			System.out.println(convertGraph.toString());
		}
	}

	/**
	 * tao 1 canh moi, co the la vo huong hoac co huong
	 */
	public void createNewEdge(Point a, Point b, boolean isDirection) {
		Edge e = new Edge(a, b);
		e.setDirection(isDirection);
		if (!listEdge.contains(e)) { // tam thoi chi cho don do thi thoi
			listEdge.add(e);
			int indexA = listPoint.indexOf(a);
			int indexB = listPoint.indexOf(b);
			convertGraph.addEdge(indexA, indexB, isDirection);
			System.out.println(convertGraph.toString());
		}
	}

	/**
	 * Ve 1 diem di theo chuot khi chon Create Point in GUI
	 */
	public void paintPointFollowMouse(Graphics g, int x, int y) {
		g.setColor(new Color(153, 153, 255));
		g.fillOval(x - 12, y - 12, 25, 25);
		g.setColor(Color.black);
	}

	/**
	 * ve 1 duong thang tu 1 dinh den con tro chuot, khi dang ve canh
	 */
	public void paintLineToMouse(Graphics g, int x, int y, boolean isDirection) {
		convert.paintLineToMouse(g, pointSelecting, x, y, isDirection);
		repaint();
	}

	public void paintWeighForEdge(Graphics g, Edge e) {
		convert.paintWeightForEdge(g, e);
	}

	/**
	 * Xoa 1 diem
	 */
	public void deletePoint(Point p) {

		deleteEdge(p, null);
		pointSelecting = null;
		int index = listPoint.indexOf(p);
		System.err.println(index);
		convertGraph.deletePoint(index);
		listPoint.remove(p);
		System.out.println(convertGraph.toString());
	}

	/**
	 * Xoa 1 canh
	 */
	public void deleteEdge(Point a, Point b) {
		if (b != null) {
			ArrayList<Edge> listRemove = new ArrayList<Edge>();
			for (int i = 0; i < listEdge.size(); i++) {
				if (listEdge.get(i).getA().equals(a) || listEdge.get(i).getB().equals(a)
						|| listEdge.get(i).getA().equals(b) || listEdge.get(i).getB().equals(b)) {
					listRemove.add(listEdge.get(i));
				}
			}
			listEdge.removeAll(listRemove);

		} else {
			ArrayList<Edge> listRemove = new ArrayList<Edge>();
			for (int i = 0; i < listEdge.size(); i++) {
				if (listEdge.get(i).getA().equals(a) || listEdge.get(i).getB().equals(a)) {
					listRemove.add(listEdge.get(i));
				}
			}
			listEdge.removeAll(listRemove);
		}

		System.out.println(convertGraph.toString());
	}

	public void deleteEdge(Edge e) {
		listEdge.remove(e);
		edgeSelecting = null;
		int indexA = listPoint.indexOf(e.getA());
		int indexB = listPoint.indexOf(e.getB());
		convertGraph.deleteEdge(indexA, indexB);
		System.out.println(convertGraph.toString());
	}

	public void setWeightForEdge(int w) {
		edgeSelecting.setWeight(w);
		int a = listPoint.indexOf(edgeSelecting.getA());
		int b = listPoint.indexOf(edgeSelecting.getB());
		convertGraph.setWeight(a, b, w);
		edgeSelecting = null;
	}

	/**
	 * When GUI click button Create Point
	 */
	public void setIsCreatePoint(boolean s) {
		isCreatingPointByGUI = s;
	}

	/**
	 * When GUI click button Create Edge
	 */
	public void setIsCreateEdge(boolean s, boolean isDirection) {
		isCreatingEdgeByGUI = s;

		if (!isCreatingEdgeByGUI || this.isDirection != isDirection) {
			pointSelecting = null;
		}
		this.isDirection = isDirection;
	}

	/**
	 * When Click button Del Point
	 */
	public void setIsDeletePoint(boolean s) {
		this.isDeletePointByGUI = s;
	}

	/**
	 * When Click button Del Edge
	 */
	public void setIsDeleteEdge(boolean s) {
		this.isDeleteEdgeByGUI = s;
	}

	public void DFS(Point firstPoint) {
		int index = listPoint.indexOf(firstPoint);
		convertGraph.DFS(index);
	}

	public void BFS(Point firstPoint) {
		int index = listPoint.indexOf(firstPoint);
		convertGraph.BFS(index);
	}

	/**
	 * this class use handling event mouse
	 * 
	 * @author SONGIANG-PRIME
	 *
	 */
	private class MouseHandling extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			// Case 1: isCreatingPoint = true;
			if (isCreatingPoint) {
				createNewPoint(x, y);
				isCreatingPoint = false;
			} else if (isCreatingPointByGUI) {
				createNewPoint(x - 12, y - 12);
			} else if (isCreatingEdge || isCreatingEdgeByGUI) {
				if (pointSelecting == null) {
					for (Point p : listPoint) {
						if (p.inside(x, y)) {
							pointSelecting = p;
							break;
						}
					}
				} else {
					for (Point p : listPoint) {
						if (p.inside(x, y)) {
							createNewEdge(pointSelecting, p, isDirection);
						}
					}
					pointSelecting = null;
					isCreatingEdge = false;
				}
			} else if (isDeletePointByGUI) {
				for (Point p : listPoint) {
					if (p.inside(x, y)) {
						deletePoint(p);
						break;
					}
				}
			} else if (isDeleteEdgeByGUI) {
				for (Edge edge : listEdge) {
					if (edge.inSide(x, y)) {
						deleteEdge(edge);
						break;
					}
				}
			} else if (isBrowseDFS) {
				for (Point p : listPoint) {
					if (p.inside(x, y)) {
						DFS(p);
						isBrowseDFS = false;
					}
				}
			} else if (isBrowseBFS) {
				for (Point p : listPoint) {
					if (p.inside(x, y)) {
						BFS(p);
						isBrowseBFS = false;
					}
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			x = e.getX();
			y = e.getY();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			if (e.isPopupTrigger()) {
				guiMain.resetButton();
				menuOfPanel.show(e.getComponent(), e.getX(), e.getY());
				for (Edge edge : listEdge) {
					if (edge.inSide(x, y)) {
						menuOfEdge.show(e.getComponent(), e.getX(), e.getY());
						edgeSelecting = edge;
					}
				}
				for (Point p : listPoint) {
					if (p.inside(x, y)) {
						menuOfPoint.show(e.getComponent(), e.getX(), e.getY());
						pointSelecting = p;
					}
				}

			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			int dx = e.getX() - x;
			int dy = e.getY() - y;
			for (Point p : listPoint) {
				if (p.inside(x, y)) {
					p.setX(dx);
					p.setY(dy);
					repaint();
					break;
				}
			}
			x += dx;
			y += dy;
		}
	}

}
