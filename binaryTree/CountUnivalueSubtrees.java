public class Solution {
    private class Tuple{
        // ABS : max count
        // Sign: +/- , is/not univalue subtree
        //
        int count;
        Integer val;
        public Tuple(int count, Integer val){
            this.count = count;
            this.val = val;
        }
    }
    public int countUnivalSubtrees(TreeNode root) {
        return Math.abs(helper(root).count);
    }
    private Tuple helper(TreeNode root){
        if(root == null) return new Tuple(0, null);
        Tuple left = helper(root.left);
        Tuple right = helper(root.right);
        if(left.count < 0 || right.count < 0 ||
        (left.val != null && !left.val.equals(root.val)) ||
        (right.val != null && !right.val.equals(root.val))){
            return new Tuple((Math.abs(left.count) + Math.abs(right.count)) * -1, 0);
        } else {
            return new Tuple(left.count + right.count + 1, root.val);
        }
    }
}
