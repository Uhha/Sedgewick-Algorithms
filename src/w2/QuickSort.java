package w2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {

	private int[] ar = new int[10000];
	private long calc = 0;
	
	
	
	public QuickSort() throws FileNotFoundException{
		
		File f = new File("src\\w2\\QuickSort.txt");
		Scanner sc = new Scanner(f);
		for (int i = 0; i < ar.length; i++) {
			ar[i] = sc.nextInt();
		}
		sc.close();
		//ar = new int[]{7,4,8,2,6,1,5,3};
		
	}
	
	
	public int[] sort(){
		int ith = 0;
		int[] a = ar;
		int jth = a.length - 1;
		
		return sort(a, ith, jth);
	}
	
	private int[] sort(int[] a, int ith, int jth){
		calc += jth-ith;
		if (jth-ith < 1){
			return a;
		}
		
		int pivot = a[ith];
		
//		//2nd quesion
//		int tmp = a[jth];
//		a[jth] = a[ith];
//		a[ith] = tmp;
//		pivot = a[ith];
//		//
//		
		int index = -1;
		int mid = ((jth-ith)/2)+ith;
		int small = a[ith];
		int medium = a[mid];
		int big = a[jth];
		if ((small < medium && small > big) || (small > medium && small < big)){index = ith;}
		if ((medium < small && medium > big) || (medium > small && medium < big)){index = mid;}
		if ((big < medium && big > small) || (big > medium && big < small)){index = jth;}
		
		int tmp = a[ith];
		a[ith] = a[index];
		a[index] = tmp;
		
		pivot = a[ith];
		
		int i = ith + 1;
		
		for (int j = ith+1; j < a.length; j++) {
			if (a[j] < pivot){
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				i++;
				
			}
		}
		int temp = a[i-1];
		a[i-1] = pivot;
		a[ith] = temp;
		
		sort(a, ith, i-2);
		sort(a, i, jth);
		
		
		return a;
		
	}
	
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		QuickSort qs = new QuickSort();
		System.out.println(Arrays.toString(qs.ar));
		System.out.println(Arrays.toString(qs.sort()));
		System.out.println(qs.calc);
	}

}
