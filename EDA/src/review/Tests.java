package review;

import java.time.*;
import java.util.Random;

/**
 *
 * @author Sergio Pereo
 * 
 *         Class that will be used to make tests efficiency tests.
 * 
 */

public class Tests {

	/**
	 * Private method that copy a partition of the original array into the other.
	 *
	 * @param arr      array that will be copied.
	 * @param other    array in which arr will be copied.
	 * @param endIndex the size of the partition.
	 */
	private static <T extends Comparable<T>> void copyPartitionArray(T[] arr, T[] other, int endIndex) {
		for (int i = 0; i < endIndex; i++) {
			other[i] = arr[i];
		}
	}

	/**
	 * Public method that compare two tests and save the comparison in a
	 * ComparisonResponse object. Arrays must be the same length and each test is
	 * made using the same quantity of n-elements.
	 *
	 * @param a           the first test set.
	 * @param b           the second tests set.
	 * @param timeOffset  time offset you would say the tests are equal.
	 * @param stepsOffset steps offset you would say the tests are equal.
	 * @return the comparison result.
	 */
	public static ComparisonResponse checkWhosBetter(TestResponse[] a, TestResponse[] b, int timeOffset,
			int stepsOffset) {
		int timeOffsetSum = 0;
		int stepsOffsetSum = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] != null && b[i] != null) {
				timeOffsetSum += (a[i].getTime().toMillis() - b[i].getTime().toMillis());
			}
		}
		if (Math.abs(timeOffsetSum) < timeOffset)
			timeOffsetSum = 0;
		if (Math.abs(stepsOffsetSum) < stepsOffset)
			stepsOffsetSum = 0;
		return new ComparisonResponse(timeOffsetSum, stepsOffsetSum);
	}

	/**
	 * Public method that makes a test set for the bubble sort algorithm. This
	 * probably need to be more parametrized. Because you need an algorithm with at
	 * least 1000 elements so this can be useful.
	 *
	 * @param arr array used to make the set.
	 * @return the test set of the algorithm
	 */
	public static <T extends Comparable<T>> TestResponse[] bubbleSortTest(T[] arr) {
		Instant startTime, endTime;
		int count = 0;
		T[] testCase;
		TestResponse[] res = new TestResponse[arr.length];
		for (int i = 0; i < arr.length; i += 100) {
			testCase = (T[]) new Comparable[i];
			copyPartitionArray(arr, testCase, i);
			startTime = Instant.now();
			count = Sorts.bubbleSort(testCase);
			endTime = Instant.now();
			res[i] = new TestResponse(Duration.between(startTime, endTime), testCase.length, count);
		}
		return res;
	}

	/**
	 * Public method that makes a test set for the selection sort algorithm. This
	 * probably need to be more parametrized. Because you need an algorithm with at
	 * least 1000 elements so this can be useful.
	 *
	 * @param arr array used to make the set.
	 * @return the test set of the algorithm
	 */
	public static <T extends Comparable<T>> TestResponse[] selectionSortTest(T[] arr) {
		Instant startTime, endTime;
		int count = 0;
		T[] testCase;
		TestResponse[] res = new TestResponse[arr.length];
		for (int i = 0; i < arr.length; i += 100) {
			testCase = (T[]) new Comparable[i];
			copyPartitionArray(arr, testCase, i);
			startTime = Instant.now();
			count = Sorts.selectionSort(testCase);
			endTime = Instant.now();
			res[i] = new TestResponse(Duration.between(startTime, endTime), testCase.length, count);
		}
		return res;
	}

	/**
	 * Public method that makes a test set for the insertion sort algorithm. This
	 * probably need to be more parametrized. Because you need an algorithm with at
	 * least 1000 elements so this can be useful.
	 *
	 * @param arr array used to make the set.
	 * @return the test set of the algorithm
	 */
	public static <T extends Comparable<T>> TestResponse[] insertionSortTest(T[] arr) {
		Instant startTime, endTime;
		int count = 0;
		T[] testCase;
		TestResponse[] res = new TestResponse[arr.length];
		for (int i = 0; i < arr.length; i += 100) {
			testCase = (T[]) new Comparable[i];
			copyPartitionArray(arr, testCase, i);
			startTime = Instant.now();
			count = Sorts.insertionSort(testCase);
			endTime = Instant.now();
			res[i] = new TestResponse(Duration.between(startTime, endTime), testCase.length, count);
		}
		return res;
	}

	/**
	 * Public method that makes a test set for the quick sort algorithm. This
	 * probably need to be more parametrized. Because you need an algorithm with at
	 * least 1000 elements so this can be useful.
	 *
	 * @param arr array used to make the set.
	 * @return the test set of the algorithm
	 */
	public static <T extends Comparable<T>> TestResponse[] quickSortTest(T[] arr) {
		Instant startTime, endTime;
		int count = 0;
		T[] testCase;
		TestResponse[] res = new TestResponse[arr.length];
		for (int i = 0; i < arr.length; i += 100) {
			testCase = (T[]) new Comparable[i];
			copyPartitionArray(arr, testCase, i);
			startTime = Instant.now();
			count = Sorts.quickSort(testCase);
			endTime = Instant.now();
			res[i] = new TestResponse(Duration.between(startTime, endTime), testCase.length, count);
		}
		return res;
	}

	/**
	 * Public method that makes a test set for the merge sort algorithm. This
	 * probably need to be more parametrized. Because you need an algorithm with at
	 * least 1000 elements so this can be useful.
	 *
	 * @param arr array used to make the set.
	 * @return the test set of the algorithm
	 */
	public static <T extends Comparable<T>> TestResponse[] mergeSortTest(T[] arr) {
		Instant startTime, endTime;
		int count = 0;
		T[] testCase;
		TestResponse[] res = new TestResponse[arr.length];
		for (int i = 0; i < arr.length; i += 100) {
			testCase = (T[]) new Comparable[i];
			copyPartitionArray(arr, testCase, i);
			startTime = Instant.now();
			count = Sorts.mergeSort(testCase);
			endTime = Instant.now();
			res[i] = new TestResponse(Duration.between(startTime, endTime), testCase.length, count);
		}
		return res;
	}

	/**
	 * Public method that makes a test set for the merge mixcoac sort algorithm.
	 * This probably need to be more parametrized. Because you need an algorithm
	 * with at least 1000 elements so this can be useful.
	 *
	 * @param arr array used to make the set.
	 * @return the test set of the algorithm
	 */
	public static <T extends Comparable<T>> TestResponse[] mergeMixSortTest(T[] arr) {
		Instant startTime, endTime;
		int count = 0;
		T[] testCase;
		TestResponse[] res = new TestResponse[arr.length];
		for (int i = 0; i < arr.length; i += 100) {
			testCase = (T[]) new Comparable[i];
			copyPartitionArray(arr, testCase, i);
			startTime = Instant.now();
			count = Sorts.mergeMixSort(testCase);
			endTime = Instant.now();
			res[i] = new TestResponse(Duration.between(startTime, endTime), testCase.length, count);
		}
		return res;
	}

	/**
	 * Private method that generates an array of random positive integers between 0
	 * and the upper bound used as input.
	 *
	 * @param size size of the random array.
	 * @param max  upper bound for the random values.
	 * @return the generated array.
	 */
	public static Integer[] generateArray(int size, int max) {
		Integer[] arr = new Integer[size];
		Random rnd = new Random();
		for (int i = 0; i < size; i++) {
			arr[i] = Integer.valueOf(rnd.nextInt(max));
		}
		return arr;
	}

}
