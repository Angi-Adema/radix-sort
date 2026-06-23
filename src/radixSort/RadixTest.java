package radixSort;

import java.util.Arrays;

public class RadixTest {
	
	public static void main(String[] args) {
		
		// REFACTOR - Assignment array to include negative numbers
		Integer[] numbers = {783, 99, -38, 472, 182, 264, -212, 543, 356, 295, -156, 692, 491, 94};
		
		// Print assignment array
		System.out.println("Original array: " + Arrays.toString(numbers));
		
		// Sort assignment array using RadixSort class
		RadixSort.radixSort(numbers);
		
		// Print the sorted array
		System.out.println("Sorted array: " + Arrays.toString(numbers));
	}

}

