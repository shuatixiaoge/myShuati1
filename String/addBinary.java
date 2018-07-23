class Solution {
    public String addBinary(String a, String b) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        int next = 0;
        while(i < a.length() || i < b.length() || next != 0) {
            int aVal = 0, bVal = 0;
            if (i < a.length()) {
                aVal = Character.getNumericValue(a.charAt(a.length() - 1 - i));
            }
            if (i < b.length()) {
                bVal = Character.getNumericValue(b.charAt(b.length() - 1 - i));
            }
            int sum = aVal + bVal + next;
            sb.insert(0, sum % 2);
            next = sum / 2;
            i++;
        }
        return sb.toString();
    }
}
