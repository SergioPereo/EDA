package repaso;

/**
 *
 * @author Sergio Pereo
 * 
 * Review class that will be used to program tests and/or some exercises to refresh recursion, sorting and DS concepts.
 * 
 */
public class Main {


	/**
	 * Counter that is used to count things in the algorithms, since steps to results.
	 *
	 */
	private static int count = 0;

	/**
	 * Private method that do the permutations of a given string. Count is used to store all the permutations.
	 *
	 * @param s string to be permuted
	 */
	private static void permutationsR(String s, String res) {
		if (s.length() > 0) {
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				String rest = s.substring(0, i) + s.substring(i + 1);
				permutationsR(rest, res + c);
			}
		} else {
			System.out.println(res);
			count++;
		}
	}
	
	/**
	 * Method that give you all the different possible permutations of the given string
	 *
	 * @param s string to be permuted
	 */
	public static void permutations(String s) {
		permutationsR(s, "");
		System.out.println("Cantidad de permutaciones: " + count);
	}

	/**
	 * Private method that returns the entire input string except the first letter.
	 *
	 * @param s the string from which the first letter needs to be removed.
	 * @return the string with the first letter removed.
	 */
	private static String tail(String s) {
		return s.substring(1);
	}

	/**
	 * Class version of the levenshtein distance algorithm (the version with the specifications given by my professor)
	 * It uses recursion to explore all the possible solutions and return the minimal one.
	 *
	 * @param s1 the string you will be looking forward to transform.
	 * @param s2 the string to which you will try to transform s1.
	 * @param cont the counter for the operations.
	 * @return the minimal amount of operations you need to do to transform s1 into s2.
	 */
	public static int eLev(String s1, String s2, int cont) {
		if (s1.length() == 0 || s2.length() == 0) {
			return cont + (s1.length() + s2.length());
		}
		if (s1.charAt(0) == s2.charAt(0)) {
			return eLev(tail(s1), tail(s2), cont);
		}
		return Math.min(Math.min(eLev(tail(s1), s2, cont + 1), eLev(s1, tail(s2), cont + 1)),
				eLev(tail(s1), tail(s2), cont + 1));
	}

	/**
	 * Original version of the levenshtein distance algorithm. It uses recursion to explore all the possible solutions and return the minimal one.
	 *
	 * @param s1 the string you will be looking forward to transform.
	 * @param s2 the string to which you will try to transform s1.
	 * @return the minimal amount of operations you need to do to transform s1 into s2.
	 */
	public static int lev(String s1, String s2) {
		count++;
		if (s1.length() == 0) {
			return s2.length();
		}
		if (s2.length() == 0) {
			return s1.length();
		}
		if (s1.charAt(0) == s2.charAt(0)) {
			return lev(tail(s1), tail(s2));
		}
		return Math.min(Math.min(lev(tail(s1), s2) + 1, lev(s1, tail(s2)) + 1), lev(tail(s1), tail(s2)) + 1);
	}

	/**
	 * Optimized version of the levenshtein distance algorithm. It uses a matrix to avoid repeating the comparison of the same strings.
	 * This one is the private method that do the algorithm in a iterative way.
	 *
	 * @param s1 the string you will be looking forward to transform.
	 * @param s2 the string to which you will try to transform s1.
	 * @param values the matrix where the comparisons will be saved.
	 * @return the minimal amount of operations you need to do to transform s1 into s2.
	 */
	private static int optLev(String s1, String s2, int[][] values) {
		count = 0;
		for (int i = 1; i < values.length; i++) {
			for (int j = 1; j < values[0].length; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1))
					values[i][j] = Math.min(values[i - 1][j], Math.min(values[i][j - 1], values[i - 1][j - 1]));
				else
					values[i][j] = Math.min(values[i - 1][j] + 1,
							Math.min(values[i][j - 1] + 1, values[i - 1][j - 1] + 1));
				count++;
			}
		}
		return values[values.length - 1][values[0].length - 1];
	}

	/**
	 * Optimized version of the levenshtein distance algorithm. It uses a matrix to avoid repeating the comparison of the same strings.
	 *
	 * @param s1 the string you will be looking forward to transform.
	 * @param s2 the string to which you will try to transform s1.
	 * @return the minimal amount of operations you need to do to transform s1 into s2.
	 */
	public static int optLev(String s1, String s2) {
		// Initialize the matrix in where I will store the comparisons
		int[][] values = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0) {
					values[i][j] = j;
				} else if (j == 0) {
					values[i][j] = i;
				} else {
					values[i][j] = -1;
				}
			}
		}
		System.out.println("Minimum changes: " + optLev(s1, s2, values));
		printMatrix(values);
		return values[values.length - 1][values[0].length - 1];
	}

	public static int[] mergeMix(int[] arr1, int[] arr2) {
		int max = arr1.length + arr2.length;
		int[] res = new int[max];
		int i = 0, j = 0;
		for (int k = 0; k < max; k++) {
			if (arr1[i] < arr2[j]) {
				res[k] = arr1[i];
				i++;
			} else {
				res[k] = j;
				j++;
			}
		}
		return res;
	}

	public static void mergeMixSort(int[] arr, int min, int max) {
		int[][] initialState = new int[arr.length][];
		for (int i = 0; i < max; i++) {
			initialState[i] = new int[] { arr[i] };
		}

	}

	public static <T extends Comparable<T>> void printArray(T[] arr) {
		System.out.print("[");
		int i = 0;
		for (T a : arr) {
			if (i == arr.length - 1)
				System.out.print(a + "]");
			else
				System.out.print(a + ", ");
			i++;
		}
		System.out.println();
	}

	public static void printArray(int arr[]) {
		System.out.print("[");
		int i = 0;
		for (int a : arr) {
			if (i == arr.length - 1)
				System.out.print(a + "]");
			else
				System.out.print(a + ", ");
			i++;
		}
		System.out.println();
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.print("\n");
		}
	}

	public static void printTestStack(TestResponse[] tR) {
		for (int i = 0; i < tR.length; i++) {
			if (tR[i] != null)
				System.out.println("Array: " + tR[i].getArraySize() + " time: " + tR[i].getTime().toMillis()
						+ " milliseconds and " + tR[i].getCount() + " steps");
		}
	}

	public static void printComparison(ComparisonResponse comparison) {
		if (comparison.getOffsetTimeSum() > 0) {
			System.out.println("B is better in time than A");
		} else if (comparison.getOffsetTimeSum() < 0) {
			System.out.println("A is better in time than B");
		} else {
			System.out.println("Are equal in time");
		}
		if (comparison.getOffsetStepsSum() > 0) {
			System.out.println("B is better in steps than A");
		} else if (comparison.getOffsetStepsSum() < 0) {
			System.out.println("A is better in steps than B");
		} else {
			System.out.println("Are equal in steps");
		}
	}

	public static void testToCsv(TestResponse[] tR) {
		for (int i = 0; i < tR.length; i++) {
			if (tR[i] != null)
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

	public static void permutationsTest() {
		permutations("absc");
		System.out.println("Execution times: " + count);
	}

	public static void listTest() {
		List<Integer> list = new List<Integer>();

		list.insertEnd(1);
		list.insertEnd(3);
		list.insertEnd(6);
		list.insertEnd(8);
		list.insertEnd(11);

		System.out.println(list.printLikeQueue());
		list.invertWithoutPointersR();
		System.out.println(list.printLikeQueue());
	}

	public static void levTest() {
		System.out.println("Levshtein Original");
		System.out.println("Mínimo de operaciones: " + lev("icor", "rico"));
		System.out.println("Cantidad de operaciones del algoritmo: " + count);
		System.out.println("Levshtein Optimizado");
		System.out.println("Mínimo de operaciones: " + optLev("icor", "rico"));
		System.out.println("Cantidad de operaciones del algoritmo: " + count);
	}

	public static void sortTest() {
		Integer[] arr = { 4, -2, 5, 100, -234, 2, 1, 5, 3, 64, -234 };
		count = Sorts.quickSort(arr);
		printArray(arr);
		System.out.println("Execution times: " + count);
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
		// Permutations
		// permutationsTest();

		// Levshtein Distance
		levTest();

		// Sorting
		// sortTest();

		// Previews
		// previewSortingTest();

		// Comparisons
		// comparisonSortingTest();
		// generateCsv();

	}

}