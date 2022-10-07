package trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class BinaryTree<T extends Comparable<T>> implements TreeADT<T> {

	BinaryNode<T> root;

	private void add(BinaryNode<T> parent, BinaryNode<T> actual, T element, int side) {
		if (actual != null)
			if (actual.getElement().compareTo(element) > 0)
				add(actual, actual.getLeft(), element, -1);
			else
				add(actual, actual.getRight(), element, 1);
		else {
			BinaryNode<T> newNode = new BinaryNode<T>(element);
			if (parent == null) {
				root = newNode;
			} else {
				newNode.setParent(parent);
				if (side < 0)
					parent.setLeft(newNode);
				else
					parent.setRight(newNode);
			}
		}
	}

	public void addI(T element) {
		BinaryNode<T> newElement = new BinaryNode<T>(element), actual = root, father = actual;
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
		add(null, root, element, 0);
	}

	private void preOrder(BinaryNode<T> root, ArrayList<T> list) {
		if (root != null) {
			list.add(root.getElement());
			preOrder(root.getLeft(), list);
			preOrder(root.getRight(), list);
		}
	}

	public Iterator<T> preOrder() {
		ArrayList<T> list = new ArrayList<T>();
		preOrder(root, list);
		return list.iterator();
	}

	public Iterator<T> preOrderI() {
		ArrayList<T> list = new ArrayList<T>();
		BinaryNode<T> actual;
		Stack<BinaryNode<T>> stack = new Stack<BinaryNode<T>>();
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
		BinaryNode<T> actual;
		ArrayList<BinaryNode<T>> queue = new ArrayList<BinaryNode<T>>();
		queue.add(root);
		while (!queue.isEmpty()) {
			actual = queue.remove(0);
			list.add(actual.getElement());
			if (actual.getLeft() != null)
				queue.add(actual.getLeft());
			if (actual.getRight() != null)
				queue.add(actual.getRight());
		}
		return list.iterator();
	}

	private void inOrder(BinaryNode<T> root, ArrayList<T> list) {
		if (root != null) {
			inOrder(root.getLeft(), list);
			list.add(root.getElement());
			inOrder(root.getRight(), list);
		}
	}

	public Iterator<T> inOrder() {
		ArrayList<T> list = new ArrayList<T>();
		inOrder(root, list);
		return list.iterator();
	}

	private void postOrder(BinaryNode<T> root, ArrayList<T> list) {
		if (root != null) {
			postOrder(root.getLeft(), list);
			postOrder(root.getRight(), list);
			list.add(root.getElement());
		}
	}

	public Iterator<T> postOrder() {
		ArrayList<T> list = new ArrayList<T>();
		postOrder(root, list);
		return list.iterator();
	}

	private int height(BinaryNode<T> actual) {
		if (actual != null)
			return 1 + Math.max(height(root.getLeft()), height(root.getRight()));
		return 0;
	}

	public int height() {
		return height(root);
	}

	private BinaryNode<T> getSucessorInOrderR(BinaryNode<T> actual) {
		if (actual.getLeft() != null)
			return getSucessorInOrderR(actual.getLeft());
		return actual;
	}

	private BinaryNode<T> getSucessorInOrder(BinaryNode<T> actual) {
		return getSucessorInOrderR(actual.getRight());
	}

	private void swapValues(BinaryNode<T> a, BinaryNode<T> b) {
		T temp = a.getElement();
		a.setElement(b.getElement());
		b.setElement(temp);
	}

	public BinaryNode<T> delete(BinaryNode<T> actual){
		BinaryNode<T> parent = actual.getParent();
		if(actual.getLeft() == null && actual.getRight() == null) {
			if(parent == null)
				root = null;
			else {
				if(parent.getLeft() == actual)
					parent.setLeft(null);
				else
					parent.setRight(null);
			}
		} else if(actual.getLeft() == null || actual.getRight() == null) {
			if (actual == parent.getLeft())
				if (actual.getLeft() != null) {
					parent.setLeft(actual.getLeft());
					actual.getLeft().setParent(parent);
				}
				else {
					parent.setLeft(actual.getRight());
					actual.getRight().setParent(parent);
				}
			else {
				if (actual.getLeft() != null) {
					parent.setRight(actual.getLeft());	
					actual.getLeft().setParent(parent);
				}
				else {
					parent.setRight(actual.getRight());
					actual.getRight().setParent(parent);
				}
			}
		} else {
			BinaryNode<T> sucessorIO = getSucessorInOrder(actual);
			swapValues(actual, sucessorIO);
			delete(sucessorIO);
		}
		return actual;
	}

	public BinaryNode<T> delete(T element) {
		BinaryNode<T> toDelete = find(root, element);
		if (toDelete == null) {
			return null;
		}
		return delete(toDelete);
	}

	private BinaryNode<T> find(BinaryNode<T> actual, T element) {
		BinaryNode<T> res = null;
		if (actual == null)
			return actual;
		int compare = actual.getElement().compareTo(element);
		if (compare > 0)
			res = find(actual.getLeft(), element);
		else if(compare < 0)
			res = find(actual.getRight(), element);
		else
			return actual;
		return res;
	}

	@Override
	public boolean find(T element) {
		BinaryNode<T> actual = find(root, element);
		return actual != null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

}
