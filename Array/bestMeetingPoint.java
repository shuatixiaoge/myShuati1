//As long as you have different numbers of people on your left and on your right, moving a little to the side with more people decreases the sum of distances.
// So to minimize it, you must have equally many people on your left and on your right.
public class Solution {
    public int minTotalDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<Integer> I = new ArrayList<Integer>();
        List<Integer> J = new ArrayList<Integer>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    I.add(i);
                }
            }
        }
        for(int j = 0; j < n; j++) {
            for(int i = 0; i < m; i ++) {
                if(grid[i][j] == 1) {
                    J.add(j);
                }
            }
        }
        return minTotalDistance(I) + minTotalDistance(J);
    }
    public int minTotalDistance(List<Integer> grid) {
        int i = 0, j = grid.size() - 1, sum = 0;
        while(i < j) {
            sum += grid.get(j--) - grid.get(i++);
        }
        return sum;
    }
}
