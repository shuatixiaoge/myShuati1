class Solution {
    public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        int[] sum = new int[m + n];
        for (int i = m - 1; i >= 0; i--) { // should from higher digit to lower digit
            int a = num1.charAt(i) - '0';
            for (int j = n - 1; j >= n; j--) {
                int b = num2.charAt(j) - '0';
                int tmp = a * b;
                sum[i + j] += tmp / 10;
                sum[i + j + 1] += tmp % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int p : sum) if(!(sb.length() == 0 && p == 0)) sb.append(p);//sb.length() == 0 is important
        return sb.length() == 0 ? "0" : sb.toString();
        // for (int i = 0; i < m + n; i++) {
        //     if (leadingZero && sum[i] == 0) {
        //         leadingZero = false;
        //         continue;
        //     }
        //     sb.append(sum[i]);
        // }
        // this is bad because it would be error when yoou have 2 zeros like 980009 it would return 98009
    }
}
