// n * k
public class Solution {
    public int minCost(int[][] costs) {
        int len = costs.length;
        if (len == 0) return 0;
        int r = 0, b = 0, g = 0;
        for (int i = 0; i < len; i++) {
            int rr = r, bb = b, gg = g;
            r = costs[i][0] + Math.min(gg, bb);
            b = costs[i][1] + Math.min(rr, gg);
            g = costs[i][2] + Math.min(rr, bb);
        }
        return Math.min(Math.min(g, b), r);
    }
}


//Paint House 2
// O(NK^2) space O(N*K)
public int minCostII(int[][] costs) {
 if(costs.length == 0) return 0;
 int n = costs.length, k = costs[0].length;
 int[][] dp = new int[n][k];
 for(int i = 0; i < k; i++){
   dp[0][i] = costs[0][i];
 }

 for(int i = 1; i < n; i++){
   for(int j = 0; j < k; j++){
     dp[i][j] = Integer.MAX_VALUE;
     for(int s = 0; s < k; s++){
       if(s == j) continue;
       dp[i][j] = Math.min(dp[i][j], dp[i - 1][s] + costs[i][j]);
     }
   }
 }

 int res = Integer.MAX_VALUE;
 for(int i = 0; i < k; i++){
   res = Math.min(res, dp[n - 1][i]);
 }
 return res;
}
//constant Space
// O(nk) space O(1)
class Solution {
    public int minCostII(int[][] costs) {
      int n = costs.length;
      if(n == 0) return 0;
      int k = costs[0].length;
      int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
      int min1index = -1;
      for(int i = 0;  i < k; i++){
        if(costs[0][i] < min1 ){
          min1index = i;
          min2 = min1;
          min1 = costs[0][i];
        } else if(costs[0][i] < min2) {
          min2 = costs[0][i];
        }
      }

      for(int i = 1; i < n; i++){
        int temp1 = min1, temp2 = min2, tempindex = min1index;
        min1 = Integer.MAX_VALUE;
        min2 = Integer.MAX_VALUE;

        for(int j = 0; j < k; j++){
          int val = 0;
          if(j != tempindex){
            val = temp1 + costs[i][j];
          } else {
            val = temp2 + costs[i][j];
          }

          if(val < min1){
            min1index = j;
            min2 = min1;
            min1 = val;
          } else if(val < min2){
            min2 = val;
          }
        }
      }

      return min1;
    }
}
