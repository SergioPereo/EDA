/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repaso;

/**
 *
 * @author Sergio Pereo
 */
public class List <F>{

	Node<F> head;

	List(){
		head = new Node<F>();
	}

	public void insertEnd(F element){
		Node<F> actualNode = head, newNode = new Node<F>(element);
		while(actualNode.getNext() != null){
			actualNode = actualNode.getNext();
		}
		actualNode.setNext(newNode);
	}

	public void inserStart(F element){
		if(head.getNext() == null){
			insertEnd(element);
		} else {
			Node<F> newNode = new Node<F>(element);
			newNode.setNext(head.getNext());
			head.setNext(newNode);
		}
	}

	public String printLikeQueue(){
		Node<F> actualNode = head;
		String res = "";
		while(actualNode!=null){
			res += actualNode.toString() + "\n";
			actualNode = actualNode.getNext();
		}
		return res;
	}

	private String printLikeStack(Node<F> node, String res){
		if(node.getNext() != null){
			return printLikeStack(node.getNext(), node.toString() + "\n" + res);
		} else {
			return node.toString() + "\n" + res;
		}
	}

	public String printLikeStack(){
		return printLikeStack(head, "");
	}

	public int count(){
		int c = -1;
		Node<F> node = head;
		while(node != null){
			node = node.getNext();
			c++;
		}
		return c;
	}

	private void invertWithPointersR(Node<F> previous, Node<F> actual, Node<F> next) {
		if(next != null) {
			invertWithPointersR(actual, next, actual.getNext());
			actual.setNext(previous);
		} else {
			if(previous != null) {
				head.getNext().setNext(null);
				head.setNext(actual);
			}
		}
	}

	public void invertWithPointersR() {
		if(head.getNext() != null){
			invertWithPointersR(null, head.getNext(), head.getNext().getNext());
		}
	}

	public void invertWithPointers(){
		if(head.getNext() != null){
			Node<F> actualNode = head.getNext(), previousNode = null, nextNode = head.getNext().getNext();
			while(nextNode != null){
				previousNode = actualNode;
				actualNode = nextNode;
				nextNode = actualNode.getNext();
				actualNode.setNext(previousNode);
			}
			if(previousNode != null){
				head.getNext().setNext(null);
				head.setNext(actualNode);
			}
		}
	}


	private void swapValue(Node<F> a, Node<F> b){
		F value = a.getValue();
		a.setValue(b.getValue());
		b.setValue(value);
	}

	private void invertWithoutPointersR(Node<F> actual, Node<F>  initial, int size, int times, int i, int j) {
		if(i < times) {
			if(j < (size-i-1)) {
				invertWithoutPointersR(actual.getNext(), initial, size, times, i, j+1);
			} else {
				System.out.println(initial + " " + actual);
				this.swapValue(initial, actual);
				invertWithoutPointersR(initial.getNext(), initial.getNext(), size, times, i+1, i+1);
			}
		}
	}

	public void invertWithoutPointersR() {
		int size = this.count(), i=0, j=0;
		if(size > 1){
			invertWithoutPointersR(head.getNext(), head.getNext(), size, (int)size/2 , i, j);
		}
	}

	public void invertWithoutPointers(){
		int size = this.count(), i=0, j=0, times;
		if(size > 1){
			times = (int)size/2;
			Node<F> actualNode, initial = head.getNext();
			while(i<times){
				actualNode = initial;
				while(j<(size-i-1)){
					actualNode = actualNode.getNext();
					j++;
				}
				this.swapValue(initial, actualNode);
				initial = initial.getNext();
				i++;
				j=i;
			}
		}
	}
}