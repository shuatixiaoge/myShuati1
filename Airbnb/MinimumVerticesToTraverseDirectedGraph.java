import java.util.*;
public class MinimumVerticesToTraverseDirectedGraph {
    private void search(Set<Integer> res, Map<Integer, Set<Integer>> nodes, int cur, int start, Set<Integer> visited, Set<Integer> currVisited) {
        // this currVisited has to refresh every time, since each loop should not influence each other, otherwise it can not find out
        // the path this node can go since most of the nodes have been marked by other paths
        currVisited.add(cur);
        visited.add(cur);
        for (int next : nodes.get(cur)) {
            // this is important, it means this point can be reached by this start, so no need to keep it, just keep those point that can't be reached
            // next != start, means this is a self loop, which we do not consider since it's not traversing any node
            if (res.contains(next) && next != start) {
                res.remove(next);
            }
            if (!currVisited.contains(next)) {
                search(res, nodes, next, start, visited, currVisited);
            }
        }
    }
    public List<Integer> getMin(int[][] edges, int n) {
        Map<Integer, Set<Integer>> nodes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nodes.put(i, new HashSet<>());
        }
        for (int[] edge : edges) {
            nodes.get(edge[0]).add(edge[1]);
        }
        Set<Integer> visited = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                res.add(i);
                search(res, nodes, i, i, visited, new HashSet<>());
            }
        }
        return new ArrayList<>(res);
    }

    public static void main(String[] args) {
        int[][] e =  new int[][]{{2, 9}, {3, 3}, {3, 5}, {3, 7}, {4, 8}, {5, 8}, {6, 6}, {7, 4}, {8, 7}, {9, 3}, {9, 6}, {1, 1}};
        MinimumVerticesToTraverseDirectedGraph t = new MinimumVerticesToTraverseDirectedGraph();
        for (int i : t.getMin(e, 10)) System.out.println(i);//0 1 2

        // find all paths from all nodes in directed graph need two visited markup.
        // for this case it indicated why we need currentVisited
        //3->2->1->0 0->1 3->1
        e =  new int[][]{{0, 1}, {1, 0}, {2, 1}, {3, 1}, {3, 2}};
        for (int i : t.getMin(e, 4)) System.out.println(i); //3

        e =  new int[][]{{0, 3}, {1, 0}, {2, 1}, {2, 4}, {3, 2}};
        for (int i : t.getMin(e, 5)) System.out.println(i); //3
    }
}
