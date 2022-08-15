

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
	
	public static int count = 0;
	
	public static String pop(String s, int index) {
		String res = "";
		for(int i = 0 ; i < s.length() ; i++) {
			if(i != index) {
				res+=s.charAt(i);
			}
		}
		return res;
	}
	
	public static void permutationsR(String s, String res) {
		if(s.length() > 0) {
			for(int i = 0 ; i < s.length() ; i++) {
				char c = s.charAt(i);
				String rest = s.substring(0, i) + s.substring(i+1);
				permutationsR(rest, res+c);
			}	
		} else {
			System.out.println(res);
			count++;
		}
	}
	
	public static void permutations(String s) {
		permutationsR(s, "");
		System.out.println("Cantidad de permutaciones: " + count);
		count = 0;
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
//		List<Integer> list = new List<Integer>();
//
//		list.insertEnd(1);
//		list.insertEnd(3);
//		list.insertEnd(6);
//		list.insertEnd(8);
//		list.insertEnd(11);
//
//		System.out.println(list.printLikeQueue());
//		list.invertWithoutPointersR();
//		System.out.println(list.printLikeQueue());
		permutations("abcs");
	}

}