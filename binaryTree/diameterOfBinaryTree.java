/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int res;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        res = 0;
        helper(root);
        return res - 1;
    }
    public int helper(TreeNode root) {
        if (root == null) return 0;
        int left = 0, right = 0;
        left = helper(root.left);
        right = helper(root.right);
        res = Math.max(left + right + 1, res);
        return Math.max(left, right) + 1;
    }
}
