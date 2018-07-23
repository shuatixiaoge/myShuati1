// Given a set of distinct integers, nums, return all possible subsets.
//
// Note: The solution set must not contain duplicate subsets.
//
// For example,
// If nums = [1,2,3], a solution is:
//
// [
//   [3],
//   [1],
//   [2],
//   [1,2,3],
//   [1,3],
//   [2,3],
//   [1,2],
//   []
// ]
class Solution {
  public List<List<Integer>> subset(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    backtrack(nums, res, 0, new ArrayList<Integer>());
    return res;
  }

  private void backtrack(int[] nums, List<List<Integer>> res, int j, List<Integer> list) {
    res.add(new ArrayList<Integer>(list));
    for (int i = j; i < nums.length; i++) {
      list.add(nums[i]);
      backtrack(nums, res, i + 1, list);
      list.remove(list.size() - 1);
    }
  }
  // Input:
  // [1,2,2]

  // Expected:
  // [[],[1],[1,2],[1,2,2],[2],[2,2]]
  public List<List<Integer>> subsetsWithDup(int[] nums) {
      List<List<Integer>> list = new ArrayList<>();
      Arrays.sort(nums);
      backtrack(list, new ArrayList<>(), nums, 0);
      return list;
  }

  private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){
      list.add(new ArrayList<>(tempList));
      for(int i = start; i < nums.length; i++){
        // // 太关键了 especailly i > start 这样就会考虑一次[1,2,2]， 而不会重复[1,2]
        //   if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
          tempList.add(nums[i]);
          backtrack(list, tempList, nums, i + 1);
          tempList.remove(tempList.size() - 1);
          // this is for skipping the duplicate result finally
          while(i < nums.length - 1 && nums[i] == nums[i + 1]) i++;
      }
  }
}
