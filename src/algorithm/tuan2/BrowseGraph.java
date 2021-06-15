package algorithm.tuan2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import algorithm.tuan1.*;

public class BrowseGraph {

	private Graph graph;

	public BrowseGraph(Graph gr) {
		graph = gr;
	}

	/**
	 * DFS by STACK
	 */
	public String DFS(int point) {
		String result = "";
		Stack<Integer> st = new Stack<Integer>();
		boolean[] isVisit = new boolean[graph.size()];
//		for (boolean item : isVisit) {
//			item = false;
//		}
		st.push(point);
		isVisit[point] = true;
		for (int i = 0; i <= graph.size(); i++) {
			while (!st.isEmpty()) {
				Integer p = st.pop();
				result += p; // browse point
				/* Browse all point side p */
				ArrayList<Integer> listSide = graph.listOfSide(p);
				for (Integer pointSide : listSide) {
					if (!isVisit[pointSide]) {
						st.push(pointSide);
						isVisit[pointSide] = true;
					}
				}
			}
			if (i < graph.size()) {
				if (!isVisit[i]) {
					st.push(i);
					isVisit[i] = true;
					graph.setConnection();
				}
			}
		}
		return result;
	}

	public String toString() {
		return graph.printMatrix();
	}

	/**
	 * BFS by QUEUE
	 */
	public String BFS(int point) {
		String result = "";
		Queue<Integer> que = new LinkedList<Integer>();
		boolean[] isVisit = new boolean[graph.size()];
//		for (boolean item : isVisit) {
//			item = false;
//		}
		isVisit[point] = true;
		que.add(point);
		for (int i = 0; i < graph.size() + 1; i++) {
			while (!que.isEmpty()) {
				Integer p = que.poll();
				result += p; // browse p
				ArrayList<Integer> listSideP = graph.listOfSide(p);
				for (Integer sideP : listSideP) {
					if (!isVisit[sideP]) {
						que.add(sideP);
						isVisit[sideP] = true;
					}
				}
			}
			if (i < graph.size()) {
				if (!isVisit[i]) {
					que.add(i);
					isVisit[i] = true;
				}
			}
		}
		return result;
	}
	
	

	/**
	 * run app and test
	 * 
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
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
		/*
		 * matrix.addEdge(0, 5, false); matrix.addEdge(2, 1, true); matrix.addEdge(1, 4,
		 * false); matrix.addEdge(2, 4, false); matrix.addEdge(4, 3, true);
		 * matrix.addEdge(4, 5, true); matrix.addEdge(6, 7, true); matrix.addEdge(8, 7,
		 * true);
		 */

		BrowseGraph br = new BrowseGraph(matrix);
		System.out.println(matrix.printMatrix());
		Thread.sleep(500);
		System.err.println("Bậc của đỉnh số 4: " + matrix.deg(4));
		Thread.sleep(500);
		System.err.println(matrix.size());
		System.err.println("Duyệt đồ thị trên bằng DFS: " + br.DFS(1));
		System.err.println("Duyệt đồ thị trên bằng BFS: " + br.BFS(1));

	}
}
