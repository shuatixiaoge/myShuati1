import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.

 For example, Given s = “eceba” and k = 2,

 T is "ece" which its length is 3.
 */
public class LC340_LongestSubstringWithAtMostKDistinctChars {
  public int lengthOfLongestSubstringKDistinct(String s, int k) {
    Map<Character, Integer> map = new HashMap<>();

    int max = 0;
    int left = 0, right = 0;
    char[] arr = s.toCharArray();

    while (right < arr.length) {
      char c = arr[right];
      if (map.containsKey(c) || map.size() < k) {
        map.put(c, map.getOrDefault(c, 0) + 1);
        max = Math.max(max, right - left + 1);
      } else {
        map.put(c, 1);
        while (map.size() > k) {
          char key = arr[left++];
          map.put(key, map.get(key) - 1);
          if (map.get(key) == 0) {
            map.remove(key);
          }
        }
      }
      right++;
    }
    return max;
  }

  public static void main(String[] args) {
    LC340_LongestSubstringWithAtMostKDistinctChars solution = new LC340_LongestSubstringWithAtMostKDistinctChars();
    System.out.println(solution.lengthOfLongestSubstringKDistinct("eceba", 2));
  }
}
