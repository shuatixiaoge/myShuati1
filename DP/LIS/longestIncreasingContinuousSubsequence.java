public class Solution {

    //One direction
public int findLengthOfLCIS(int[] nums) {
    if (nums.length == 0) return 0;
    int res = 1;
    int lastIndex = 0;
    int last = nums[0];
    for (int i = 1; i < nums.length; i++) {
        if (nums[i] <= last) {
            lastIndex = i;
        } else {
            res = Math.max(i - lastIndex + 1, res);
        }
        last = nums[i];
    }
    return res;
}



    //The following is for both directions

    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int n = A.length;
        int answer = 1;

        // from left to right
        int length = 1; // just A[0] itself
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i - 1]) {
                length++;
            } else {
                length = 1;
            }
            answer = Math.max(answer, length);
        }

        // from right to left
        length = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                length++;
            } else {
                length = 1;
            }
            answer = Math.max(answer, length);
        }

        return answer;
    }
}

//  方法二
public class Solution {
    /**
     * @param A an array of Integer
     * @return  an integer
     */

    int LIS(int[] A) {
        int n = A.length;
        int[] f = new int[n];
        int i, res = 0;
        for (i = 0; i < n; ++i) {
            f[i] = 1;
            if (i > 0 && A[i-1] < A[i]) {
                f[i] = f[i-1] + 1;
            }
            if (f[i] > res) {
                res = f[i];
            }
        }

        return res;
    }

    public int longestIncreasingContinuousSubsequence(int[] A) {
        int n = A.length;
        int r1 = LIS(A);
        int i = 0, j = n-1, t;
        while (i < j) {
            t = A[i];
            A[i] = A[j];
            A[j] = t;
            ++i;
            --j;
        }

        int r2 = LIS(A);

        if (r1 > r2) {
            return r1;
        }
        else {
            return r2;
        }
    }
}


//what if its a square
//滑雪问题 最长的滑雪跑道

public class Solution {
    /**
     * @param A an integer matrix
     * @return  an integer
     */
    int [][]dp;
    //记忆化搜索
    // 普通的动态规划不能解决,因为不知道未来（右边）的情况
    // 还有起点不好找，因此此题比较适合dfs和记忆化搜索
    //如果不记忆的话复杂度是O(N2M2)
    int [][]flag ;
    int n ,m;
    public int longestIncreasingContinuousSubsequenceII(int[][] A) {
        // Write your code here
        if(A.length == 0)
            return 0;
        n = A.length;
         m  = A[0].length;
        int ans= 0;
        dp = new int[n][m];
        flag = new int[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                dp[i][j] = search(i, j, A);
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
    int []dx = {1,-1,0,0};
    int []dy = {0,0,1,-1};

    int search(int x, int y, int[][] A)   {
        if(flag[x][y] != 0)
            return dp[x][y];

        int ans = 1;
        int nx , ny;
        for(int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if(0<= nx && nx < n && 0<= ny && ny < m ) {
                if( A[x][y] > A[nx][ny]) {
                    ans = Math.max(ans,  search( nx, ny, A) + 1);
                }
            }
        }
        flag[x][y] = 1;
        dp[x][y] = ans;
        return ans;
    }
}
