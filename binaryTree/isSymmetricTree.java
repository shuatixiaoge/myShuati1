public boolean isSymmetric(TreeNode root) {
    return root==null || isSymmetricHelp(root.left, root.right);
}

private boolean isSymmetricHelp(TreeNode left, TreeNode right){
    if(left==null || right==null)
        return left==right;
    if(left.val!=right.val)
        return false;
    return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
}

public boolean isSymmetric(TreeNode root) {
    if(root == null) return true;
    Stack<TreeNode> stack1 = new Stack<>();
    Stack<TreeNode> stack2 = new Stack<>();
    stack1.push(root);
    stack2.push(root);
    while(!stack1.isEmpty() && !stack2.isEmpty()){
        TreeNode node1 = stack1.pop();
        TreeNode node2 = stack2.pop();
        if(node1.val != node2.val) return false;
        if(node1.left != null) stack1.push(node1.left);
        if(node2.right != null) stack2.push(node2.right);
        if(stack1.size() != stack2.size()) return false;
        if(node1.right != null) stack1.push(node1.right);
        if(node2.left != null) stack2.push(node2.left);
        if(stack1.size() != stack2.size()) return false;
    }
    return stack1.size() == stack2.size();
}
