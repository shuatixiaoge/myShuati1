public class Solution {
    public int divide(int dividend, int divisor) {
        long a=dividend;
		long b=divisor;
	    a=dividend<0 ? -1*a:a;
	    b= divisor<0 ? -1*b:b;

        // long a=dividend<0 ? -1*dividend:dividend;
        // long b= divisor<0 ? -1*divisor:divisor;
        if(a<b) return 0;
        long b0=b;
        boolean sign=(dividend<0)^(divisor<0);
        int step=0;
        while(a>b){
        	b=b<<1;
        	step++;
        }
        long result=0;
        while(a>=b0){
        	if(a>=b){
        		a-=b;
        		result+=(long)1<<step;
        	}
        	step--;
        	b=b>>>1;
        }
        long finalQuo= sign ? -result:result;
        if (finalQuo < Integer.MIN_VALUE || finalQuo > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return (int) finalQuo;
        }
    }
}
