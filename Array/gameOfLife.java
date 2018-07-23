//Follow up
private Set<Coord> gameOfLife(Set<Coord> live) {
    Map<Coord,Integer> neighbours = new HashMap<>();
    for (Coord cell : live) {
        for (int i = cell.i-1; i<cell.i+2; i++) {
            for (int j = cell.j-1; j<cell.j+2; j++) {
                if (i==cell.i && j==cell.j) continue;
                Coord c = new Coord(i,j);
                if (neighbours.containsKey(c)) {
                    neighbours.put(c, neighbours.get(c) + 1);
                } else {
                    neighbours.put(c, 1);
                }
            }
        }
    }
Set<Coord> newLive = new HashSet<>();
    for (Map.Entry<Coord,Integer> cell : neighbours.entrySet())  {
        if (cell.getValue() == 3 || cell.getValue() == 2 && live.contains(cell.getKey())) {
            newLive.add(cell.getKey());
        }
    }
    return newLive;
}

private static class Coord {
    int i;
    int j;
    private Coord(int i, int j) {
        this.i = i;
        this.j = j;
    }
    public boolean equals(Object o) {
        return o instanceof Coord && ((Coord)o).i == i && ((Coord)o).j == j;
    }
    public int hashCode() {
        int hashCode = 1;
        hashCode = 31 * hashCode + i;
        hashCode = 31 * hashCode + j;
        return hashCode;
    }
}

public void gameOfLife(int[][] board) {
    Set<Coord> live = new HashSet<>();
    int m = board.length;
    int n = board[0].length;
    for (int i = 0; i<m; i++) {
        for (int j = 0; j<n; j++) {
            if (board[i][j] == 1) {
                live.add(new Coord(i,j));
            }
        }
    };
    live = gameOfLife(live);
    for (int i = 0; i<m; i++) {
        for (int j = 0; j<n; j++) {
            board[i][j] = live.contains(new Coord(i,j))?1:0;
        }
    };

}

//normal one with no extra space
// To solve it in place, we use 2 bits to store 2 states:
//
// [2nd bit, 1st bit] = [next state, current state]
//
// - 00  dead (next) <- dead (current)
// - 01  dead (next) <- live (current)
// - 10  live (next) <- dead (current)
// - 11  live (next) <- live (current)
// In the beginning, every cell is either 00 or 01.
// Notice that 1st state is independent of 2nd state.
// Imagine all cells are instantly changing from the 1st to the 2nd state, at the same time.
// Let’s count # of neighbors from 1st state and set 2nd state bit.
// Since every 2nd state is by default dead, no need to consider transition 01 -> 00.
// In the end, delete every cell’s 1st state by doing >> 1.
// For each cell’s 1st bit, check the 8 pixels around itself, and set the cell’s 2nd bit.
//
// Transition 01 -> 11: when board == 1 and lives >= 2 && lives <= 3.
// Transition 00 -> 10: when board == 0 and lives == 3.
// To get the current state, simply do
//
// board[i][j] & 1
// To get the next state, simply do
//
// board[i][j] >> 1
// Hope this helps!

public void gameOfLife(int[][] board) {
    if (board == null || board.length == 0) return;
    int m = board.length, n = board[0].length;

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            int lives = liveNeighbors(board, m, n, i, j);

            // In the beginning, every 2nd bit is 0;
            // So we only need to care about when will the 2nd bit become 1.
            if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
                board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
            }
            if (board[i][j] == 0 && lives == 3) {
                board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
            }
        }
    }

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            board[i][j] >>= 1;  // Get the 2nd state.
        }
    }
}

public int liveNeighbors(int[][] board, int m, int n, int i, int j) {
    int lives = 0;
    for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
        for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
            lives += board[x][y] & 1;
        }
    }
    lives -= board[i][j] & 1;
    return lives;
}


public class Solution {
    public void gameOfLife(int[][] board) {
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = nextState(board, i, j);
            }
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == 2 || board[i][j] == -2) board[
                i][j] = 1;
                else board[i][j] = 0;
            }
        }
    }
    private int nextState(int[][] board, int row, int col){
        int[] xDirs = {0, 0, 1, -1, 1, -1, -1, 1};
        int[] yDirs = {1,-1, 0, 0, 1, -1, 1, -1};
        int aliveCount = 0;
        for(int i = 0; i < 8; i++){
            int x = row + xDirs[i];
            int y = col + yDirs[i];
            // legal position
            if(x >= 0 && x < board.length && y >= 0 && y < board[
            0].length){
                if(board[x][y] > 0) aliveCount ++;
            }
        }
        if(board[row][col] == 0){
            if(aliveCount == 3) return -2;
            else return -3;
        } else {
            if(aliveCount == 2 || aliveCount == 3) return 2;
            else return 3;
        }
    }
}
