package trees;

import java.util.Iterator;

public class Main {
	
	public static void printTree(Iterator<Integer> it) {
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
	public static void binaryTreeTest() {
		BinaryTree<Integer> tree = new BinaryTree<Integer>();
		tree.add(1);
		tree.add(6);
		tree.add(-14);
		tree.add(10);
		tree.add(4);
		tree.add(3);
		tree.add(5);
		tree.add(9);
		tree.add(21);
		printTree(tree.perLevelorderI());
		System.out.println("Delete\n");
		tree.delete(6);
		printTree(tree.inOrder());
	}
	
	public static void avlTreeTest() {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		tree.add(5);
		tree.add(2);
		tree.add(8);
		tree.add(1);
		tree.add(3);
		tree.add(7);
		tree.add(10);
		tree.add(4);
		tree.add(6);
		tree.add(9);
		tree.add(11);
		tree.add(12);
		tree.delete(1);
		
		
		
		// This method prints the tree in a per-level order
		tree.printTree();
		// Search test
		System.out.println("Find: 9 - " + tree.find(-9));
	}
	
	public static void main(String[] args) {
		
		//Binary Trees
		//binaryTreeTest();
		
		//AVL Trees
		avlTreeTest();
		
	}

}
