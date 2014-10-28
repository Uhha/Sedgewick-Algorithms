package w1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class CountInversions {

	private int[] ar = new int[100000];
	private int[] arr = new int[]{1,3,5,2,4,6,7,45,6,3,5,7,7,4,3,5,7,8,1,2,4,56,8};
	private long inversions = 0;
	
	public CountInversions() throws FileNotFoundException{
		File file = new File("src/w1/IntegerArray.txt");
		Scanner sc = new Scanner(file);
		for (int i = 0; i < ar.length; i++) {
			ar[i] = sc.nextInt();
		}
		sc.close();
		
	}
	
	public long counter(){
		int[] sorted = counter(ar);
		
		return inversions;
		
	}
	private int[] counter(int[] a){
		int len = a.length;
		if (len == 1){
			return a;
		}
		if (len == 2){
			if (a[0] > a[1]){
				inversions += 1;
				int temp = a[0];
				a[0] = a[1];
				a[1] = temp;
				return a;
			}
			return a;
		}
		
		int[] left = counter(Arrays.copyOfRange(a, 0, len/2)); 
		int[] right = counter(Arrays.copyOfRange(a, len/2, len)); 
		
		int[] mergeret = merge(left, right);
		
		return mergeret;
		
	}
	
	
	
	private int[] merge(int[] left, int[] right) {
		//printarray(left);
		//printarray(right);
		int[] retlist = new int[left.length+right.length];
		int i = 0; int j = 0; int s = 0;
		while (i < left.length && j < right.length){
			if (left[i] > right[j]){
				retlist[s] = right[j];
				inversions += left.length - i; //??
				j++;
				s++;
			} else {
				retlist[s] = left[i];
				i++;
				s++;
			}
			if (i == left.length){
				while (j < right.length){
					retlist[s] = right[j];
					j++;
					s++;
				}
			}
			if (j == right.length){
				while (i < left.length){
					retlist[s] = left[i];
					i++;
					s++;
				}
			}
		}
		
		return retlist;
		
	}

	private void printarray(int[] a){
		System.out.println("Printing...");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
			
		}
		System.out.println();
	}

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		CountInversions ci = new CountInversions();
		System.out.println(ci.counter());
		
	}

}
