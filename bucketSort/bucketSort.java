class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) return false;
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long remappedNum = (long) nums[i] - Integer.MIN_VALUE;
            long bucket = remappedNum / ((long) t + 1); // why t+1 ? because, if t not plus 1, when t == 0, num divide by 0 will cause crash.

            if (map.containsKey(bucket) // means the key in the map duplicated, it means the must be exist two numbers that the different value between them are less than t
                    || (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t) // if the two different numbers are located in two adjacent bucket, the value still might be less than t
                        || (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t))
                            return true; // the same reason for -1
            if (map.entrySet().size() >= k) {
                long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
                map.remove(lastBucket);
            }
            map.put(bucket, remappedNum); //replace the duplicated key
        }
        return false;
    }
}
