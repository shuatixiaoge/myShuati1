class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int Ay = A.length;
        int Ax = A[0].length;
        int Bx = B[0].length;
        int[][] res = new int[Ay][Bx];
        for (int j = 0; j < Ay; j++) {
            for (int i = 0; i < Bx; i++) { //not Ax careful!, example should be only one length match like 2*3 times 3*2
                for (int k = 0; k < Ax; k++) {
                    if (A[j][k] == 0 || B[k][i] == 0) continue;//better to do skip here to pass
                    res[j][i] += A[j][k] * B[k][i];
                }
            }
        }
        return res;
    }

    //优化
//     Generally for the following equation:
// C[ i ][ j ] = A[ i ][0]*B[0][j] + A[i][1]*B[1][j] + A[i][2]*B[2][j] + ... A[i][k]*B[k][j] .... A[i][K]*B[K][j]
// j can be from 0 to nB, if we write all of them down, it will like following:
// [For i from 0 to nB
// C[ i ][ 0 ]=A[ i ][0]*B[0][0] + A[i][1]*B[1][0] + A[i][2]*B[2][0] + ... A[i][k]B[k][0] .... A[i][K]*B[K][0]
// C[ i ][ 1 ]=A[ i ][0]*B[0][1] + A[i][1]*B[1][1] + A[i][2]*B[2][1] + ... A[i][k]B[k][0] .... A[i][K]*B[K][1]
// ...
// C[ i ][ nB ]=A[ i ][0]*B[0][nB] + A[i][1]*B[1][nB] + A[i][2]*B[2][nB] + ... A[i][k]B[k][nB] .... A[i][K]*B[K][nB]
//
// As you can see from above: for the same ralue A[i][k] from the first matrix,
// it will be used at most nB times if A[i][k] is not zero.
// And the smart solution is taking advantage of that!!!, the smart solution can be described as:
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length, n = A[0].length, nB = B[0].length;
        int[][] C = new int[m][nB];

        for(int i = 0; i < m; i++) {
            for(int k = 0; k < n; k++) {
                if (A[i][k] != 0) {
                    for (int j = 0; j < nB; j++) {
                        if (B[k][j] != 0) C[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
        return C;
    }

    //what if B is so big

    public int[][] multiply(int[][] A, int[][] B) {
    // Write your code here
    int n = A.length;
    int m = B[0].length;
    int t = A[0].length;
    int[][] C = new int[n][m];

    List<List<Integer>> col = new ArrayList<>();
    for (int i = 0; i < t; i++) {
        col.add(new ArrayList<>());
        for (int j = 0; j < m; j++) {
            if (B[i][j] != 0) {
                col.get(i).add(j);
            }
        }
    }
    for (int i = 0; i < n; i++) {
        for (int k = 0; k < t; k++) {
            if (A[i][k] == 0) {
                continue;
            }
            for (int p = 0; p < col.get(k).size(); p++) {
                int j = col.get(k).get(p);
                C[i][j] += A[i][k] * B[k][j];
            }
        }
    }
    return C;
}


// if for two vector multiply, using arraylist and sort and two pointer

}
