public class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int len = nums.length;
        int[] max = new int[len];
        int[] min = new int[len];
        max[0] = nums[0];
        min[0] = nums[0];
        //这种能初始化的情况，最好就用现成的值先初始化了
        int res = nums[0];
        for (int i = 1; i < len; i++) {
            max[i] = Math.max(Math.max(max[i-1] * nums[i], min[i-1] * nums[i]), nums[i]);
            min[i] = Math.min(Math.min(max[i-1] * nums[i], min[i-1] * nums[i]), nums[i]);
            res = Math.max(max[i], res);
        }
        return res;
    }
}
