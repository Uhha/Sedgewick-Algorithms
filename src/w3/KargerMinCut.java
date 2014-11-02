package w3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import com.rits.cloning.Cloner;

public class KargerMinCut {

	Map<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
	Map<Integer, ArrayList<Integer>> graph_clone = new HashMap<Integer, ArrayList<Integer>>();
	Cloner cloner = new Cloner();
	
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
		graph_clone = cloner.deepClone(graph);
		
//		graph = new HashMap<Integer, ArrayList<Integer>>();
//		graph.put(1, new ArrayList<Integer>(){{add(2);add(6);}});
//		graph.put(2, new ArrayList<Integer>(){{add(1);add(3);add(6);}});
//		graph.put(3, new ArrayList<Integer>(){{add(2);add(4);add(5);}});
//		graph.put(4, new ArrayList<Integer>(){{add(3);add(5);}});
//		graph.put(5, new ArrayList<Integer>(){{add(3);add(4);}});
//		graph.put(6, new ArrayList<Integer>(){{add(1);add(2);}});
		
		//System.out.println(graph.toString());
		
		
	}
	
	public int mincut(){
		
		Random rand = new Random();
		//rand.setSeed(7);
		while (graph.size() > 2){
			Set<Integer> possibleKeys = graph.keySet();
			Object[] ali = possibleKeys.toArray();
			int key = (int) ali[rand.nextInt(ali.length)];
			
			//int key = rand.nextInt(graph.size());
			ArrayList<Integer> valuesOfFirst = graph.get(key);
			int lookupKey = valuesOfFirst.get(rand.nextInt(valuesOfFirst.size()));
			
			int cnt = 0;
			for (int n = 0; n<valuesOfFirst.size(); n++) {
				if (valuesOfFirst.get(n) == lookupKey){
					cnt++;
				}
			}
			for (int i = 0; i < cnt; i++) {
				valuesOfFirst.remove(Integer.valueOf(lookupKey));
			}
			//valuesOfFirst.remove(Integer.valueOf(lookupKey));
			ArrayList<Integer> lookupValues = graph.get(lookupKey);
			
			cnt = 0;
			for (int n = 0; n<lookupValues.size(); n++) {
				if (lookupValues.get(n) == key){
					cnt++;
				}
			}
			
			for (int i = 0; i < cnt; i++) {
				lookupValues.remove(Integer.valueOf(key));
			}
			//lookupValues.remove(Integer.valueOf(key));   //CHECK
			
			List<Integer> st = new ArrayList<Integer>();
			st.addAll(valuesOfFirst);
			st.addAll(lookupValues);
			graph.remove(Integer.valueOf(lookupKey));
			graph.remove(Integer.valueOf(key));
			graph.put(key, new ArrayList<Integer>(st));
			
			deleteLookup(lookupKey, key);
			
		}
		//System.out.println(graph.size());
		//System.out.println(graph.toString());
		
		
		
		for (ArrayList<Integer> in : graph.values()) {
			//graph = graph_solid;
			graph = cloner.deepClone(graph_clone);
			return in.size();
			
		}
		
		return 0;
		
		
		
	}
	
	
	
	private void deleteLookup(int lookupKey, int insertKey) {
		Set<Integer> keyset = graph.keySet();
		for ( Integer key : keyset) {
			
			ArrayList<Integer> val = graph.get(key);
			
			if(val.contains(lookupKey) && key != insertKey){
				int cnt = 0;
				for (int n = 0; n<val.size(); n++) {
					if (val.get(n) == lookupKey){
						cnt++;
					}
				}
				
				for (int i = 0; i < cnt; i++) {
					val.add(insertKey);
				}
				
				//val.add(insertKey);
			}
			
			int cnt = 0;
			for (int n = 0; n<val.size(); n++) {
				if (val.get(n) == lookupKey){
					cnt++;
				}
			}
			for (int i = 0; i < cnt; i++) {
				val.remove(Integer.valueOf(lookupKey));
			}
			
			//val.remove(Integer.valueOf(lookupKey));
			
		}
		
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		KargerMinCut km = new KargerMinCut();
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 40000; i++) {
			
			//System.out.println(i);
			int newmin = km.mincut();
			if (newmin < min){
				min = newmin;
			}
		}
		//System.out.println(km.mincut());
		System.out.println(min);
		
	}

}
