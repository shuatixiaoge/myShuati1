//Minimum Combination size
public class Solution {
    public int coinChange(int[] A, int M) {
        int[] f = new int[M + 1];
        int n = A.length;

        f[0] = 0;
        int i, j;
        for (i = 1; i <= M; ++i) {
            f[i] = -1;
            for (j = 0; j < n; ++j) {
                if (i >= A[j] && f[i - A[j]] != -1) {
                    if (f[i] == -1 || f[i - A[j]] + 1 < f[i]) {
                        f[i] = f[i - A[j]] + 1;
                    }
                }
            }
        }

        return f[M];
    }
}


//Coin change 2  return the count of combination


public class Solution {
    /**
     * @param amount: a total amount of money amount
     * @param coins: the denomination of each coin
     * @return: the number of combinations that make up the amount
     */
    public int change(int M, int[] A) {
        // write your code here
        if(M == 0) {
            return 1;
        }
        int[] dp = new int[M + 1];
        dp[0] = 1;
        for(int i = 0; i < A.length; i++) {
            for(int j = A[i]; j <= M; j++) {
                dp[j] += dp[j - A[i]];
            }
        }
        return dp[M];
    }
}
