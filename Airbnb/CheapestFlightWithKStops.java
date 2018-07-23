class Solution {
    //Dijkstra's
    //Follow Up if you have budget
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        for (int[] f : flights) {
            if (!prices.containsKey(f[0])) prices.put(f[0], new HashMap<>());
            prices.get(f[0]).put(f[1], f[2]);
        }
        // Heap sort by cost, so the first city arrived destination would be min price
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (Integer.compare(a[0], b[0])));
        // k + 1 since 1 stop means 2 flights
        pq.add(new int[] {0, src, k + 1});
        while (!pq.isEmpty()) {
            int[] top = pq.remove();
            int price = top[0];
            int city = top[1];
            int stops = top[2];
            if (city == dst) return price;
            if (stops > 0) {
                // there could be no routes so need new HashMap to get ride of nil case, which would make adj nil
                Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
                for (int a : adj.keySet()) {
                    pq.add(new int[] {price + adj.get(a), a, stops - 1});
                }
            }
        }
        return -1;
    }
    //Bellman Ford
    // Dijkstra's worst case time complexity is O((|V|+|E|)log|V|), which is O(|V|^2logV) when E = V^2. For Bellman Ford, it's O(|V||E|), which is O(V^3).
    // Bellman ford does run faster in the test cases though. You can use queue to make it even faster.
    //
    // Unlike Dijkstra's algorithm, the Bellman–Ford algorithm can be used on graphs with negative edge weights,
    // as long as the graph contains no negative cycle reachable from the source vertex s. The presence of such cycles means there is no shortest path,
    // since the total weight becomes lower each time the cycle is traversed. It is possible to adapt Dijkstra's algorithm to
    // handle negative weight edges by combining it with the Bellman-Ford algorithm (to remove negative edges and detect negative cycles),
    // such an algorithm is called Johnson's algorithm.
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;
        for (int i = 0; i <= K; i++) {
            int[] temp = Arrays.copyOf(prices, n);
            for (int[] flight : flights) {
                int cur = flight[0], next = flight[1], price = flight[2];
                if (prices[cur] == Integer.MAX_VALUE) continue;
                temp[next] = Math.min(temp[next], prices[cur] + price);
            }
            prices = temp;
        }
        //Bellman Ford can help detect cycles with negative weight, positive cycle is fine
        // for each edge (u, v) with weight w in edges:
        //     if distance[u] + w < distance[v]:
        //     error "Graph contains a negative-weight cycle"
        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }
}
