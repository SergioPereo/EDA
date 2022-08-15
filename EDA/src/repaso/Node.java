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
public class Node <F>{
	F element;
	Node<F> next;

	public Node(){
	}

	public Node(F element){
		this.element = element;
	}

	public F getValue(){
		return element;
	}

	public Node<F> getNext(){
		return next;
	}

	public void setValue(F value){
		element = value;
	}

	public void setNext(Node<F> node){
		next = node;
	}

	public String toString(){
		if(element == null){
			return "";
		}
		return element.toString();
	}

}