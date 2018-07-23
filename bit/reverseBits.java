public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int tmp = 0;
        for (int i = 0; i < 32; i++) {
            // bit operation has lower priority than +
            tmp = tmp + (n & 1);
            // for negative numbers
            n >>>= 1;
            if (i < 31) tmp <<= 1;
        }
        return tmp;
    }


    // to save more time, cache could be used
    // cache
    // this way of bit operation has been used in a lot cases.
    // 1 byte has only 1024 space for cache, but can save a lot of time.

private final Map<Byte, Integer> cache = new HashMap<Byte, Integer>();
public int reverseBits(int n) {
    byte[] bytes = new byte[4];
    for (int i = 0; i < 4; i++) // convert int into 4 bytes
        bytes[i] = (byte)((n >>> 8*i) & 0xFF);
    int result = 0;
    for (int i = 0; i < 4; i++) {
        result += reverseByte(bytes[i]); // reverse per byte
        if (i < 3)
            result <<= 8;
    }
    return result;
}

private int reverseByte(byte b) {
    Integer value = cache.get(b); // first look up from cache
    if (value != null)
        return value;
    value = 0;
    // reverse by bit
    for (int i = 0; i < 8; i++) {
        value += ((b >>> i) & 1);
        if (i < 7)
            value <<= 1;
    }
    cache.put(b, value);
    return value;
}


// the above solution takes long time to save and read
//another better solution
public int reverseBits(int n) {
        int ret=n;
        ret = ret >>> 16 | ret<<16;
        ret = (ret & 0xff00ff00) >>> 8 | (ret & 0x00ff00ff) << 8;
        ret = (ret & 0xf0f0f0f0) >>> 4 | (ret & 0x0f0f0f0f) << 4;
        ret = (ret & 0xcccccccc) >>> 2 | (ret & 0x33333333) << 2;
        ret = (ret & 0xaaaaaaaa) >>> 1 | (ret & 0x55555555) << 1;
        return ret;
}
// First step, interchange 1234 with 5678 -> 56781234
// Second step, interchange 56~~12~~ with ~~78~~34-> 78563412
// Last step, interchange 7~5~3~1~ with ~8~6~4~2 ->87654321
}
