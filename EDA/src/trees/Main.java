package trees;

import java.util.Iterator;

public class Main {
	
	public static void main(String[] args) {
		BinaryTree<Integer> tree = new BinaryTree<Integer>();
		tree.add(1);
		tree.add(6);
		tree.add(-14);
		tree.add(10);
		Iterator<Integer> it = tree.preorder();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

}
