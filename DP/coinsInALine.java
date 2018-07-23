public class Solution {
    /**
     * @param n: an integer
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        // write your code here
        boolean []dp = new boolean[n+1];
        boolean []flag = new boolean[n+1];
        return MemorySearch(n, dp, flag);
    }
    boolean MemorySearch(int i, boolean []dp, boolean []flag) {
        if(flag[i] == true) {
            return dp[i];
        }
        if(i == 0) {
            dp[i] = false;
        } else if(i == 1) {
            dp[i] = true;
        } else if(i == 2) {
            dp[i] = true;
        } else {
            dp[i] = !MemorySearch(i-1, dp, flag) || !MemorySearch(i-2, dp, flag);
        }
        flag[i] =true;
        return dp[i];
    }
}

//方法三
public class Solution {
    /**
     * @param n: an integer
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        // write your code here
        if (n == 0)
            return false;
        else if (n == 1)
            return true;
        else if (n == 2)
            return true;

        boolean []dp = new boolean[n+1];
        dp[0] = false;
        dp[1] = true;
        dp[2] = true;
        for (int i = 3; i <= n; i++)
            dp[i] = !dp[i - 1] || !dp[i - 2];

        return dp[n];
    }
}
