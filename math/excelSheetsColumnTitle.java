class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            char c = (char)('A' + (n - 1) % 26); // don't do n % 26 - 1, which would break Z
            sb.insert(0, c);
            n = (n - 1) / 26; // cannot use n too.
        }
        return sb.toString();
    }
}
