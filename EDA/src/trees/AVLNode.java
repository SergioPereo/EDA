package trees;

public class AVLNode<T extends Comparable<T>> implements TreeNodeADT<T>{

	private int balance = 0;
	T element;
	private AVLNode<T> left, right, parent;
	
	public void setToParent(AVLNode<T> other) {
		if(this == parent.getLeft())
			parent.setLeft(other);
		else
			parent.setRight(other);
	}
	
	public void calcBalance() {
		if(left == null && right == null)
			balance = 0;
		else if(left != null && right == null)
			balance = Math.min(left.getBalance(), -left.getBalance())-1;
		else if(left == null && right != null)
			balance = Math.max(right.getBalance(), -right.getBalance())+1;
		else
			balance = Math.min(left.getBalance(), -left.getBalance()) + Math.max(right.getBalance(), -right.getBalance());
	}

	public AVLNode(T element) {
		this.element = element;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public AVLNode<T> getLeft() {
		return left;
	}

	public void setLeft(AVLNode<T> left) {
		this.left = left;
	}

	public AVLNode<T> getRight() {
		return right;
	}

	public void setRight(AVLNode<T> right) {
		this.right = right;
	}

	public AVLNode<T> getParent() {
		return parent;
	}

	public void setParent(AVLNode<T> parent) {
		this.parent = parent;
	}

	public void cuelga(AVLNode<T> newNode) {
		if (newNode == null)
			return;
		if (element.compareTo(newNode.getElement()) > 0)
			left = newNode;
		else
			right = newNode;
	}
	
	private int maxDepth(AVLNode<T> actual) {
		if(actual == null)
			return 0;
		return 1+Math.max(maxDepth(actual.getLeft()), maxDepth(actual.getRight()));
	}
	
	public void calcWHeight() {
		balance = maxDepth(right) - maxDepth(left);
	}

	@Override
	public int getDesc() {
		int count = 0;
		if (left != null)
			count += left.getDesc() + 1;
		if (right != null)
			count += right.getDesc() + 1;
		return count;
	}

}
