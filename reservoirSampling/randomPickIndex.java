//鱼塘问题
//https://www.geeksforgeeks.org/reservoir-sampling/
public class Solution {

    int[] nums;
    Random rnd;

    public Solution(int[] nums) {
        this.nums = nums;
        this.rnd = new Random();
    }

    public int pick(int target) {
        int result = -1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target)
                continue;
                //这个问题的精髓在于result 拿到之后不会立马返回，所以可以随时更新target的值
            if (rnd.nextInt(++count) == 0)
                result = i;
        }

        return result;
    }
}

//Follow up find the max and random return the index
    public int pick(int target) {
        int result = -1;
        int count = 0;
        int max = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < max) {
                continue;
            }
            //这个问题的精髓在于result 拿到之后不会立马返回，所以可以随时更新target的值
            if (nums[i] == max) {
                if (rnd.nextInt(++count) == 0)
                    result = i;
            } else {
                count = 0;
                result = i;
                max = nums[i];
            }
        }

        return result;
    }
