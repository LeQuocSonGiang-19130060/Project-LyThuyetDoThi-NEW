package algorithm.tuan1;

import java.util.ArrayList;

public class Graph {

	private ArrayList<ArrayList<Integer>> matrix;
	private boolean isDirectionGraph = false; // Graph is undirection
	private boolean isMultiGraph = false; // Graph is not multi graph
	private int connection = 1;

	public Graph() {
		matrix = new ArrayList<ArrayList<Integer>>();
	}

	public Graph(ArrayList<ArrayList<Integer>> mt) {
		matrix = mt;
	}

	public int getConnection() {
		return connection;
	}

	public void setConnection() {
		this.connection += 1;
	}

	public boolean isDirectionGraph() {
		return isDirectionGraph;
	}

	public int size() {
		return matrix.size();
	}

	public ArrayList<ArrayList<Integer>> getMatrix() {
		return matrix;
	}

	public void setDirectionGraph(boolean isDirectionGraph) {
		this.isDirectionGraph = isDirectionGraph;
	}

	public boolean isMultiGraph() {
		return isMultiGraph;
	}

	public void setMultiGraph(boolean multiGraph) {
		this.isMultiGraph = multiGraph;
	}

	/**
	 * add new Point
	 */
	public void addPoint() {
		if (matrix.isEmpty()) {
			matrix.add(new ArrayList<Integer>());
			matrix.get(0).add(0);
			return;
		}
		for (int i = 0; i < matrix.size(); i++) {
			matrix.get(i).add(0);
		}
		ArrayList<Integer> newRow = new ArrayList<Integer>();
		for (int i = 0; i <= matrix.size(); i++) {
			newRow.add(0);
		}
		matrix.add(newRow);
	}

	/**
	 * if you add edge is Direction then SET <isDirection> = true; if you add edge
	 * have point haved edge then SET <isMultiGraph> = true;
	 */
	public void addEdge(int a, int b, int weight, boolean isDirectionS) {
		if (isDirectionS) {
			matrix.get(a).set(b, weight);
			setDirectionGraph(true);
			if (matrix.get(a).get(b) > 1)
				setMultiGraph(true);
		} else {
			if (a != b) {
				matrix.get(a).set(b, weight);
				matrix.get(b).set(a, weight);
			}

			if (matrix.get(a).get(b) > 1 || matrix.get(b).get(a) > 1)
				setMultiGraph(true);
		}
	}

	/**
	 * if point A is side with point B then return true, else false;
	 */
	public boolean isSide(int a, int b) {
		return matrix.get(a).get(b) != 0 && a != b;
	}

	public boolean isSideForPaintColor(int a, int b) {
		return matrix.get(a).get(b) != 0 || matrix.get(b).get(a) != 0;
	}

	/**
	 * @return list side of point A
	 */
	public ArrayList<Integer> listOfSide(int a) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < matrix.size(); i++) {
			if (isSide(a, i))
				result.add(i);
		}
		return result;
	}

	/**
	 * @return deg of point. if graph is undirection then call method
	 *         degPointofUndirectionGraph, else degPointOfDirectionGraph
	 */
	public ArrayList<Integer> deg(int a) {
		if (isDirectionGraph) {
			return degPointOfDirection(a);
		} else {
			return degPointOfUndirection(a);
		}
	}

	/**
	 * deg point of direction graph
	 */
	public ArrayList<Integer> degPointOfUndirection(int a) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int deg = 0;
		for (int i = 0; i < matrix.size(); i++) {
			if (isSide(a, i))
				deg++;
		}
		result.add(deg);
		return result;
	}

	/**
	 * deg point of undirection graph
	 */
	public ArrayList<Integer> degPointOfDirection(int a) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int degIn = 0, degOut = 0;
		for (int i = 0; i < matrix.size(); i++) {
			if (isSide(a, i))
				degOut++;
		}
		for (int i = 0; i < matrix.size(); i++) {
			if (isSide(i, a))
				degIn++;
		}
		result.add(degIn);
		result.add(degOut);
		return result;
	}

	/**
	 * Check graph is continuity, if continuity is true, check graph is Strong
	 * continuity or weak continuity
	 */
	public String checkContinuity() {
		if (connection > 1) {
			return "The Graph is not continuity";
		} else {
			if (isDirectionGraph)
				return "The Graph is Weak continuity";
			return "The Graph is Strong continuity";
		}
	}

	/**
	 * Print the Matrix
	 */
	public String printMatrix() {
		String result = "";
		for (int i = 0; i < matrix.size(); i++) {
			for (int j = 0; j < matrix.size(); j++) {
				result += matrix.get(i).get(j) + " ";
			}
			result += "\n";
		}
		return result;
	}

	/**
	 * remote 1 point
	 */
	public void deletePoint(int x) {
		matrix.remove(x);
		for (ArrayList<Integer> list : matrix) {
			list.remove(x);
		}
	}

	/**
	 * delete 1 edge
	 */
	public void deleteEdge(int a, int b) {

		matrix.get(a).set(b, 0);

		matrix.get(b).set(a, 0);

	}

	public void updateEdge(int a, int b, int weight) {

		// da la canh thi weight mac dinh != 0
		if (matrix.get(a).get(b) != 0) {
			matrix.get(a).set(b, weight);
		}
		if (matrix.get(b).get(a) != 0) {
			matrix.get(b).set(a, weight);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		/* chua dung lam nha */
		Graph matrix = new Graph();
		matrix.addPoint();
		matrix.addPoint();
		matrix.addPoint();
		matrix.addPoint();
		matrix.addPoint();
		matrix.addPoint();
		matrix.addPoint();
		matrix.addPoint();
		matrix.addPoint();

		System.out.println(matrix.printMatrix());
		Thread.sleep(500);
		System.err.println("�?ồ thị có hướng: " + matrix.isDirectionGraph());
		Thread.sleep(500);
		System.err.println("�?a đô thị :" + matrix.isMultiGraph());
		System.err.println("Danh sách k�? của đỉnh số 3: " + matrix.listOfSide(3));
		System.err.println("Bậc của đính 3 là: " + matrix.deg(3));

	}

}
