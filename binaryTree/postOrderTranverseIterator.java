private static class PostOrderIterator implements Iterator<T
reeNode>{
    Stack<TreeNode> stack;
    TreeNode cur;
    TreeNode prev;
    public PostOrderIterator(TreeNode root){
        stack = new Stack<>();
        cur = root;
        prev = null;
        if(cur != null) stack.push(cur);
    }
    public boolean hasNext(){
        return (!stack.isEmpty());
    }
    public TreeNode next(){
        TreeNode rst = null;
        while(!stack.isEmpty()){
            cur = stack.peek();
            // three case 1. going down through left/right nodes,
            if(prev == null || prev.left == cur || prev.right == cur){
                if(cur.left != null){
                    stack.push(cur.left);
                } else if(cur.right != null){
                    stack.push(cur.right);
                }
                //case 2, going back through left nodes
            } else if(cur.left == prev){
                if(cur.right != null) stack.push(cur.right);
                //case 3, jump from left node to right node when parent has both left and right node, curr is on right node
            } else {
                rst = cur;
                stack.pop();
            }
            prev = cur;
            if(rst != null) break;
        }
        return rst;
    }
}

//Better solution
public class PostOrderBinaryTreeIteratorImpl implements PostOrderBinaryTreeIterator {
   Stack<TreeNode> stack = new Stack<TreeNode>();

   /** find the first leaf in a tree rooted at cur and store intermediate nodes */
   private void findNextLeaf(TreeNode cur) {
     while (cur != null) {
       stack.push(cur);
       if (cur.left != null) {
         cur = cur.left;
       } else {
         cur = cur.right;
       }
     }
   }

   /** Constructor */
   public PostOrderBinaryTreeIterator(TreeNode root) {
     findNextLeaf(root);
   }

   /** {@inheritDoc} */
   @Override
   public boolean hasNext() {
     return !stack.isEmpty();
   }

   /** {@inheritDoc} */
   @Override
   public Integer next() {
     if (!hasNext()) {
       throw new NoSuchElementException("All nodes have been visited!");
     }

     TreeNode res = stack.pop();
     if (!stack.isEmpty()) {
       TreeNode top = stack.peek();
       if (res == top.left) {
         findNextLeaf(top.right); // find next leaf in right sub-tree
       }
     }

     return res.val;
   }

   @Override
   public void remove() {
     throw new UnsupportedOperationException("remove() is not supported.");
   }
 }  
