package w6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Median {

	private PriorityQueue<Integer> left;
	private PriorityQueue<Integer> right;
	
	public Median(String file) throws FileNotFoundException{
		
		left = new PriorityQueue<Integer>(new Comparator<Integer>(){

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1, o2);
			}
			
		});
		
		right = new PriorityQueue<Integer>(new Comparator<Integer>(){

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o2, o1);
			}
			
		});
		
		File f = new File(file);
		Scanner sc = new Scanner(f);
		while(sc.hasNext()){
			int x = sc.nextInt();
			left.add(x);
			right.add(x);
		}
		sc.close();
		
	}
	
	
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String file = "src/w6/median_s.txt";
		Median md = new Median(file);
		System.out.println(md.left.poll());
		System.out.println(md.right.poll());
		
	}

}
