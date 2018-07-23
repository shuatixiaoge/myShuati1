// 例如:
// [[3,	5,	7,	9],	[2,	3,	8],	[5,	8]]
// 然后你要根据这个输入，输出一个总的preference	list。 这这一题应该就是:
// [2,	3,	5,	8,	7,	9]
// 因为这道题可能有多种符合要求的输出，如何 break	tie	by	person	1，也就是说 bfs 的时候每次优先
// 选择 person	1	list 里面的元素。
class PreferenceList {
  public List<Integer> getPreference(List<List<Integer>> preferences) {
    Map<Integer, Set<Integer>> map = new HashMap<>();
    List<Integer> res = new ArrayList<>();
    Map<Integer, Integer> inDegree = new HashMap<>();
    Map<Integer, Integer> group = new HashMap<>();

    for (int j = 0; j < preferences.size(); j++) {
      List<Integer> l = preferences.get(j);
      for (int i = 0; i < l.size(); i++) {
        inDegree.put(l.get(i), 0);
        group.putIfAbsent(l.get(i), j);
      }
    }
    for (List<Integer> l : preferences) {
      for (int i = 0; i < l.size() - 1; i++) {
        int i1 = l.get(i);
        int i2 = l.get(i + 1);
        Set<Integer> set = new HashSet<>();
        if (map.containsKey(i1)) set = map.get(i1);
        if (!set.contains(i2)) {
          set.add(i2);
          map.put(i1, set);
          inDegree.put(i2, inDegree.get(i2) + 1);
        }
      }
    }

    Queue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
    for (int k : inDegree.keySet()) {
      if (inDegree.get(k) == 0) {
        q.offer(new int[]{k, group.get(k)});
      }
    }
    while(!q.isEmpty()) {
      int[] curr = q.poll();
      res.add(curr[0]);
      for (int next : map.getOrDefault(curr[0], new HashSet<>())) {
        if (inDegree.get(next) == 1) {
          inDegree.put(next, 0);
          q.offer(new int[]{next, group.get(next)});
        } else {
          inDegree.put(next, inDegree.get(next) - 1);
        }
      }
    }

    return res;
  }

  public static void main(String[] args) {
    int[][] b = new int[][]{{3,  5,  7,  9},  {2,  3,  8},  {5,  8}};
    List<List<Integer>> l = new ArrayList<>();
    for (int[] a : b) {
      List<Integer> l1 = new ArrayList<>();
      for (int i : a) {
        l1.add(i);
      }
      l.add(l1);
    }

    Solution s= new Solution();
    for (int j : s.getPreference(l)) {
      System.out.print(j);
      System.out.print(",");
    }
  }
}
