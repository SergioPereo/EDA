package repaso;
import java.time.*;
import java.util.Random;

public class Tests {

	public static <T extends Comparable<T>> void copyPartitionArray(T[] arr, T[] other, int endIndex) {
		for (int i = 0; i < endIndex; i++) {
			other[i] = arr[i];
		}
	}

	public static ComparisonResponse checkWhosBetter(TestResponse[] a, TestResponse[] b, int timeOffset, int stepsOffset) {
		int timeOffsetSum = 0;
		int stepsOffsetSum = 0;
		for(int i = 0 ; i < a.length ; i++) {
			if(a[i] != null && b[i] != null) {
				timeOffsetSum += (a[i].getTime().toMillis()-b[i].getTime().toMillis());
			}
		}
		if(Math.abs(timeOffsetSum)<timeOffset)
			timeOffsetSum = 0;
		if(Math.abs(stepsOffsetSum)<stepsOffset)
			stepsOffsetSum = 0;
		return new ComparisonResponse(timeOffsetSum, stepsOffsetSum);
	}

	public static <T extends Comparable<T>> TestResponse[] bubbleSortTest(T[] arr) {
		Instant startTime, endTime;
		int count = 0;
		T[] testCase;
		TestResponse[] res = new TestResponse[arr.length];
		for (int i = 0; i < arr.length; i+=100) {
			testCase = (T[]) new Comparable[i];
			copyPartitionArray(arr, testCase, i);
			startTime = Instant.now();
			count = Sorts.bubbleSort(testCase);
			endTime = Instant.now();
			res[i] = new TestResponse(Duration.between(startTime, endTime), testCase.length, count);
		}
		return res;
	}

	public static <T extends Comparable<T>> TestResponse[] selectionSortTest(T[] arr) {
		Instant startTime, endTime;
		int count = 0;
		T[] testCase;
		TestResponse[] res = new TestResponse[arr.length];
		for (int i = 0; i < arr.length; i+=100) {
			testCase = (T[]) new Comparable[i];
			copyPartitionArray(arr, testCase, i);
			startTime = Instant.now();
			count = Sorts.selectionSort(testCase);
			endTime = Instant.now();
			res[i] = new TestResponse(Duration.between(startTime, endTime), testCase.length, count);
		}
		return res;
	}

	public static <T extends Comparable<T>> TestResponse[] insertionSortTest(T[] arr) {
		Instant startTime, endTime;
		int count = 0;
		T[] testCase;
		TestResponse[] res = new TestResponse[arr.length];
		for (int i = 0; i < arr.length; i+=100) {
			testCase = (T[]) new Comparable[i];
			copyPartitionArray(arr, testCase, i);
			startTime = Instant.now();
			count = Sorts.insertionSort(testCase);
			endTime = Instant.now();
			res[i] = new TestResponse(Duration.between(startTime, endTime), testCase.length, count);
		}
		return res;
	}
	
	public static Integer[] generateArray(int size, int max) {
		Integer[] arr = new Integer[size];
		Random rnd = new Random();
		for(int i = 0 ; i < size ; i++) {
			arr[i] = Integer.valueOf(rnd.nextInt(max));
		}
		return arr;
	}

}
