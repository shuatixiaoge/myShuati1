class FloodFill {
    public void floodFill(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i > board.length || j > board[0].length || board[i][j] == 'L' || board[i][j] == 'O') return;
        board[i][j] = 'O';
        floodFill(board, i + 1, j);
        floodFill(board, i, j + 1);
        floodFill(board, i, j - 1);
        floodFill(board, i - 1, j);
    }

    public static void main(String[] args) {
        char[][] b = {
                        {'W', 'W', 'W','L'},
                        {'W', 'W', 'L','W'},
                        {'W', 'L', 'L','W'},
                        {'L', 'L', 'W','W'}
                    };
        FloodFill t = new FloodFill();
        t.floodFill(b, 0, 0);
        for (char[] cs : b) {
            for (char c : cs) {
                System.out.print(c);
            }
            System.out.println(';');
        }
    }
}
