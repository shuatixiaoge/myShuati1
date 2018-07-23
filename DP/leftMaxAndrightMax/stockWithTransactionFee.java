class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        if (n == 0) return 0;
        int[] hold = new int[n];
        int[] notHold = new int[n];

        hold[0] = -prices[0];

        for (int i = 1; i < n; i++) {
            notHold[i] = Math.max(notHold[i - 1], hold[i - 1] + prices[i] - fee);
            hold[i] = Math.max(hold[i - 1], notHold[i - 1] - prices[i]);
        }
        return notHold[n - 1];
    }

    //solution 2 Constant space
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int len = prices.length;
        int hold = -prices[0];
        int notHold = 0;

        for (int i = 1; i < prices.length; i++) {
            hold = Math.max(hold, notHold - prices[i]);
            notHold = Math.max(notHold, hold + prices[i] - fee);
        }

        return notHold;
    }
}
