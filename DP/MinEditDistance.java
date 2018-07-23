public class Main {
    private static int minEditDistance(String str){
        if(str == null || str.length() <= 1) return 0;
        int n = str.length();
        // dp[i, j] = min edit distance for substring (i , j);
        int[][] dp = new int[n][n];
        for(int i = 1; i < n; i++){
            for(int j = i; j >= 0; j- ){
                if(j == i) {
                    dp[j][i] = dp[i][j] = 0;
                } else {
                    if(str.charAt(i) == str.charAt(j)){
                        dp[j][i] = dp[i][j] = (j + 1 == i) ? 0 : dp[j + 1][i - 1];
                    } else {
                        dp[j][i] = dp[i][j] = (j + 1 == i) ? 1 : Math.min(dp[j + 1][i], dp[j][i - 1]) + 1;
                    }
                }
            }
        }
        return dp[0][n - 1];
    }
    public static void main(String[] args){
        System.out.println(minEditDistance("geekforgeeks"));
    }
}
