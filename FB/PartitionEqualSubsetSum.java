public boolean canPartition(int[] nums) {
    int sum = 0;

    for (int num : nums) {
        sum += num;
    }

    if ((sum & 1) == 1) {
        return false;
    }
    sum /= 2;

    int n = nums.length;
    boolean[] dp = new boolean[sum+1];
    Arrays.fill(dp, false);
    dp[0] = true;

    for (int num : nums) {
        for (int i = sum; i > 0; i--) {
            if (i >= num) {
                dp[i] = dp[i] || dp[i-num];
            }
        }
    }

    return dp[sum];
}
//Partition into K parts

public boolean canPartitionKSubsets(int[] nums, int k) {
    int sum = 0;
    for(int num:nums)sum += num;
    if(k <= 0 || sum%k != 0)return false;
    int[] visited = new int[nums.length];
    return canPartition(nums, visited, 0, k, 0, 0, sum/k);
}

public boolean canPartition(int[] nums, int[] visited, int start_index, int k, int cur_sum, int cur_num, int target){
    if(k==1)return true;
    if(cur_sum == target && cur_num>0)return canPartition(nums, visited, 0, k-1, 0, 0, target);
    for(int i = start_index; i<nums.length; i++){
        if(visited[i] == 0){
            visited[i] = 1;
            if(canPartition(nums, visited, i+1, k, cur_sum + nums[i], cur_num++, target))return true;
            visited[i] = 0;
        }
    }
    return false;
}
