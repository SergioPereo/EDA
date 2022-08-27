package repaso;

public class Sorts {
	
	public static int count = 0;
	
	public static <T extends Comparable<T>> void swap(T[] arr, int i1, int i2) {
		//System.out.println("I1: " + arr[i1] + " I2: " + arr[i2]);
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
	
}
