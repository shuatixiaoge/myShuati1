// https://leetcode.com/problems/palindromic-substrings/description/
public class Solution {
    int count = 0;

    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;

        for (int i = 0; i < s.length(); i++) { // i is the mid point
            extendPalindrome(s, i, i); // odd length;
            extendPalindrome(s, i, i + 1); // even length
        }

        return count;
    }

    private void extendPalindrome(String s, int left, int right) {
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++; left--; right++;
        }
    }
}
//Solution 2
public static int longestPalindrome(String s) {
    int start = 0, end = 0, res = 0;
    for (int i = 0; i < s.length(); i++)
        res += countExpandPalindromes(s, i, i);
    for (int i = 0; i < s.length() - 1; i++)
        res += countExpandPalindromes(s, i, i + 1);
    return res;
}
private static int countExpandPalindromes(String s, int i, int j) {
    while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
        i--;
        j++;
    }
    return (j - i) / 2;
}

//Find Longest one

public String longestPalindrome(String s) {
    int start = 0, end = 0;
    for (int i = 0; i < s.length() - 1; i++) {
        int len1 = expandPalindrome(s, i, i);
        int len2 = expandPalindrome(s, i, i + 1);
        int len = Math.max(len1, len2);
        if (len > end - start) {
            start = i - (len - 1) / 2;
            end = i + len / 2;
        }
    }
    return s.substring(start, end + 1);
}


//Find Longest one without order on S(permutation)
public int longestPalindrome(String s) {
        if(s==null || s.length()==0) return 0;
        HashSet<Character> hs = new HashSet<Character>();
        int count = 0;
        for(int i=0; i<s.length(); i++){
            if(hs.contains(s.charAt(i))){
                hs.remove(s.charAt(i));
                count++;
            }else{
                hs.add(s.charAt(i));
            }
        }
        if(!hs.isEmpty()) return count*2+1;
        return count*2;
}
