// Solution 2. From solution 1, we know the key to solve this problem is SUM[i, j].
 // So if we know SUM[0, i - 1] and SUM[0, j], then we can easily get SUM[i, j]. 
// To achieve this, we just need to go through the array,
// calculate the current sum and save number of all seen PreSum to a HashMap.
// Time complexity O(n), Space complexity O(n).


public class Solution {
    public int subarraySum(int[] nums, int k) {
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (preSum.containsKey(sum - k)) {
                result += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }

        return result;
    }
}
