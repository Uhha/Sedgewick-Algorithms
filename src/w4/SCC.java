package w4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class SCC {

	private List<Integer>[] graph;
	private List<Integer>[] regraph;
	private boolean[] visited;
	private int[] changed;
	private int T;
	private Queue<Integer> leaders;
	private int ccCount;
	private ArrayList<Integer> ccSums;
	private int counter;

	@SuppressWarnings("unchecked")
	public SCC(int size, String filename) throws FileNotFoundException {
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
		
		reverse();

	}

	@SuppressWarnings("unchecked")
	public void reverse() {
		regraph = (List<Integer>[]) new List[graph.length];
		for (int i = 0; i < regraph.length; i++) {
			regraph[i] = new ArrayList<Integer>();
		}
		for (int i = 1; i < graph.length; i++) {
			for (int j = 0; j < graph[i].size(); j++) {
				regraph[graph[i].get(j)].add(i);
			}
		}
		
	}

	public void firstDFS() {
		for (int i = graph.length - 1; i > 0; i--) {
			if (!visited[i]) {
				leaders.add(i);
				DFS_1(i);
			}
		}
	}

	public void secondDFS() {
		visited = new boolean[visited.length];
		for (int i = 0; i < leaders.size(); i++) {
			int pop = leaders.poll();
			pop = changed[pop];
			if (!visited[pop]) {
				counter = 0;
				DFS_2(pop);
				ccSums.add(ccCount, counter);
				ccCount++;
				
			}
		}
		for (int i = graph.length-1; i > 0 ; i--) {
			int pop = i;
			//pop = changed[pop];
			if (!visited[pop]) {
				counter = 0;
				DFS_2(pop);
				ccSums.add(ccCount, counter);
				ccCount++;
				
			}
		}
	}

	private void DFS_1(int node) {
		visited[node] = true;
		for (int i = 0; i < regraph[node].size(); i++) {
			int element = regraph[node].get(i);
			if (!visited[element]) {
				DFS_1(element);
			}
		}
		changed[T] = node; // changed[node] = T;
		T++;
	}
	
	private void DFS_1while(int node){
		
	}
	
	private void DFS_2(int node) {
		visited[node] = true;
		for (int i = 0; i < graph[node].size(); i++) {
			int element = graph[node].get(i);
			if (!visited[element]) {
				DFS_2(element);
			}
		}
		
		counter++;
		

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
		SCC scc = new SCC(n + 1, bigfile);
		//scc.printGraph(scc.graph);
		//System.out.println("REVERSED");
		//scc.printGraph(scc.reverse());
		System.out.println("First starts");
		scc.firstDFS();
		System.out.println("First done");
		//System.out.println(Arrays.toString(scc.changed));
		scc.secondDFS();
		System.out.println("CC COunt = " + scc.ccCount);
		System.out.println("CC Sums = " + (scc.ccSums.toString()));
	}

}
