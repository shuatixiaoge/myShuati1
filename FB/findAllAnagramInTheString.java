class Solution {
    public List<Integer> findAnagrams(String s, String t) {
        int[] A = new int[256];
        int[] B = new int[256];
        int m = s.length();
        int n = t.length();
        init(B, t);
        int min = Integer.MAX_VALUE;
        List<Integer> res = new ArrayList<>();
        for (int i = 0, j = 0; i < s.length(); i++) {
            while(j < m && j < i + t.length()) {
                A[s.charAt(j++)]++;
            }
            if(isValid(A, B)) {
                res.add(i);
            }
            A[s.charAt(i)]--;// after the loop you have to deal with the counter
        }
        return res;
    }

    public boolean isValid(int[] A, int[] B) {
        for (int i = 0; i < 256; i++) {
            if (A[i] < B[i] || A[i] > B[i]) {
                return false;
            }
        }
        return true;
    }

    public void init(int[] A, String s) {
        for (char c : s.toCharArray()) {
            A[c]++;
        }
    }

}
