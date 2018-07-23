//Divide one array into 2 subarray make sure the sum is maximum

public class Solution {
    /**
    * @param nums: A list of integers
    * @return: An integer denotes the sum of max two non-overla
    pping subarrays
    */
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        // write your code
        if(nums == null || nums.size() == 0) return 0;
        int n = nums.size();
        // Maximum subarray value from left/right with n elements
        int[] left = new int[n];
        int[] right = new int[n];
        int prefixSum = 0;
        int minSum = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            prefixSum += nums.get(i);
            max = Math.max(max, prefixSum - minSum);
            minSum = Math.min(minSum, prefixSum);
            left[i] = max;
        }
        prefixSum = 0;
        minSum = 0;
        max = Integer.MIN_VALUE;
        // Same logic if starting from right
        for(int i = n - 1; i >= 0; i- ){
            prefixSum += nums.get(i);
            max = Math.max(max, prefixSum - minSum);
            minSum = Math.min(minSum, prefixSum);
            right[i] = max;
        }
        int rst = Integer.MIN_VALUE;
        for(int i = 0; i < n - 1; i++){
            rst = Math.max(rst, left[i] + right[i + 1]);
        }
        return rst;
    }
}
