public class Solution {
    public boolean wordPattern(String pattern, String str) {
        HashMap<String, String> pat2word = new HashMap<>();
        HashMap<String, String> word2pat = new HashMap<>();
        String[] strs = str.split(" ");
        if(strs.length != pattern.length()) return false;
        for(int i = 0; i < strs.length; i++){
            String pat = "" + pattern.charAt(i);
            String word = strs[i];
            if(!pat2word.containsKey(pat) && !word2pat.containsKey(word)){
                pat2word.put(pat, word);
                word2pat.put(word, pat);
            } else {
                if(!pat2word.containsKey(pat)) return false;
                if(!word2pat.containsKey(word)) return false;
                if(!pat2word.get(pat).equals(word)) return false
                ;
                if(!word2pat.get(word).equals(pat)) return false
                ;
            }
        }
        return true;
    }
}
