package algorithm.tuan4;

public class Edge implements Comparable<Edge> {

	private int pointA, pointB, weight;

	public Edge(int a, int b, int weight) {
		pointA = a;
		pointB = b;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		if (this.weight == o.weight)
			return 0;
		if (this.weight > o.weight)
			return 1;
		return -1;
	}
	@Override
	public String toString() {
		return weight+"";
	}

	public int getPointA() {
		return pointA;
	}

	public void setPointA(int pointA) {
		this.pointA = pointA;
	}

	public int getPointB() {
		return pointB;
	}

	public void setPointB(int pointB) {
		this.pointB = pointB;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
}
