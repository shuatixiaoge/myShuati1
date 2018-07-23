private long search(int[] nums, int k, long left, long right) {
    if (left >= right) {
        return left;
    }
    long res = left;
    long guess = left + (right - left) / 2;
    int count = 0;
    for (int num : nums) {
        if (num <= guess) {
            count++;
            res = Math.max(res, num);
        }
    }
    if (count == k) {
        return res;
    } else if (count < k) {
        // res + 1 and guess Math is kind of better optiomization
        return search(nums, k, Math.max(res + 1, guess), right);
    } else {
        return search(nums, k, left, res);
    }
}
public double findMedian(int[] nums) {
    int len = 0;
    for (int num : nums) {
        len++;
    }
    if (len % 2 == 1) {
        return (double) search(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    } else {
        return (double) (search(nums, len / 2, Integer.MIN_VALUE, Integer.MAX_VALUE) + search(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE)) / 2;
    }
}
}
