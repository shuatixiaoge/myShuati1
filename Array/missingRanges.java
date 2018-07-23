// this doesn't pass the OJ on Integer.MAX_VALUE

class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int candidate = nums[i] - 1;
            if (candidate > lower)  res.add(lower + "->" + candidate);
            else if (candidate == lower) res.add("" + lower);
            lower = nums[i] + 1;
        }
        if (lower < upper) res.add(lower + "->" + upper);
        else if (lower == upper) res.add("" + lower);
        return res;
    }
}
