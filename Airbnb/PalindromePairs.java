import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
      HashMap<String, Integer> map = new HashMap<>();
      ArrayList<List<Integer>> res = new ArrayList<>();
      for (int i = 0; i < words.length; i++) map.put(words[i], i);
      // has to use i here to include in the results
      for (int i = 0; i < words.length; i++) {
        String word = words[i];
        for (int j = 0; j <= word.length(); j++) {
          String str1 = word.substring(0, j);
          String str2 = word.substring(j);
          if (isPalindrome(str1)) {
            String revStr2 = new StringBuilder(str2).reverse().toString();
            if (map.containsKey(revStr2) && map.get(revStr2) != i) {
              List<Integer> l = new ArrayList<>();
              l.add(map.get(revStr2));
              l.add(i);
              res.add(l);
            }
          }
          if (isPalindrome(str2) && j != word.length()) {
            // has to check j != length to make sure no duplcates like ['a', ""]
            String revStr1 = new StringBuilder(str1).reverse().toString();
            // has to check value is not duplicate here
            if (map.containsKey(revStr1) && map.get(revStr1) != i) {
              List<Integer> l = new ArrayList<>();
                // add order is reversed.
              l.add(i);
              l.add(map.get(revStr1));
              res.add(l);
            }
          }
        }
      }
      return res;

    }
    private boolean isPalindrome(String str) {
      int n = str.length();
      int i = 0;
      int j = n - 1;
      while ( i < j ) {
          if (str.charAt(i) == str.charAt(j)) {
              i++; j--;
          } else {
              return false;
          }
      }
      return true;
    }
}
