class Solution {
  public int strStr(String a, String b) {
    for (int i = 0; ;i++) {
      for (int j = 0; ; j++) {
        if (j == b.length()) return i;
        if (i + j == a.length()) return -1;
        if (b.charAt(j) != a.charAt(i + j)) break;
      }
    }
  }
}
