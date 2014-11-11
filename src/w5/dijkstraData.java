package w5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import w4_1.Graph;

public class dijkstraData {

	private int[][] length;
	private Node[] graph;
	
	public dijkstraData(int size, String file) throws FileNotFoundException {
		graph = new Node[size];
		length = new int[size][size];
		File f = new File(file);
		Scanner sc = new Scanner(f);
		int id_counter = 1;
		while(sc.hasNextLine()){
			String[] line = sc.nextLine().split("\t");
			//System.out.println(Arrays.toString(line));
			int id = Integer.parseInt(line[0]);
			graph[id] = new Node(id_counter);
			for (int i = 1; i < line.length; i++) {
				String[] numdist = line[i].split(",");
				length[Integer.parseInt(numdist[0])][id] = Integer.parseInt(numdist[1]);
				length[id][Integer.parseInt(numdist[0])] = Integer.parseInt(numdist[1]);
				graph[id].next.add(Integer.parseInt(numdist[0]));
			}
			id_counter++;
		}
		graph[1].dist = 0;
		sc.close();
	}
	
	public void shotP(){
		LinkedList<Node> ll = new LinkedList<>();
		ll.add(graph[1]);
		int cnt = 0;
		while(ll.size() > 0){
			cnt++;
			Node element = ll.poll();
			element.visited = true;
			System.out.println("polled " + cnt);
			for (Integer nextnode : element.next) {
				int newdist = element.dist + length[element.id][nextnode];
				if (newdist < graph[nextnode].dist){
					graph[nextnode].dist = newdist;
				}
				
				if(!graph[nextnode].visited){
					ll.add(graph[nextnode]);
				}
			}
		}
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (Node node : graph) {
			sb.append(node);
		}
		return sb.toString();
	}
	
	private class Node {

		private int dist;
		private int id;
		private LinkedList<Integer> next ;
		private boolean visited;
		
		private Node(int id) {
			next = new LinkedList<>();
			dist = 1000000;
			this.id = id;
			visited = false;
		}
		public String toString(){
			return "ID = " + this.id + " DIST = " + this.dist + "\n";
			
		}
	}
	
	
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//dijkstraData dj = new dijkstraData(201, "src/w5/dijkstraData.txt");
		dijkstraData dj = new dijkstraData(14+1, "src/w5/tc02.txt");
		
		dj.shotP();
		System.out.println(dj.toString());
//		int[] vert = new int[]{7,37,59,82,99,115,133,165,188,197};
//		for (int i = 0; i < vert.length; i++) {
//			System.out.print(dj.graph[vert[i]].dist + ",");
//		}
	}

	
	
	
}
