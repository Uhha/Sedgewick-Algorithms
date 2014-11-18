package w6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class TwoSum {

	private HashMap<Long, Boolean> ar;
	
	public TwoSum(String file) throws FileNotFoundException{
		ar = new HashMap<Long, Boolean>();
		File f = new File(file);
		Scanner sc = new Scanner(f);
		while(sc.hasNext()){
			long x = sc.nextLong();
			ar.put(x, true);
		}
		sc.close();
		
	}
	
	public int calc2(){
		int count = 0;
		for (int i = -10000; i <= 10000; i++){
			if(i%100 == 0) System.out.println(i);
			for (long  num : ar.keySet()) {
				if(ar.containsKey(i-num)){
					count++;
					break;
				}
			}
		}
		return count;
	}
	
	public int calc(){
		int count = 0;
		//for (Integer i : Arrays.asList(-10000, -9999, 3))
		for (int i = -10000; i <= 10000; i++){
			if(i%100 == 0) System.out.println(i);
			for (long  num : ar.keySet()) {
				if(ar.containsKey(i-num) && (i-num) != num){
					//System.out.println("num = " + num + " i = " + i + " sum = " + (i-num));
					count++;
					break;
				}
			}
		}
		return count;
	}
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String file = "src/w6/twosum.txt";
		TwoSum ts = new TwoSum(file);
		System.out.println(ts.calc());
		//427
	}

}
