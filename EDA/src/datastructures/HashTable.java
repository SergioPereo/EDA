package datastructures;

import review.Node;

public class HashTable<T> {

	private Node<T>[] arr;
	private double loadFactor = 0.8, count = 0;

	public HashTable() {
		arr = (Node<T>[]) (new Node[100]);
	}

	public HashTable(double loadFactor) {
		arr = (Node<T>[]) (new Node[100]);
		this.loadFactor = loadFactor;
	}

	private void setToRight(Node<T> toAdd, Node<T> previousToAdd) {
		toAdd.setNext(previousToAdd);
	}

	private void expand() {

		Node<T>[] newArr = (Node<T>[]) (new Node[arr.length * 4]);
		Node<T> pointer = null, newToAdd;
		int code = 0;
		for (int i = 0; i < arr.length; i++) {
			pointer = arr[i];
			while (pointer != null) {
				code = pointer.getValue().hashCode() % newArr.length;
				if (newArr[code] == null)
					newArr[code] = new Node<T>(pointer.getValue());
				else {
					newToAdd = new Node<T>(pointer.getValue());
					newToAdd.setNext(newArr[code]);
					newArr[code] = newToAdd;
				}
				pointer = pointer.getNext();
			}
		}
		arr = newArr;
	}

	public void add(T element) {
		int code = element.hashCode() % arr.length;
		Node<T> toAdd = new Node<T>(element);
		if (arr[code] == null)
			arr[code] = toAdd;
		else {
			toAdd.setNext(arr[code]);
			arr[code] = toAdd;
		}
		count++;
		if ((double) count / arr.length > loadFactor)
			expand();

	}
	
	public String toString() {
		StringBuilder sB = new StringBuilder();
		Node<T> pointer;
		for(int i = 0 ; i < arr.length ; i++) {
			pointer = arr[i];
			while(pointer != null) {
				sB.append(pointer.getValue() + " ");
				pointer = pointer.getNext();
			}
			sB.append("null\n");
		}
		return sB.toString();
	}

}
