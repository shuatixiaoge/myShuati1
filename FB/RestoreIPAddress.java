public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        helper(s,"",res,0);
        return res;
    }
    public void helper(String s, String tmp, List<String> res,int n){
        if(n==4){
            if(s.length()==0){
                res.add(tmp.substring(0,tmp.length()-1));
            }
            return;
        }
        for(int k=1;k<=3;k++){
            if(s.length()<k) break;
            int val = Integer.parseInt(s.substring(0,k));
            if(val>255 || k!=String.valueOf(val).length()) continue;
            helper(s.substring(k),tmp+s.substring(0,k)+".",res,n+1);
        }
    }
}
