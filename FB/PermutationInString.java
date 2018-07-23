class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 > len2 || len1 == 0 || len2 == 0) return false;
        int[] dp = new int[26];
        int[] dp2 = new int[26];
        for (int i = 0; i < len1; i++) {
            dp[s1.charAt(i) - 'a']++;
            dp2[s2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < len2 - len1 + 1; i++) {
            if (i != 0) dp2[s2.charAt(i + len1 - 1) - 'a']++;
            if (isValid(dp, dp2)) {
                return true;
            }
            dp2[s2.charAt(i) - 'a']--;
        }
        return false;
    }

    private boolean isValid(int[] dp, int[] dp2) {
        for (int i = 0; i < 26; i++) {
            if (dp[i] != dp2[i]) return false;
        }
        return true;
    }
}
