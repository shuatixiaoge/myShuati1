//DP solution
//Time O(n^2*m), space O(n*m)
class Solution {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] f = new int[n + 1][m + 1];
        int[] sub = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < n; i++) {
            sub[i + 1] = sub[i] + nums[i];
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 0; k < i; k++) {
                    f[i][j] = Math.min(f[i][j], Math.max(f[k][j - 1], sub[i] - sub[k]));
                }
            }
        }
        return f[n][m];
    }
}
// Greedy and Binary Search
// We can use Binary search to find the value x0. Keeping a value mid = (left + right) / 2.
// If F(mid) is false, then we will search the range [mid + 1, right];
 // If F(mid) is true, then we will search [left, mid - 1].
//
// For a given x, we can get the answer of F(x) using a greedy approach.
// Using an accumulator sum to store the sum of the current processing subarray and
// a counter cnt to count the number of existing subarrays.
// We will process the elements in the array one by one.
// For each element num, if sum + num <= x, it means we can add num to the
// current subarray without exceeding the limit. Otherwise, we need to make a cut here,
// start a new subarray with the current element num. This leads to an increment in
// the number of subarrays.
//
// After we have finished the whole process, we need to compare the value cnt to the size limit
// of subarrays m. If cnt <= m, it means we can find a splitting method that ensures the maximum
// largest subarray sum will not exceed x. Otherwise, F(x) should be false.
class Solution {
    public int splitArray(int[] nums, int m) {
        long l = 0;
        long r = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            r += nums[i];
            if (l < nums[i]) {
                l = nums[i];
            }
        }
        long ans = r;
        while (l <= r) {
            long mid = (l + r) >> 1;
            long sum = 0;
            int cnt = 1;
            for (int i = 0; i < n; i++) {
                if (sum + nums[i] > mid) {
                    cnt ++;
                    sum = nums[i];
                } else {
                    sum += nums[i];
                }
            }
            if (cnt <= m) {
                ans = Math.min(ans, mid);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int)ans;
    }
}
