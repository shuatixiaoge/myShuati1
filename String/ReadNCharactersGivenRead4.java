/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
  /**
   * @param buf Destination buffer
   * @param n   Maximum number of characters to read
   * @return    The number of characters read
   */
  public int read(char[] buf, int n) {
      int idx = 0;
      boolean eof = false;
      while(idx < n) {
          char[] buffer = new char[4];
          int len = read4(buffer);
          if (len == 0) break;
          len = Math.min(len, n - idx);
          for (int i = 0; i < len; i++) { //cannot do the condition do be i < len && i < n - idx, since idx is changing.
             buf[idx++] = buffer[i];
          }
      }
      return idx;
  }
}
