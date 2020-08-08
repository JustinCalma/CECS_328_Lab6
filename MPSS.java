import java.text.DecimalFormat;
import java.util.*;

public class MPSS {
	
	public static void main (String[] args) {
		Scanner scnr = new Scanner(System.in); // Creates Scanner object
		MPSS mpss = new MPSS(); // Creates an MPSS object
		DecimalFormat df1 = new DecimalFormat("0.##"); // Creates an object to format the double values to the nearest tenth
		
		System.out.println("Enter the desired size of the random generated array: "); // Asks the user for the desired array size
		int n = scnr.nextInt(); // Saves the input into variable n
		
		double[] a = new double[n]; // Creates a double[] array with the desired array size
		
		for (int i = 0; i < n; i++) { // For loop to generate random double values from the range -20 to 20 and saves them into the array
			double randNum = -20 + (double)(Math.random() * (21 - -20));
			a[i] = randNum;
		}

		// Test Cases
//		double[] a = {2, -3, 1, 4, -6, 10, -12, 5.2, 3.6, -8};
//		double[] a = {10, -2, 11, -4, 13, -5, -2}; 
//		double[] a = {1, 2, 3, -30, -40, 4, 5, 6};
//		double[] a = {-34, 49, -58, 76, 29, -71, -54, 63};
//		double[] a = {-6, 2, -3, 1, -1, 2};
		
		int left = 0; // Left most element / index
		int right = a.length - 1; // Right most element / index
		
		double result = mpss.MPSS(a, left, right); // Calls the MPSS method and passes in array a and left and right indexes
		
		System.out.println("The minimum positive subsequence sum (MPSS) from the array "); // Prints the first part of the output
		
		System.out.print("[ "); // Left bracket 
		for (int i = 0; i < a.length; i++) { // Loops through the array and prints out the formatted value at each index
			System.out.printf("%.2f", a[i]);
			System.out.print(" ");
		}

		System.out.print("] is " + df1.format(result)); // Prints the last backet and result
		
	}
	
	// Time Complexity: 
	// Recursive MPSS Method
	public double MPSS (double[] a, int left, int right) {
		// Base case 
		if (left == right) {
			if (a[left] < 0) {
				return Double.MAX_VALUE;
			} else {
				return a[left];
			}
		}
		
		// Finds the middle index of the array
		int mid = (left + right) / 2;
		
		// Find the MPSS of the left array
		double MPSS_Left = MPSS(a, left, mid);		

		// Find the MPSS of the right array
		double MPSS_Right = MPSS(a, mid + 1, right);
		
		// Find the MPSS of the middle portion that intersects the left and right arrays
		double MPSS_Middle = MPSS_Mid(a, left, mid, right);

		// Return the MPSS of the entire array
		return Math.min(Math.min(MPSS_Left, MPSS_Right), MPSS_Middle); 
		
	}

	// Time Complexity: 
	// Method to calculate the MPSS_Middle
	public double MPSS_Mid (double[] a, int left, int mid, int right) {
		// Finds the middle of array a
		int subArrayMid = (a.length) / 2;
		
		// Creates a variable to save the current element of subarray left
		double currentElem = 0;
		
		// Creates the left Array
		double[] S_L = new double[subArrayMid]; 
		
		// For loop finding the subsequence sums for the subarray left
		for (int i = S_L.length - 1; i >= 0; i--) { // O(1) 
			
			// Calculates the sum and saves it into the correct index
			S_L[i] = a[i] + currentElem;
			
			// Sets the current element as the calculated sum
			currentElem = S_L[i];
		}
		
		// Instantites the right subarray
		double[] S_R;
		
		// Checks and determines if the array a is of odd or even length
		if (a.length % 2 != 0) {
			S_R = new double[subArrayMid + 1];
		} else {
			S_R = new double[subArrayMid];
		}

		// Creates a variable to save the current element of subarray right
		double currentElem2 = 0;
				
		// Creates a counter correctly find and index from the middle of array a to the end
		int counter = subArrayMid;
		
		// For loop finding the subsequence sums for the subarray right
		for(int j = 0; j < S_R.length; j++) { // O(1) 
			
			//Calculates the sum and saves it into the correct index
			S_R[j] = a[counter] + currentElem2;
			
			// Sets the current element as the calculated sum
			currentElem2 = S_R[j];
			
			// Increments counter to get the next element in array a 
			counter++;
		}
		
		// Uses built in sort method in java to sort in ascending order for left subarray
		Arrays.sort(S_L);
		
		// Uses build in sort method to sort right subarray in ascending order
		Arrays.sort(S_R);
		
		// Calls a helper method to reverse the right subarray into descending order
		Reverse(S_R);
		
		// Define two markers i and j for S_L and S_R
		int i = 0;
		int j = 0;
		
		// Sets s_min to be the largers Double value 
		double s_min = Double.MAX_VALUE;
		
		// Keep looping until the elements of S_L or S_R gets exhausted
		while (true) {

			// Calculates the sum of S_L and S_R 
			double s = S_L[i] + S_R[j];
			
			// Checks if sum is negative then iterate i 
			if (s <= 0) {
				i++;
			} else if (s < s_min) { // Else if the sum is positive then iterate j
				s_min = s;
				j++;
			} else { // Else iterate j
				j++;
			}
			
			// Checks if the elements of S_L or the elements of S_R have been exhausted and return the min calculated sum
			if (i == S_L.length - 1 || j == S_R.length - 1) {
				return s_min;
			}
		}	
	}	
	
	// Time Complexity: O(1)
	// Helper Reverse method to make S_R into descending order
	public static void Reverse(double[] a) { 
		int right = a.length - 1; 
		int mid = a.length / 2; 
		
		for (int i = 0; i < mid; i++) { 
			double temp = a[i]; 
			a[i] = a[right - i]; 
			a[right - i] = temp; 
			
		}
	}		
}

// Explain: 
// --> It will be harder to calculate MPSS_Middle because now you have to find the subsequence sum of 
// --> each element both of the subarrays. It will also be harder to calculate MPSS_Middle because you are
// --> calculating the subsequence sum from the middle to the ends of the array.
// Calculate Time Complexity:
// --> The Time Complexity of the MPSS algorithm would be Big Theta of (n log^2 n).
// --> This can be found by using its recurrence forumla of T(n) = 2T(n/2) + n log n.
// --> There will be 2 sub-problems being created, the left and righ subarrays. The sub problems
// --> will have a size of n/2 because to find the 2 sub arrays, you just have to divide the array, with size n,
// --> from the middle and the 2 sub problems will have half of the size of the array a. The time it will take to 
// --> divide and combine each sub-problem will take n log n time.
// Explain how/why MPSS_Middle works:
// --> The way that the MPSS_Middle algorithm works is by first creating the left and right subarrays. Then find the 
// --> subsequence sums for both of the subarrays. Then using the QuickSort algorithm, or using the built in sorting method
// --> in Java, sort the left sub array in ascending order and the right sub array in descending order. Then create
// --> indexes i and j to represent as markers within each sub array. Use a while loop to keep iterating until one of the 
// --> sub arrays have exhausted their elements. Within the while loop add the first elements of each sub array to find the
// --> first subsequence sum, check if the sum is <= 0 (sum is a negative value), if true then increment i, else if (meaning that sum is positve)
// --> increment j, else increment j. This will keep iterating through each of the subarrays calculating a new subsequence sum
// --> check if it is an appropriate value, and once it has exhausted one of the subarrays, then it will return the last calculated positive sum.