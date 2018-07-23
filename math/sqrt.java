public int mySqrt(int x) {
    int left = 1, right = x;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (mid == x / mid) {
            return mid;
        } else if (mid < x / mid) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return right;
}
//newton's Solution

public int mySqrt(int x ){
    long r = x;
    while (r*r > x)
        r = (r + x/r) / 2;
    return (int) r;
}
