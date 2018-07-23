// Take the max of each window
// Window position                Max
// ---------------               -----
// [1  3  -1] -3  5  3  6  7       3
//  1 [3  -1  -3] 5  3  6  7       3
//  1  3 [-1  -3  5] 3  6  7       5
//  1  3  -1 [-3  5  3] 6  7       5
//  1  3  -1  -3 [5  3  6] 7       6
//  1  3  -1  -3  5 [3  6  7]      7
public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length==0) return new int[0];
        Deque<Integer> deque = new LinkedList<Integer>();
        int[] res = new int[nums.length-k+1];
        // the pointer here is the front of the window, so have to clear the last at first
        for(int i=0;i<nums.length;i++){
            if(i>=k && deque.getLast()==nums[i-k]) deque.removeLast();
            //min Deque
            while(!deque.isEmpty() && deque.getFirst()<nums[i]) deque.removeFirst();
            deque.addFirst(nums[i]);
            if(i+1>=k) res[i-k+1] = deque.getLast();
        }
        return res;
    }
}
