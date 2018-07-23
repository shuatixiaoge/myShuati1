public class Solution {
    int max  = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root){
        maxPathSum1(root);
        return max;
    }
    public int maxPathSum1(TreeNode root) {
        if(root==null) return 0;
        int left = Math.max(0,maxPathSum1(root.left));
        int right = Math.max(0,maxPathSum1(root.right));
        int val = left+ right + root.val;
        max = Math.max(max,val);
        return Math.max(left,right)+root.val;
    }
}
