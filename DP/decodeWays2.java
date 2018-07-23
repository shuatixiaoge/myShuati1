class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0) return 0;

        // have to be long, otherwise it would fail on some specail case like when memo[i] is 1billon
        long[] memo = new long[n+1];
        memo[n]  = 1;
        memo[n-1] = s.charAt(n-1) != '0' ? 1 : 0;
        if (s.charAt(n - 1) == '*') memo[n - 1] = 9;

        //if iterate from 0 to n would have so many corner cases because of 0;
        // if 0 exist in i, and you have to pre check, doing backwards would get rid of this case
        for (int i = n - 2; i >= 0; i--) {
            //if doing forward you cannot continue here, etc 30
            if (s.charAt(i) == '0') continue;
            else if (s.charAt(i) == '*') {
                // special case **
                // not including special case like 01 02 ... 09, 10 and 20
                // cannot count 10 like 103, it's the same ways
                if (s.charAt(i + 1) == '*') memo[i] = 9 * memo[i+1] + 15 * memo[i+2];
                else if (s.charAt(i + 1) <= '6') memo[i] = 9 * memo[i+1] + 2 * memo[i+2];
                else memo[i] = 9 * memo[i+1] + memo[i+2];// only the one with 17
            }
            // 1*2
            else if (s.charAt(i + 1) == '*') {
                if (s.charAt(i) == '1') {
                    // not 10 here, since 102 would be same ways as 2
                    memo[i] = memo[i+1] + 9 * memo[i+2];
                } else if (s.charAt(i) == '2') {
                    memo[i] = memo[i+1] + 6 * memo[i+2];
                } else {
                    memo[i] = memo[i+1];
                }
            }
            else memo[i] = (Integer.parseInt(s.substring(i,i+2))<=26) ? memo[i+1]+memo[i+2] : memo[i+1];
            memo[i] = memo[i]%1000000007;
        }

        return (int)memo[0];
    }

    //solution 2, forward
    public int numDecodings(String s) {
        /* initial conditions */
        long[] dp = new long[s.length()+1];
        dp[0] = 1;
        if(s.charAt(0) == '0'){
            return 0;
        }
        dp[1] = (s.charAt(0) == '*') ? 9 : 1;

        /* bottom up method */
        for(int i = 2; i <= s.length(); i++){
            char first = s.charAt(i-2);
            char second = s.charAt(i-1);

            // For dp[i-1]
            if(second == '*'){
                dp[i] += 9*dp[i-1];
            }else if(second > '0'){
                dp[i] += dp[i-1];
            }

            // For dp[i-2]
            if(first == '*'){
                if(second == '*'){
                    dp[i] += 15*dp[i-2];
                }else if(second <= '6'){
                    dp[i] += 2*dp[i-2];
                }else{
                    dp[i] += dp[i-2];
                }
            }else if(first == '1' || first == '2'){
                if(second == '*'){
                    if(first == '1'){
                       dp[i] += 9*dp[i-2];
                    }else{ // first == '2'
                       dp[i] += 6*dp[i-2];
                    }
                }else if( ((first-'0')*10 + (second-'0')) <= 26 ){
                    dp[i] += dp[i-2];
                }
            }

            dp[i] %= 1000000007;
        }
        /* Return */
        return (int)dp[s.length()];
    }
}
