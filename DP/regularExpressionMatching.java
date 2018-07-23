class Solution {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'); // . cannot match empty string, so you need the parenthesis
        if(p.length() >= 2 && p.charAt(1) == '*') {
            return firstMatch && isMatch(s.substring(1), p) || // a* if match then take first char
                isMatch(s, p.substring(2));//a* no match then give up the regex
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }

    //Top Down Version
    enum Result {
        TRUE, FALSE
    }
    Result[][] memo
    public boolean isMatchDP(String s, String p) {
        memo = new Result[s.length()][p.length()];
        return helper(0, 0, s, p, memo);
    }
    public boolean helper(int i, int j, String s, String p) {
        if(memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }
        boolean res;
        if (p.length() == j) { //check p len here
            res = i == s.length();
        } else {
            //check s len here
            boolean firstMatch = i < s.length && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')
            if(j < p.length() - 1 && p.charAt(j + 1) == '*') {
                res = firstMatch && helper(i + 1, j, s, p) || helper(i, j + 2, s, p);
            } else {
                res = firstMatch && helper(i + 1, j + 1, s, p);
            }
        }
        memo[i][j] = res ? Result.TRUE : Result.FALSE;
        return res;
    }

    //Bottom-up
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true; // this is important

        for(int i = s.length(); i >= 0; i --) { // starts from the length
            for (int j = p.length() - 1; j >= 0; j--) {
                boolean firstMatch = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
                    dp[i][j] = firstMatch && dp[i+1][j] || dp[i][j + 2];
                } else {
                    dp[i][j] = firstMatch && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }

    //dp
    class Solution{
        public boolean isMatch(String s, String p) {
            if(s == null || p == null) return false;
            int n = s.length();
            int m = p.length();
            boolean[][] dp = new boolean[n + 1][m + 1];
            dp[0][0] = true;
            for(int i = 2; i <= m; i++){
                if(p.charAt(i - 1) == '*') dp[0][i] = dp[0][i - 2];
            }
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= m; j++){
                    char chars = s.charAt(i - 1);
                    char charp = p.charAt(j - 1);
                    if(charp == '.' || charp == chars){
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (charp == '*'){
                        char charPrev = p.charAt(j - 2);
                        if(charPrev == '.' || charPrev == chars){
                            dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                        } else {
                            dp[i][j] = dp[i][j - 2];
                        }
                    }
                }
            }
            return dp[n][m];
        }
    }


    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    //Follow up  what if * is allowed, then more conditions are below
    public boolean search(String word) {
            return dfs(word, root);
    }
    private boolean dfs(String word, TrieNode parent) { //The TrieNode here is Parent Node
        if (word.length() == 0) {//since it's parent node so idx cannot be len - 1
            return parent.isLeaf;
        }
        boolean firstMatch = word.length() > 0 && (parent.map.containsKey(word.charAt(0)) || word.charAt(0) == '.');
        if (word.length() > 1 && word.charAt(1) == '*') {
            if(word.charAt(0) == '.') {
                boolean tmp = false;
                for (TrieNode curr : parent.map.values()) {
                    tmp = tmp || firstMatch && dfs(word, curr);
                }
                return tmp || firstMatch && dfs(word.substring(2), parent); //match || no match
            } else {
                System.out.println("Hi");
                return firstMatch && (dfs(word, parent.map.get(word.charAt(0))) || dfs(word.substring(2), parent.map.get(word.charAt(0))));
            }
        } else {
            if (word.charAt(0) == '.') {
                boolean tmp = false;
                for (TrieNode curr : parent.map.values()) {
                    tmp = tmp || firstMatch && dfs(word.substring(1), curr);
                }
                return tmp;
            } else {
                return firstMatch && dfs(word.substring(1), parent.map.get(word.charAt(0)));
            }
        }
    }
}
