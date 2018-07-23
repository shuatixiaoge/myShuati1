public class Solution {
    public int maxCoins(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        for(int i = 0; i < n; i++){
            arr[i + 1] = nums[i];
        }
        int[][] dp = new int[n + 2][n + 2];
        return memoizedSearch(1, n, arr, dp);
    }
    private int memoizedSearch(int start, int end, int[] arr, int[][] dp){
        if(dp[start][end] != 0) return dp[start][end];
        int max = 0;
        for(int i = start; i <= end; i++){
            //check the boundary to busrt the ballon, means the things in between would gone
            int cur = arr[start - 1] * arr[i] * arr[end + 1];
            int left = memoizedSearch(start, i - 1, arr, dp);
            int right = memoizedSearch(i + 1, end, arr, dp);
            max = Math.max(max, cur + left + right);
        }
        dp[start][end] = max;
        return max;
    }
}
