public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges)
    {
        int[] degrees = new int[n];
        ArrayList[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] edge : edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
            degrees[edge[0]]++;
            degrees[edge[1]]++;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 0; i < n; i++){
            if(degrees[i] <= 1) queue.offer(i);
        }
        List<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()){
            int size = queue.size();
            list = new ArrayList<>();
            for(int i = 0; i < size; i++){
                int node = queue.poll();
                list.add(node);
                for(int j = 0; j < graph[node].size(); j++){
                    int next = (int)graph[node].get(j);
                    degrees[next] --;
                    if(degrees[next] == 1) queue.offer(next);
                }
            }
        }
        return list;
    }
}
