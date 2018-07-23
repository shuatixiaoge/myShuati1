class Solution {
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (!set.contains(n)) {
            set.add(n);
            int tmp = n;
            int sum = 0;
            while(tmp != 0) {
                sum += (tmp % 10)*(tmp % 10);
                tmp = tmp / 10;
            }
            if (sum == 1) return true;
            n = sum;
        }
        return false;
    }
}
