//单调栈，Monotonious Stack 如果下一个数字 比栈顶的数字小，一直pop直到栈顶的数字比下一个数字小， 同时在pop的时候也可以利用判断 左边第一小 和 右边第一小的信息 
//然后利用信息计算面积
// O(N) worst case O(2N)
public class Solution {
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        for (int i = 0; i <= height.length; i++) {
            int curt = (i == height.length) ? -1 : height[i];
            while (!stack.isEmpty() && curt <= height[stack.peek()]) {
                int h = height[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, h * w);
            }
            stack.push(i);
        }

        return max;
    }
}
