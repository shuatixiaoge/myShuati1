public class Solution {
    /**
    * @param values: an array of integers
    * @return: a boolean which equals to true if the first play
    er will win
    */
    public boolean firstWillWin(int[] values) {
        // write your code here
        int n = values.length;
        if(n <= 1) return true;
        // dp[i][j] = maximum value current player can get
        // between start = i, end = j
        int[][] dp = new int[n][n];
        dp[0][0] = values[0];
        dp[n - 1][n - 1] = values[n - 1];
        int sum = 0;
        for(int num : values){
            sum += num;
        }
        return 2 * memoizedSearch(0, n - 1, values, dp) > sum;
    }
    private int memoizedSearch(int start, int end, int[] values, int[][] dp){
        if(start > end) return 0;
        if(dp[start][end] != 0) return dp[start][end];
        int takeLeft = values[start] + Math.min(memoizedSearch(start + 2, end, values, dp), memoizedSearch(start + 1, end - 1, values, dp));
        int takeRight = values[end] + Math.min(memoizedSearch(start + 1, end - 1, values, dp), memoizedSearch(start, end - 2, values, dp));
        dp[start][end] = Math.max(takeLeft, takeRight);
        return dp[start][end];
    }
}
