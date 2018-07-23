// Find Kth element in two sorted array -> related to find median of two sorted array
// Complexity Log(m + n) 只能对前半部分进行放缩，不能对后半部分进行放缩，所以只用start就够了
public static double findKth(int[] A, int A_start, int[] B, int B_start,int k){
  if (A_start >= A.length) return B[B_start + k - 1];
  if (B_start >= B.length) return A[A_start + k - 1];
  if (k == 1) return Math.min(A[A_start], B[B_start]);

  int A_mid = A_start + k/2 - 1 < A.length ? A[A_start + k/2 - 1] : Integer.MAX_VALUE;
  int B_mid = B_start + k/2 - 1 < B.length ? B[B_start + k/2 - 1] : Integer.MAX_VALUE;
  if (A_mid > B_mid) {
    findKth(A, A_start, B, B_start + k/2, k - k/2);
  } else {
    findKth(A, A_start + k/2, B, B_start, k - k/2)
  }
}
