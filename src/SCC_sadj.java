import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;



public class SCC_sadj {

	private Digraph G;
	
	public SCC_sadj(int size, String file) throws FileNotFoundException{
		G = new Digraph(size);
		File f = new File(file);
		Scanner sc = new Scanner(f);
		while(sc.hasNextLine()){
			String[] line = sc.nextLine().split(" ");
			G.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
		}
		sc.close();
		Kosaraju_Fixed cc = new Kosaraju_Fixed(G);
		System.out.println(cc.count());
		Comparator<Integer> comparator = Collections.reverseOrder();
		cc.ccSums.sort(comparator);
		System.out.println(cc.ccSums.toString());
		
		
	}
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		int n = 875714;
		String file = "src/w4/sedj.txt";
		String bigfile = "src/w4/SCC.txt";
		SCC_sadj scc = new SCC_sadj(50+1,file);
		
		
		//System.out.println(scc.G.toString());
	}

}
