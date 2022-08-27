

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
	}
	
	public static String tail(String s) {
		return s.substring(1);
	}
	
	public static int eLev(String s1, String s2, int cont) {
		if(s1.length() == 0 || s2.length() == 0) {
			return cont + (s1.length() + s2.length());
		}
		if(s1.charAt(0) == s2.charAt(0)) {
			return eLev(tail(s1), tail(s2), cont);
		}
		return Math.min(Math.min(eLev(tail(s1), s2, cont+1), eLev(s1, tail(s2), cont+1)), eLev(tail(s1), tail(s2), cont+1));
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
		return 0;
	}
	
	public static void swap(int[] arr, int i1, int i2) {
		//System.out.println("I1: " + arr[i1] + " I2: " + arr[i2]);
		int temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}
	
	private static int partition(int[] arr, int start, int end) {
		int pivot = start;
		for(int i = start+1 ; i < (end-start)+start ; i++) {
			if(arr[pivot] > arr[i]) {
				pivot = i;
			}
		}
		return 0;
	}
	
	private static void quickSort(int[] arr, int start, int end) {
		if(start >= end) {
			return;
		}
		int middle = partition(arr, start, end);
		quickSort(arr, start, middle);
		quickSort(arr, middle+1, end);
	}
	
	public static void quickSort(int[] arr) {
		
	}
	
	public static <T extends Comparable<T>> void printArray(T[] arr) {
		System.out.print("[");
		int i = 0;
		for(T a : arr) {
			if(i == arr.length-1)
				System.out.println(a + "]");
			else
				System.out.println(a + ", ");
			i++;
		}
	}
	
	public static void printMatrix(int[][] matrix) {
		for(int i = 0 ; i < matrix.length ; i++) {
			for(int j = 0 ; j < matrix[0].length ; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.print("\n");
		}
	}
	
	public static void permutationsTest() {
		permutations("absc");
	}
	
	public static void levTest() {
		
		List<Integer> list = new List<Integer>();

		list.insertEnd(1);
		list.insertEnd(3);
		list.insertEnd(6);
		list.insertEnd(8);
		list.insertEnd(11);

		System.out.println(list.printLikeQueue());
		list.invertWithoutPointersR();
		System.out.println(list.printLikeQueue());
		permutations("abcs");
		String s1 = "salvado", s2 = "serpiente";
		int[][] values = new int[s1.length()+1][s2.length()+1];
		for (int i = 0; i < s1.length(); i++) {
			for (int j = 0; j < s2.length(); j++) {
				if (i == 0) {
					values[i][j] = j;
				} else if (j == 0) {
					values[i][j] = i;
				} else {
				values[i][j] = -1;
				}
			}
		}
		System.out.println(eLev(s1, s2, 0));
		System.out.println("Minimum changes: " + optLev(s1, s2, values, s1.length()-1, s2.length()-1));
		printMatrix(values);
		System.out.println("Execution times: " + count);
	}
	
	public static void sortTest() {
		Integer[] arr = {4, -2, 5, 100, -234, 2, 1, 5, 3 ,64, -234};
		Sorts.insertionSort(arr);
		printArray(arr);
		System.out.println("Execution times: " + count);
	}
	
	public static void printTestStack(TestResponse[] tR) {
		for(int i = 0 ; i < tR.length ; i++){
			if(tR[i] != null)
				System.out.println("Array: " + tR[i].getArraySize() + " time: " + tR[i].getTime().toMillis() + " milliseconds and " + tR[i].getCount() + " steps");
		}
	}
	
	public static void printComparison(ComparisonResponse comparison) {
		if(comparison.getOffsetTimeSum() > 0) {
			System.out.println("B is better in time than A");
		} else if(comparison.getOffsetTimeSum() < 0) {
			System.out.println("A is better in time than B");
		} else {
			System.out.println("Are equal in time");
		}
		if(comparison.getOffsetStepsSum() > 0) {
			System.out.println("B is better in steps than A");
		} else if(comparison.getOffsetStepsSum() < 0) {
			System.out.println("A is better in steps than B");
		} else {
			System.out.println("Are equal in steps");
		}
	}
	
	public static void testToCsv(TestResponse[] tR) {
		for(int i = 0 ; i < tR.length ; i++){
			if(tR[i] != null)
				System.out.println(tR[i].getArraySize() + "," + tR[i].getTime().toMillis() + "," + tR[i].getCount());
		}
		System.out.println();
	}

	public static void generateCsv() {
		Integer arr[] = Tests.generateArray(10100, 100000000);
		TestResponse[] a = Tests.bubbleSortTest(arr);
		TestResponse[] b = Tests.insertionSortTest(arr);
		System.out.println("Bubble Sort");
		testToCsv(a);
		System.out.println("Insertion Sort");
		testToCsv(b);
		
	}
	
	public static void comparisonSortingTest() {
		Integer arr[] = Tests.generateArray(10000, 100000000);
		TestResponse[] a = Tests.bubbleSortTest(arr);
		TestResponse[] b = Tests.insertionSortTest(arr);
		printComparison(Tests.checkWhosBetter(a, b, 3, 10));
		
	}
	
	public static void previewSortingTest() {
		Integer arr[] = Tests.generateArray(10000, 100000000);
		TestResponse[] testStack = Tests.bubbleSortTest(arr);
		printTestStack(testStack);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		//Permutations
		//permutationsTest();
		
		//Levshtein Distance
		//levTest();
		
		//Sorting
		//sortTest();
		
		//Previews
		//previewSortingTest();
		
		//Comparisons
		//comparisonSortingTest();
	
		generateCsv();
	}

}