public class regularExpressionWithPlus{
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true; // this is important

        for(int i = s.length(); i >= 0; i --) { // starts from the length, to match to the end of string
            // starts from p - 1, otherwise firstMatch will fail on case (aa a*)
            for (int j = p.length() - 1; j >= 0; j--) {
                boolean firstMatch = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
                    dp[i][j] = firstMatch && dp[i+1][j] || dp[i][j + 2];
                }else if (j < p.length() - 1 && p.charAt(j + 1) == '+') {
                    // for the '+' case you need to make sure i + 1 is within range
                    dp[i][j] = firstMatch && (dp[i+1][j] || dp[i + 1][j + 2]);
                } else {
                    dp[i][j] = firstMatch && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }

    public boolean regMatch(String s, String p) {
      if (p.length() == 0) return s.length() == 0;
      boolean firstMatch = s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
      if (p.length() > 1 && p.charAt(1) == '*') {
        return firstMatch && regMatch(s.substring(1), p) || regMatch(s, p.substring(2));
      } else if (p.length() > 1 && p.charAt(1) == '+') {
          // fisrtMatch usually comes with the s.substring(1);
        return firstMatch && (regMatch(s.substring(1), p) || regMatch(s.substring(1), p.substring(2)));
      } else {
        return firstMatch && regMatch(s.substring(1), p.substring(1));
      }
    }

    public static void main(String args[]) {
        regularExpressionWithPlus test = new regularExpressionWithPlus();
        System.out.println(test.isMatch("abbc", "a.+c"));
        System.out.println(test.isMatch("abc", "ab+c"));
        System.out.println(test.isMatch("ac", "ab+c"));
        System.out.println(test.isMatch("abc", "ab+"));
        System.out.println(test.isMatch("ac", "ab+"));
        System.out.println(test.isMatch("abbbbbbbbbab", "ab+"));
    }

}
