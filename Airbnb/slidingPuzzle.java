//
// class Solution {
//     public int slidingPuzzle(int[][] board) {
//         Set<String> seen = new HashSet<>(); // used to avoid duplicates
//         String target = "123450";
//         // convert board to string - initial state.
//         String s = Arrays.deepToString(board).replaceAll("\\[|\\]|,|\\s", "");
//         Queue<String> q = new LinkedList<>(Arrays.asList(s));
//         seen.add(s); // add initial state to set.
//         int ans = 0; // record the # of rounds of Breadth Search
//         while (!q.isEmpty()) { // Not traverse all states yet?
//             // loop used to control search breadth.
//             for (int sz = q.size(); sz > 0; --sz) {
//                 String str = q.poll();
//                 if (str.equals(target)) { return ans; } // found target.
//                 int i = str.indexOf('0'); // locate '0'
//                 int[] d = { 1, -1, 3, -3 }; // potential swap displacements.
//                 for (int k = 0; k < 4; ++k) { // traverse all options.
//                     int j = i + d[k]; // potential swap index.
//                     // conditional used to avoid invalid swaps.
//                     if (j < 0 || j > 5 || i == 2 && j == 3 || i == 3 && j == 2) { continue; }
//                     char[] ch = str.toCharArray();
//                     // swap ch[i] and ch[j].
//                     char tmp = ch[i];
//                     ch[i] = ch[j];
//                     ch[j] = tmp;
//                     s = String.valueOf(ch); // a new candidate state.
//                     if (seen.add(s)) { q.offer(s); } //Avoid duplicate.
//                 }
//             }
//             ++ans; // finished a round of Breadth Search, plus 1.
//         }
//         return -1;
//     }
// }


//Follow Up OOD
import java.util.*;
class SlidingPuzzle {
    class Status {
        int[][] matrix;
        int x, y;
        Status(int[][] m, int i, int j) {
            matrix = new int[m.length][m[0].length];
            for (int ii = 0; ii < m.length; ii++)
                matrix[ii] = m[ii].clone();
            x = i;
            y = j;
        }
        String encodeMatrix() {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < matrix.length; i++)
                for (int j = 0; j < matrix[0].length; j++)
                    builder.append(matrix[i][j]).append(",");
            return builder.toString();
        }
        void move(int i, int j) {
            matrix[x][y] = matrix[i][j];
            matrix[i][j] = 0;
            x = i;
            y = j;
        }

        int getScore() {
            int count = 0;
            int n = matrix.length;
            int m = matrix[0].length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int num = i * m + j + 1;
                    if (i == n - 1 && j == m - 1) {
                        num = 0;
                    }
                    if (num == matrix[i][j]) {
                        count++;
                    }
                }
            }
            return count;
        }
    }
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    public boolean canSolve(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int startX = 0, startY = 0;
        int[][] finalMatrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                finalMatrix[i][j] = i * m + j + 1;
            }
        }
        finalMatrix[n - 1][m - 1] = 0;

        Status initial = new Status(matrix, startX, startY);
        Status finals = new Status(finalMatrix, n - 1, m - 1);
        Queue<Status> queue = new PriorityQueue<>((s1, s2) -> s2.getScore() - s1.getScore());
        queue.add(initial);
        Set<String> visited = new HashSet<>();
        visited.add(initial.encodeMatrix());

        while (!queue.isEmpty()) {
            Status current = queue.poll();
            int xx = current.x, yy = current.y;
            for (int i = 0; i < 4; i++) {
                int x = xx + dx[i];
                int y = yy + dy[i];
                if (x < 0 || x >= n || y < 0 || y >= m)
                    continue;
                current.move(x, y);
                String encoded = current.encodeMatrix();
                if (encoded.equals(finals.encodeMatrix()))
                    return true;
                if (!visited.contains(encoded)) {
                    System.out.println(encoded);
                    visited.add(encoded);
                    queue.add(new Status(current.matrix, current.x, current.y));
                }
                current.move(xx, yy);
            }
        }

        return false;
    }


    // If the width is odd, then every solvable state has an even number of inversions.
    // If the width is even, then every solvable state has
    // an even number of inversions if the blank is on an odd numbered row counting from the bottom;
    // an odd number of inversions if the blank is on an even numbered row counting from the bottom;
public boolean isSolvable(int[][] puzzle)
{
    int inversionCount = 0;
    int N = puzzle.length;
    int row = 0; // the blank row

    for (int i = 0; i < N * N - 1; i++) {
        for (int j = i + 1; j < N * N; j++) {
            int x1 = i / N;
            int y1 = i % N;
            int x2 = j / N;
            int y2 = j % N;
            if (puzzle[x1][y1] > puzzle[x2][y2]) {
                inversionCount++;
            }
        }
    }
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (puzzle[i][j] == 0) {
                row = j;
            }
        }
    }
    System.out.println(inversionCount);

    if (N % 2 == 0) { // even grid
        if ((N - row) % 2 == 0) { // blank on even row; counting from bottom
            return inversionCount % 2 != 0;
        } else { // blank on even row; counting from bottom
            return inversionCount % 2 == 0;
        }
    } else { // odd grid
        return inversionCount % 2 == 0;
    }
}



        public final static void main(String[] args) {
            SlidingPuzzle sg = new SlidingPuzzle();
            int puzzle[][] = {
                    {1,5,4},
                    {2, 3, 8},
                    {0, 6, 7},
                };


            int[][] matrix = new int[][]{{0, 5, 3, 2, 1}, {9, 4, 8, 7, 6}};
            System.out.println(sg.canSolve(matrix));
        }

    }
