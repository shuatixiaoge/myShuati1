class Solution {
    public int removeDuplicates(int[] nums) {
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (idx > 1 && nums[i] == nums[idx - 2]) {
// this is comparing idx not comparing i,
// for case 11122233, the second 2 would overwrite the third 1, and if you do nums[i] == nums[i - 2],
//it's not right, idx - 2 would check the second 1 which is always 1, correct!
                continue;
            }
            nums[idx++] = nums[i];
        }
        return idx;
    }
    //solution 2
    class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums.length <= 1) return nums.length;
            int idx = 1;
            int i = 1;
            int cnt = 1;// cnt is initial with 1, so there is one chance it could be equal.
            while(i < nums.length) {
                if (nums[i] != nums[i - 1]) {
                    nums[idx++] = nums[i++];
                    cnt = 1;
                } else {
                    if (cnt < 2) {
                        cnt++;
                        nums[idx++] = nums[i++];
                    } else {
                        i++;
                    }
                }
            }
            return idx;
        }
    }
}
