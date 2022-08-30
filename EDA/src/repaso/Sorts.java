package repaso;

import java.util.Random;

public class Sorts {
	
	public static int count = 0;
	
	public static <T extends Comparable<T>> void swap(T[] arr, int i1, int i2) {
		T temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}
	
	public static <T extends Comparable<T>> int selectionSort(T[] arr) {
		count = 0;
		if(arr.length > 1) {
			T value; 
			int index;
			for(int i = 1 ; i < arr.length ; i++) {
				value = arr[i];
				index = i;
				for(int j = i+1 ; j < arr.length ; j++) {
					if(arr[j].compareTo(value) < 0) {
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
	
	public static <T extends Comparable<T>> int insertionSort(T[] arr) {
		count = 0;
		if(arr.length > 1) {
			int j = 0;
			for(int i = 1 ; i < arr.length ; i++) {
				while(j < i) {
					if(arr[j].compareTo(arr[i]) > 0) {
						swap(arr, i, j);
					}
					j++;
					count++;
				}
				j=0;
			}
		}
		return count;
	}
	
	public static <T extends Comparable<T>> int bubbleSort(T[] arr) {
		count = 0;
		if(arr.length > 1) {
			int j = 0;
			for(int i = 0 ; i < arr.length ; i++) {
				while(j < arr.length-i-1) {
					if(arr[j].compareTo(arr[j+1]) > 0)
						swap(arr, j, j+1);
					j++;
					count++;
				}
				j = 0;
			}
		}
		return count;
	}
	
	private static <T extends Comparable<T>> int partition(T[] arr, int start, int end) {
		Random rnd = new Random();
		int pivot = ((int)rnd.nextDouble()*(end-start)) + start, bigPlaceHolder = end-1, i = start+1;
		swap(arr, start, pivot);
		pivot = start;
		while(i <= bigPlaceHolder) {
			if(arr[pivot].compareTo(arr[i])>0) {
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
	
	private static <T extends Comparable<T>> void quickSort(T[] arr, int start, int end) {
		if(start >= end) {
			return;
		}
		int pivot = partition(arr, start, end);
		quickSort(arr, start, pivot);
		quickSort(arr, pivot+1, end);
	}
	
	public static <T extends Comparable<T>> int quickSort(T[] arr) {
		count = 0;
		quickSort(arr, 0, arr.length);
		return count;
	}
	
}
