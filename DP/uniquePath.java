//
// A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
//
// The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
//
// How many possible unique paths are there?

public class Solution {
    public int uniquePaths(int m, int n) {
        int[] col = new int[n];
        col[0] = 1;
        for(int j=0;j<m;j++){
            for(int i=1;i<n;i++){
                col[i] = col[i-1] + col[i];
            }
        }
        return col[n-1];
    }

// Follow up for "Unique Paths":
// 
// Now consider if some obstacles are added to the grids. How many unique paths would there be?
//
// An obstacle and empty space is marked as 1 and 0 respectively in the grid.
//
// For example,
// There is one obstacle in the middle of a 3x3 grid as illustrated below.
//
// [
//   [0,0,0],
//   [0,1,0],
//   [0,0,0]
// ]
// The total number of unique paths is 2.
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] col = new int[n];
        if(obstacleGrid[0][0]==1) return 0;
        col[0]=1;
        for(int i=0;i<m;i++){
            if(obstacleGrid[i][0]==1) col[0] = 0;
            for(int j=1;j<n;j++){
                if(obstacleGrid[i][j]==1) {
                    col[j]=0;
                    continue;
                }
                col[j]+=col[j-1];
            }
        }
        return col[n-1];
    }
}
