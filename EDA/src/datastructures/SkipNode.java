package datastructures;

public class SkipNode<T extends Comparable<T>> {

	T element;

	SkipNode<T> left, right, down, up;

	public SkipNode() {
	}

	public SkipNode(T element) {
		this.element = element;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public SkipNode<T> getLeft() {
		return left;
	}

	public void setLeft(SkipNode<T> left) {
		this.left = left;
	}

	public SkipNode<T> getRight() {
		return right;
	}

	public void setRight(SkipNode<T> right) {
		this.right = right;
	}

	public SkipNode<T> getDown() {
		return down;
	}

	public void setDown(SkipNode<T> down) {
		this.down = down;
	}

	public SkipNode<T> getUp() {
		return up;
	}

	public void setUp(SkipNode<T> up) {
		this.up = up;
	}

}
