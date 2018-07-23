public class Solution {
    TreeNode prev = null;
    public void flatten(TreeNode node) {
        if (node == null) return;
        TreeNode left = node.left;
        TreeNode right = node.right;
        if (prev != null) {
            prev.right = node;
            prev.left = null;
        }
        prev = node;
        flatten(left);
        flatten(right);
    }
}
