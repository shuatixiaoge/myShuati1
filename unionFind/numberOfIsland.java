//DFS
public int numIslands(char[][] grid) {
    int count=0;
    for(int i=0;i<grid.length;i++)
        for(int j=0;j<grid[0].length;j++){
            if(grid[i][j]=='1'){
                dfsFill(grid,i,j);
                count++;
            }
        }
    return count;
}
private void dfsFill(char[][] grid,int i, int j){
    if(i>=0 && j>=0 && i<grid.length && j<grid[0].length&&grid[i][j]=='1'){
        grid[i][j]='0';
        dfsFill(grid, i + 1, j);
        dfsFill(grid, i - 1, j);
        dfsFill(grid, i, j + 1);
        dfsFill(grid, i, j - 1);
    }
}

//Follow up what if the island is marked by 2, it means there is city, return how many island have city
public int numIslands(char[][] grid) {
    int count=0;
    for(int i=0;i<grid.length;i++)
        for(int j=0;j<grid[0].length;j++){
            if(grid[i][j]=='1'){
                if (dfsFill(grid,i,j)) count++;
            }
        }
    return count;
}
private boolean dfsFill(char[][] grid,int i, int j){
    if(i>=0 && j>=0 && i<grid.length && j<grid[0].length&&(grid[i][j]=='1' || grid[i][j] == '2'){
        boolean hasCity = grid[i][j] == '2';
        grid[i][j]='0';
        return dfsFill(grid, i + 1, j) ||
        dfsFill(grid, i - 1, j) ||
        dfsFill(grid, i, j + 1) ||
        dfsFill(grid, i, j - 1) || hasCity;
    }
    return false;
}


//BFS

public int numIslands(char[][] grid) {
    int count=0;
    for(int i=0;i<grid.length;i++)
        for(int j=0;j<grid[0].length;j++){
            if(grid[i][j]=='1'){
                bfsFill(grid,i,j);
                count++;
            }
        }
    return count;
}
private void bfsFill(char[][] grid,int x, int y){
    grid[x][y]='0';
    int n = grid.length;
    int m = grid[0].length;
    LinkedList<Integer> queue = new LinkedList<Integer>();
    int code = x*m+y;
    queue.offer(code);
    while(!queue.isEmpty())
    {
        code = queue.poll();
        int i = code/m;
        int j = code%m;
        if(i>0 && grid[i-1][j]=='1')    //search upward and mark adjacent '1's as '0'.
        {
            queue.offer((i-1)*m+j);
            grid[i-1][j]='0';
        }
        if(i<n-1 && grid[i+1][j]=='1')  //down
        {
            queue.offer((i+1)*m+j);
            grid[i+1][j]='0';
        }
        if(j>0 && grid[i][j-1]=='1')  //left
        {
            queue.offer(i*m+j-1);
            grid[i][j-1]='0';
        }
        if(j<m-1 && grid[i][j+1]=='1')  //right
        {
            queue.offer(i*m+j+1);
            grid[i][j+1]='0';
        }
    }
}

// 求largest size of islands
// dfs solution
public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)    return 0;
    int m = grid.length, n = grid[0].length, max = 0;
    boolean[][] visited = new boolean[m][n];//O(1) space: directly modify the '1' to '2' to mark grid[i][j] visited
    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
            if (grid[i][j] == '1' && !visited[i][j]) 
                max = Math.max(max, dfs(grid, visited, m, n, i, j));
    return max;
}
private int dfs(char[][] grid, boolean[][] visited, int m, int n, int i, int j) {
    if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1' || visited[i][j])    return 0; // important!
    visited[i][j] = true;
    return 1 + dfs(grid, visited, m, n, i + 1, j) + dfs(grid, visited, m, n, i - 1, j)
            + dfs(grid, visited, m, n, i, j + 1) + dfs(grid, visited, m, n, i, j - 1);
}

//
//
// *****变种*****
// 求perimeter of given island，注意想明白island的周长到底指什么

// dfs solution
public int numIslands(char[][] grid, int i, int j) {
    if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)    return 0;
    int m = grid.length, n = grid[0].length;
    if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1')    return 0;
    boolean[][] visited = new boolean[m][n];//O(1) space: directly modify the '1' to '2' to mark grid[i][j] visited
    return dfs(grid, visited, m, n, i, j);
}
private int dfs(char[][] grid, boolean[][] visited, int m, int n, int i, int j) {
    if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1')
        return 1; // important
    if (visited[i][j])    return 0;
    visited[i][j] = true;
    return dfs(grid, visited, m, n, i + 1, j) + dfs(grid, visited, m, n, i - 1, j) + dfs(grid, visited, m, n, i, j + 1) + dfs(grid, visited, m, n, i, j - 1);
}
