public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while(p!= null || !stack.isEmpty()){
            if(p!=null){
                stack.push(p);
                p = p.left;
            }
            else{
                TreeNode tmp = stack.pop();
                res.add(tmp.val);
                p=tmp.right;
            }
        }
        return res;
    }
}
