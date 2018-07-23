// https://leetcode.com/problems/range-addition/description/
//Best solution is O(n + k) same as schedule meetings
//和longest consecutive  sequence比较类似
class Solution {
     public int[] getModifiedArray(int length, int[][] updates) {

    int[] res = new int[length];
     for(int[] update : updates) {
        int value = update[2];
        int start = update[0];
        int end = update[1];

        res[start] += value;

        if(end < length - 1)
            res[end + 1] -= value;

    }

    int sum = 0;
    for(int i = 0; i < length; i++) {
        sum += res[i];
        res[i] = sum;
    }

    return res;
}
}
