public int threeSumSmaller(int[] nums, int target) {
    Arrays.sort(nums);
    int res = 0;
    int len = nums.length;
    if (len < 3) return 0;
    for (int i = 0; i < len - 2; i++) {
        int a = i + 1;
        int b = len - 1;
        while (a < b) {
            int val = nums[i] + nums[a] + nums[b];
            if (val < target) {
                res += b - a;
                a++;
            }
            else {
                b--;
            }
        }
    }
    return res;
}
