// Given a collection of distinct numbers, return all possible permutations.
//
// For example,
// [1,2,3] have the following permutations:
// [
//   [1,2,3],
//   [1,3,2],
//   [2,1,3],
//   [2,3,1],
//   [3,1,2],
//   [3,2,1]
// ]
class Solution {
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    permuteNow(nums, res, new ArrayList<Integer>());
    return res;
  }

  private void permuteNow(int[] nums, List<List<Integer>> res, List<Integer> list) {
    if (list.size() == nums.length) {
      res.add(list);
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if (list.contains(nums[i])) continue;
      list.add(nums[i]);
      permuteNow(nums, res, list);
      list.remove(list.size() - 1);
    }
  }

  public List<List<Integer>> permuteUnique(int[] nums) {
      List<List<Integer>> list = new ArrayList<>();
      Arrays.sort(nums);
      backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
      return list;
  }

  private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
      if(tempList.size() == nums.length){
          list.add(new ArrayList<>(tempList));
      } else{
          for(int i = 0; i < nums.length; i++){
              if(used[i]) continue;
              used[i] = true;
              tempList.add(nums[i]);
              backtrack(list, tempList, nums, used);
              used[i] = false;
              tempList.remove(tempList.size() - 1);
              ///SSSSSSSSSSSOOOOOOOOOOO GOOOOOOOOOOOOOOOOD
              while(i < nums.length - 1 && nums[i] == nums[i+1]) i++;
          }
      }
  }
}
