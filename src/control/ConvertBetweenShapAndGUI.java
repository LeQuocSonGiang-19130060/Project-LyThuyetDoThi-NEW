package control;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import gui.MainPanel;
import shape.Edge;
import shape.Point;

public class ConvertBetweenShapAndGUI {

	public ConvertBetweenShapAndGUI() {

	}

	/**
	 * dung de ve cac diem
	 * 
	 * @param g : Graphics
	 * @param p : Point
	 */
	public void paintNewPoint(Graphics g, Point p) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setStroke(new BasicStroke(3));
		g2.setColor(new Color(255, 255, 255));
		g2.fillOval((int) p.getX(), (int) p.getY(), 25, 25);
		g2.setColor(Color.black);
		g2.setFont(new Font("Arial", Font.BOLD, 15));
		// g2.drawRect((int)p.getX(), (int)p.getY(), 25, 25);
		g2.draw(p);
		if (Integer.parseInt(p.getName()) > 9) {
			g2.drawString(p.getName(), (int) p.getX() + 4, (int) p.getY() + 18);
		} else {
			g2.drawString(p.getName(), (int) p.getX() + 8, (int) p.getY() + 18);
		}
	}

	/**
	 * Dung de tao ra cac Edge. Don Do Thi
	 */
	public void paintNewEdge(Graphics g, Edge e) {
		if (e.isDirection()) {
			paintEdgeDirection(g, e);
		} else {
			paintEdgeUnDirection(g, e);
		}
	}

	public void paintEdgeUnDirection(Graphics g, Edge e) {
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(e);
		// g.drawLine(e.getAx(), e.getAy(), e.getBx(), e.getBy());
	}

	public void paintEdgeDirection(Graphics g, Edge e) {
		
		double AANG = Math.PI / 7;
		int ALEN = 13;
		int x1 = (int) e.getX1();
		int y1 = (int) e.getY1();
		int x2 = (int) e.getX2();
		int y2 = (int) e.getY2();
		double theta, len, side = 1;
		Point tip, side1, side2;

		// Figure out the angle and length
		theta = Math.atan((double) (y2 - y1) / (double) (x2 - x1));
		len = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));

		// Hack to get it to display correctly to the left of center
		if (x2 < x1)
			side = -1;
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(e);
		// Arrow at end
		tip = new Point((int) (x1 + side * len * Math.cos(theta)), (int) (y1 + side * len * Math.sin(theta)), null);
		side1 = new Point((int) (tip.x - side * ALEN * Math.cos(theta + AANG)),
				(int) (tip.y - side * ALEN * Math.sin(theta + AANG)), null);
		side2 = new Point((int) (tip.x - side * ALEN * Math.cos(theta - AANG)),
				(int) (tip.y - side * ALEN * Math.sin(theta - AANG)), null);

		g2.fillPolygon(new int[] { (int) tip.x, (int) side1.x, (int) side2.x },
				new int[] { (int) tip.y, (int) side1.y, (int) side2.y }, 3);
	}

	/**
	 * Tuong tu nhu ve cac canh, deu chia ra 2 truong hop la vo huong hay co huong
	 * 
	 * @param p
	 * @param x
	 * @param y
	 */
	public void paintLineToMouse(Graphics g, Point p, int x, int y, boolean isDirection) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setStroke(new BasicStroke(1));
		if (isDirection) {
			paintLineToMouseDirection(g, p, x, y);
		} else {
			paintLineToMouseUnDirection(g, p, x, y);
		}
	}

	public void paintLineToMouseUnDirection(Graphics g, Point a, int x, int y) {

		double radius = a.getRadius();

		double Ax = a.getX() + radius;
		double Ay = a.getY() + radius;
		double Bx = x;
		double By = y;

		double d = Math.sqrt(Math.pow(Ax - Bx, 2) + Math.pow(Ay - By, 2));
		double AM = Math.abs(((radius) * (Ay - By) / d));
		// int CM = (int)Math.sqrt(radius*radius - AM*AM);
		double CM;
		if (AM == 0) {
			CM = radius;
		} else {
			CM = Math.abs(AM * (Ax - Bx) / (Ay - By));
		}
		int ax = 0, ay = 0, bx = 0, by = 0;
		if (Ax < Bx && Ay < By) {
			ax = (int) (Ax + CM);
			ay = (int) (Ay + AM);
			bx = (int) (Bx - CM);
			by = (int) (By - AM);
		} else if (Ax > Bx && Ay > By) {
			ax = (int) (Ax - CM);
			ay = (int) (Ay - AM);
			bx = (int) (Bx + CM);
			by = (int) (By + AM);
		} else if (Ax < Bx && Ay > By) {
			ax = (int) (Ax + CM);
			ay = (int) (Ay - AM);
			bx = (int) (Bx - CM);
			by = (int) (By + AM);
		} else if (Ax > Bx && Ay < By) {
			ax = (int) (Ax - CM);
			ay = (int) (Ay + AM);
			bx = (int) (Bx + CM);
			by = (int) (By - AM);
		} else if (Ay == By && Ax > Bx) {
			ax = (int) (Ax - CM);
			bx = (int) (Bx + CM);
		} else if (Ay == By && Ax < Bx) {
			ax = (int) (Ax + CM);
			bx = (int) (Bx - CM);
		}

		g.drawLine(ax, ay, x, y);
		
	}

	public void paintLineToMouseDirection(Graphics g, Point a, int x, int y) {
		double radius = a.getRadius();

		double Ax = a.getX() + radius;
		double Ay = a.getY() + radius;
		double Bx = x;
		double By = y;

		double d = Math.sqrt(Math.pow(Ax - Bx, 2) + Math.pow(Ay - By, 2));
		double AM = Math.abs(((radius) * (Ay - By) / d));
		// int CM = (int)Math.sqrt(radius*radius - AM*AM);
		double CM;
		if (AM == 0) {
			CM = radius;
		} else {
			CM = Math.abs(AM * (Ax - Bx) / (Ay - By));
		}
		int ax = 0, ay = 0, bx = 0, by = 0;
		if (Ax < Bx && Ay < By) {
			ax = (int) (Ax + CM);
			ay = (int) (Ay + AM);
			bx = (int) (Bx - CM);
			by = (int) (By - AM);
		} else if (Ax > Bx && Ay > By) {
			ax = (int) (Ax - CM);
			ay = (int) (Ay - AM);
			bx = (int) (Bx + CM);
			by = (int) (By + AM);
		} else if (Ax < Bx && Ay > By) {
			ax = (int) (Ax + CM);
			ay = (int) (Ay - AM);
			bx = (int) (Bx - CM);
			by = (int) (By + AM);
		} else if (Ax > Bx && Ay < By) {
			ax = (int) (Ax - CM);
			ay = (int) (Ay + AM);
			bx = (int) (Bx + CM);
			by = (int) (By - AM);
		} else if (Ay == By && Ax > Bx) {
			ax = (int) (Ax - CM);
			bx = (int) (Bx + CM);
		} else if (Ay == By && Ax < Bx) {
			ax = (int) (Ax + CM);
			bx = (int) (Bx - CM);
		}

		g.drawLine(ax, ay, x, y);
	}

	public void paintWeightForEdge(Graphics g, Edge e) {
		int x = (int) (e.getX1() + e.getX2()) / 2 - 15;
		int y = (int) (e.getY1() + e.getY2()) / 2 - 15;
		g.drawString(e.getWeight() + "", x, y);
	}

}
