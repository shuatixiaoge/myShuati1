// '?' Matches any single character.
// '*' Matches any sequence of characters (including the empty sequence).
//
// The matching should cover the entire input string (not partial).
//
// The function prototype should be:
// bool isMatch(const char *s, const char *p)
//
// Some examples:
// isMatch("aa","a") → false
// isMatch("aa","aa") → true
// isMatch("aaa","aa") → false
// isMatch("aa", "*") → true
// isMatch("aa", "a*") → true
// isMatch("ab", "?*") → true
// isMatch("aab", "c*a*b") → false


// difference between regex is the * can stands for any long sequence, regex need .*, basically two characters
// regex * need a start char, wildcard not needed.
// wildcard don't need firstMatch
class Solution {
    public boolean isMatch(String s, String p) {
        //some special case to test, 'a' *, '' *, 'aa' 'a*'
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;
        //initial state with *, otherwise would fail on 'aa', 'a*'
        for(int i=p.length()-1;i>=0;i--){
            if(p.charAt(i)!='*')
                break;
            else
                dp[s.length()][i]=true;
        }
        // from last to start is good way to avoid corner cases, like * at the end
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                if (p.charAt(j) == '*') {
                    // no need for another loop
                    //don't forget dp[i+1][j]
                    dp[i][j] = dp[i][j+1] || dp[i+1][j];
                } else if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?'){
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    //don't forget this else
                    dp[i][j] = false;
                }
            }
        }
        return dp[0][0];
    }
}
