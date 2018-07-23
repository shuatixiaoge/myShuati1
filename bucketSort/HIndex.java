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
}
