package w4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class SCC_v1 {

	private List<Integer>[] graph;
	private boolean[] visited;
	private int[] changed;
	private int T;
	private Queue<Integer> leaders;
	private int ccCount;
	private ArrayList<Integer> ccSums;
	private int counter;

	@SuppressWarnings("unchecked")
	public SCC_v1(int size, String filename) throws FileNotFoundException {
		counter = 0;
		ccSums = new ArrayList<>();
		ccCount = 0;
		T = 1;
		leaders = new LinkedList<Integer>();
		changed = new int[size];
		visited = new boolean[size];
		graph = (List<Integer>[]) new List[size];
		for (int i = 1; i < size; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		File f = new File(filename);
		Scanner sc = new Scanner(f);
		while (sc.hasNextLine()) {
			String[] line = sc.nextLine().split(" ");
			graph[Integer.parseInt(line[0])].add(Integer.parseInt(line[1]));
		}
		sc.close();

	}

	public List<Integer>[] reverse() {
		@SuppressWarnings("unchecked")
		List<Integer>[] regraph = (List<Integer>[]) new List[graph.length];
		for (int i = 0; i < regraph.length; i++) {
			regraph[i] = new ArrayList<Integer>();
		}
		for (int i = 1; i < graph.length; i++) {
			for (int j = 0; j < graph[i].size(); j++) {
				regraph[graph[i].get(j)].add(i);
			}
		}
		return regraph;
	}

	public void firstBFS(List<Integer>[] G) {
		for (int i = graph.length - 1; i > 0; i--) {
			if (!visited[i]) {
				leaders.add(i);
				DFS(G, i, false);
			}
		}
	}

	public void secondDFS(List<Integer>[] G) {
		visited = new boolean[visited.length];
		for (int i = 0; i < leaders.size(); i++) {
			int pop = leaders.poll();
			pop = changed[pop];
			if (!visited[pop]) {
				counter = 0;
				DFS(G, pop, true);
				ccSums.add(ccCount, counter);
				ccCount++;
				
			}
		}
		for (int i = G.length-1; i > 0 ; i--) {
			int pop = i;
			//pop = changed[pop];
			if (!visited[pop]) {
				counter = 0;
				DFS(G, pop, true);
				ccSums.add(ccCount, counter);
				ccCount++;
				
			}
		}
	}

	private void DFS(List<Integer>[] G, int node, boolean secondDFS) {
		visited[node] = true;
		for (int i = 0; i < G[node].size(); i++) {
			int element = G[node].get(i);
			if (!visited[element]) {
				DFS(G, element, secondDFS);
			}
		}
		if (secondDFS) {
			counter++;
		} else {
			changed[T] = node; // changed[node] = T;
			T++;
		}

	}

	public void printGraph(List<Integer>[] G) {
		for (int i = 1; i < G.length; i++) {
			System.out.print(i + " : ");
			for (int j = 0; j < G[i].size(); j++) {
				System.out.print(G[i].get(j) + " ");
			}
			System.out.println();
		}
	}

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		int n = 875714;
		String bigfile = "src/w4/SCC.txt";
		String smallfile = "src/w4/SCC_small.txt";
		String tc1 = "src/w4/tc3.txt";
		SCC_v1 scc = new SCC_v1(n + 1, bigfile);
		//scc.printGraph(scc.graph);
		//System.out.println("REVERSED");
		//scc.printGraph(scc.reverse());
		System.out.println("First starts");
		scc.firstBFS(scc.reverse());
		System.out.println("First done");
		//System.out.println(Arrays.toString(scc.changed));
		scc.secondDFS(scc.graph);
		System.out.println("CC COunt = " + scc.ccCount);
		System.out.println("CC Sums = " + (scc.ccSums.toString()));
	}

}
