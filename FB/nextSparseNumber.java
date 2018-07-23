// 从左向右找连续的两个1，如果第i位和第i-1位都是1，那么保留高于i的所有位并给第i位加个1，然后重复这个过程直到找到结果。
// 一共32个bit，所以常数时间复杂度。
public class Solution {
    public int nextSparseNum(int x) {
        int i = 31;
        int y = 0;

        while (i >= 0) {
            int curr = (x >> i) & 1;
            int next = (i > 0) ? (x >> (i - 1)) & 1 : 0;

            y |= (curr << i);

            if (curr == 1 && next == 1) {
                x = (y + (y & (-y)));
                y = 0;
                i = 32;
            }

            i--;
        }

        return y;
    }
}
