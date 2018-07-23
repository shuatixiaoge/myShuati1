// /1）一个题 给一个string 像这样a---a--b---c,两个连着的字母相同的话中间就算桥，就都变成+，就变成+++++--b----c
//         再给个例子a---a-a-b---c => +++++++-b---c./

class Solution {

  String convert(String s) {
    String res = "";
    int i = 0;
    while(i < s.length()) {
      int[] tmp = findBridge(s, i);
      if (tmp[1] == 1) {
        for (int j = i; j <= tmp[0]; j++) {
          res += "+";
        }
        res += s.substring(tmp[0] + 1, tmp[2]);
      } else {
        res += s.substring(i, tmp[2]);
      }
      i = tmp[2];
    }
    return res;
  }

  int[] findBridge(String s, int i) {
    char c = s.charAt(i);
    int idx = i;
    int found = 0;
    int foundIdx = i;
    while(i < s.length()) {
      char curr = s.charAt(i);
      if (curr == '-' || curr == c) {
        if (curr == c && i != idx) {
          found = 1;
          foundIdx = i;
        }
        i++;
      }
      else break;
    }
    return new int[]{foundIdx, found, i};
  }
  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.convert("a---a--a--b--c"));
    System.out.println(s.convert("b--c--a---a--a"));
    System.out.println(s.convert("a--c--a---a--a-b"));
  }
}
