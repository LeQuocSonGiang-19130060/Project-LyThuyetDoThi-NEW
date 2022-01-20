package algorithm.tuan3;

import algorithm.tuan1.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class FindWay {

	private Graph graph;

	public FindWay(Graph gr) {
		graph = gr;
	}

	/**
	 * Find way form a to b by DFS
	 */
	public String findWayByDFS(int a, int b) {
		int[] traces = new int[graph.size()];
		for (int i = 0; i < traces.length; i++) {
			traces[i] = -1;
		}
		Stack<Integer> st = new Stack<Integer>();
		boolean[] isVisit = new boolean[graph.size()];
		for (int i = 0; i < isVisit.length; i++) {
			isVisit[i] = false;
		}
		st.push(a);
		isVisit[a] = true;
		while (!st.isEmpty()) {
			Integer p = st.pop();
			if (p == b) {
				break;
			}
			ArrayList<Integer> listSide = graph.listOfSide(p);
			for (Integer pointSide : listSide) {
				if (!isVisit[pointSide]) {
					st.push(pointSide);
					isVisit[pointSide] = true;
					traces[pointSide] = p;
				}
			}
		}
		if (traces[b] == -1) {
			return "Không có đường đi giữa 2 điểm";
		} else {
			String result = "" + b;
			int u = traces[b];
			for (int i = 0; i < traces.length; i++) {
				if (u == a) {
					// duyet/ket thuc thuat toan/break;
					result += u;
					break;
				} else {
					result += u;
					u = traces[u];
				}
			}
			StringBuffer rs = new StringBuffer(result);
			return rs.reverse().toString();
		}
	}

	public String findWayByBFS(int a, int b) {
		int[] traces = new int[graph.size()];
		for (int i = 0; i < traces.length; i++) {
			traces[i] = -1;
		}
		Queue<Integer> que = new LinkedList<Integer>();
		boolean[] isVisit = new boolean[graph.size()];
		for (int i = 0; i < isVisit.length; i++) {
			isVisit[i] = false;
		}
		que.add(a);
		isVisit[a] = true;
		while (!que.isEmpty()) {
			Integer p = que.poll();
			if (p == b)
				break;
			ArrayList<Integer> listSide = graph.listOfSide(p);
			for (Integer pointSide : listSide) {
				if (!isVisit[pointSide]) {
					que.add(pointSide);
					isVisit[pointSide] = true;
					traces[pointSide] = p;
				}
			}
		}
		if (traces[b] == -1) {
			return "Không có đường đi giữa 2 điểm";
		} else {
			String result = "" + b;
			int u = traces[b];
			for (int i = 0; i < traces.length; i++) {
				if (u == a) {
					result += u;
					break;
				} else {
					result += u;
					u = traces[u];
				}
			}
			StringBuffer rs = new StringBuffer(result);
			return rs.reverse().toString();
		}

	}

	/**
	 * Check graph is direction of undirection
	 */
	public boolean checkDirection() {
		return graph.isDirectionGraph();
	}
	
	/**
	 * Check graph is continuity, if continuity is true, check graph is Strong
	 * continuity or weak continuity
	 */
	public String checkContinuity() {
		return graph.checkContinuity();
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
		

		FindWay br = new FindWay(matrix);
		System.out.println(matrix.printMatrix());
		System.err.println("Ä?Æ°á»?ng Ä‘i tá»« 1 => 0 lÃ : " + br.findWayByDFS(1, 0));
		System.err.println("Ä?Æ°á»?ng Ä‘i tá»« 0 => 1 lÃ : " + br.findWayByDFS(0, 1));
		System.err.println("Ä?Æ°á»?ng Ä‘i tá»« 1 => 0 lÃ : " + br.findWayByBFS(1, 0));
		System.err.println("Ä?Æ°á»?ng Ä‘i tá»« 0 => 1 lÃ : " + br.findWayByBFS(0, 1));
		System.err.println("Ä?á»“ thá»‹ lÃ  vÃ´ hÆ°á»›ng: " + br.checkDirection());
		System.err.println(br.checkContinuity());
	}

}
