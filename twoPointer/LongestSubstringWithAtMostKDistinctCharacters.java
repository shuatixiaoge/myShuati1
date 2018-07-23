public class Solution {
    /**
     * @param s : A string
     * @return : The length of the longest substring
     *           that contains at most k distinct characters.
     */
     public int lengthOfLongestSubstringKDistinct(String s, int k) {
         if (s == null || s.length() == 0) return 0;
         char[] ch = s.toCharArray();
         int j = 0;
         int max = 0;
         HashMap<Character, Integer> map = new HashMap<>();
         for (int i = 0; i < ch.length; i++) {
             char c = ch[i];
             if (map.containsKey(c)) {
                     map.put(c, map.get(c) + 1);
             }
             else {
                 while (j < s.length() && map.size() >= k) {
                     removeMap(map, ch[j++]);
                 }
                 map.put(c, 1);
             }
             max = Math.max(max, i - j + 1);
         }
         return max;
     }
     public void removeMap(HashMap<Character, Integer> map, Character c) {
         if (!map.containsKey(c)) return;
         if (map.get(c) > 1) {
             map.put(c, map.get(c) - 1);
         }
         else {
             map.remove(c);
         }
     }
 }

  //better solution
  public class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];
        int num = 0, i = 0, res = 0;
        for (int j = 0; j < s.length(); j++) {
            if (count[s.charAt(j)]++ == 0) num++;
            if (num > k) {
                while (--count[s.charAt(i++)] > 0);
                num--;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
}
