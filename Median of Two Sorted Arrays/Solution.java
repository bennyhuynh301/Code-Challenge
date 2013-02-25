public class Solution {
		private static int[] mergeTwoSortedArray(int A[], int B[]) {
			if (A.length == 0) {
				return B;
			}
			if (B.length == 0) {
				return A;
			}
			int[] C = new int[A.length + B.length];
			int i = 0;
			int j = 0;
			for (int k = 0; k < C.length; k++) {
				if (A[i] <= B[j]) {
					C[k] = A[i];
					i++;
					if (i == A.length) {
						for (int h = 0; h < B.length - j; h++) {
							C[k+h+1] = B[j+h];
						}
						break;
					}
				}
				else {
					C[k] = B[j];
					j++;
					if (j == B.length) {
						for (int h = 0; h < A.length - i; h++) {
							C[k+h+1] = A[i+h];
						}
						break;
					}
				}
			}
			return C;
		}

    public double findMedianSortedArrays(int A[], int B[]) {
    	int[] C = Solution.mergeTwoSortedArray(A, B);
    	if ((C.length%2) != 0) {
    		return C[C.length/2];
    	} 
    	return (double)(C[C.length/2-1] + C[C.length/2])/2;    
    }

    public static void main(String[] args) {
    	int[] A = {1,2,11,15};
    	int[] B = {5,8,9};
    	Solution sol = new Solution();
    	System.out.println("Median of two sorted array is: " + sol.findMedianSortedArrays(A,B));
    }
}