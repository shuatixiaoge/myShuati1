import java.util.*;

/*
  Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.
  You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
 */
public class LC681_NextClosestTime {
  public String nextClosestTime(String time) {
    String[] t = time.split(":");
    String tt = t[0] + t[1];
    List<String> ts = allComb(tt);
    Collections.sort(ts);
    int i = Collections.binarySearch(ts, tt);

    for (String s : ts) {
      System.out.println(s);
    }
    StringBuilder result = new StringBuilder();
    if (i + 1 >= ts.size()) {
       result.append(ts.get(0));
    } else {
      result.append(ts.get(i + 1));
    }
    result.insert(2, ":");
    return result.toString();
  }

  public List<String> allComb(String str) {
    List<String> result = new ArrayList<>();
    char[] arr = str.toCharArray();
    dfs(arr, result, 0, str);
    return result;
  }

  public void dfs(char[] arr, List<String> result, int level, String str) {
    if (level == arr.length) {
      result.add(new String(arr));
      return;
    }
    Set<Character> set = new HashSet<>();
    for (int i = 0; i < arr.length; i++) {
      arr[level] = str.charAt(i);
      if (valid(level, arr) && set.add(arr[level])) {
        dfs(arr, result, level + 1, str);
      }
    }
  }

  private boolean valid(int level, char[] arr) {
    if (level == 0) {
      return  (arr[0] - '0') <= 2;
    } else if (level == 1) {
      return arr[0] - '0' <= 1 || arr[1] - '0' <= 4;
    } else
      return level != 2 || (arr[2] - '0') <= 5;
  }

  private void swap(char[] arr, int i, int level) {
    char temp = arr[i];
    arr[i] = arr[level];
    arr[level] = temp;
  }

  public static void main(String[] args) {
    LC681_NextClosestTime solution = new LC681_NextClosestTime();
    List<String> result = new ArrayList<>();
    System.out.println(solution.nextClosestTime("06:02"));

  }
}
