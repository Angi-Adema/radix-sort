package radixSort;

public class RadixSort {

	// Find the largest value in an array
	private static int highestNum(int[] array) {
		
		// Variable to hold the highest number
		int number = 0;
		
		// Loop through the array to find the highest number
		for (int num : array) {
			if (num > number) {
				number = num;
			}
		}
		
		// Return the highest number
		return number;
	}
}
