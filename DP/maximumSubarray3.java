//K subarray with maximum sum
//localMax[i][j] 表示前i各元素，分化为j个subarray，一定要take第i个元素的结果
//globalMax[i][j] 不论去不去第i个元素的结果
//localMax[i][j] = Math.max(localMax[i - 1][j], globalMax[i - 1][j - 1]) + value[i]
// you don't need to consider localMax[i - 1][j - 1] here, since it's already being included in global
// globalMax[i][j] = Math.max(globalMax[i - 1][j], localMax[i][j])
// , so this is like max = Math.max(max, curr)

public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */
    public int maxSubArray(int[] nums, int k) {
        if (nums.length < k) {
            return 0;
        }
        int len = nums.length;


        int[][] globalMax = new int[k + 1][len + 1];
        int[][] localMax = new int[k + 1][len + 1];

        // since i >= j
        for (int i = 1; i <= k; i++) {
            //based on the i subarray cannot divide with i -1 len
            localMax[i][i-1] = Integer.MIN_VALUE;
            //小于 i 的数组不能够partition
            for (int j = i; j <= len; j++) {
                localMax[i][j] = Math.max(localMax[i][j-1], globalMax[i - 1][j-1]) + nums[j-1];
                if (j == i)
                    globalMax[i][j] = localMax[i][j];
                else
                    globalMax[i][j] = Math.max(globalMax[i][j-1], localMax[i][j]);
            }
        }
        return globalMax[k][len];
    }

}
