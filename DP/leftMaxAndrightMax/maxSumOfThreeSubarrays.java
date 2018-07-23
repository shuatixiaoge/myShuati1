class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] W = new int[nums.length - k + 1];
        int[] left = new int[nums.length - k + 1];
        int[] leftIndex = new int[nums.length - k + 1];
        int[] right = new int[nums.length - k + 1];
        int[] rightIndex = new int[nums.length - k + 1];

        for (int i = 0; i < k; i++) {
            W[0] += nums[i];
        }
        for (int i = 1; i < nums.length - k + 1; i++){
            W[i] = W[i - 1] - nums[i - 1] + nums[i + k - 1];
        }

        int leftMax = 0;
        int leftMaxIndex = 0;
        for (int i = 0; i < nums.length - k + 1; i++) {
            if (W[i] > leftMax) {
                leftMaxIndex = i;
                leftMax = W[i];
            }
            leftIndex[i] = leftMaxIndex;
            left[i] = leftMax;
        }

        int rightMax = 0;
        int rightIndexMax = 0;
        for (int i = nums.length - k; i >= 0; i--) {
            if (W[i] > rightMax) {
                rightIndexMax = i;
                rightMax = W[i];
            }
            rightIndex[i] = rightIndexMax;
            right[i] = rightMax;
        }

        int[] res = new int[3];
        int max = 0;
        for (int i = k; i < nums.length - 2 * k + 1; i++) {
            if (left[i - k] + W[i] + right[i + k] > max) {
                res[0] = leftIndex[i - k];
                res[1] = i;
                res[2] = rightIndex[i + k];
                max = left[i - k] + W[i] + right[i + k];
            }
        }
        return res;
    }


    //no need for leftIndex[i] and rightIndex[i]
    public int[] maxSumOfThreeSubarrays(int[] nums, int K) {
        //W is an array of sums of windows
        int[] W = new int[nums.length - K + 1];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= K) sum -= nums[i-K];
            if (i >= K-1) W[i-K+1] = sum;
        }

        int[] left = new int[W.length];
        int best = 0;
        for (int i = 0; i < W.length; i++) {
            if (W[i] > W[best]) best = i;
            left[i] = best;
        }

        int[] right = new int[W.length];
        best = W.length - 1;
        for (int i = W.length - 1; i >= 0; i--) {
            if (W[i] >= W[best]) best = i;
            right[i] = best;
        }

        int[] ans = new int[]{-1, -1, -1};
        for (int j = K; j < W.length - K; j++) {
            int i = left[j - K], k = right[j + K];
            if (ans[0] == -1 || W[i] + W[j] + W[k] >
                    W[ans[0]] + W[ans[1]] + W[ans[2]]) {

                ans[0] = i;
                ans[1] = j;
                ans[2] = k;
            }
        }
        return ans;
    }
}
