import java.util.*;

public class MSS {
	
	public static void main (String[] args) {
		Scanner scnr = new Scanner(System.in);
		MSS sum = new MSS();
		
		int[] a = {6, 2, -5, 4, -8, 3, 10, -2, 4, 1};
		
		int left = 0;
		
		int right = a.length - 1;
		
		int result = sum.MSS(a, left, right);
		
		System.out.println("The MSS of the array " + Arrays.toString(a) + " is: " + result);
	}
	
	int MSS (int[] a, int left, int right) {
		// Base case 1
		if (right == left) {
			return a[left];
		}
		// Base case 2
		if (right == left + 1) {
			return Math.max(Math.max(a[left], a[right]), a[left] + a[right]);
		}
		
		int mid = (left + right) / 2;
		
		// Find MSS of left array
		int MSS_Left = MSS (a, left, mid);
		
		// Find MSS of right array
		int MSS_Right = MSS (a, mid + 1, right);
		
		int MSS_Middle = MSS_Mid (a, left, mid, right);
		
		return Math.max(Math.max(MSS_Left, MSS_Right), MSS_Middle);
		
	}
	
	int MSS_Mid (int[] a, int left, int mid, int right) {
		int max_left_sum = -999;
		int sum = 0;
		int i;
		
		for (i = mid; i >= left; i--) {
			sum += a[i];
			
			if (sum > max_left_sum) {
				max_left_sum = sum;
			}
		}
		
		int max_right_sum = -999;
		sum = 0;
		
		for (i = mid + 1; i <= right; i++) {
			sum += a[i];
			
			if (sum > max_right_sum) {
				max_right_sum = sum;
			}
		}
		
		return max_left_sum + max_right_sum;
		
	}
	
}
