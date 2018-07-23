import java.util.*;
    public class maximumNumberANigthForYouCanAccomadate {
    public int rob(int[] nums) {
        int[] result = new int[nums.length];
        int[] path = new int[nums.length];
        List<Integer> res = new ArrayList<>();
        if(nums.length == 0){
            return 0;
        }
        Arrays.fill(path, Integer.MIN_VALUE);

        if(nums.length == 1){
            return nums[0];
        }
        result[0] = nums[0];
        result[1] = Math.max(nums[0],nums[1]);


        //注意边界问题
        for(int i=2;i < nums.length;i++) {
            if (nums[i] + result[i-2] > result[i-1]) {
                result[i] = nums[i] + result[i-2];
                path[i] = i - 2 ;
            } else {
                path[i] = i - 1;
                result[i] = result[i - 1];
            }
        }

        int i = nums.length - 1;
        while (i >= 0) {
            if (path[i] == i - 1) {
                i--;
            } else {
                res.add(0,i);
                i = path[i];
            }
        }
        for (int j : res) {
            System.out.println(j);
        }


        return result[nums.length - 1];
    }
    public static void main(String[] args) {
        maximumNumberANigthForYouCanAccomadate t = new maximumNumberANigthForYouCanAccomadate();
        int[] nums = new int[]{1, 0, 3, 8};
        t.rob(nums);
    }


}
