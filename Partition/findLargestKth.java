public class Solution {
    //O(NlogK) not the best solution
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> heap=new PriorityQueue(k);
        for(int num:nums){
            if(heap.size()<k)
                heap.add(num);
            else if(num>heap.peek()){
                heap.remove();
                heap.add(num);
            }
        }
        return heap.peek();
    }
    //O(N) Best Solution with quick selection
    //Final Version
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            return quickSelect(nums, nums.length - k, 0, nums.length - 1);
        }
        public int quickSelect(int[] nums, int k, int start, int end) {
            if (start >= end) return nums[end];
            int pivot = partition(nums, start, end);
            if (pivot < k) {
                return quickSelect(nums, k, pivot + 1, end);
            } else if (pivot > k) {
                return quickSelect(nums, k, start, pivot -1);
            } else {
                return nums[pivot];
            }
        }

        public void quickSort(int[] nums, int start, int end) {
            if (start >= end) return;
            int pivot = partition(nums, start, end);
            quickSort(nums, start, pivot - 1);
            quickSort(nums, pivot + 1, end);
        }

        //pivot is set to be end, and equal sign in second while loop is in end too.
    //the second swap doesn't matter you swap end or start.

        private int partition(int[] nums, int start, int end) {
            int pivot = end;
            while(true) {
                while(start < end && nums[start] < nums[pivot]) start++;
                while(start < end && nums[end] >= nums[pivot]) end--;
                if (start == end) break;
                swap(nums, start, end);
            }
            swap(nums, pivot, end);
            return end;
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

    }
}
