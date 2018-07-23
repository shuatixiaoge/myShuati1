class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int res = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            // max with 0 if no transaction has been made
            res += Math.max(0, prices[i + 1] - prices[i]);
        }
        return res;
    }
}
