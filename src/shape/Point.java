package shape;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Point extends Ellipse2D.Double {

	private static final long serialVersionUID = 1L;
	private String name;
	private double radius = 12.5;
	private Color color = Color.white;

	public Point(float x, float y, String n) {
		setFrame(x, y, 25, 25);
		this.name = n;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setX(float x) {
		this.x += x;
	}

	public void setY(float y) {
		this.y += y;
	}

	public String getName() {
		return this.name;
	}

	public double getRadius() {
		return radius;
	}

	public String toString() {
		return x + ":" + y;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public boolean inside(float x, float y) {
		return getBounds2D().contains(x, y);
	}

}

class CentralPoint extends Point2D.Float {

	public CentralPoint(float x, float y) {
		super(x, y);
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public void setLocation(double x, double y) {
		this.x = (float) x;
		this.y = (float) y;
	}

}
