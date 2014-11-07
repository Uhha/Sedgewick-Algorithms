package w4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
			//System.out.println(Integer.parseInt(line[0]+1));
			//System.out.println(Integer.parseInt(line[1]+1));
			graph[Integer.parseInt(line[0])+1].add(Integer.parseInt(line[1])+1);
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
				DFS_1while(i);
				//DFS_1(i);
			}
		}
	}

	public void secondDFS() {
		visited = new boolean[visited.length];
		int lastLeader = 0;
		for (int i = 0; i < leaders.size(); i++) {
			int pop = leaders.poll();
			lastLeader = pop;
			pop = changed[pop];
			if (!visited[pop]) {
				counter = 0;
				DFS_2while(pop);
				ccSums.add(ccCount, counter);
				ccCount++;
				
			}
		}
//		for (int i = graph.length-1; i > 0 ; i--) {
//			int pop = i;
//			//pop = changed[pop];
//			if (!visited[pop]) {
//				counter = 0;
//				DFS_2while(pop);
//				ccSums.add(ccCount, counter);
//				ccCount++;
//				
//			}
//		}
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
		Queue<Integer> q = new LinkedList<>();
		ArrayList<Integer> T_prime = new ArrayList<>();
		ArrayList<Integer> T_sec = new ArrayList<>();
		q.add(node);
		int stt = 0;
		while(q.size() > 0){
			stt++;
			if (stt % 1000 == 0){
				System.out.println(stt);
			}
			
			node = q.poll();
			visited[node] = true;
			boolean added = false;
			for (int i = 0; i < regraph[node].size(); i++) {
				if (!visited[regraph[node].get(i)] && !q.contains(regraph[node].get(i))) {
					q.add(regraph[node].get(i));
					added = true;
				}
			}
			if(added){
				T_sec.add(node);
			}
			else {
				T_prime.add(node);
			}
			
		}
		for (Integer integer : T_prime) {
			changed[T] = integer;
			leaders.add(integer);    //??
			T++;
		}
		for (int i = T_sec.size()-1; i>=0; i--) {
			changed[T] = T_sec.get(i);
			leaders.add(T_sec.get(i));    //??
			T++;
		}
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
	
	private void DFS_2while(int node){
		Queue<Integer> q = new LinkedList<>();
		q.add(node);
		int stt = 0;
		while(q.size() > 0){
			stt++;
			if (stt % 1000 == 0){
				System.out.println(stt);
			}
			
			node = q.poll();
			visited[node] = true;
			for (int i = 0; i < graph[node].size(); i++) {
				if (!visited[graph[node].get(i)] && !q.contains(graph[node].get(i))) {
					q.add(graph[node].get(i));
				}
			}
			counter++;
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
		String sedj = "src/w4/sedj_nod.txt";
		String smallfile = "src/w4/SCC_small.txt";
		String tc1 = "src/w4/cust.txt";
		SCC scc = new SCC(50 + 1, sedj);
		scc.printGraph(scc.graph);
		//System.out.println("REVERSED");
		//scc.printGraph(scc.reverse());
		System.out.println("First starts");
		scc.firstDFS();
		System.out.println("First done");
		//System.out.println(Arrays.toString(scc.changed));
//		File f = new File("src/w4/changed.txt");
//		Scanner sc = new Scanner(f);
//		String[] line = sc.nextLine().split(", ");
//		for (int i = 0; i < n+1; i++) {
//			scc.changed[i] = Integer.parseInt(line[i]);
//		}
//		
		scc.secondDFS();
		System.out.println("CC COunt = " + scc.ccCount);
		Comparator<Integer> comparator = Collections.reverseOrder();
		Collections.sort(scc.ccSums, comparator);
		
		
		System.out.println("CC Sums = " + scc.ccSums.toString());
		
		
		//600516, 318, 288, 191, 178 wrong one
		//504386, 982, 459, 318, 213,
		//tc1 = [12] 6 3 3
		//tc2 = [4] 4
		//tc3 = [4] 1 1 1 1
		//tc4 = [9] 3 3 3 0 0
		//tc5 = [8] 3 3 2 0 0
		//tc6 = [8] 3 3 1 1 0
		//tc7 = [8] 7 1 0 0 0
		//tc8 = [12] 6 3 2 1 0
		//tc9 = [14]?[9] 6 1 1 0 0
		//tc10 = [11] 3 2 2 2 1
		//tc11 = [12] 6 3 3
		
		
		
		
		
		
	}

}
