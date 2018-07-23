// Given nums = [2, 7, 11, 15], target = 9,
//
// Because nums[0] + nums[1] = 2 + 7 = 9,
// return [0, 1].

public class Solution {
  public int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> map =new HashMap<>();
      int[] res = new int[2];
      for (int i = 0; i < nums.length; i++) {
          if (map.containsKey(target - nums[i])) {
              int n = map.get(target - nums[i]);
            res[0] = n;
              res[1] = i;
              break;
          } else {
            map.put(nums[i], i);
          }
        }
      return res;
      }
}
