public class Solution {
    /**
    *@param root, start, end: The root of segment tree and
    * an segment / interval
    *@return: The maximum number in the interval [start, end]
    */
    public int query(SegmentTreeNode root, int start, int end) {
        // write your code here
        if(end < root.start) return Integer.MIN_VALUE;
        if(start > root.end) return Integer.MIN_VALUE;
        start = Math.max(start, root.start);
        end = Math.min(end, root.end);
        if(start == root.start && end == root.end) return root.max;
        int left = query(root.left, start, end);
        int right = query(root.right, start, end);
        return Math.max(left, right);
    }


    /**
    *@param root, start, end: The root of segment tree and
    * an segment / interval
    *@return: The count number in the interval [start, end]
    */
    public int query(SegmentTreeNode root, int start, int end) {
        // write your code here
        if(root == null) return 0;
        if(end < root.start) return 0;
        if(start > root.end) return 0;
        start = Math.max(start, root.start);
        end = Math.min(end, root.end);
        if(root.start == start && root.end == end) return root.count;
        int left = query(root.left, start, end);
        int right = query(root.right, start, end);
        return left + right;
    }
}
