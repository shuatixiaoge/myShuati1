/**
 * dp[i, j] represents the max profit up until prices[j] using at most i transactions.
 * dp[i, j] = max(dp[i, j-1], prices[j] - prices[jj] + dp[i-1, jj]) { jj in range of [0, j-1] }
 *          = max(dp[i, j-1], prices[j] + max(dp[i-1, jj] - prices[jj]))
 * dp[0, j] = 0; 0 transactions makes 0 profit
 * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
 */

public int maxProfit(int k, int[] prices) {
	int n = prices.length;
	if (n <= 1)
		return 0;

	//if k >= n/2, then you can make maximum number of transactions.
	if (k >=  n/2) {
		int maxPro = 0;
		for (int i = 1; i < n; i++) {
			if (prices[i] > prices[i-1])
				maxPro += prices[i] - prices[i-1];
		}
		return maxPro;
	}

    int[][] dp = new int[k+1][n];
    for (int i = 1; i <= k; i++) {
    	int localMax = dp[i-1][0] - prices[0];
    	for (int j = 1; j < n; j++) {
    		dp[i][j] = Math.max(dp[i][j-1],  prices[j] + localMax);
    		localMax = Math.max(localMax, dp[i-1][j] - prices[j]);
    	}
    }
    return dp[k][n-1];
}


//Solution 2, similar to maximum subarray 3
public class Solution {
	public int maxProfit(int k, int[] prices) {
		if(prices == null || prices.length == 0) return 0;
		int n = prices.length;
		if(k >= n / 2){
			int profit = 0;
			for(int i = 1; i < n; i++){
				int diff = prices[i] - prices[i - 1];
				if(diff > 0) profit += diff;
			}
			return profit;
		}
		int[][] localMax = new int[n][k + 1];
		int[][] globalMax = new int[n][k + 1];
		for(int i = 1; i < n; i++){
			int diff = prices[i] - prices[i - 1];
			for(int j = 1; j <= k && j * 2 <= i + 1; j++){
				//if you already sell at i - 1 for all your chance, you could have sold at i by extending your last txn
				//if you still have one transaction left, why not sell at last day.
				localMax[i][j] = Math.max(localMax[i - 1][j], globalMax[i - 1][j - 1]) + diff;
				//should be globalMax[i - 1][j - 1] not localMax[i - 1][j - 1]; Pay attention to this.
				globalMax[i][j] = Math.max(localMax[i][j], globalMax[i - 1][j]);
			}
		}
		return globalMax[n - 1][k];
	}
}
