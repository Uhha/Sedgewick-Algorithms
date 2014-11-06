package w4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SCC {

	private List<Integer>[] graph;
	private boolean[] visited;
	private int[] changed;
	private int T;
	
	@SuppressWarnings("unchecked")
	public SCC(int size, String filename) throws FileNotFoundException{
		T = 1;
		changed = new int[size];
		visited = new boolean[size];
		graph = (List<Integer>[]) new List[size];
		for (int i = 1; i < size; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		File f = new File(filename);
		Scanner sc = new Scanner(f);
		while (sc.hasNextLine()){
			String[] line = sc.nextLine().split(" ");
			graph[Integer.parseInt(line[0])].add(Integer.parseInt(line[1]));
		}
		sc.close();
		
	}
	
	public List<Integer>[] reverse(){
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
	
	public void firstBFS(List<Integer>[] G){
		for (int i = graph.length-1; i > 0  ; i--) {
			if (!visited[i]){
				DFS(G,i);
			}
		}
	}
	
	public void secondBFS(List<Integer>[] G){
		
	}
	
	private void DFS(List<Integer>[] G, int node){
		visited[node] = true;
		for (int i = 0; i < G[node].size(); i++) {
			int element = G[node].get(i);
			if(!visited[element]){
				DFS(G, element);
			}
		}
		changed[node] = T;
		T++;
	}
	
	
	
	public void printGraph(List<Integer>[] G){
		for (int i = 1; i < G.length; i++) {
			System.out.print(i + " : ");
			for (int j = 0; j < G[i].size(); j++) {
				System.out.print(G[i].get(j)+ " ");
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
		SCC scc = new SCC(9+1, smallfile);
		scc.printGraph(scc.graph);
		System.out.println("REVERSED");
		scc.printGraph(scc.reverse());
		scc.firstBFS(scc.reverse());
		System.out.println(Arrays.toString(scc.changed));
	}

}
