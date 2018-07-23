public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer indicate the value of maximum difference between two
     *          Subarrays
     */
    public int maxDiffSubArrays(int[] nums) {
        // write your code here
        if (nums.length < 2)
        {
            return 0;
        }
        int size = nums.length;
        int[] leftSumMax = new int[size];
        int[] leftSumMin = new int[size];
        leftSumMax[0] = nums[0];
        leftSumMin[0] = nums[0];
        int max = nums[0], min = nums[0], sumMax = nums[0], sumMin = nums[0];
        for (int i = 1; i < size; i++)
        {
            sumMax = Math.max(sumMax + nums[i], nums[i]);
            sumMin = Math.min(sumMin + nums[i], nums[i]);
            max = Math.max(max, sumMax);
            min = Math.min(min, sumMin);
            leftSumMax[i] = max;
            leftSumMin[i] = min;
        }

        int[] rightSumMax = new int[size];
        int[] rightSumMin = new int[size];
        rightSumMax[size -1] = nums[size - 1];
        rightSumMin[size -1] = nums[size - 1];
        max = nums[size - 1];
        min = nums[size - 1];
        sumMax = nums[size - 1];
        sumMin = nums[size - 1];
        for (int i = size - 2; i >= 0; i--)
        {
            sumMax = Math.max(sumMax + nums[i], nums[i]);
            sumMin = Math.min(sumMin + nums[i], nums[i]);
            max = Math.max(max, sumMax);
            min = Math.min(min, sumMin);
            rightSumMax[i] = max;
            rightSumMin[i] = min;
        }

        int rst = Integer.MIN_VALUE;
        for (int i = 0; i <= size - 2; i++)
        {
            rst = Math.max(rst, Math.max(Math.abs(leftSumMax[i] - rightSumMin[i + 1]),
            Math.abs(leftSumMin[i] - rightSumMax[i + 1])));
        }

        return rst;
    }
}
