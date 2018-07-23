//Undirected Graph has no loop
//Solution 1 Union find
public class Solution {
    /*
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
  private int[] father;
  public boolean validTree(int n, int[][] edges) {
        // write your code here
        father = new int[n];
        if (n != edges.length + 1) return false;
        for (int j = 0; j < n; j++) {
            father[j] = j;
        }
        for (int i = 0; i < edges.length; i++) {
            if (find(edges[i][0]) == find(edges[i][1])) return false;
            union(edges[i][0], edges[i][1]);
        }
        return true;
      }

    public int find(int x) {
        if (father[x] == x) {
         return x;
        }
        return father[x] = find(father[x]);
      }


    public void union(int a, int b) {
      int root_a = find(a);
      int root_b = find(b);
      if (root_a != root_b) {
        father[root_a] = root_b;
      }
    }
}
//Solution 2 BFS,
//visited = 2 means it's the parent node, visited = 1 means this node has been visited
//bfs cannot used for directed graph
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        int[] visited = new int[n];
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i=0; i<n; ++i) { adjList.add(new ArrayList<Integer>()); }
        for (int[] edge: edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.addLast(0); visited[0] = 1;  // vertex 0 is in the queue, being visited
        while (!q.isEmpty()) {
            Integer cur = q.removeFirst();
            for (Integer succ: adjList.get(cur)) {
                if (visited[succ] == 1) { return false; }  // loop detected
                if (visited[succ] == 0) { q.addLast(succ); visited[succ] = 1; }
            }
            visited[cur] = 2;  // visit completed
        }
        for (int v: visited) { if (v == 0) { return false; } }  // # of connected components is not 1
        return true;
    }
}
//dfs
//in dfs visited = 2 is not enough, that means it has been dfs already,
//so you still need the predecessor to make sure it's not going back, for undirected graph
//for directed graph, don't need predecessor

public class Solution {
    public boolean validTree(int n, int[][] edges) {
        int[] visited = new int[n];
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i=0; i<n; ++i) { adjList.add(new ArrayList<Integer>()); }
        for (int[] edge: edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        if (hasCycle(-1, 0, visited, adjList)) { return false; }  // has cycle
        for (int v: visited) { if (v == 0) { return false; } }  // not 1 single connected component
        return true;
    }

    private boolean hasCycle(int pred, int vertex, int[] visited, List<List<Integer>> adjList) {
        visited[vertex] = 1;  // current vertex is being visited
        for (Integer succ: adjList.get(vertex)) {  // successors of current vertex
            if (succ != pred) {  // exclude current vertex's predecessor
                if (visited[succ] == 1) { return true; }  // back edge/loop detected!
                else if (visited[succ] == 0) {
                    if (hasCycle(vertex, succ, visited, adjList)) { return true; }
                }
            }
        }
        visited[vertex] = 2;
        return false;
    }
}
