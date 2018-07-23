class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (Math.abs(s.length() - t.length()) > 1) return false;
        if (s.length() > t.length()) {
            return helper(t, s);
        } else {
            return helper(s, t);
        }
    }
    private boolean helper(String a, String b) {
        if (a.equals(b)) return false;//not true
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                // no need to compare a.subtring(0, i) to b.subtring(0, i)
                // abc adc
                if (a.substring(i+1).equals(b.substring(i+1))) return true;
                // ac abc
                // ab bab
                if (a.subtring(i).equals(b.substring(i+1))) return true;
                return false;
            }
        }
        return b.length() - a.length() == 1;
    }
}
