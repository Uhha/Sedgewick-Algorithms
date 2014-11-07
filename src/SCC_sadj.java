import java.io.File;
import java.io.FileNotFoundException;
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
		KosarajuSharirSCC cc = new KosarajuSharirSCC(G);
		System.out.println(cc.count());
		
	}
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		int n = 875714;
		String file = "src/w4/SCC_small.txt";
		String bigfile = "src/w4/SCC.txt";
		SCC_sadj scc = new SCC_sadj(n+1,bigfile);
		
		//System.out.println(scc.G.toString());
	}

}
