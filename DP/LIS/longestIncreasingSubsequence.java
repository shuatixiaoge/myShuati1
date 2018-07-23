Solution {
    //O(N^2)
    public int longestIncreasingSubsequence(int[] nums) {
        int []f = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            f[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            max = Math.max(max, f[i]);
        }
        return max;
    }

    //Solution with dp and binary search O(nlogn)
    // https://segmentfault.com/a/1190000003819886
    public int lengthOfLIS(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int[] tails = new int[nums.length];
        tails[0] = nums[0];
        int len = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < tails[0]) {
                tails[0] = nums[i];
            } else if (nums[i] > tails[len]) {// shouldn't be = here, considering [2, 2] output is 1
                tails[++len] = nums[i];
            } else {
                //search with len, not with tails.length
                int idx = binarySearch(tails, 0, len, nums[i]);//search in tails not nums
                tails[idx] = nums[i];
            }
        }
        return len + 1;
    }
    //find greate or equal
    public int binarySearch(int[] nums, int i, int j, int target) {
        while (i < j) {
            int mid = i + (j - i)/2;
            if (target <= nums[mid]) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }
}
