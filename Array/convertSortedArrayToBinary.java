// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
public class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        return helper(nums,0, nums.length-1);
    }
    public TreeNode helper(int[] A, int left, int right) {
        if (left > right) return null;
        int mid = (left + right)/2;
        TreeNode node = new TreeNode(A[mid]);
        node.left = helper(A, left, mid - 1);
        node.right = helper(A,mid + 1, right);
        return node;
    }
}
