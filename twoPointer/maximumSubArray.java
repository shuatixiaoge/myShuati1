// Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
//
// For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
// the contiguous subarray [4,-1,2,1] has the largest sum = 6.

// maxSubArray(A, i) = maxSubArray(A, i - 1) > 0 ? maxSubArray(A, i - 1) : 0 + A[i];
// And here's the code

public int maxSubArray(int[] A) {
        int n = A.length;
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
        dp[0] = A[0];
        int max = dp[0];

        for(int i = 1; i < n; i++){
          // check with 0 is important
            dp[i] = A[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }

        return max;
}
// This can be simplified as no extra space O(1)
public int maxSubArray(int[] nums) {
    int res = Integer.MIN_VALUE, curSum = 0;
    for (int num : nums) {
        curSum = Math.max(curSum + num, num);
        res = Math.max(res, curSum);
    }
    return res;
}
//solution3
public int maxSubArray(int[] nums) {
    if(nums == null || nums.length == 0) return 0;
    int max = Integer.MIN_VALUE, min = 0;
    int prefixSum = 0;
    for(int i = 0; i < nums.length; i++){
        prefixSum += nums[i];
        max = Math.max(max, prefixSum - min);
        min = Math.min(min, prefixSum);
    }
    return max;
}

//Solution 4
//No negative number
public int maxSubArray(int[] nums) {
    if (nums.length <= 0) return 0;
    int res = nums[0];
    int sum = nums[0];
    for (int i = 1; i < nums.length; i++) {
        if (sum < 0) {
            sum = 0;
        }
        sum += nums[i];
        res = Math.max(res, sum);
    }
    return res;
}
