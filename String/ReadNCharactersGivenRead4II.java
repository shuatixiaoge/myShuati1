/* The read4 API is defined in the parent class Reader4.
  int read4(char[] buf); */

public class Solution extends Reader4 {
  /**
   * @param buf Destination buffer
   * @param n   Maximum number of characters to read
   * @return    The number of characters read
   */
//The difference
// Think that you have 4 chars "a, b, c, d" in the file,
// and you want to call your function twice like this:

// read(buf, 1); // should return 'a'
// read(buf, 3); // should return 'b, c, d'
// All the 4 chars will be consumed in the first call.
// So the tricky part of this question is how can you preserve the remaining 'b, c, d'
// to the second call.
  private int bufPtr = 0;
  private int bufCnt = 0;
  private char[] buff = new char[4];
  public int read(char[] buf, int n) {
      int ptr = 0;
      while(ptr < n) {
          int count = 0;
          if (bufPtr == 0) {
              bufCnt = read4(buff);
          }
          if (bufCnt == 0) break;
          while(ptr < n && buffPtr < buffCnt) {
              buf[ptr++] = buff[bufPtr++];
          }
          if (buffPtr >= buffCnt) buffPtr = 0;
      }
      return ptr;
  }

  //my better solution
private int bufPtr = 4;
private int bufCnt = 0;
private char[] buff = new char[4];
public int read(char[] buf, int n) {
    int ptr = 0;
    while(ptr < n) {
        if (bufPtr == 4) {
            bufCnt = read4(buff);
            bufPtr = 0;
        }
        if (bufPtr >= bufCnt) break; //check the case when not enough result return
        buf[ptr++] = buff[bufPtr++];
    }
    return ptr;
}
}
