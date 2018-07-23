//does not allow EQUAL
public class Solution {
    public static void wiggleSort(int[] nums) {
        int[] tem = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            tem[i] = nums[i];
        }
        int mid = partition(tem, 0, nums.length-1, nums.length/2);
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = mid;
        }
        int l, r;
        if (nums.length % 2 == 0) {
            l = nums.length - 2;
            r = 1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < mid) {
                    ans[l] = nums[i];
                    l -= 2;
                } else if (nums[i] > mid) {
                    ans[r] = nums[i];
                    r += 2;
                }
            }
        } else {
            l = 0;
            r = nums.length - 2;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < mid) {
                    ans[l] = nums[i];
                    l += 2;
                } else if (nums[i] > mid) {
                    ans[r] = nums[i];
                    r -= 2;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = ans[i];
        }
    }
    public static int partition(int[] nums, int l, int r, int rank) {
        int left = l, right = r;
        int now = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= now) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= now) {
                left++;
            }
            nums[right] = nums[left];
        }
        if (left - l == rank) {
            return now;
        } else if (left - l < rank) {
            return partition(nums, left + 1, r, rank - (left - l + 1));
        } else {
            return partition(nums, l, right - 1, rank);
        }
    }


    // no extra space
    public void wiggleSort(int[] nums) {
         int median = findKthLargest(nums, (nums.length + 1) / 2);
         int n = nums.length;

         int left = 0, i = 0, right = n - 1;

         while (i <= right) {

             if (nums[newIndex(i,n)] > median) {
                 swap(nums, newIndex(left++,n), newIndex(i++,n));
             }
             else if (nums[newIndex(i,n)] < median) {
                 swap(nums, newIndex(right--,n), newIndex(i,n));
             }
             else {
                 i++;
             }
         }


     }

     private int newIndex(int index, int n) {
         return (1 + 2*index) % (n | 1);
     }
}
