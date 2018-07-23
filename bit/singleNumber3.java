// Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
//
// For example:
//
// Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].


public class Solution {
    public int[] singleNumber(int[] nums) {
        int diff = 0;
        for(int num: nums){
            diff^=num;
        }
        diff = Integer.highestOneBit(diff);
        // could be any 1 digit number, you can do diff &= ~(diff - 1) to find the lowestOneBit
    
        int[] result = new int[2];
        Arrays.fill(result,0);
        for(int num: nums){
            if((diff & num) == 0){
                result[0] ^= num;
            }
            else{
                result[1] ^= num;
            }
        }
        return result;
    }
}
