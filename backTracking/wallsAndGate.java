class Solution {
    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) helper(rooms, i, j, 0);
            }
        }
    }
    int[] dx = {0, 1, -1 ,0};
    int[] dy = {1, 0, 0, -1};
    private void helper(int[][] rooms, int i, int j, int step) {
        step++;
        //no need for visited[][], if rooms[x][y] > 0 its already marked
        for(int n = 0; n < 4; n++) {
            int x = i + dx[n];
            int y = j + dy[n];
            if (x >= rooms.length || x < 0 || y >= rooms[0].length || y < 0) continue;
            if (rooms[x][y] > step) {
                rooms[x][y] = step;
                helper(rooms, x, y, step);
            }
        }
    }
}
