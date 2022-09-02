package trees;

public class BinaryTree<T> implements TreeADT<T> {
	
	TreeNode<T> root;
	
	private TreeNode<T> find(TreeNode<T> actual, T element) {
		TreeNode<T> res = null;
		if(actual == null)
			return actual;
		if(actual.getElement().equals(element))
			return actual;
		res = find(actual.getLeft(), element);
		if(res == null)
			res = find(actual.getRight(), element);
		return res;
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
