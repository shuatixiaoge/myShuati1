// For example, given citations = [3, 0, 6, 1, 5],
// which means the researcher has 5 papers in total and
// each of them had received 3, 0, 6, 1, 5 citations respectively.
// Since the researcher has 3 papers with at least 3 citations each
// and the remaining two with no more than 3 citations each, his h-index is 3.
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] bucket = new int[n + 1];
        for (int c : citations) {
            if (c >= n) bucket[n] += 1;
            else bucket[c] += 1;
        }
        int sum = 0;
        //go reverse direction
        for (int i = n; i >= 0; i--) {
            sum += bucket[i];
            if (sum >= i) {
                return i;
            }
        }
        return 0;
    }
// h-index2  what if the citations is sorted
    public int hIndex2(int[] citations) {
        int n = citations.length;
        if (n == 1) return Math.min(1, citations[0]);
        if (n == 0) return 0;
        int right = n - 1;
        int left = 0;
        int mid = right/2;
        while(left < right) {
            if (citations[mid] == n - mid) return n - mid; //当相等的时候就不应该改变left了至少
            if (citations[mid] > n - mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
            mid = left + (right - left)/2;
        }
        if(citations[mid] >= n - mid) // Final check.
            return n - mid;
        return 0;
    }

}
