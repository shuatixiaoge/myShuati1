//Max Value Segment Tree
public class Solution {
    /**
    *@param A: a list of integer
    *@return: The root of Segment Tree
    */
    public SegmentTreeNode build(int[] A) {
        // write your code here
        return buildHelper(A, 0, A.length - 1);
    }
    private SegmentTreeNode buildHelper(int[] A, int start, int end){
        if(start > end) return null;
        if(start == end) return new SegmentTreeNode(start, end, A[start]);
        int mid = start + (end - start) / 2;
        SegmentTreeNode left = buildHelper(A, start, mid);
        SegmentTreeNode right = buildHelper(A, mid + 1, end);
        SegmentTreeNode root = new SegmentTreeNode(start, end, Math.max(left.max, right.max));
        root.left = left;
        root.right = right;
        return root;
    }
}
