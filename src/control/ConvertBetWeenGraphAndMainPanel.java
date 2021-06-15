package control;

import algorithm.tuan1.Graph;
import algorithm.tuan2.BrowseGraph;
import shape.Edge;
import shape.Point;

public class ConvertBetWeenGraphAndMainPanel {

	private Graph matrix;
	private BrowseGraph browseGraph;

	public ConvertBetWeenGraphAndMainPanel() {
		matrix = new Graph();
		browseGraph = new BrowseGraph(matrix);
	}

	public void addPoint() {
		matrix.addPoint();
	}

	public void addEdge(int a, int b, boolean isDirection) {
		
		matrix.addEdge(a, b, -1, isDirection);
	}

	public void deletePoint(int index) {
		
		matrix.deletePoint(index);
	}

	public void deleteEdge(int indexA, int indexB) {
		
		matrix.deleteEdge(indexA, indexB);

		// xoa 1 diem thi khong can xoa canh, vi canh nam trong dinh

	}
	
	public void DFS(int index) {
		System.err.println(browseGraph.DFS(index));   ;
	}
	public void BFS(int index) {
		System.err.println(browseGraph.BFS(index));   ;
	}

	public String toString() {
		return matrix.printMatrix();
	}
}
