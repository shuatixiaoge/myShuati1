class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        if (n == 1) return Math.min(1, citations[0]);
        if (n == 0) return 0;
        int right = n - 1;
        int left = 0;
        int mid = 0;
        while(left < right) {
            mid = left + (right - left)/2;
            if (citations[mid] == n - mid) return n - mid; //当相等的时候就不应该改变left了至少
            if (citations[mid] > n - mid) {
                right = mid; 
            } else {
                left = mid + 1;
            }
        }
        //left is the value to use now
        //corner case like [0,0] return 0, not 1
        if (citations[left] >= n - left) return n - left;
        return 0;
    }
}
