// Example 1:
// Input: 2736
// Output: 7236
// Explanation: Swap the number 2 and the number 7.
// Example 2:
// Input: 9973
// Output: 9973
// Explanation: No swap.
class Solution {
    public int maximumSwap(int num) {
        char[] A = Integer.toString(num).toCharArray();
        int[] last = new int[10];
        for (int i = 0; i < A.length; i++) {
            last[A[i] - '0'] = i;// this idea is important
        }
        for (int j = 0; j < A.length; j++) {
            //只能比较比当前j位还大的数
            //i >= can't be equal, otherwise would fail on 98368
            for (int i = 9; i > A[j] - '0'; i--) { // i >=0 is not good, swap only need a larger number
                if (last[i] > j){
                    char tmp = A[j];
                    A[j] = A[last[i]];
                    A[last[i]] = tmp;
                    return Integer.valueOf(new String(A));
                }
            }
        }
        return num;
    }
}
