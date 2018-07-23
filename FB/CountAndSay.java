public class Solution {
    public String countAndSay(int n) {
        String s="1";
        for(int i=0;i<n-1;i++){
            s=say(s);
        }
        return s;
    }
    public String say(String s){
        if(s.length()==0)   return "";
        if(s.length()==1)   return "1"+s;
        String res="";
        int count=1;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(i < s.length() - 1 && c==s.charAt(i+1)){
                count++;
            }
            else{
                res=res+count+c;
                count=1;
            }
        }
        return res;
    }
}
