package review;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Sergio Pereo
 * 
 *         Review class that will be used to program tests and/or some exercises
 *         to refresh recursion, sorting and DS concepts.
 * 
 */
public class Main {
	public static void swap(int[] arr, int i1, int i2) {
		int temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}

	/**
	 * Counter that is used to count things in the algorithms, since steps to
	 * results.
	 *
	 */
	private static int count = 0;

	/**
	 * Private method that do the permutations of a given string. Count is used to
	 * store all the permutations.
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
	 * Method that give you all the different possible permutations of the given
	 * string
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
	 * My class version of the levenshtein distance algorithm (the version with the
	 * specifications given by my professor) It uses recursion to explore all the
	 * possible solutions and return the minimal one.
	 *
	 * @param s1   the string you will be looking forward to transform.
	 * @param s2   the string to which you will try to transform s1.
	 * @param cont the counter for the operations.
	 * @return the minimal amount of operations you need to do to transform s1 into
	 *         s2.
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
	 * Original version of the levenshtein distance algorithm. It uses recursion to
	 * explore all the possible solutions and return the minimal one.
	 *
	 * @param s1 the string you will be looking forward to transform.
	 * @param s2 the string to which you will try to transform s1.
	 * @return the minimal amount of operations you need to do to transform s1 into
	 *         s2.
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
	 * Optimized version of the levenshtein distance algorithm. It uses a matrix to
	 * avoid repeating the comparison of the same strings. This one is the private
	 * method that do the algorithm in a iterative way.
	 *
	 * @param s1           the string you will be looking forward to transform.
	 * @param s2           the string to which you will try to transform s1.
	 * @param values       the matrix where the comparisons will be saved.
	 * @param delete       the value of delete operation.
	 * @param insert       the value of insert operation.
	 * @param substitution the value of substitution operation.
	 * @return the minimal amount of operations you need to do to transform s1 into
	 *         s2.
	 */
	private static double optLev(String s1, String s2, double[][] values, int delete, int insert, int substitution) {
		count = 0;
		int sum = 0;
		for (int i = 1; i < values.length; i++) {
			for (int j = 1; j < values[0].length; j++) {

				if (s1.charAt(i - 1) == s2.charAt(j - 1))
					sum = 0;
				else
					sum = 1;
				values[i][j] = Math.min(values[i - 1][j] + sum * delete,
						Math.min(values[i][j - 1] + sum * insert, values[i - 1][j - 1] + sum * substitution));
				count++;
				sum = 0;
			}
		}
		return values[values.length - 1][values[0].length - 1];
	}

	/**
	 * Optimized version of the levenshtein distance algorithm. It uses a matrix to
	 * avoid repeating the comparisons of the same strings.
	 *
	 * @param s1 the string you will be looking forward to transform.
	 * @param s2 the string to which you will try to transform s1.
	 * @return the minimal amount of operations you need to do to transform s1 into
	 *         s2.
	 */
	public static double optLev(String s1, String s2) {
		// Initialize the matrix in where I will store the comparisons
		double[][] values = new double[s1.length() + 1][s2.length() + 1];
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
		System.out.println("Minimum changes: " + optLev(s1, s2, values, 1, 1, 1));
		printMatrix(values);
		return values[values.length - 1][values[0].length - 1];
	}

	/**
	 * Prints an array of generic types.
	 *
	 * @param arr the generic array.
	 * 
	 */
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

	/**
	 * Prints an array of ints.
	 *
	 * @param arr the int array.
	 * 
	 */
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

	/**
	 * Prints a matrix of ints.
	 *
	 * @param arr the int matrix.
	 * 
	 */
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.print("\n");
		}
	}

	/**
	 * Prints a matrix of ints.
	 *
	 * @param arr the int matrix.
	 * 
	 */
	public static void printMatrix(double[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.print("\n");
		}
	}

	/**
	 * Prints all tests of the experiment.
	 *
	 * @param tR the array of tests.
	 * 
	 */
	public static void printTests(TestResponse[] tR) {
		for (int i = 0; i < tR.length; i++) {
			if (tR[i] != null)
				System.out.println("Array: " + tR[i].getArraySize() + " time: " + tR[i].getTime().toMillis()
						+ " milliseconds and " + tR[i].getCount() + " steps");
		}
	}

	/**
	 * Tells you if the algorithms are better and/or equal in steps and time based
	 * on the differences in steps and time.
	 *
	 * @param comparison the comparison of both algorithms.
	 * 
	 */
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

	/**
	 * Prints the test response in a csv form to use it somewhere else.
	 *
	 * @param tR the array of tests that an algorithm has.
	 * 
	 */
	public static void testToCsv(TestResponse[] tR) {
		for (int i = 0; i < tR.length; i++) {
			if (tR[i] != null)
				System.out.println(tR[i].getArraySize() + "," + tR[i].getTime().toMillis() + "," + tR[i].getCount());
		}
		System.out.println();
	}

	/**
	 * Generate the csv of the desired algorithms. Probably need to parametrize this
	 * function to avoid code changing.
	 * 
	 */
	public static void generateCsv(String title, TestResponse[] tR) {
		System.out.println(title);
		testToCsv(tR);

	}

	/**
	 * Prints all the possible permutations of the input string.
	 *
	 * @param s input string that need to be permutated.
	 * 
	 */
	public static void permutationsTest(String s) {
		permutations(s);
		System.out.println("Quantity of permutations: " + count);
	}

	/**
	 * Tests of the lists implementation, this is mainly just a desired test placed
	 * in a function to avoid overcharging the main method.
	 * 
	 */
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

	/**
	 * This tests prints the result of the levenshtein algorithm both in the
	 * recursive way and the optimized iterative way. It also prints the resulting
	 * matrix of the optimized levenshtein algorithm.
	 * 
	 */
	public static void levTest() {
		System.out.println("Levenshtein Original");
		System.out.println("M??nimo de operaciones: " + lev("icor", "rico"));
		System.out.println("Cantidad de operaciones del algoritmo: " + count);
		System.out.println("Levshtein Optimizado");
		System.out.println("M??nimo de operaciones: " + optLev("icor", "rico"));
		System.out.println("Cantidad de operaciones del algoritmo: " + count);
	}

	/**
	 * This function can be used to make tests of the sorting methods of the Sorts
	 * class.
	 * 
	 */
	public static void sortTest() {
		Integer[] arr = { 4, -2, 5, 100, -234, 2, 1, 5, 3, 64, -234, 4, 24, 3, 5, 35, -350 };
		count = Sorts.heapSort(arr);
		printArray(arr);
		System.out.println("Execution times: " + count);
	}

	/**
	 * This test compare two sorting algorithms. You can change this code to try
	 * your own. This probably needs to be parametrized as well.
	 * 
	 */
	public static void comparisonSortingTest() {
		Integer arr[] = Tests.generateArray(10000, 100000000);
		TestResponse[] a = Tests.heapSortTest(arr);
		TestResponse[] b = Tests.mergeSortTest(arr);
		printComparison(Tests.checkWhosBetter(a, b, 3, 0));
	}

	/**
	 * This test make an array of tests for the desired sorting algorithm. You can
	 * change this code to try your own. This probably needs to be parametrized as
	 * well.
	 * 
	 */
	public static void previewSortingTest() {
		Integer arr[] = Tests.generateArray(10000, 100000000);
		TestResponse[] testStack = Tests.bubbleSortTest(arr);
		printTests(testStack);
	}

	/**
	 * Homework methods
	 */

	public static TestResponse[] averageOfTests(TestResponse[][] tests, int runs) {
		TestResponse[] algorithmAverage = new TestResponse[tests[0].length];
		for (int i = 0; i < algorithmAverage.length; i++) {
			algorithmAverage[i] = new TestResponse(Duration.ZERO, tests[0][i].getArraySize(), 0);
		}
		for (int i = 0; i < runs; i++) {
			for (int j = 0; j < algorithmAverage.length; j++) {
				algorithmAverage[j].sum(tests[i][j]);
			}
		}
		for (int i = 0; i < algorithmAverage.length; i++) {
			algorithmAverage[i].setTime(algorithmAverage[i].getTime().dividedBy(runs));
			algorithmAverage[i].setCount(algorithmAverage[i].getCount() / runs);
		}
		return algorithmAverage;
	}

	public static void getRandomTests(Movie[] movies, int runs) {

		Movie[] toSort = (Movie[]) Tests.generateArrayCopy(movies);

		TestResponse[][] bubble = new TestResponse[runs][];
		//TestResponse[][] selection = new TestResponse[runs][];
		//TestResponse[][] insertion = new TestResponse[runs][];
		//TestResponse[][] quick = new TestResponse[runs][];
		TestResponse[][] merge = new TestResponse[runs][];
		//TestResponse[][] mergeMix = new TestResponse[runs][];

		for (int i = 0; i < runs; i++) {
			System.out.println("Doing the " + i + " run...");
			Tests.shuffleArray(toSort);
			bubble[i] = Tests.heapSortTest(toSort);
			/*
			Tests.shuffleArray(toSort);
			selection[i] = Tests.selectionSortTest(toSort);
			Tests.shuffleArray(toSort);
			insertion[i] = Tests.insertionSortTest(toSort);
			Tests.shuffleArray(toSort);
			quick[i] = Tests.quickSortTest(toSort);
			*/
			
			Tests.shuffleArray(toSort);
			merge[i] = Tests.mergeSortTest(toSort);
			/*
			Tests.shuffleArray(toSort);
			mergeMix[i] = Tests.mergeMixSortTest(toSort);
			*/
		}

		System.out.println("Doing averages...");
		generateCsv("Heap sort", averageOfTests(bubble, runs));
		//generateCsv("Selection sort", averageOfTests(selection, runs));
		//generateCsv("Insertion sort", averageOfTests(insertion, runs));
		//generateCsv("Quick sort", averageOfTests(quick, runs));
		generateCsv("Merge sort", averageOfTests(merge, runs));
		//generateCsv("Merge Mixcoac sort", averageOfTests(mergeMix, runs));
	}

	public static void getInvSortedArrayTests(Movie[] movies) {

		Movie[] toSort = Tests.generateSortedInvArray(movies);

		TestResponse[] bubble = Tests.bubbleSortTest(toSort);
		toSort = Tests.generateSortedInvArray(movies);
		TestResponse[] selection = Tests.selectionSortTest(toSort);
		toSort = Tests.generateSortedInvArray(movies);
		TestResponse[] insertion = Tests.insertionSortTest(toSort);
		toSort = Tests.generateSortedInvArray(movies);
		TestResponse[] quick = Tests.quickSortTest(toSort);
		toSort = Tests.generateSortedInvArray(movies);
		TestResponse[] merge = Tests.mergeSortTest(toSort);
		toSort = Tests.generateSortedInvArray(movies);
		TestResponse[] mergeMix = Tests.mergeMixSortTest(toSort);

		generateCsv("Bubble sort", bubble);
		generateCsv("Selection sort", selection);
		generateCsv("Insertion sort", insertion);
		generateCsv("Quick sort", quick);
		generateCsv("Merge sort", merge);
		generateCsv("Merge Mixcoac sort", mergeMix);
	}

	public static void getSortedArrayTests(Movie[] movies) {

		Movie[] toSort = Tests.generateSortedArray(movies);

		TestResponse[] bubble = Tests.bubbleSortTest(toSort);
		toSort = Tests.generateSortedArray(movies);
		TestResponse[] selection = Tests.selectionSortTest(toSort);
		toSort = Tests.generateSortedArray(movies);
		TestResponse[] insertion = Tests.insertionSortTest(toSort);
		toSort = Tests.generateSortedArray(movies);
		TestResponse[] quick = Tests.quickSortTest(toSort);
		toSort = Tests.generateSortedArray(movies);
		TestResponse[] merge = Tests.mergeSortTest(toSort);
		toSort = Tests.generateSortedArray(movies);
		TestResponse[] mergeMix = Tests.mergeMixSortTest(toSort);

		generateCsv("Bubble sort", bubble);
		generateCsv("Selection sort", selection);
		generateCsv("Insertion sort", insertion);
		generateCsv("Quick sort", quick);
		generateCsv("Merge sort", merge);
		generateCsv("Merge Mixcoac sort", mergeMix);
	}
	
	public static void switchFor(Integer[] toSort, int i, double noisyFactor) {
		if(i==0) {
			Sorts.bubbleSort(toSort, noisyFactor);
		} else if(i == 1) {
			Sorts.selectionSort(toSort, noisyFactor);
		} else if(i == 2) {
			Sorts.insertionSort(toSort, noisyFactor);
		} else if(i == 3) {
			Sorts.quickSort(toSort, noisyFactor);
		} else if(i == 4) {
			Sorts.mergeSort(toSort, noisyFactor);
		} else if(i == 5) {
			Sorts.mergeMixSort(toSort, noisyFactor);
		} else if(i == 6) {
			Sorts.heapSort(toSort, noisyFactor);
		}
	}
	
	public static void noisyCompareTest() {
		Integer arr[] = Tests.generateArray(10000, 100000000);
		Integer sortedArr[] = arr.clone();
		Sorts.mergeSort(sortedArr);
		Integer toSort[][] = new Integer[7][];
		System.out.println("Sorted");
		printArray(sortedArr);
		int results[] = new int[7];
		for(int i = 0 ; i < toSort.length ; i++) {
			toSort[i] = arr.clone();
			switchFor(toSort[i], i, 0.3d);
		}
		for(int i = 0 ; i < toSort.length ; i++) {
			printArray(toSort[i]);
			System.out.println();
			printArray(sortedArr);
			System.out.println();
			results[i] = Sorts.optLev(toSort[i], sortedArr);
		}
		printArray(results);
	}

	public static Movie[] readFile() {
		ArrayList<Movie> moviesList = new ArrayList<Movie>();
		try {
			File myObj = new File("movie_titles2.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] info = data.split(",");
				moviesList.add(new Movie(info[2], Integer.parseInt(info[0]), Integer.parseInt(info[1])));
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return moviesList.toArray(new Movie[moviesList.size()]);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		// Permutations
		// permutationsTest();

		// Levshtein Distance
		//levTest();

		// Sorting
		//sortTest();

		// Previews
		// previewSortingTest();

		// Comparisons
		// comparisonSortingTest();
		// generateCsv();

		// Homework
		//getRandomTests(readFile(), 30);
		//getInvSortedArrayTests(readFile());
		//getSortedArrayTests(readFile());
		
		//sortTest();
		noisyCompareTest();
	}

}