class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        return helper(root, false);
    }
    private int helper(TreeNode root, boolean isLeft) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            if (isLeft) return root.val;
            else return 0;
        }
        return helper(root.left, true) + helper(root.right, false);
    }
}
