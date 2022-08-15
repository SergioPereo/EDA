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

	public void invertWithoutPointers(){
		int size = this.count(), i=0, times, j=0;
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