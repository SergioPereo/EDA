package datastructures;

public class SkipList<T extends Comparable<T>> {

	SkipNode<T> head, tail;
	int elementCount = 0;
	int levelCount = 1;
	final double LN_2 = Math.log(2);

	public SkipList() {
		head = new SkipNode<T>();
		tail = new SkipNode<T>();

		head.setRight(tail);
		tail.setLeft(head);
	}

	private SkipNode<T> find(T element) {
		if (element == null)
			return null;
		SkipNode<T> pointer = head;
		boolean flag = false;
		// Go to last list
		while (!flag) {
			while (pointer.getRight().getElement() != null && pointer.getRight().getElement().compareTo(element) <= 0) {
				pointer = pointer.getRight();
			}
			if (pointer.getDown() != null)
				pointer = pointer.getDown();
			else
				flag = true;
		}
		return pointer;
	}

	// TODO: Maybe we can make a method that makes easier the logic below
	private void dump(SkipNode<T> node) {
		head.getRight().setLeft(null);
		head = node;
	}

	private void addBetween(SkipNode<T> a, SkipNode<T> toAdd, SkipNode<T> b) {
		a.setRight(toAdd);
		toAdd.setLeft(a);
		toAdd.setRight(b);
		b.setLeft(toAdd);
	}
	
	private void deleteBetween(SkipNode<T> toDelete) {
		toDelete.getLeft().setRight(toDelete.getRight());
		toDelete.getRight().setLeft(toDelete.getLeft());
	}
	
	private void collapse() {
		if(elementCount > 0) {
			SkipNode<T> pointer = head.getRight();
			while(pointer.getElement() != null) {
				if(pointer.getDown()!=null)
					pointer.getDown().setUp(null);
				pointer = pointer.getRight();
			}
			
			pointer = head.getDown();
			if (pointer != null) {
				
				head.getRight().setLeft(null);
				head = pointer;
				head.setUp(null);
				pointer = tail.getDown();
				tail.getLeft().setRight(null);
				tail = pointer;
				tail.setUp(null);
				levelCount--;
			}	
		}
	}

	private void makeFloor() {
		SkipNode<T> newHead = new SkipNode<T>(), newTail = new SkipNode<T>();
		newHead.setRight(newTail);
		newHead.setDown(head);
		head.setUp(newHead);

		newTail.setLeft(newHead);
		newTail.setDown(tail);
		tail.setUp(newTail);

		head = newHead;
		tail = newTail;
		levelCount++;

	}

	private SkipNode<T> goUp(SkipNode<T> noded) {
		SkipNode<T> pointer = noded, newToAdd = new SkipNode<T>(noded.getElement());

		while (pointer.getUp() == null && pointer.getLeft() != null) {
			pointer = pointer.getLeft();
		}
		if (pointer.getUp() == null) {
			makeFloor();
			if (pointer.getUp() == null)
				System.out.println("Problem in make floor");
		}
		pointer = pointer.getUp();

		while (pointer.getRight().getElement() != null
				&& pointer.getRight().getElement().compareTo(noded.getElement()) < 0) {
			pointer = pointer.getRight();
		}
		addBetween(pointer, newToAdd, pointer.getRight());
		noded.setUp(newToAdd);
		newToAdd.setDown(noded);
		return newToAdd;
	}

	public void delete(T element) {
		boolean flag = true;
		if (levelCount >= (Math.log(elementCount + 1) / LN_2) * elementCount)
			collapse();
		SkipNode<T> toDelete = find(element);
		if (toDelete != null && toDelete.getElement() != null) {
			while (flag) {
				toDelete.setDown(null);
				deleteBetween(toDelete);
				if(toDelete.getUp() != null) {
					toDelete = toDelete.getUp();	
				} else {
					flag = false;
				}
			}
		}
	}

	public void add(T element) {
		SkipNode<T> toAdd = new SkipNode<T>(element), lastToAdd = find(element);
		//checkIfHead(lastToAdd);
		addBetween(lastToAdd, toAdd, lastToAdd.getRight());
		//System.out.println("Test: " + this.toString());
		elementCount++;
		double flip = Math.random();
		while (flip > 0.5) {
			toAdd = goUp(toAdd);
			flip = Math.random();
		}
	}

	public String toString() {
		StringBuilder sB = new StringBuilder();
		SkipNode<T> pointer = head, next = head.getDown();
		boolean flag = true;
		String a = "";
		while (flag) {
			a = pointer.getElement() != null ? pointer.getElement().toString() : "null";
			sB.append(a);
			pointer = pointer.getRight();
			if (pointer != null) {
				sB.append("<--->");
			} else {
				sB.append("\n");
				pointer = next;
				if (next != null)
					next = pointer.getDown();
				else
					flag = false;
			}
			a = "";
		}
		return sB.toString();
	}

	public void checkIfHead(SkipNode<T> node) {
		System.out.println(head.equals(node));
	}

}
