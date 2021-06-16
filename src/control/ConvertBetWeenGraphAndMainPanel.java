package control;

import algorithm.tuan1.Graph;
import algorithm.tuan2.BrowseGraph;
import algorithm.tuan5.Floyd;
import gui.GUIBrowse;

public class ConvertBetWeenGraphAndMainPanel {

	private Graph matrix;
	private BrowseGraph browseGraph;
	private GUIBrowse guiBrowse;
	private Floyd floyd;
	public ConvertBetWeenGraphAndMainPanel() {
		matrix = new Graph();
		browseGraph = new BrowseGraph(matrix);
		floyd = new Floyd(matrix);
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

	public void setWeight(int indexA, int indexB, int weight) {
		matrix.updateEdge(indexA, indexB, weight);
	}

	public void DFS(int index) {
		System.err.println(browseGraph.DFS(index));
		guiBrowse = new GUIBrowse("Browse DFS");
		guiBrowse.setLabel(browseGraph.DFS(index));
		guiBrowse.setVisible(true);
	}

	public void BFS(int index) {
		System.err.println(browseGraph.BFS(index));
		guiBrowse = new GUIBrowse("Browse BFS");
		guiBrowse.setLabel(browseGraph.BFS(index));
		guiBrowse.setVisible(true);
	}

	public String toString() {
		return matrix.printMatrix();
	}

	public boolean isSide(int a, int b) {
		return matrix.isSideForPaintColor(a, b);
	}
	
	public void floyd(int a, int b) {
		guiBrowse = new GUIBrowse("Floyd");
		guiBrowse.setLabel(floyd.shortestPathExtend(a, b));
		guiBrowse.setVisible(true);
	}
}
