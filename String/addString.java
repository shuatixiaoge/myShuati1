class Solution {
    public String addStrings(String num1, String num2) {
        int len1 = num1.length();   
        int len2 = num2.length();
        if (len1 == 0) return num2;
        if (len2 == 0) return num1;
        int maxLen = Math.max(len1, len2);
        int carry = 0;
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < maxLen || carry != 0) {
            int a = i >= len1 ? 0 : num1.charAt(len1 - 1 - i) - '0';
            int b = i >= len2 ? 0 : num2.charAt(len2 - 1 - i) - '0';
            int sum = a + b + carry;
            sb.insert(0, sum % 10);
            carry = sum / 10;
            i++;
        }
        return sb.toString();
    }
}
