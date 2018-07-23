// Given "aacecaaa", return "aaacecaaa".
//
// Given "abcd", return "dcbabcd".

class Solution {
    public String shortestPalindrome(String s) {
        int i = 0, end = s.length() - 1, j = end; char chs[] = s.toCharArray();
        while(i < j) {
             if (chs[i] == chs[j]) {
                 i++; j--;
             } else {
                 i = 0; end--; j = end;
             }
        }
        return new StringBuilder(s.substring(end+1)).reverse().toString() + s;
    }
    // there is a better solution called KMP
}
