// Given an array of non-negative integers, you are initially positioned at the first index of the array.
//
// Each element in the array represents your maximum jump length at that position.
//
// Determine if you are able to reach the last index.
//
// For example:
// A = [2,3,1,1,4], return true.
//
// A = [3,2,1,0,4], return false.
class Solution {
  //fail on time Complexity
  public boolean canJump(int[] nums) {
    boolean[] res = new boolean[nums.length];
    res[0] = true;
    for (int i = 0; i < nums.length; i++) {
      if (!res[i]) break;
      for (int j = 1; j <= nums[i] && i + j < nums.length; j++) {
        res[i + j] = true;
      }
    }
    return res[nums.length - 1];
  }

  //real Solution, O(1) could solve this problem
  public  boolean canJump(int[] nums) {
    int max = 0;
    // the last one in the array is useless, and nums.length - 1 help deal with the [0] case
    for (int i = 0; i < nums.length - 1; i++) {
      if (i > max) return false;
      max = Math.max(max, i + nums[i]);
    }
    return true;
  }

  //O(N)
  public boolean canJump(int[] nums) {
    int lastPos = nums.length - 1;
    for (int i = nums.length - 1; i >= 0; i--) {
        if (i + nums[i] >= lastPos) {
            lastPos = i;
        }
    }
    return lastPos == 0;
}

  // Jump Game 2

  public int jump(int[] nums) {
    if (!canJump(nums)) return 0;
    int max = 0;
    int count = 0;
    int lastMax = 0;

    for (int i = 0; i < nums.length; i++) {
      max = Math.max(max, i + nums[i]);
      if (i == lastMax) {
        lastMax = max;
        count++;
      }
    }
    return count;
  }

}
