//without * /
public class Solution {
    public int calculate(String s) {
        char[] ch=s.toCharArray();
        if(ch.length==0) return 0;
        Stack<Integer> stack=new Stack<Integer>();
        int num=0;
        int result=0;
        int sign=1;
        for(char c:ch){
            if(Character.isDigit(c)){
                num=10*num+Character.getNumericValue(c);
            }
            else if(c==' '){
                continue;
            }
            else if(c=='-'){
                result+=sign*num;//每一个sign结算一次
                num=0;
                sign=-1;
            }
            else if(c=='+'){
                result+= sign*num;
                num=0;
                sign=1;
            }
            else if(c=='('){
                stack.push(result);
                stack.push(sign);
                sign=1;
                result=0;//result需要清理，因为存在了stack里面，num不需要，因为已经结算了
            }
            else if(c==')'){
                result+=sign*num;//要先结算一次
                num=0;
                sign=1;
                sign=stack.pop();
                result=stack.pop()+result*sign;
            }
        }
        if(num!=0) result+=sign*num;  //最后还要结算一次
        return result;
    }
}
