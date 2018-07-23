// Example 1:
//
// Input: grid =
// [[1, 0, 0, 1, 0],
//  [0, 0, 1, 0, 1],
//  [0, 0, 0, 1, 0],
//  [1, 0, 1, 0, 1]]
// Output: 1
// Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].

class Solution {
    public int countCornerRectangles(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length - 1; i++) {
            for (int j = i + 1; j < grid.length; j++) {
                int counter = 0;
                // for (int k = 0; k < grid[0].length; k++) {
                //     if (grid[i][k] == 1 && grid[j][k] == 1) counter++;
                // }
                // if (counter > 0) ans += counter * (counter - 1) / 2;
                for (int k = 0; k < grid[0].length; k++) {
                    if (grid[i][k] == 1 && grid[j][k] == 1) res += counter++;
                }
            }
        }
        return ans;
    }
}
