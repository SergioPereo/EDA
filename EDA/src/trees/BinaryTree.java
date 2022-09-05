package trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class BinaryTree<T extends Comparable<T>> implements TreeADT<T> {

	TreeNode<T> root;

	private void add(TreeNode<T> actual, T element) {
		if (actual != null)
			if (actual.getElement().compareTo(element) > 0)
				add(actual.getLeft(), element);
			else
				add(actual.getRight(), element);
		else
			actual = new TreeNode<T>(element);
	}

	public void addI(T element) {
		TreeNode<T> newElement = new TreeNode<T>(element), actual = root, father = actual;
		while (actual != null) {
			father = actual;
			if (actual.getElement().compareTo(element) > 0)
				actual = actual.getLeft();
			else
				actual = actual.getRight();

		}
		if (father.getElement().compareTo(element) > 0) {

		}
		actual = newElement;
	}

	public void add(T element) {
		add(root, element);
	}

	private TreeNode<T> find(TreeNode<T> actual, T element) {
		TreeNode<T> res = null;
		if (actual == null)
			return actual;
		if (actual.getElement().equals(element))
			return actual;
		res = find(actual.getLeft(), element);
		if (res == null)
			res = find(actual.getRight(), element);
		return res;
	}

	private void preorder(TreeNode<T> root, ArrayList<T> list) {
		if (root != null) {
			list.add(root.getElement());
			preorder(root.getLeft(), list);
			preorder(root.getRight(), list);
		}
	}

	public Iterator<T> preorder() {
		ArrayList<T> list = new ArrayList<T>();
		preorder(root, list);
		return list.iterator();
	}

	public Iterator<T> preorderI() {
		ArrayList<T> list = new ArrayList<T>();
		TreeNode<T> actual;
		Stack<TreeNode<T>> stack = new Stack<TreeNode<T>>();
		stack.add(root);
		while (!stack.isEmpty()) {
			actual = stack.pop();
			list.add(actual.getElement());
			if (actual.getRight() != null)
				stack.add(actual.getRight());
			if (actual.getLeft() != null)
				stack.add(actual.getRight());
		}
		return list.iterator();
	}

	public Iterator<T> perLevelorderI() {
		ArrayList<T> list = new ArrayList<T>();
		TreeNode<T> actual;
		ArrayList<TreeNode<T>> queue = new ArrayList<TreeNode<T>>();
		queue.add(root);
		while (!queue.isEmpty()) {
			actual = queue.remove(0);
			list.add(actual.getElement());
			if (actual.getRight() != null)
				queue.add(actual.getRight());
			if (actual.getLeft() != null)
				queue.add(actual.getRight());
		}
		return list.iterator();
	}

	private void inorder(TreeNode<T> root, ArrayList<T> list) {
		if (root != null) {
			inorder(root.getLeft(), list);
			list.add(root.getElement());
			inorder(root.getRight(), list);
		}
	}

	public Iterator<T> inorder() {
		ArrayList<T> list = new ArrayList<T>();
		inorder(root, list);
		return list.iterator();
	}

	private void postorder(TreeNode<T> root, ArrayList<T> list) {
		if (root != null) {
			postorder(root.getLeft(), list);
			postorder(root.getRight(), list);
			list.add(root.getElement());
		}
	}

	public Iterator<T> postorder() {
		ArrayList<T> list = new ArrayList<T>();
		postorder(root, list);
		return list.iterator();
	}

	@Override
	public boolean find(T element) {
		TreeNode<T> actual = find(root, element);
		return actual != null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
