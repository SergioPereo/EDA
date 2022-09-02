package trees;

public class TreeNode<T> {

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
