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
	
	// Method for radix sort
	public static void radixSort(Integer[] array) {
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
	
}
