public class Solution {
    public int maxProfit(int[] prices) {
    if(prices == null || prices.length <= 1) return 0;
    int n = prices.length;
    // max money in hand after each action
    int[] sell = new int[n];
    int[] buy = new int[n];
    buy[0] = -prices[0];
    sell[0] = 0;
    for(int i = 2; i < n; i++){
        buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
        sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);        }
    }
    return sell[n - 1];
}

}
