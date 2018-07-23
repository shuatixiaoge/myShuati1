class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        HashMap<String, String> p2w = new HashMap<>();
        HashMap<String, String> w2p = new HashMap<>();
        return dfs(pattern, str, p2w, w2p);
    }

    private boolean dfs(String pattern, String str, HashMap<String, String> p2w, HashMap<String, String> w2p) {
        if (pattern.length() == 0) return str.length() == 0;

        for (int i = 0; i < str.length(); i++) {
            String pat = pattern.substring(0, 1);
            String currWord = str.substring(0, i + 1);
            if (!p2w.containsKey(pat) && !w2p.containsKey(currWord)) {
                p2w.put(pat, currWord);
                w2p.put(currWord, pat);
                if (dfs(pattern.substring(1), str.substring(i+1), p2w, w2p)) return true;
                p2w.remove(pat);
                w2p.remove(currWord);
            } else if (p2w.containsKey(pat) && w2p.containsKey(currWord)) {
                if (p2w.get(pat).equals(currWord) && w2p.get(currWord).equals(pat))
                    if (dfs(pattern.substring(1), str.substring(i+1), p2w, w2p)) return true;
            }
        }
        return false;
    }
}
