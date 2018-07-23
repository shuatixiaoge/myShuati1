// Let initialize a N*2 array dp,
//
// dp[i][0] means the least swaps used to make A[:i+1] and B[:i+1] sorted having no swap at i-th position.
// dp[i][1] means the least swaps used to make A[:i+1] and B[:i+1] sorted having swap at i-th position.
// Here is the recursive formula:
//
// For $i in [1, N]$:
//
// If A[i]>A[i-1] and B[i]>B[i-1] (they are in order without swap):
// dp[i][0]=min(dp[i][0], dp[i-1][0]) (no swap at i-1 and no swap at i)
// dp[i][1]=min(dp[i][1], dp[i-1][1]+1) (swap at i-1 so swap at i to make in order)
//
// If A[i]>B[i-1] and B[i]>A[i-1] (they are in order with a swap):
// dp[i][0]=min(dp[i][0], dp[i-1][1]) (swap at i-1, no need to swap at i)
// dp[i][1]=min(dp[i][1], dp[i-1][0]+1) (no swap at i-1, so swap at i)
class Solution {
    public int minSwap(int[] A, int[] B) {
        int n = A.length;
        int[] pre = new int[2];
        pre[1] = 1;
        for (int i = 1; i < A.length; i++) {
            int[] cur = new int[2]; // has to declare inside, otherwise pre = cur will cause issue
            cur[0] = Integer.MAX_VALUE;
            cur[1] = Integer.MAX_VALUE;
            if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                cur[0] = Math.min(cur[0], pre[0]);
                cur[1] = Math.min(cur[1], pre[1]+1);
            }
            if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
                cur[0] = Math.min(cur[0], pre[1]);
                cur[1] = Math.min(cur[1], pre[0]+1);
            }
            pre = cur;
        }
        return Math.min(pre[0], pre[1]);
    }
}
