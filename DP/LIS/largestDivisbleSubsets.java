// if i (in the first array) can divide one of the bigger one in j (in second subarray) than it can divide everything in the second subarray.

public class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> rst = new ArrayList<>();
        if(nums == null || nums.length == 0) return rst;
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        int[] parent = new int[nums.length];
        Arrays.fill(dp, 1);
        Arrays.fill(parent, -1);
        int maxIndex = -1;
        int maxLen = 1;
        for(int i = 0; i < nums.length; i++){
            for(int j = i - 1; j >= 0; j- ){
                if(nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1){
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                    if(dp[i] > maxLen){
                        maxLen = dp[i];
                        maxIndex = i;
                    }
                }
            }
        }

        //nothing found
        if(maxIndex == -1){
            rst.add(nums[0]);
        } else {
            // find the result. by using a parent node
            while(maxIndex != -1){
                rst.add(nums[maxIndex]);
                maxIndex = parent[maxIndex];
            }
        }
        return rst;
    }
}
