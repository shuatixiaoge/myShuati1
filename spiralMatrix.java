import java.util.*;

public class spiralMatrix {

// Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
//
// For example,
// Given the following matrix:
//
// [
// [ 1, 2, 3 ],
// [ 4, 5, 6 ],
// [ 7, 8, 9 ]
// ]
// You should return [1,2,3,6,9,8,7,4,5].
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res=new ArrayList<Integer>();
        if(matrix.length==0) return res;
        int m=matrix.length-1;
        int n=matrix[0].length;
        int row=0;
        int col=-1;
        while(true){
            for(int i=0;i<n;i++){
                res.add(matrix[row][++col]);
            }
            if(n--==0) break;
            for(int i=0;i<m;i++){
                res.add(matrix[++row][col]);
            }
            if(m--==0) break;
            for(int i=0;i<n;i++){
                res.add(matrix[row][--col]);
            }
            if(n--==0) break;
            for(int i=0;i<m;i++){
                res.add(matrix[--row][col]);
            }
            if(m--==0) break;
        }
            return res;

    }

// Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
//
// For example,
// Given n = 3,
//
// You should return the following matrix:
// [
//  [ 1, 2, 3 ],
//  [ 8, 9, 4 ],
//  [ 7, 6, 5 ]
// ]
    public int[][] generateMatrix(int n) {
        int i=n;
        int j=n-1;
        int col=-1;
        int row=0;
        int cur=1;
        int[][] matrix=new int[n][n];
        if(n==0) return matrix;
        while(true){
            for(int k=0;k<i;k++){
                matrix[row][++col] = cur++;
            }
            if(i--==0) break;
            for(int k=0;k<j;k++){
                matrix[++row][col] = cur++;
            }
            if(j--==0) break;
            for(int k=0;k<i;k++){
                matrix[row][--col] = cur++;
            }
            if(i--==0) break;
            for(int k=0;k<j;k++){
                matrix[--row][col] = cur++;
            }
            if(j--==0) break;
        }
        return matrix;
    }

    public static void main(String[] args) {
      int[][] matrix = {
        {1,2,3}, {2,3,4}, {3,4,5}
      };
      spiralMatrix order = new spiralMatrix();
      List<Integer> arr = order.spiralOrder(matrix);
      for (int i : arr) {
        System.out.println(i);
      }
    }


    public class Solution {
        public int[][] generateMatrix(int n) {
            if(n <= 0) return new int[0][0];
            int[][] matrix = new int[n][n];
            int rowStart = 0;
            int rowEnd = n - 1;
            int colStart = 0;
            int colEnd = n - 1;
            int curNum = 1;
            while(rowStart <= rowEnd && colStart <= colEnd){
                for(int i = colStart; i <= colEnd; i++) matrix[rowStart][i] = curNum++;
                rowStart ++;
                for(int i = rowStart; i <= rowEnd; i++) matrix[i][colEnd] = curNum++;
                colEnd --;
                if(rowStart <= rowEnd) for(int i = colEnd; i >= colStart; i--) matrix[rowEnd][i] = curNum++;
                rowEnd --;
                if(colStart <= colEnd) for(int i = rowEnd; i >= rowStart; i--) matrix[i][colStart] = curNum++;
                colStart ++;
            }
            return matrix;
        }
    }
}
