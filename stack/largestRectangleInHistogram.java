//2d version largest matrix
public int largestRectangleArea(int[] heights) {
    if (heights.length == 0) return 0;
    Stack<Integer> stack = new Stack<>();
    // this is help to get rid of the -1 when area is calcuated including the fisrt bar
    stack.push(-1);
    int max = 0;
    for (int i = 0; i < heights.length; i++) {
        while(stack.peek() != -1 && heights[stack.peek()] > heights[i]) {
            //calculate the bar before, not including self
            max = Math.max(max, heights[stack.pop()] * (i - stack.peek() - 1));
        }
        stack.push(i);
    }
    // Have to be a while loop to go through all cases like [2, 3]
    while(stack.peek() != -1) {
        max = Math.max(max, heights[stack.pop()] * (heights.length - stack.peek() - 1));
    }
    return max;
}
