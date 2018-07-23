public class Solution {
    public int findPeakElement(int[] A) {
        int left = 0;
        int right = A.length-1;

        while(left <right){
            int mid = left + (right-left)/2;
            //mid > 0 dont forget
            if(mid>0 && A[mid]<A[mid-1]){
                right = mid;
            }
            else if(A[mid+1]>A[mid]){
                left = mid + 1;
            }
            else{
                return mid;
            }
        }
        if(A[left]<A[right]){
            return right;
        }else{
            return left;
        }
    }
}
