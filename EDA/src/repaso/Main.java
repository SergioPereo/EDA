

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
public class Main {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		List<Integer> list = new List<Integer>();

		list.insertEnd(1);
		list.insertEnd(3);
		list.insertEnd(6);
		list.insertEnd(8);
		list.insertEnd(11);

		System.out.println(list.printLikeQueue());
		System.out.println(list.printLikeStack());
		System.out.println(list.count());
	}

}