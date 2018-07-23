//Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
// (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
//
// You are given a target value to search. If found in the array return its index, otherwise return -1.
//
// You may assume no duplicate exists in the array.
public class Solution {
    public int search(int[] nums, int target) {
      if (nums.length == 0) return -1;
      int left = 0;
      int right = nums.length - 1;
      while(left + 1 < right) {
        int mid = (left + right) / 2;
        if (nums[mid] == target) return mid;
        if (nums[left] < nums[mid]) {
          if (nums[left] <= target && nums[mid] > target) { //eqaul sign here is necessary
            // the reason is it's using left to compare with target, so left cannot be changed if its target.
            right = mid;
          } else {
            left = mid;
          }
        } else { // for the second question this one cannot be equal sign anymore
          if (nums[mid] < target && target < nums[left]) {
            left = mid;
          } else {
            right = mid;
          }
        }
      }
      if (nums[left] == target) return left;
      if (nums[right] == target) return right;
      return -1;
    }
}


// firstGreaterEqual 的解法在这里必须要对边界条件做处理
public class Solution {
    public int search(int[] nums, int target) {
      if (nums.length == 0) return -1;
      int left = 0;
      int right = nums.length - 1;
      while(left < right) {
        int mid = (left + right) / 2;
        if (nums[mid] == target) return mid;
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        if (nums[left] < nums[mid]) {
          if (nums[left] < target && nums[mid] > target) {
            right = mid;
          } else {
            left = mid + 1;
          }
        } else {
          if (nums[mid] < target && target < nums[left]) {
            left = mid + 1;
          } else {
            right = mid;
          }
        }
      }
      if (nums[left] == target) return left;
      if (nums[right] == target) return right;
      return -1;
    }

}
