package algorithm.tuan4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

public class SmallSpanningTree {

	private ArrayList<Edge> listEdge;

	public SmallSpanningTree() {
		listEdge = new ArrayList<Edge>();
	}

	public SmallSpanningTree(ArrayList<Edge> ed) {
		listEdge = ed;
		Collections.sort(listEdge);
	}

	/**
	 * check graph is process or way. if graph is process then return true, else
	 * return false
	 */
	public static boolean checkProcess(ArrayList<Edge> listE) {
		ArrayList<Edge> listEdge = new ArrayList<Edge>(listE);
		boolean result = false;
		ArrayList<Integer> listPoint = new ArrayList<Integer>(); // dung de chua cac tap dinh da xet
		Edge e = listEdge.get(0);// get element at 0
		listEdge.remove(0);// delete element at 0
		listPoint.add(e.getPointA());
		listPoint.add(e.getPointB());
		while (!listEdge.isEmpty()) {
			for (int i = 0; i < listEdge.size(); i++) {
				/* kiem tra xem co phaikhong co dau nao co trong danh sach chua */
				if (listPoint.indexOf(listEdge.get(i).getPointA()) != -1
						&& listPoint.indexOf(listEdge.get(i).getPointB()) != -1) {
					return result = true;// co chu trinh
				}
				if (listPoint.indexOf(listEdge.get(i).getPointA()) == -1
						|| listPoint.indexOf(listEdge.get(i).getPointB()) != -1) {
					listPoint.add(listEdge.get(i).getPointA());
					listEdge.remove(listEdge.get(i));
				} else if (listPoint.indexOf(listEdge.get(i).getPointA()) != -1
						|| listPoint.indexOf(listEdge.get(i).getPointB()) == -1) {
					listPoint.add(listEdge.get(i).getPointB());
					listEdge.remove(listEdge.get(i));
				} else {
					listPoint.add(listEdge.get(i).getPointA());
					listPoint.add(listEdge.get(i).getPointB());
					listEdge.remove(listEdge.get(i));
				}
			}
		}
		return result;
	}

	/**
	 * Method Kruskal
	 */
	public static ArrayList<Edge> kruskal(ArrayList<Edge> list) {
		ArrayList<Edge> listEdge = new ArrayList<Edge>(list);
		ArrayList<Edge> result = new ArrayList<Edge>();
		Collections.sort(listEdge);
		while (!listEdge.isEmpty()) {
			Edge e = listEdge.get(0);
			result.add(e);
			if (checkProcess(result)) {
				result.remove(e);
				listEdge.remove(e);
			} else {
				listEdge.remove(e);
			}
		}
		return result;
	}

	/**
	 * Method Prim
	 */
	public static ArrayList<Edge> prim(ArrayList<Edge> listEdge) {
		ArrayList<Edge> list = new ArrayList<Edge>(listEdge);
		ArrayList<Edge> result = new ArrayList<Edge>();
		ArrayList<Integer> listPoint = new ArrayList<Integer>();
		Edge e = list.get(0);
		list.remove(e);
		listPoint.add(e.getPointA());
		listPoint.add(e.getPointB());
		Collections.sort(list);
		int totalPoint = countTotalPoint(listEdge);
		while (result.size() < totalPoint - 1) {
			for (int i = 0; i < list.size(); i++) {
				if (listPoint.contains(list.get(i).getPointA()) && !listPoint.contains(list.get(i).getPointB())) {
					result.add(list.get(i));
					list.remove(i);
					listPoint.add(list.get(i).getPointB());
				} else if (listPoint.contains(list.get(i).getPointB())
						&& !listPoint.contains(list.get(i).getPointA())) {
					result.add(list.get(i));
					list.remove(i);
					listPoint.add(list.get(i).getPointA());
				}
			}
		}
		return result;
	}

	/**
	 * Count tall point in list edge
	 */
	public static int countTotalPoint(ArrayList<Edge> listEdge) {
		Set<Integer> listPoint = new LinkedHashSet<Integer>();
		for (Edge e : listEdge) {
			listPoint.add(e.getPointA());
			listPoint.add(e.getPointB());
		}
		return listPoint.size();
	}

	/**
	 * main
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		ArrayList<Edge> listEdge = new ArrayList<Edge>();
		listEdge.add(new Edge(1, 2, 3));
		listEdge.add(new Edge(2, 3, 5));
		listEdge.add(new Edge(2, 4, 6));
		listEdge.add(new Edge(3, 4, 8));
		listEdge.add(new Edge(7, 2, 12));
		listEdge.add(new Edge(6, 2, 55));
		listEdge.add(new Edge(10, 2, 13));
		listEdge.add(new Edge(1, 7, 7));
		listEdge.add(new Edge(2, 8, 4));
		listEdge.add(new Edge(4, 5, 9));
		listEdge.add(new Edge(4, 8, 90));
		Collections.sort(listEdge);
		System.err.println(listEdge);
		ArrayList<Edge> listEdgen = new ArrayList<Edge>(listEdge);
		System.out.println(listEdgen);
		System.err.println(SmallSpanningTree.countTotalPoint(listEdgen));
		// method kruskal
		ArrayList<Edge> list = SmallSpanningTree.kruskal(listEdge);System.out.println(list);
		//method Prim
		System.out.println("Prim: "+SmallSpanningTree.prim(listEdgen));
		

	}

}
