public int maximalRectangle(char[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) return 0;
    int[][] m = new int[matrix.length][matrix[0].length];
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
            m[i][j] = matrix[i][j] - '0';
            if (i > 0 && matrix[i][j] == '1')
                m[i][j] = m[i-1][j] + 1;
        }
    }
    int max = 0;
    for (int i = 0; i < m.length; i++) {
        max = Math.max(largestRectangleArea(m[i]), max);
    }
    return max;
}
//same as largest Rectangle in histogram
private int largestRectangleArea(int[] heights) {
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
    while(stack.peek() != -1) {
        max = Math.max(max, heights[stack.pop()] * (heights.length - stack.peek() - 1));
    }
    return max;
}
