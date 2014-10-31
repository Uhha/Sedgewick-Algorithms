package w3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class KargerMinCut {

	Map<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
	
	public KargerMinCut() throws FileNotFoundException{
		
		File f = new File("src\\w3\\kargerMinCut.txt");
		Scanner sc = new Scanner(f);
		for (int i = 0; i < 200; i++) {
			String[] line = sc.nextLine().split("\t");
			//System.out.println(line[0]);
			ArrayList<Integer> al = new ArrayList<Integer>();
			for (int j = 1; j < line.length; j++) {
				al.add(Integer.parseInt(line[j]));
			}
			graph.put(Integer.parseInt(line[0]), al);
		}
		
		sc.close();
		System.out.println(graph.toString());
		
	}
	
	public int mincut(){
		Random rand = new Random();
		while (graph.size() > 2){
			int key = rand.nextInt(graph.size());
			ArrayList<Integer> valuesOfFirst = graph.get(key);
			int lookupKey = valuesOfFirst.get(rand.nextInt(valuesOfFirst.size()));
			ArrayList<Integer> lookupValues = graph.get(lookupKey);
			if (lookupValues.contains(key)){
				//lookupValues.remove(key);   //CHECK
			}
			
			Set<Integer> st = new HashSet<Integer>();
			st.addAll(valuesOfFirst);
			st.addAll(lookupValues);
			graph.remove(key);
			graph.put(key, new ArrayList<Integer>(st));
			
			deleteLookup(lookupKey);
			
		}
		System.out.println(graph.size());
		System.out.println(graph.toString());
		return 0;
		
	}
	
	
	
	private void deleteLookup(int lookupKey) {
		Set<Integer> keyset = graph.keySet();
		for ( Integer key : keyset) {
			
			if (graph.get(key).contains(key)){
				graph.get(key).remove(lookupKey);
			}
		}
		
	}

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		KargerMinCut km = new KargerMinCut();
		km.mincut();
	}

}
