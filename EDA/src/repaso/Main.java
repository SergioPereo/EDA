

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repaso;

import java.util.LinkedList;

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
	
	public static String tail(String s) {
		return s.substring(1);
	}
	
	public static int lev(String s1, String s2) {
		System.out.println("between: " + s1 + " and " + s2);
		count++;
		if(s1.length() == 0) {
			return s2.length();
		}
		if(s2.length() == 0) {
			return s1.length();
		}
		if(s1.charAt(0) == s2.charAt(0)) {
			return lev(tail(s1), tail(s2));
		}
		return Math.min(Math.min(lev(tail(s1), s2)+1, lev(s1, tail(s2))+1), lev(tail(s1), tail(s2))+1);
	}
	
	public static int optLev(String s1, String s2, int[][] values, int i, int j) {
		if(i >= 0 && j >= 0) {
			if(values[i][j] == -1) {
				count++;
				if(i==0 && j == 0) {
					values[i][j] = 0;
					return values[i][j];
				}
				if(i==0) {
					values[i][j] = s2.length();
					return values[i][j];
				}
				if(j==0) {
					values[i][j] = s1.length();
					return values[i][j];
				}
				if(s1.charAt(0) == s2.charAt(0)) {
					values[i][j] = optLev(tail(s1), tail(s2), values, i-1, j-1);
					return values[i][j];
				}
				values[i][j] = Math.min(Math.min(optLev(tail(s1), s2, values, i-1, j)+1, optLev(s1, tail(s2), values, i, j-1)+1), optLev(tail(s1), tail(s2), values, i-1, j-1)+1);
				return values[i][j];
			}
			return values[i][j];
		}
		return 0;
	}
	
	public static void printMatrix(int[][] matrix) {
		for(int i = 0 ; i < matrix.length ; i++) {
			for(int j = 0 ; j < matrix[0].length ; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.print("\n");
		}
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
//		permutations("abcs");
		String s1 = "salvado", s2 = "serpiente";
		int[][] values = new int[s1.length()+1][s2.length()+1];
		for (int i = 0; i < s1.length(); i++) {
			for (int j = 0; j < s2.length(); j++) {
//				if (i == 0) {
//					values[i][j] = j;
//				} else if (j == 0) {
//					values[i][j] = i;
//				} else {
				values[i][j] = -1;
//				}
			}
		}
		System.out.println("Minimum changes: " + optLev(s1, s2, values, s1.length()-1, s2.length()-1));
		printMatrix(values);
		
		
		System.out.println("Execution times: " + count);
	}

}