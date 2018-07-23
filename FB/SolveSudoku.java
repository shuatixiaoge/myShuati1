public class Solution {
    public void solveSudoku(char[][] board){
        helper(board);
    }
    public boolean helper(char[][] board) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]=='.'){
                    for(int k=1;k<=9;k++){
                        board[i][j] =(char) ('0'+k);
                        if(isValidSudoku(board) && helper(board)){
                            return true;
                        }
                        board[i][j] = '.';
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean complete(char[][] board){
        if(!isValidSudoku(board)) return false;
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                if(board[i][j]=='.') return false;
            }
        }
        return true;
    }


    public boolean isValidSudoku(char[][] board) {
        int n=board.length;
        if(n==0) return true;
        for(int i=0;i<n;i++){
            Set<Character> map=new HashSet<>();
            for(int j=0;j<n;j++){
                char c=board[i][j];
                if(c=='.') continue;
                if(map.contains(c)){
                    return false;
                }
                else{
                    map.add(c);
                }
            }
        }
        for(int i=0;i<n;i++){
            Set<Character> map=new HashSet<>();
            for(int j=0;j<n;j++){
                char c=board[j][i];
                if(c=='.') continue;
                if(map.contains(c)){
                    return false;
                }
                else{
                    map.add(c);
                }
            }
        }

        for(int i=0;i<n/3;i++){
            for(int j=0;j<n/3;j++){
                Set<Character> map=new HashSet<>();
                for(int a=0;a<3;a++){
                    for(int b=0;b<3;b++){
                        int x=a+i*3;
                        int y=b+j*3;
                        char c=board[x][y];
                        if(c=='.') continue;
                        if(map.contains(c)){
                            return false;
                        }
                        else{
                            map.add(c);
                        }
                    }
                }
            }
        }
        return true;
    }
}
