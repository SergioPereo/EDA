package trees;

public class TreeNode<T extends Comparable<T>> {

	T element;
	TreeNode<T> left, right;

	public TreeNode(T element) {
		this.element = element;
	}

	public int getDesc() {
		int count = 0;
		if (left != null)
			count += left.getDesc() + 1;
		if (right != null)
			count += right.getDesc() + 1;
		return count;
	}

	public void cuelga(TreeNode<T> newNode) {
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

	public TreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}

	public TreeNode<T> getRight() {
		return right;
	}

	public void setRight(TreeNode<T> right) {
		this.right = right;
	}

}
