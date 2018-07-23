class Solution {
    public int maxProfit(int[] prices) {
        int[] leftmax = new int[prices.length];
        int leftmin = Integer.MAX_VALUE;
        int[] rightmax = new int[prices.length];
        int rightmaxVal = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            leftmin = Math.min(leftmin, prices[i]);
            if (i == 0) {
                leftmax[i] = 0;
            } else {
                leftmax[i] = Math.max(leftmax[i - 1], prices[i] - leftmin);
            }
        }

        for (int i = prices.length - 1; i >= 0; i--) {
            rightmaxVal = Math.max(rightmaxVal, prices[i]);// this is max, not min
            if (i == prices.length - 1) {
                rightmax[i] = 0;
            } else {
                rightmax[i] = Math.max(rightmax[i + 1], rightmaxVal - prices[i]);
            }
        }

        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            res = Math.max(res, leftmax[i] + rightmax[i]);
        }
        return res;
    }
}
