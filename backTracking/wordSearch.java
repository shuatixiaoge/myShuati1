    // Given a 2D board and a word, find if the word exists in the grid.
//
// The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
//
// For example,
// Given board =
//
// [
//   ['A','B','C','E'],
//   ['S','F','C','S'],
//   ['A','D','E','E']
// ]
// word = "ABCCED", -> returns true,
// word = "SEE", -> returns true,
// word = "ABCB", -> returns false.

class Solution {
    public boolean exist(char[][] board, String word) {
      if (board.length == 0) return false;
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
          if (backtrack("", board, word, i, j)) {
              return true;
          }
        }
      }
      return false;
    }

    private boolean backtrack(String result, char[][] board, String word, int i, int j) {
      if (i >= board.length || i < 0) return false;
      if (j >= board[0].length || j < 0) return false;
      if (result.length() >= word.length()) return false;
      if (board[i][j] != word.charAt(result.length())) return false;
      if (board[i][j] == '#') return false;
      char oldValue = board[i][j];
      String newWord = result + board[i][j];
      // This is quite necessary to prevent to have a loop in the backtracking process
      board[i][j] = '#';

      if (newWord.equals(word)) {
          return true;
      }
      boolean b = backtrack(newWord, board, word, i + 1, j) ||
             backtrack(newWord, board, word, i, j + 1) ||
             backtrack(newWord, board, word, i - 1, j) ||
             backtrack(newWord, board, word, i, j - 1);
      board[i][j] = oldValue;
      return b;
    }


    public class Solution {
        public boolean exist(char[][] board, String word) {
            int m=board.length;
            if(m==0) return false;
            int n=board[0].length;
            if(word.length()>m*n) return false;
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(helper(board,word,i,j,0))
                        return true;
                }
            }
            return false;
        }

        int[] dx = { 0, 0 ,-1,1};
        int[] dy = { 1, -1, 0, 0};
        public boolean helper(char[][] board, String word,int x,int y,int k){
            if(board[x][y]!=word.charAt(k)) return false;
            if(word.length()==k+1){
                return true;
            }
            char tmp = board[x][y];
            board[x][y]='#';

            for(int i=0;i<4;i++){
                int x1=x+dx[i];
                int y1=y+dy[i];
                if(x1<0 || x1>=board.length || y1<0 || y1>=board[0].length || board[x1][y1]=='#') continue;
                if(helper(board,word,x1,y1,k+1)) return true;
            }
            board[x][y]=tmp;
            return false;
        }
    }

}
