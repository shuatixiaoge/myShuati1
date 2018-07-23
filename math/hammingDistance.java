// Input: x = 1, y = 4
//
// Output: 2
//
// Explanation:
// 1   (0 0 0 1)
// 4   (0 1 0 0)
public int hammingDistance(int x, int y) {
    int xor = x ^ y, count = 0;

    while (xor != 0) {
        xor &= (xor - 1);
        count++;
    }
    return count;
}
