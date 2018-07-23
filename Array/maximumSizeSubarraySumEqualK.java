// Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
//
// Note:
// The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
//
// Example 1:
// Given nums = [1, -1, 5, -2, 3], k = 3,
// return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
//
// Example 2:
// Given nums = [-2, -1, 2, 1], k = 1,
// return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
//
// Follow Up:
// Can you do it in O(n) time?

class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int[] sumNums = new int[nums.length + 1];
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        map.put(0, list);
        sumNums[0] = 0; //initialize 0 at the first element of array
        for (int i = 0; i < nums.length; i++) {
            sumNums[i+1] = sumNums[i] + nums[i];
            if (!map.containsKey(sumNums[i+1])){
                ArrayList<Integer> newList = new ArrayList<>();
                newList.add(i + 1);
                map.put(sumNums[i+1], newList);
            }
            else {
                ArrayList<Integer> storeList = map.get(sumNums[i+1]);
                storeList.add(i + 1);
                map.put(sumNums[i+1], storeList);
            }
        }

        int res = 0;
        for (int i = 1; i < nums.length + 1; i++){
            int max = 0;
            if (map.containsKey(sumNums[i] - k)) { //can't do sumNums[i] + k cause its sum array, cannot deduct on the other way
                for (Integer index : map.get(sumNums[i] - k)) {
                    max = Math.max(i - index, max);
                }
            }
            res = Math.max(res, max);
        }
        return res;
    }
    //No need to have two iteration
    //Best Solution
    public int maxSubArrayLen(int[] nums, int k) {
        int sum = 0, max = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            if (sum == k) max = i + 1;
            else if (map.containsKey(sum - k)) max = Math.max(max, i - map.get(sum - k));
            // no -1 in i - map.get(sum - k), since line 58 was dealing with it, in this case.
            //already know the first one in the hasmap has to be the maximum length one!!!
            if (!map.containsKey(sum)) map.put(sum, i);
        }
        return max;
    }
}
