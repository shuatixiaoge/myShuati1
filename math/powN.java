class Solution {
    public double myPow(double x, int n) {
        if (x == 0) return 0;
        // this corner case is important other wise it would TLE
        if (x == 1) return 1;
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n == 2) return x * x;
        double val = myPow(x, n/2);
        if (n%2 == 0) {
            return myPow(val, 2);
        } else if (n > 0) {
            return myPow(val, 2) * x;
        }
        //put this statement to the last of else would help solve the MIN to -MIN out of bound problem
        else return 1 / myPow(x, -n);

    }
}
