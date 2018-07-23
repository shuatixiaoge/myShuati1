public class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sba = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (sb.length() > 0) {
                    sba.append(sb.reverse().toString());
                    sba.append(' ');
                    sb.setLength(0);
                }
            } else {
                sb.append(c);
            }
        }
        if (sb.length() != 0) {
           sba.append(sb.reverse().toString());
        }
        return sba.toString().trim();
    }
}

public class Solution {
    public void reverseWords(char[] s) {
        int wordStart = 0;
        int index = 0;
        while(index < s.length){
            while(index < s.length && s[index] != ' ') index++;
            reverse(s, wordStart, index - 1);
            index++;
            wordStart = index;
        }
        reverse(s, 0, s.length - 1);
    }
    private void reverse(char[] s, int start, int end){
        while(start < end){
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end --;
        }
    }
}
