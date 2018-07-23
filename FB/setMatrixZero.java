public class Solution {
    public void setZeroes(int[][] matrix) {
        int col=matrix[0].length,row=matrix.length,col0=1;

        for(int i=0;i<row;i++){
            if(matrix[i][0]==0) col0=0;
            for(int j=1;j<col;j++){//this j has to start with 1; or the matrix[0][j]=0 will have the matrix[0][0]==0 thus the first row would become 0s; since first column is already 0 if there is a 0, no need to change, so it won't influence the result.
                if(matrix[i][j]==0){
                    matrix[i][0]=0;
                    matrix[0][j]=0; //this is essential since in the later part, the first row is not considered.
                }
            }
        }
        for(int i=row-1;i>=0;i--){
            for(int j=col-1;j>0;j--){ //the j cannot be 0 here, leaving col
                if(matrix[i][0]==0) matrix[i][j]=0;
                if(matrix[0][j]==0) matrix[i][j]=0;
            }
            if(col0==0) matrix[i][0]=0;
        }
    }
}
