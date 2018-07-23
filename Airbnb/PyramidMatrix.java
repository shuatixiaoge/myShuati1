class Solution {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, Set<Character>> map = new HashMap<>();
        for (String s : allowed) {
            String s1 = s.substring(0, 2);
            map.putIfAbsent(s1, new HashSet<>());
            map.get(s1).add(s.charAt(2));
        }
        return dfs(bottom,"", map, 1);
    }

    boolean dfs(String row, String nextRow, Map<String, Set<Character>> allowed, int start) {
        // don't forget this line
        if (row.length() == 1) return true;
        if (nextRow.length() + 1 == row.length())
            return bfs(nextRow, "", allowed, 1);
        //allowed可能找不到string注意返回空的set
        for (Character c : allowed.getOrDefault(row.substring(start - 1, start + 1), new HashSet<>()))
            //注意这个是i+1不是idx+1，最好命名为start比较好
            if (dfs(row, nextRow + c, allowed, start + 1))
                return true;
        return false;
    }
}
