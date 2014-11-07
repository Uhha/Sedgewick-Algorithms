package w4_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Graph {

//	private static int REALLOCSIZE = 100000;
//	private int maxNodes;
	private int t; //?

	public int nrNodes;
	public Node[] nodes;

	public Graph(int size, String file) throws FileNotFoundException{
		nodes = new Node[size];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new Node(i);
		}
//		maxNodes = size;
		t = 1;
		nrNodes = size;
		File f = new File(file);
		Scanner sc = new Scanner(f);
		while (sc.hasNextLine()){
			String[] line = sc.nextLine().split(" ");
			addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
		}
		sc.close();
	}

	public void addEdge(int to, int from) {

		// check if we need to extend the node array
//		if (from >= maxNodes || to >= maxNodes) {
//
//			int newMaxNodes = (Math.max(from, to) / REALLOCSIZE + 1)
//					* REALLOCSIZE;
//			Node[] newNodes = new Node[newMaxNodes];
//
//			for (int i = 0; i < maxNodes; i++)
//				newNodes[i] = nodes[i];
//			for (int i = maxNodes; i < newMaxNodes; i++)
//				newNodes[i] = new Node(i);
//
//			nodes = newNodes;
//			maxNodes = newMaxNodes;
//		}

		nodes[from].connected.add(nodes[to]);
		nrNodes = Math.max(nrNodes, Math.max(from, to) + 1);
	}

	public void dfs_loop_1st_pass() {
		t = 1;
		for (int i = nrNodes - 1; i >= 0; i--)
			if (!nodes[i].explored) {
				dfs_1st_pass(nodes[i]);
			}
	}

	public void dfs_1st_pass(Node i) {
		i.explored = true;

		for (Node connected : i.connected)
			if (!connected.explored)
				dfs_1st_pass(connected);
		i.f = t++;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		int n = 875714;
		String small = "src/w4/SCC_small.txt";
		String big = "src/w4/SCC.txt";
		Graph G = new Graph(n+1,big);
		G.dfs_loop_1st_pass();
		//System.out.println(Arrays.toString(G.nodes));
		
		
	}
}
