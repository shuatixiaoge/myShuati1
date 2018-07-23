public class Solution {
public static double findMedianSortedArrays(int A[], int B[]) {
    int total = A.length + B.length;
    if(total%2 == 0){
        return (findKth(A,0,B,0,total/2) + findKth(A,0,B,0,total/2+1))/2.0;
    }else{
        return findKth(A,0,B,0,total/2 + 1);
    }
}
public static double findKth(int[] A, int A_start, int[] B, int B_start,int k){
    if(A_start >= A.length){
        return B[B_start+k-1];
    }
    if(B_start >= B.length){
        return A[A_start+k-1];
    }
    if(k == 1){
        return Math.min(A[A_start],B[B_start]);
    }
    int AKey = A_start+k/2-1 < A.length ? A[A_start + k/2 -1] : Integer.MAX_VALUE;
    int BKey = B_start+k/2-1 < B.length ? B[B_start + k/2 -1] : Integer.MAX_VALUE;
    if(AKey >= BKey){
        return findKth(A,A_start,B,B_start+k/2,k-k/2);
    }else{
        return findKth(A,A_start+k/2,B,B_start,k-k/2);
    }
}
}
