package shape;

import java.awt.geom.Line2D;

public class Edge extends Line2D.Float {

	private static final long serialVersionUID = 1L;
	private Point a, b;
//	private int ax, ay, bx, by;
	private boolean isDirection = false;
	private int weight = 0;

	public Edge(Point a, Point b) {
		this.a = a;
		this.b = b;
		update();
		this.setLine(new CentralPoint(x1, y1), new CentralPoint(x2, y2));

	}

	public void update() {
		double radius = a.getRadius();

		double Ax = a.getX() + radius;
		double Ay = a.getY() + radius;
		double Bx = b.getX() + radius;
		double By = b.getY() + radius;

		double d = Math.sqrt(Math.pow(Ax - Bx, 2) + Math.pow(Ay - By, 2));
		double AM = Math.abs(((radius) * (Ay - By) / d));
		// int CM = (int)Math.sqrt(radius*radius - AM*AM);
		double CM;
		if (AM == 0) {
			CM = radius;
		} else {
			CM = Math.abs(AM * (Ax - Bx) / (Ay - By));
		}

		if (Ax < Bx && Ay < By) {
			x1 = (int) (Ax + CM);
			y1 = (int) (Ay + AM);
			x2 = (int) (Bx - CM);
			y2 = (int) (By - AM);
		} else if (Ax > Bx && Ay > By) {
			x1 = (int) (Ax - CM);
			y1 = (int) (Ay - AM);
			x2 = (int) (Bx + CM);
			y2 = (int) (By + AM);
		} else if (Ax < Bx && Ay > By) {
			x1 = (int) (Ax + CM);
			y1 = (int) (Ay - AM);
			x2 = (int) (Bx - CM);
			y2 = (int) (By + AM);
		} else if (Ax > Bx && Ay < By) {
			x1 = (int) (Ax - CM);
			y1 = (int) (Ay + AM);
			x2 = (int) (Bx + CM);
			y2 = (int) (By - AM);
		} else if (Ay == By && Ax > Bx) {
			x1 = (int) (Ax - CM);
			x2 = (int) (Bx + CM);
		} else if (Ay == By && Ax < Bx) {
			x1 = (int) (Ax + CM);
			x2 = (int) (Bx - CM);
		}

	}

	public Point getA() {
		return a;
	}

	public void setA(Point a) {
		this.a = a;
	}

	public Point getB() {
		return b;
	}

	public void setB(Point b) {
		this.b = b;
	}

	public boolean isDirection() {
		return isDirection;
	}

	public void setDirection(boolean isDirection) {
		this.isDirection = isDirection;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public boolean inSide(int x, int y) {
		return getBounds2D().contains(x, y);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Edge o = (Edge) obj;
		if ((this.a.equals(o.a) && this.b.equals(o.b)) || (this.a.equals(o.b) && this.b.equals(o.a))) {
			return true;
		}
		return false;
	}

}
