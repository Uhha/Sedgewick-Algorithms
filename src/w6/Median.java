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
	private int mediansum = 0;
	
	public Median(String file) throws FileNotFoundException{
		
		right = new PriorityQueue<Integer>(new Comparator<Integer>(){

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1, o2);
			}
			
		});
		
		left = new PriorityQueue<Integer>(new Comparator<Integer>(){

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o2, o1);
			}
			
		});
		
		File f = new File(file);
		Scanner sc = new Scanner(f);
		int e1 = sc.nextInt();
		int e2 = sc.nextInt();
		mediansum += e1;
		if (Integer.compare(e1, e2) > 0){
			left.add(e2);
			right.add(e1);
		} else {
			left.add(e1);
			right.add(e2);
		}
		mediansum += left.peek();
		while(sc.hasNext()){
			int x = sc.nextInt();
			
			//add element
			if(x < right.peek()){
				left.add(x);
			} else {
				right.add(x);
			}
			
			//check stability
			if (left.size() - right.size() >= 2){
				right.add(left.poll());
			}
			if (right.size() - left.size() >= 1){
				left.add(right.poll());
			}
			mediansum += left.peek();
			//System.out.println(left.peek());	
		}
		sc.close();
		
	}
	
	public int result(){
		System.out.println(mediansum%10000);
		return mediansum%10000;
	}
	
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String file = "src/w6/median.txt";
		Median md = new Median(file);
		md.result();
		
	}

}
