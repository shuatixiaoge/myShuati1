class Solution {
  public int removeDuplicates(int[] nums) {
    if (nums.length <= 1) return nums.length;
    int result = 1;
    int curr = nums[0];
    int i = 1;
    while (i < nums.length) {
      if (curr == nums[i]) {
        i++;
      }
      else {
        curr = nums[i];
        nums[result] = nums[i];
        result++;
      }
    }
    return result;
  }
}
