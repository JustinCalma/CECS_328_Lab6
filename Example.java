public class Example {
	
	public static void main (String[] args) {
		Example obj = new Example();
		
		int[] a = {2, -3, 1, 4, -6, 10, -12, 5, 3, -8};
				
		int left = 0;
		int right = a.length - 1;
		
		int result = obj.MPSS(a, left, right);
		
		System.out.println("The MPSS of the array is: " + result);

	}
	
	public int MPSS (int[] a, int left, int right) {
		// Base case 1
		if (right == left) {
			return a[left];
		}
		//Base case 2
		if (right == left + 1) {
			return Math.min(Math.min(a[left], a[right]) , a[left] + a[right]);
		}
				
		int mid = (left + right) / 2;
				
		// Find the MPSS of the left array
		int MPSS_Left = MPSS(a, left, mid);
		
		// Find the MPSS of the right array
		int MPSS_Right = MPSS(a, mid + 1, right);
				
		// Find the MPSS of the middle portion that intersects the left and right arrays
		int MPSS_Middle = MPSS_Mid(a, left, mid, right);
				
		// Return the MPSS of the entire array
		return Math.min(Math.min(MPSS_Left, MPSS_Right), MPSS_Middle);
	}
	
	public int MPSS_Mid (int[] a, int left, int mid, int right) {
		int min_pos_sum = 0;
		int n = a.length - 1;

		//i: starting index of sum
		for(int i = 0; i < n; i++)
		{
		    int this_sum = 0;
		    
		    //compute all sums that begin with index i
		    for(int j = i; j < n; j++)
		    {
		        this_sum += a[j];

		        if(this_sum > 0 && this_sum < min_pos_sum)
		            min_pos_sum = this_sum;
		    }
		}
		return min_pos_sum;
	}
	
}
