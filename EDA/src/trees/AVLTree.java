package trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * 
 * AVL El objetivo de esta estructura es tener árboles binarios de búsqueda con
 * complejidad O(log(n)) en sus operaciones.
 * Son ABB. 
 * Tienen la propiedad que para todo n (vértice) del árbol la diferencia en alturas 
 * entre su subárbol derecho e izquierdo es a lo más |1|.
 * 
 * Para cada nodo nos vamos a acordar que Altura(derecha)-Altura(izquierda) es el
 * factor de equilibrio.
 * 
 * Para esto usaremos rotaciones:
 * 
 * Rotación izquierda-derecha: Cambiar el nodo a la izquierda del nodo papá TODO: Mejorar esta explicación (es errónea)
 * y partir sus hijos en su predecesor y sucesor in order.
 * 
 * Rotación derecha-izquierda: Cambiar el nodo a la derecha del nodo papá
 * y partir sus hijos en su predecesor y sucesor in order.
 * 
 * @author Sergio
 * 
 * @param <T>
 */

public class AVLTree<T extends Comparable<T>> implements TreeADT<T> {

	AVLNode<T> root;
	
	private void inOrder(AVLNode<T> root, ArrayList<T> list) {
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
	
	private void right(AVLNode<T> toRot, int balance, int eBalance) {
		if(balance > 0) {
			AVLNode<T> store = toRot.getRight().getLeft();
			if(toRot.getParent() != null) {
				toRot.setToParent(toRot.getRight());
				toRot.getRight().setParent(toRot.getParent());
			}else {
				root = toRot.getRight();
				toRot.getRight().setParent(null);
			}
			toRot.getRight().setBalance(0);
			toRot.setParent(toRot.getRight());
			toRot.getRight().setLeft(toRot);
			toRot.setRight(store);
			if(store != null)
				store.setParent(toRot);
			toRot.calcWHeight();
			return;
		} else if(balance == 0) {
			AVLNode<T> r = toRot.getRight(), rr = toRot.getRight().getRight();
			if(toRot.getParent() != null) {
				toRot.setToParent(r);
				r.setParent(toRot.getParent());
			} else {
				root = r;
				r.setParent(null);
			}
			toRot.setRight(r.getLeft());
			r.getLeft().setParent(toRot);
			r.setLeft(toRot);
			toRot.setParent(r);
			toRot.calcWHeight();
			rr.calcWHeight();
			r.calcWHeight();
		} else {
			AVLNode<T> right = toRot.getRight();
			toRot.setRight(right.getLeft());
			right.getLeft().setParent(toRot);
			toRot.getRight().setRight(right);
			right.setParent(toRot.getRight());
			right.setLeft(null);
			right.calcWHeight();
			toRot.getRight().calcWHeight();
			rotate(toRot, eBalance);
		}
	}
	
	private void left(AVLNode<T> toRot, int balance, int eBalance) {
		if(balance < 0) {
			AVLNode<T> store = toRot.getLeft().getRight();
			if(toRot.getParent() != null) {
				toRot.setToParent(toRot.getLeft());
				toRot.getLeft().setParent(toRot.getParent());
			}else {
				root = toRot.getLeft();
				toRot.getLeft().setParent(null);
			}
			toRot.getLeft().setBalance(0);
			toRot.setParent(toRot.getLeft());
			toRot.getLeft().setRight(toRot);
			toRot.setLeft(store);
			if(store != null)
				store.setParent(toRot);
			toRot.calcWHeight();
		} else if(balance == 0) {
			AVLNode<T> l = toRot.getLeft(), ll = toRot.getLeft().getLeft();
			if(toRot.getParent() != null) {
				toRot.setToParent(l);
				l.setParent(toRot.getParent());
			} else {
				root = l;
				l.setParent(null);
			}
			toRot.setLeft(l.getRight());
			l.getRight().setParent(toRot);
			l.setRight(toRot);
			toRot.setParent(l);
			toRot.calcWHeight();
			ll.calcWHeight();
			l.calcWHeight();
		} else {
			AVLNode<T> left = toRot.getLeft(), store = null;
			if(left.getRight().getLeft() != null)
				store = left.getRight().getLeft();
			toRot.setLeft(left.getRight());
			left.getRight().setParent(toRot);
			toRot.getLeft().setLeft(left);
			left.setParent(toRot.getLeft());
			left.setRight(store);
			if(store != null) {
				store.setParent(left);
			}
			left.calcWHeight();
			toRot.getLeft().calcBalance();
			rotate(toRot, eBalance);
		}
	}
	
	private AVLNode<T> find(AVLNode<T> actual, T element) {
		AVLNode<T> res = null;
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
	
	private AVLNode<T> getSucessorInOrderR(AVLNode<T> actual) {
		if (actual.getLeft() != null)
			return getSucessorInOrderR(actual.getLeft());
		return actual;
	}

	private AVLNode<T> getSucessorInOrder(AVLNode<T> actual) {
		return getSucessorInOrderR(actual.getRight());
	}
	

	private void swapValues(AVLNode<T> a, AVLNode<T> b) {
		T temp = a.getElement();
		a.setElement(b.getElement());
		b.setElement(temp);
	}
	
	private void rotate(AVLNode<T> toRot, int balance) {
		if(balance == 2) {
			right(toRot, toRot.getRight().getBalance(), balance);
		} else {
			left(toRot, toRot.getLeft().getBalance(), balance);
		}
	}
	
	private void updateBDelete(AVLNode<T> actual) {
		if(actual != null) {
			actual.calcWHeight();
			int totB = actual.getBalance();
			if(totB < -1 || totB > 1) {
				rotate(actual, totB);
			}
			if(actual.getParent() == null) {
				return;
			}
			updateBDelete(actual.getParent());	
		}
	}
	
	public AVLNode<T> delete(AVLNode<T> actual){
		AVLNode<T> parent = actual.getParent(), res = null;
		if(actual.getLeft() == null && actual.getRight() == null) {
			if(parent == null)
				root = null;
			else {
				actual.setToParent(null);
				res = parent;
			}
		} else if(actual.getLeft() == null || actual.getRight() == null) {
			res = parent;
			if(actual.getLeft() != null) {
				if(parent != null)
					actual.setToParent(actual.getLeft());
				else
					root = actual.getLeft();
				actual.getLeft().setParent(parent);	
			} else {
				if(parent != null)
					actual.setToParent(actual.getRight());	
				else {
					root = actual.getRight();
				}
				actual.getRight().setParent(parent);
			}
		} else {
			AVLNode<T> sucessorIO = getSucessorInOrder(actual);
			swapValues(actual, sucessorIO);
			res = delete(sucessorIO);
		}
		return res;
	}
	
	/**
	 * Method that deletes an element to the tree.
	 *
	 * @param element value of the element to delete.
	 * @return the deleted element.
	 */

	public AVLNode<T> delete(T element) {
		AVLNode<T> toDelete = find(root, element);
		if (toDelete == null) {
			return null;
		}
		AVLNode<T> nde = delete(toDelete);
		updateBDelete(nde);
		return toDelete;
	}
	
	private void updateBalance(AVLNode<T> actual, Stack<Integer> balances) {
		int totB = actual.getBalance()+balances.pop();
		actual.setBalance(totB);
		if(totB < -1 || totB > 1) {
			rotate(actual, totB);
			return;
		}
		if(totB == 0 || actual.getParent() == null) {
			return;
		}
		updateBalance(actual.getParent(), balances);
	}
	
	private void add(T element, AVLNode<T> actual, AVLNode<T> parent, Stack<Integer> balances, int side) {
		if(actual == null) {
			AVLNode<T> newNode = new AVLNode<T>(element);
			if(parent == null) {
				root = newNode;
			} else {
				newNode.setParent(parent);
				if (side < 0)
					parent.setLeft(newNode);
				else
					parent.setRight(newNode);
				updateBalance(parent, balances);
			}
			return;
		}
		if(actual.getElement().compareTo(element) > 0) {
			balances.add(-1);
			add(element, (AVLNode<T>)actual.getLeft(), actual, balances, -1);
		} else {
			balances.add(1);
			add(element, (AVLNode<T>)actual.getRight(), actual, balances, 1);
		}
		
	}
	
	/**
	 * Method that adds an element to the tree. (Preferable it must be unique)
	 *
	 * @param element value of the element to add.
	 */
	public void add(T element) {
		add(element, root, null, new Stack<Integer>(), 0);
	}
	
	private void lookPath(AVLNode<T> actual, T element){
		if (actual == null)
			return;
		int compare = actual.getElement().compareTo(element);
		System.out.print("[");
		try {
			System.out.print(actual.getParent().getElement());
		} catch(Exception e) {
			
		}
		System.out.print(", ");
		try {
			System.out.print(actual.getElement());
		} catch(Exception e) {
			
		}
		System.out.print("]");
		System.out.println();
		if (compare > 0) {
			lookPath(actual.getLeft(), element);	
		}
		else if(compare < 0)
			lookPath(actual.getRight(), element);
		else
			return;
		return;
	}
	
	public void lookPath(T element) {
		lookPath(root, element);
	}
	
	/**
	 * Method that prints the tree in a per-level order with their value and balances in a table format.
	 *
	 */
	
	public void printTree() {
		AVLNode<T> actual;
		ArrayList<AVLNode<T>> queue = new ArrayList<AVLNode<T>>();
		queue.add(root);
		while (!queue.isEmpty()) {
			actual = queue.remove(0);
			System.out.println("Node:\t" + actual.getElement() + " \tBalance:\t" + actual.getBalance());
			if (actual.getLeft() != null)
				queue.add(actual.getLeft());
			if (actual.getRight() != null)
				queue.add(actual.getRight());
		}
	}
	
	public void printTree(Iterator<T> it) {
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Method that check if a element is in the tree.
	 *
	 * @param element value of the element to check.
	 * @return true if the element is in the array, false otherwise.
	 */
	@Override
	public boolean find(T element) {
		return find(root, element) != null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
