package w4_1;

import java.util.ArrayList;
import java.util.List;

public class Node {

	public int id;
	public int f; // finish time
	public boolean explored;
	public List<Node> connected;

	public Node(int id){
		this.id = id;
		explored = false;
		connected = new ArrayList<>();
	}
	
	public String toString(){
		return id+" "+"["+ f+ "]";
		
	}
//...
}