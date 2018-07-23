// Given a string, find the length of the longest substring without repeating characters.
//
// Examples:
//
// Given "abcabcbb", the answer is "abc", which the length is 3.
//
// Given "bbbbb", the answer is "b", with the length of 1.
//
// Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

public class Solution {
    public int lengthOfLongestSubstring(String s) {
      char[] arr = s.toCharArray();
      HashMap<Character, Integer> map = new HashMap<>();
      if (arr.length == 0) return 0;
      int max = 1;
      int j = 0;

      for (int i = 0; i < arr.length; i++) {
        char c = arr[i];
        if (map.containsKey(c)) {
          j = Math.max(j, map.get(c) + 1);
        }
        map.put(c, i);
        max = Math.max(max, i - j + 1);
      }
      return max;
    }
}

// amazing needs j to record the beginning of the String
// Needs to comparing hash value and j in order to determine the beginning of the string ex: abba
