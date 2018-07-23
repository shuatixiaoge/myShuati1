public class Solution {
    /**
    *@param start, end: Denote an segment / interval
    *@return: The root of Segment Tree
    */
    public SegmentTreeNode build(int start, int end) {
        // write your code here
        if(start > end) return null;
        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if(start == end) return root;
        int mid = start + (end - start) / 2;
        root.left = build(start, mid);
        root.right = build(mid + 1, end);
        return root;
    }
}
