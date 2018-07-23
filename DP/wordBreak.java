class Solution {
    // backtracking  -> Time out
    // wordBreak 2 Time out
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        if(!canBreak(s, wordDict)) return res;
        HashSet<String> set = new HashSet<>();
        for (String tmp : wordDict) {
            set.add(tmp);
        }
        helper(res, s, "", 0, set);
        return res;
    }

    public void helper(List<String> res,String s, String str, int i, Set<String> dict){
        //maybe better to use hashmap to store the Result
		int len=s.length();
		if(len==i){
			res.add(str.substring(1));
		}
		for(int j=i;j<len;j++){
			String sub=s.substring(i,j+1);//important to take care what is in substring with j+1 instead of j;
			if(dict.contains(sub)){
				helper(res,s,str+" "+sub,j+1,dict);
			}
		}
	}}
    //Memorization with Map need to return a values
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < wordDict.size(); i++) {
            set.add(wordDict.get(i));
        }
        return word_Break(s, set, 0);
    }
    HashMap<Integer, List<String>> map = new HashMap<>();

    public List<String> word_Break(String s, Set<String> wordDict, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))) {
                List<String> list = word_Break(s, wordDict, end);
                for (String l : list) {
                    res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
                }
            }
        }
        map.put(start, res);
        return res;
    }    

    //wordBreak 1

    public boolean wordBreakDP(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        for (String tmp : wordDict) {
            set.add(tmp);
        }
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }
        return dp[s.length()];
    }
}
