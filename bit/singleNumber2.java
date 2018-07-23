// Given an array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
public class Solution {
    public int singleNumber(int[] A) {
        int count,result=0;
        for(int i=0;i<32;i++){
            count=0;
            for(int a:A){
                count+=(a & 1<<i)==0 ? 0:1;
            }
            if(count%3!=0){
                result |= (1<<i);
            }
        }
        return result;
    }
}
//better solution
public int singleNumber(int[] A) {
    int ones = 0, twos = 0;
    for(int i = 0; i < A.length; i++){
        ones = (ones ^ A[i]) & ~twos;
        twos = (twos ^ A[i]) & ~ones;
    }
    return ones;
}
