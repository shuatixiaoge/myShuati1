//Convert String to Integer
// "   -321321a8329" return -321321
public class Solution {
    public int myAtoi(String str) {
      int sum = 0;
      int i = 0;

      char[] c_arr = str.toCharArray();
      if (c_arr.length == 0) return 0;
      while(i<c_arr.length && Character.isWhitespace(c_arr[i])) i++;
      int j = 0;
      if (c_arr[i] == '-' || c_arr[i] == '+') {
        j = i;
        i++;
      }

      for (; i < c_arr.length; i++) {
        int num = c_arr[i] - '0';
        if (!Character.isDigit(c_arr[i])) break;

        if (sum > Integer.MAX_VALUE / 10 || (sum == Integer.MAX_VALUE / 10 && num >= 8)) {
          return c_arr[j] != '-' ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        sum = sum * 10 + num;
      }

      return c_arr[j] == '-' ? -sum : sum;
    }
}
