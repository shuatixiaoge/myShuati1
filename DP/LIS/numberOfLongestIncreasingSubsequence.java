class Solution {
    //LIS 是多长 这个问题是有几个
    public int findNumberOfLIS(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int[] dp = new int[nums.length];
        int[] count = new int[nums.length];
        int maxLen = 0, res = 0;
        dp[0] = nums[0];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[i] == dp[j] + 1) {
                        count[i] += count[j];
                    } else if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    }
                }
            }
            if (maxLen == dp[i]) res += count[i];
            if (maxLen < dp[i]) {
                maxLen = dp[i];
                res = count[i];
            }
        }
        return res;
    }
}
