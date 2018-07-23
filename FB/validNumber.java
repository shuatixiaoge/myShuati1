public class Solution {
    public boolean isNumber(String s) {
        char[] ch = s.toCharArray();
        int len = ch.length;
        int i=0;
        boolean number = false;
        while(i<len && Character.isWhitespace(ch[i])) {
            i++;
        }
        if(i<len && (ch[i]=='-' || ch[i]=='+')) i++;

        while(i<len && Character.isDigit(ch[i])) {
            i++;
            number = true;
        }
        if(i<len && ch[i]=='.'){
            i++;

            if(i==len && !number) return false;
            while(i<len && Character.isDigit(ch[i])) {
                i++;
                number = true;
            }
        }
        if(number && i<len && ch[i]=='e'){
            i++;
            number = false;
            if(i<len && (ch[i]=='-' || ch[i]=='+')) i++;
            while(i<len && Character.isDigit(ch[i])) {
                i++;
                number = true;
            }

        }

        while(i<len && Character.isWhitespace(ch[i])) i++;
        return number && i==len;
    }
}
