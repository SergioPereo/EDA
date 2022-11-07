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
 *         TODO: Robust sort. Whick of the algorithms generate a better sorting
 *         if we know there is a certain probability of a comparison works bad.
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
	public static <T extends Comparable<T>> void swap(T[] arr, int i1, int i2) {
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
		T merged[] = (T[]) new Comparable[(end - start) + 1];
		int k = start, j = middle;
		for (int i = 0; i < merged.length; i++) {
			if (k >= middle) {
				merged[i] = arr[j++];
			} else if (j > end) {
				merged[i] = arr[k++];
			} else if (arr[k].compareTo(arr[j]) > 0) {
				merged[i] = arr[j++];
			} else {
				merged[i] = arr[k++];
			}
			count++;
		}
		for (int i = 0; i < merged.length; i++) {
			arr[start + i] = merged[i];
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
		merge(arr, start, middle + 1, end);
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
	 * Private method that merge two sorted and contiguous sub-arrays using an extra
	 * array. This method is used by the merge mixcoac algorithm we talked about in
	 * class.
	 *
	 * @param arr    array used by the algorithm.
	 * @param start  start index of the first sub-array.
	 * @param middle middle index that separated both arrays.
	 * @param end    end index of the second sub-array.
	 */
	private static <T extends Comparable<T>> void mergeMix(T arr[], int start, int middle, int end) {
		T merged[] = (T[]) new Comparable[(end - start) + 1];
		int k = start, j = middle;
		for (int i = 0; i < merged.length; i++) {
			if (k >= middle) {
				merged[i] = arr[j++];
			} else if (j > end) {
				merged[i] = arr[k++];
			} else if (arr[k].compareTo(arr[j]) > 0) {
				// System.out.println("Arr[k]: " + arr[k] + " arr[j]: " + arr[j]);
				merged[i] = arr[j++];
			} else {
				// System.out.println("Arr[k]: " + arr[k] + " arr[j]: " + arr[j]);
				merged[i] = arr[k++];
			}
			count++;
		}
		for (int i = 0; i < merged.length; i++) {
			arr[start + i] = merged[i];
		}
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
		ArrayList<int[]> initialState = new ArrayList<int[]>();
		for (int i = 0; i < max; i++) {
			int[] toAdd = new int[2];
			toAdd[0] = i;
			toAdd[1] = i;
			initialState.add(toAdd);
		}
		int i;
		while (initialState.size() > 1) {
			i = 0;
			while (i < initialState.size() - 1) {
				mergeMix(arr, initialState.get(i)[0], initialState.get(i + 1)[0], initialState.get(i + 1)[1]);
				initialState.get(i)[1] = initialState.get(i + 1)[1];
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

	/**
	 * Heap sort algorithm implementation.
	 *
	 * @param arr array to sort.
	 * @return steps the algorithm will take.
	 */
	public static <T extends Comparable<T>> int heapSort(T[] arr) {
		count = 0;
		int j = 1, temp = 0;
		int size = arr.length;
		int whichChild = 0;
		T[] auxArr = (T[]) new Comparable[size + 1];
		if (arr.length > 1) {
			for (int i = 1; i < size + 1; i++) {
				auxArr[i] = arr[i - 1];
				while (i >> j >= 1 && auxArr[i >> (j - 1)].compareTo(auxArr[i >> j]) < 0) {
					// System.out.println("I:\t" + i + "\tJ:\t" + j);
					swap(auxArr, i >> (j - 1), i >> j);
					//printArray(auxArr);
					j++;
					count++;
				}
				j = 1;
			}
			//System.out.println("------------------------------------------------");
			for (int i = 0; i < size; i++) {
				arr[i] = auxArr[1];
				swap(auxArr, 1, size - i);
				//printArray(auxArr);
				while ((j << 1) <= size - i - 1) {
					whichChild = 0;
					if ((j << 1) + 1 <= size - i - 1) {
						if (auxArr[j << 1].compareTo(auxArr[(j << 1) + 1]) > 0) {
							whichChild = 1;
						} else {
							whichChild = 0;
						}
						count++;
					}
					if (auxArr[j].compareTo(auxArr[(j << 1) + 1 * whichChild]) > 0) {
						swap(auxArr, j, (j << 1) + 1 * whichChild);
					}
					count++;
					//printArray(auxArr);
					j = (j << 1) + 1 * whichChild;
				}
				j = 1;
			}
			arr[size - 1] = auxArr[1];

		}
		return count;
	}

	public static <T> void printArray(T arr[]) {
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
	private static <T> int optLev(T a[], T b[], int[][] values, int delete, int insert, int substitution) {
		count = 0;
		int sum = 0;
		for (int i = 1; i < values.length; i++) {
			for (int j = 1; j < values[0].length; j++) {

				if (a[i - 1].equals(b[j - 1]))
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
	public static <T> int optLev(T a[], T b[]) {
		// Initialize the matrix in where I will store the comparisons
		int[][] values = new int[a.length + 1][b.length + 1];
		for (int i = 0; i <= a.length; i++) {
			for (int j = 0; j <= b.length; j++) {
				if (i == 0) {
					values[i][j] = j;
				} else if (j == 0) {
					values[i][j] = i;
				} else {
					values[i][j] = -1;
				}
			}
		}
		System.out.println("Minimum changes: " + optLev(a, b, values, 1, 1, 1));
		return values[values.length - 1][values[0].length - 1];
	}
	
	private static <T> int noisyCompareTo() {
		return (int)Math.round(Math.random());
	}
	
	/**
	 * Selection sorth algorithm implementation.
	 *
	 * @param arr array to sort.
	 * @return steps the algorithm will take.
	 */
	public static <T extends Comparable<T>> int selectionSort(T[] arr, double noisyFactor) {
		count = 0;
		if (arr.length > 1) {
			T value;
			int index;
			for (int i = 1; i < arr.length; i++) {
				value = arr[i];
				index = i;
				for (int j = i + 1; j < arr.length; j++) {
					if(Math.random() > noisyFactor) {
						if (arr[j].compareTo(value) < 0) {
							value = arr[j];
							index = j;
						}
					} else {
						if (arr[j].compareTo(value) > 0) {
							value = arr[j];
							index = j;
						}	
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
	public static <T extends Comparable<T>> int insertionSort(T[] arr, double noisyFactor) {
		count = 0;
		if (arr.length > 1) {
			int j = 0;
			for (int i = 1; i < arr.length; i++) {
				while (j < i) {
					if(Math.random() > noisyFactor) {
						if (arr[j].compareTo(arr[i]) > 0) {
							swap(arr, i, j);
						}	
					} else {
						if (arr[j].compareTo(arr[i]) < 0) {
							swap(arr, i, j);
						}	
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
	public static <T extends Comparable<T>> int bubbleSort(T[] arr, double noisyFactor) {
		count = 0;
		if (arr.length > 1) {
			int j = 0;
			for (int i = 0; i < arr.length; i++) {
				while (j < arr.length - i - 1) {
					if(Math.random() > noisyFactor) {
						if (arr[j].compareTo(arr[j + 1]) > 0)
							swap(arr, j, j + 1);
					} else {
						if (arr[j].compareTo(arr[j + 1]) < 0)
							swap(arr, j, j + 1);
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
	 * Private method that makes the partition of the quick sort algorithm using a
	 * random pivot.
	 *
	 * @param arr   array used to make the partition.
	 * @param start start index of the partition.
	 * @param end   end index of the partition.
	 * @return final index of the partition.
	 */
	private static <T extends Comparable<T>> int partition(T[] arr, int start, int end, double noisyFactor) {
		Random rnd = new Random();
		int pivot = ((int) rnd.nextDouble() * (end - start)) + start, bigPlaceHolder = end - 1, i = start + 1;
		swap(arr, start, pivot);
		pivot = start;
		while (i <= bigPlaceHolder) {
			if(Math.random() > noisyFactor) {
				if (arr[pivot].compareTo(arr[i]) > 0) {
					swap(arr, pivot, i);
					pivot = i;
					i++;
				} else {
					swap(arr, i, bigPlaceHolder);
					bigPlaceHolder--;
				}	
			} else {
				if (arr[pivot].compareTo(arr[i]) < 0) {
					swap(arr, pivot, i);
					pivot = i;
					i++;
				} else {
					swap(arr, i, bigPlaceHolder);
					bigPlaceHolder--;
				}
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
	private static <T extends Comparable<T>> void quickSort(T[] arr, int start, int end, double noisyFactor) {
		if (start >= end) {
			return;
		}
		int pivot = partition(arr, start, end, noisyFactor);
		quickSort(arr, start, pivot);
		quickSort(arr, pivot + 1, end);
	}

	/**
	 * Public method that starts the quick sort algorithm.
	 *
	 * @param arr array used for the algorithm
	 * @return steps the algorithm will take.
	 */
	public static <T extends Comparable<T>> int quickSort(T[] arr, double noisyFactor) {
		count = 0;
		quickSort(arr, 0, arr.length, noisyFactor);
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
	private static <T extends Comparable<T>> void merge(T arr[], int start, int middle, int end, double noisyFactor) {
		T merged[] = (T[]) new Comparable[(end - start) + 1];
		int k = start, j = middle;
		for (int i = 0; i < merged.length; i++) {
			if(Math.random() > noisyFactor) {
				if (k >= middle) {
					merged[i] = arr[j++];
				} else if (j > end) {
					merged[i] = arr[k++];
				} else if (arr[k].compareTo(arr[j]) > 0) {
					merged[i] = arr[j++];
				} else {
					merged[i] = arr[k++];
				}	
			} else {

				if (k >= middle) {
					merged[i] = arr[j++];
				} else if (j > end) {
					merged[i] = arr[k++];
				} else if (arr[k].compareTo(arr[j]) < 0) {
					merged[i] = arr[j++];
				} else {
					merged[i] = arr[k++];
				}
			}
			count++;
		}
		for (int i = 0; i < merged.length; i++) {
			arr[start + i] = merged[i];
		}
	}

	/**
	 * Private method that makes the logic of the merge sort algorithm.
	 *
	 * @param arr   array used for the algorithm.
	 * @param start start index for this run of the algorithm.
	 * @param end   end index for this run of the algorithm.
	 */
	private static <T extends Comparable<T>> void mergeSort(T arr[], int start, int end, double noisyFactor) {
		if (start >= end) {
			return;
		}
		int middle = (start + end) / 2;
		mergeSort(arr, start, middle, noisyFactor);
		mergeSort(arr, middle + 1, end, noisyFactor);
		merge(arr, start, middle + 1, end, noisyFactor);
	}

	/**
	 * Public method that starts the merge sort algorithm.
	 *
	 * @param arr array used for the algorithm
	 * @return steps the algorithm will take.
	 */
	public static <T extends Comparable<T>> int mergeSort(T arr[], double noisyFactor) {
		count = 0;
		mergeSort(arr, 0, arr.length - 1, noisyFactor);
		return count;
	}

	/**
	 * Private method that merge two sorted and contiguous sub-arrays using an extra
	 * array. This method is used by the merge mixcoac algorithm we talked about in
	 * class.
	 *
	 * @param arr    array used by the algorithm.
	 * @param start  start index of the first sub-array.
	 * @param middle middle index that separated both arrays.
	 * @param end    end index of the second sub-array.
	 */
	private static <T extends Comparable<T>> void mergeMix(T arr[], int start, int middle, int end, double noisyFactor) {
		T merged[] = (T[]) new Comparable[(end - start) + 1];
		int k = start, j = middle;
		for (int i = 0; i < merged.length; i++) {
			if(Math.random() > noisyFactor) {
				if (k >= middle) {
					merged[i] = arr[j++];
				} else if (j > end) {
					merged[i] = arr[k++];
				} else if (arr[k].compareTo(arr[j]) > 0) {
					// System.out.println("Arr[k]: " + arr[k] + " arr[j]: " + arr[j]);
					merged[i] = arr[j++];
				} else {
					// System.out.println("Arr[k]: " + arr[k] + " arr[j]: " + arr[j]);
					merged[i] = arr[k++];
				}	
			} else {
				if (k >= middle) {
					merged[i] = arr[j++];
				} else if (j > end) {
					merged[i] = arr[k++];
				} else if (arr[k].compareTo(arr[j]) < 0) {
					// System.out.println("Arr[k]: " + arr[k] + " arr[j]: " + arr[j]);
					merged[i] = arr[j++];
				} else {
					// System.out.println("Arr[k]: " + arr[k] + " arr[j]: " + arr[j]);
					merged[i] = arr[k++];
				}
			}
			count++;
		}
		for (int i = 0; i < merged.length; i++) {
			arr[start + i] = merged[i];
		}
	}

	/**
	 * Private method that makes the logic of the merge mixcoac sort algorithm.
	 *
	 * @param arr   array used for the algorithm.
	 * @param start start index for this run of the algorithm.
	 * @param end   end index for this run of the algorithm.
	 */
	private static <T extends Comparable<T>> void mergeMixSort(T[] arr, int min, int max, double noisyFactor) {
		ArrayList<int[]> initialState = new ArrayList<int[]>();
		for (int i = 0; i < max; i++) {
			int[] toAdd = new int[2];
			toAdd[0] = i;
			toAdd[1] = i;
			initialState.add(toAdd);
		}
		int i;
		while (initialState.size() > 1) {
			i = 0;
			while (i < initialState.size() - 1) {
				mergeMix(arr, initialState.get(i)[0], initialState.get(i + 1)[0], initialState.get(i + 1)[1], noisyFactor);
				initialState.get(i)[1] = initialState.get(i + 1)[1];
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
	public static <T extends Comparable<T>> int mergeMixSort(T arr[], double noisyFactor) {
		count = 0;
		mergeMixSort(arr, 0, arr.length, noisyFactor);
		return count;
	}

	/**
	 * Heap sort algorithm implementation.
	 *
	 * @param arr array to sort.
	 * @return steps the algorithm will take.
	 */
	public static <T extends Comparable<T>> int heapSort(T[] arr, double noisyFactor) {
		count = 0;
		int j = 1, temp = 0;
		int size = arr.length;
		int whichChild = 0;
		T[] auxArr = (T[]) new Comparable[size + 1];
		if (arr.length > 1) {
			for (int i = 1; i < size + 1; i++) {
				auxArr[i] = arr[i - 1];
				while (i >> j >= 1 && auxArr[i >> (j - 1)].compareTo(auxArr[i >> j]) < 0) {
					// System.out.println("I:\t" + i + "\tJ:\t" + j);
					swap(auxArr, i >> (j - 1), i >> j);
					//printArray(auxArr);
					j++;
					count++;
				}
				j = 1;
			}
			//System.out.println("------------------------------------------------");
			for (int i = 0; i < size; i++) {
				arr[i] = auxArr[1];
				swap(auxArr, 1, size - i);
				//printArray(auxArr);
				while ((j << 1) <= size - i - 1) {
					whichChild = 0;
					if ((j << 1) + 1 <= size - i - 1) {
						whichChild = auxArr[j << 1].compareTo(auxArr[(j << 1) + 1]) > 0 ? 1 : 0;
						count++;
					}
					if(Math.random() > noisyFactor) {
						if (auxArr[j].compareTo(auxArr[(j << 1) + 1 * whichChild]) > 0) {
							swap(auxArr, j, (j << 1) + 1 * whichChild);
						}	
					} else {
						if (auxArr[j].compareTo(auxArr[(j << 1) + 1 * whichChild]) < 0) {
							swap(auxArr, j, (j << 1) + 1 * whichChild);
						}
					}
					count++;
					//printArray(auxArr);
					j = (j << 1) + 1 * whichChild;
				}
				j = 1;
			}
			arr[size - 1] = auxArr[1];

		}
		return count;
	}

}
