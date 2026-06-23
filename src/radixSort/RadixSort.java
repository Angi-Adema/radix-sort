package radixSort;

import java.util.Arrays;

public class RadixSort {

	// Find the largest value in an array
	private static int highestNum(Integer[] array) {
		
		// Variable to hold the highest number initially storing first element
		// Important to discern the number of digits in the highest number
		int number = array[0];
		
		// Loop through the array to find the highest number
		for (int num : array) {
			if (num > number) {
				number = num;
			}
		}
		
		// Return the highest number
		return number;
	}
	
	// Counting sort method to perform the sort based on the current number position
	private static void sortByCurrentPosition(Integer[] array, int position) {
		
		// Create a temp array to hold sorted numbers for each pass
		Integer[] tempSorted = new Integer[array.length];
		
		// Create an array with 10 elements, one for each possible digit 0-9
		int[] positionCounts = new int[10];
		
		// Loop through numbers in the array to determine number occurrences
		for (int number : array) {
			
			// Locate the position digit such as ones, tens, hundreds, etc.
			int digit = (number/position) % 10;
			
			// Increment the count for this digit keeping a tally of each digit
			positionCounts[digit]++;
		}
		
		// Loop through the positionCounts starting with first element so there 
		// is something to add to - otherwise ArrayIndexOutOfBounds error
		for (int i = 1; i < positionCounts.length; i++) {
			
			// Convert counts into cumulative positions showing where
			// each digit group ends in the sorted array
			positionCounts[i] += positionCounts[i - 1];
		}
		
		// Decrement through the array to preserve order from previous pass
		for (int i = array.length - 1; i >= 0; i--) {
			
			// Store the digit position of the current number (preserve stability)
			int digit = (array[i] / position) % 10;
			
			// Locate the index this number should occupy in the sorted array
			// subtract 1 considering arrays are zero based
			int sortIndex = positionCounts[digit] - 1;
			
			// Add number from above to the temporary array
			tempSorted[sortIndex] = array[i];
			
			// Move current position one to the left
			positionCounts[digit]--;
		}
		
		// Loop through the original array to add sorted numbers back in
		for (int i = 0; i < array.length; i++) {
			
			array[i] = tempSorted[i];
		}
	}
	
	// REFACTOR - Add helper method to handle non-negative numbers
	// Copy all existing logic in radixSort into this helper method
	private static void radixSortPositiveNumbers(Integer[] array) {
		
		// Ensure array to sort is not empty
		if (array == null || array.length == 0) {
			return;
		}
		
		// Call highestNum() to determine number of passes through numbers
		int highestNum = highestNum(array);
		
		// Variable to hold pass through count
		int pass = 1;
		
		// Loop through using highest number to determine number position sorting by
		for (int position = 1; highestNum / position > 0; position *= 10) {
			
			// Call sortByCurrentPosition() to sort by current position
			sortByCurrentPosition(array, position);
			
			// Print statement to confirm array after each iteration
			System.out.println("Pass " + pass + ": " + Arrays.toString(array));
			
			// Increment the pass
			pass++;
		}
		
	}
	
	// REFACTOR - Method for radix sort handling positive and negative numbers
	// Strategy: Get a count of positive and negative numbers in the array, create two temp arrays
	// holding the positive and negative numbers, make negative numbers positive calling radixSortPositiveNumbers() to sort 
	// positive and negative arrays, make negative numbers negative again when adding back to original array, 
	// then add positive sorted numbers after the negatives
	public static void radixSort(Integer[] array) {
		
		// Check to be sure the array to sort is not empty
		if (array == null || array.length == 0) {
			return;
		}
		
		// Variables to count negative and positive numbers in the array
		int negativeNumCount = 0;
		int positiveNumCount = 0;
		
		// Loop through the array to get a count of number types
		for (int number : array) {
			if (number < 0) {
				negativeNumCount++;
			} else {
				positiveNumCount++;
			}
		}
		
		// Create two Integer object arrays to hold positive nums and negative nums separately based on count
		Integer[] negativeNums = new Integer[negativeNumCount];
		Integer[] positiveNums = new Integer[positiveNumCount];
		
		// Create index variables for adding values to the temporary arrays
		int negNum = 0;
		int posNum = 0;
		
		// Loop through the array of numbers and store the positives and negatives separately making negatives
		// positive temporarily
		for (int number : array) {
			if (number < 0) {
				negativeNums[negNum] = Math.abs(number);
				negNum++;
			} else {
				positiveNums[posNum] = number;
				posNum++;
			}
		}
		
		// Sort the values in both arrays
		radixSortPositiveNumbers(negativeNums);
		radixSortPositiveNumbers(positiveNums);
		
		// Variable to keep track of the current index of the original array
		int index = 0;
		
		// Loop through and restore negative numbers in reverse order adding them to the original array
		for (int i = negativeNums.length - 1; i >= 0; i--) {
			array[index] = -negativeNums[i];
			index++;
		}
		
		// Loop through the positive numbers and add them after the negative numbers to the original array
		for (int i = 0; i < positiveNums.length; i++) {
			array[index] = positiveNums[i];
			index++;
		}

	}
	
}
