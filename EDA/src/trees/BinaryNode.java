package trees;

public class BinaryNode<T extends Comparable<T>> implements TreeNodeADT<T>{

	T element;
	BinaryNode<T> left, right, parent;

	public BinaryNode(T element) {
		this.element = element;
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

	public void cuelga(BinaryNode<T> newNode) {
		if (newNode == null)
			return;
		if (element.compareTo(newNode.getElement()) > 0)
			left = newNode;
		else
			right = newNode;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}
	
	public BinaryNode<T> getParent(){
		return parent;
	}
	
	public void setParent(BinaryNode<T> parent) {
		this.parent = parent;
	}

	public BinaryNode<T> getLeft() {
		return left;
	}

	public void setLeft(BinaryNode<T> left) {
		this.left = left;
	}

	public BinaryNode<T> getRight() {
		return right;
	}

	public void setRight(BinaryNode<T> right) {
		this.right = right;
	}

}
