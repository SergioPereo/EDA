package trees;

import java.util.Iterator;

public class Main {
	// The guide to markdown
	// Juan Carlos
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
		System.out.println("Pre-delete");
		System.out.println();
		System.out.println("Per-level");
		tree.add(100);
		tree.add(200);
		tree.add(300);
		tree.add(250);
		tree.add(375);
		tree.add(280);
		
		tree.add(290);
		tree.add(295);
		
		tree.printTree();
		System.out.println("In-order");
		printTree(tree.inOrder());
		
		System.out.println("Post-delete");
		System.out.println();
		System.out.println("Per-level");
		// This method prints the tree in a per-level order
		tree.delete(250);
		tree.delete(280);
		tree.delete(200);
		tree.delete(250);
		tree.delete(100);
		tree.printTree();
		System.out.println("In-order");
		printTree(tree.inOrder());
		
		// Search test
		//System.out.println("Find: 9 - " + tree.find(-9));
	}
	
	public static void main(String[] args) {
		
		//Binary Trees
		//binaryTreeTest();
		
		//AVL Trees
		avlTreeTest();
		
	}

}
