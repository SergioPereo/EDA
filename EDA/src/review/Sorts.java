package review;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Sergio Pereo
 * 
 *         Class that will be used to implement the sorting algorithms seen in
 *         class.
 * 
 */
public class Sorts {

	/**
	 * Counter that is used to count steps of the algorithms.
	 *
	 */
	public static int count = 0;

	/**
	 * Method that swaps values in the input indexes.
	 *
	 * @param arr array where you will exchange the values.
	 * @param i1  first index.
	 * @param i2  second index.
	 */
	private static <T extends Comparable<T>> void swap(T[] arr, int i1, int i2) {
		T temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}

	/**
	 * Selection sorth algorithm implementation.
	 *
	 * @param arr array to sort.
	 * @return steps the algorithm will take.
	 */
	public static <T extends Comparable<T>> int selectionSort(T[] arr) {
		count = 0;
		if (arr.length > 1) {
			T value;
			int index;
			for (int i = 1; i < arr.length; i++) {
				value = arr[i];
				index = i;
				for (int j = i + 1; j < arr.length; j++) {
					if (arr[j].compareTo(value) < 0) {
						value = arr[j];
						index = j;
					}
					count++;
				}
				swap(arr, i, index);
			}
		}
		return count;
	}

	/**
	 * Insertion sort algorithm implementation.
	 *
	 * @param arr array to sort.
	 * @return steps the algorithm will take.
	 */
	public static <T extends Comparable<T>> int insertionSort(T[] arr) {
		count = 0;
		if (arr.length > 1) {
			int j = 0;
			for (int i = 1; i < arr.length; i++) {
				while (j < i) {
					if (arr[j].compareTo(arr[i]) > 0) {
						swap(arr, i, j);
					}
					j++;
					count++;
				}
				j = 0;
			}
		}
		return count;
	}

	/**
	 * Bubble sort algorithm implementation.
	 *
	 * @param arr array to sort.
	 * @return steps the algorithm will take.
	 */
	public static <T extends Comparable<T>> int bubbleSort(T[] arr) {
		count = 0;
		if (arr.length > 1) {
			int j = 0;
			for (int i = 0; i < arr.length; i++) {
				while (j < arr.length - i - 1) {
					if (arr[j].compareTo(arr[j + 1]) > 0)
						swap(arr, j, j + 1);
					j++;
					count++;
				}
				j = 0;
			}
		}
		return count;
	}

	/**
	 * Private method that makes the partition of the quick sort algorithm using a
	 * random pivot.
	 *
	 * @param arr   array used to make the partition.
	 * @param start start index of the partition.
	 * @param end   end index of the partition.
	 * @return final index of the partition.
	 */
	private static <T extends Comparable<T>> int partition(T[] arr, int start, int end) {
		Random rnd = new Random();
		int pivot = ((int) rnd.nextDouble() * (end - start)) + start, bigPlaceHolder = end - 1, i = start + 1;
		swap(arr, start, pivot);
		pivot = start;
		while (i <= bigPlaceHolder) {
			if (arr[pivot].compareTo(arr[i]) > 0) {
				swap(arr, pivot, i);
				pivot = i;
				i++;
			} else {
				swap(arr, i, bigPlaceHolder);
				bigPlaceHolder--;
			}
			count++;
		}
		return pivot;
	}

	/**
	 * Private method that makes the logic of the quick sort algorithm.
	 *
	 * @param arr   array used for the algorithm.
	 * @param start start index for this run of the algorithm.
	 * @param end   end index for this run of the algorithm.
	 */
	private static <T extends Comparable<T>> void quickSort(T[] arr, int start, int end) {
		if (start >= end) {
			return;
		}
		int pivot = partition(arr, start, end);
		quickSort(arr, start, pivot);
		quickSort(arr, pivot + 1, end);
	}

	/**
	 * Public method that starts the quick sort algorithm.
	 *
	 * @param arr array used for the algorithm
	 * @return steps the algorithm will take.
	 */
	public static <T extends Comparable<T>> int quickSort(T[] arr) {
		count = 0;
		quickSort(arr, 0, arr.length);
		return count;
	}

	/**
	 * Private method that merge two sorted and contiguous sub-arrays using an extra
	 * array.
	 *
	 * @param arr    array used by the algorithm.
	 * @param start  start index of the first sub-array.
	 * @param middle middle index that separated both arrays.
	 * @param end    end index of the second sub-array.
	 */
	private static <T extends Comparable<T>> void merge(T arr[], int start, int middle, int end) {
		System.out.println("Start: " + start + " middle: " + middle + " end: " + end);
		T merged[] = (T[]) new Comparable[(end - start) + 1];
		int k = start, j = middle;
		for (int i = 0; i < merged.length; i++) {
			if (k >= middle) {
				merged[i] = arr[j++];
			} else if (j > end) {
				merged[i] = arr[k++];
			} else if (arr[k].compareTo(arr[j]) > 0) {
				System.out.println("Arr[k]: " + arr[k] + " arr[j]: " + arr[j]);
				merged[i] = arr[j++];
			} else {
				System.out.println("Arr[k]: " + arr[k] + " arr[j]: " + arr[j]);
				merged[i] = arr[k++];
			}
			count++;
		}
		for (int i = 0; i < merged.length; i++) {
			arr[start + i] = merged[i];
			count++;
		}
	}

	/**
	 * Private method that merge two sorted and contiguous sub-arrays without using
	 * an extra array.
	 *
	 * @param arr    array used by the algorithm.
	 * @param start  start index of the first sub-array.
	 * @param middle middle index that separated both arrays.
	 * @param end    end index of the second sub-array.
	 */
	private static <T extends Comparable<T>> void mergeWthtArr(T arr[], int start, int middle, int end) {
		T value;
		int index;
		int middleStart = middle;
		for (int i = start; i < (end - start) + start; i++) {
			if (arr[i].compareTo(arr[middleStart]) > 0) {
				value = arr[middleStart];
				index = middleStart;
				while (index > i) {
					arr[index] = arr[--index];
					count++;
				}
				arr[i] = value;
				middleStart++;
			}
			count++;
		}
	}

	/**
	 * Private method that makes the logic of the merge sort algorithm.
	 *
	 * @param arr   array used for the algorithm.
	 * @param start start index for this run of the algorithm.
	 * @param end   end index for this run of the algorithm.
	 */
	private static <T extends Comparable<T>> void mergeSort(T arr[], int start, int end) {
		if (start >= end) {
			return;
		}
		int middle = (start + end) / 2;
		mergeSort(arr, start, middle);
		mergeSort(arr, middle + 1, end);
		mergeWthtArr(arr, start, middle + 1, end);
	}

	/**
	 * Public method that starts the merge sort algorithm.
	 *
	 * @param arr array used for the algorithm
	 * @return steps the algorithm will take.
	 */
	public static <T extends Comparable<T>> int mergeSort(T arr[]) {
		count = 0;
		mergeSort(arr, 0, arr.length - 1);
		return count;
	}

	/**
	 * Private method that merge two sorted and contiguous sub-arrays without using
	 * an extra array. This method is used by the merge mixcoac algorithm we talked
	 * about in class.
	 *
	 * @param arr    array used by the algorithm.
	 * @param start  start index of the first sub-array.
	 * @param middle middle index that separated both arrays.
	 * @param end    end index of the second sub-array.
	 */
	private static <T extends Comparable<T>> void mergeMixWthtArr(T arr[], int start, int middle, int end) {
		T value;
		int index;
		int middleStart = middle;
		for (int i = start; i < (end - start) + start; i++) {
			if (middleStart <= end) {
				if (arr[i].compareTo(arr[middleStart]) > 0) {
					value = arr[middleStart];
					index = middleStart;
					while (index > i) {
						arr[index] = arr[--index];
						count++;
					}
					arr[i] = value;
					middleStart++;
				}
				count++;
			}
		}
	}

	/**
	 * Private method that makes the logic of the merge mixcoac sort algorithm.
	 *
	 * @param arr   array used for the algorithm.
	 * @param start start index for this run of the algorithm.
	 * @param end   end index for this run of the algorithm.
	 */
	private static <T extends Comparable<T>> void mergeMixSort(T[] arr, int min, int max) {
		ArrayList<ArrayList<Integer>> initialState = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < max; i++) {
			ArrayList<Integer> toAdd = new ArrayList<Integer>();
			toAdd.add(i);
			toAdd.add(i);
			initialState.add(toAdd);
		}
		int i;
		while (initialState.size() > 1) {
			i = 0;
			while (i < initialState.size() - 1) {
				mergeMixWthtArr(arr, initialState.get(i).get(0), initialState.get(i + 1).get(0),
						initialState.get(i + 1).get(1));
				initialState.get(i).set(1, initialState.get(i + 1).get(1));
				initialState.remove(i + 1);
				i++;
			}
		}

	}

	/**
	 * Public method that starts the merge sort algorithm.
	 *
	 * @param arr array used for the algorithm
	 * @return steps the algorithm will take.
	 */
	public static <T extends Comparable<T>> int mergeMixSort(T arr[]) {
		count = 0;
		mergeMixSort(arr, 0, arr.length);
		return count;
	}

}
